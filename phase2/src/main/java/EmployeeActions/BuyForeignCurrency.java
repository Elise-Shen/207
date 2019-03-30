package EmployeeActions;
import ATM.CashStorage;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.javamoney.moneta.Money;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuyForeignCurrency extends EmployeeAction {

    private CashStorage cashStorage;


    /**
     * Constructs a new BuyForeignCurrency instance.
     */
    public BuyForeignCurrency(CashStorage cashStorage) {
        this.cashStorage = cashStorage;
    }

    /**
     * Use local currency to buy foreign currency.
     */
    @Override
    public void execute() {
        List<String[]> deposits = readFromCSV("ForeignCurrencyPurchase.txt");
        String[] lastLine = null;
        for (String[] s : deposits) {
            lastLine = s;
        }
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        try {
            if ((lastLine.length % 2 == 1) && enoughLocalCurrency(lastLine[0], getAmount(lastLine))) {
                if (cashStorage.withdrawal(localCurrency, getAmount(lastLine))) {
                    int i = 0;
                    while (lastLine[i] != null) {
                        cashStorage.addBills(lastLine[0], Integer.valueOf(lastLine[i+1]), Integer.valueOf(lastLine[i+2]));
                    }
                }
            } else {
                System.out.println("Transaction failed.");
            }
        } catch (IllegalArgumentException e) {
            //
        }
    }

    private int getAmount(String[] line) {
        int amount = 0;
        int i = 0;
        while (line[i] != null) {
            amount += Integer.valueOf(line[i+1]) * Integer.valueOf(line[i+2]);
        }
        return amount;
    }

    /**
     * Determines whether the cash storage has enough local currency to purchase the foreign currency.
     */
    private boolean enoughLocalCurrency(String currencyType, int amount) {
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        CurrencyConversion conversion = MonetaryConversions.getConversion(localCurrency);
        MonetaryAmount converted = Money.of(amount, currencyType).with(conversion);
        MonetaryAmount localStorage = Money.of(cashStorage.getCashStorage().get(localCurrency).getTotalStorage(), localCurrency);
        if (!cashStorage.getCashStorage().containsKey(currencyType)) {
            return false;
        } else {
            return converted.isGreaterThan(localStorage);
        }
    }

    private static List<String[]> readFromCSV(String fileName) {
        List<String[]> deposits = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                for (int i = 0; i < attributes.length; i++){
                    attributes[i] = attributes[i].replaceAll("\\s+","");
                }
                deposits.add(attributes);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return deposits;
    }
}
