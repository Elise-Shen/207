package Controllers.EmployeeControllers;

import ATM.CashStorage;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BuyForeignCurrencyController implements Initializable {

    @FXML
    private Label result;

    private MonetaryAmount convertTo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goBack() throws Exception {
        Main.showNewBorderPane("/EmployeeActionPage.fxml");
    }

    public void okPressed() {
        CashStorage cashStorage = Main.getCurrentATM().getCashStorage();
        List<String[]> deposits = readFromCSV("ForeignCurrencyPurchase.txt");
        String[] lastLine = null;
        for (String[] s : deposits) {
            lastLine = s;
        }
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        try {
            if ((lastLine.length % 2 == 1) && enoughLocalCurrency(cashStorage, lastLine[0], getAmount(lastLine))) {
                int amount = getAmount(lastLine);
                if (cashStorage.withdrawal(localCurrency, amount)) {
                    int i = 0;
                    while (i < lastLine.length && lastLine[i+2] != null) {
                        cashStorage.addBills(lastLine[0], Integer.valueOf(lastLine[i+1]), Integer.valueOf(lastLine[i+2]));
                        i++;
                    }
                    result.setText("Used local currency " + convertTo.toString() + " to purchase foreign currency " + lastLine[0]
                            + amount);
                }
            } else {
                result.setText("Transaction failed.");
            }
        } catch (IllegalArgumentException e) {
            //
        }
    }


    /**
     * Get information from the text file.
     */
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

    /**
     * Determines whether the cash storage has enough local currency to purchase the foreign currency.
     */
    private boolean enoughLocalCurrency(CashStorage cs, String currencyType, int amount) {
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        CurrencyConversion conversion = MonetaryConversions.getConversion(localCurrency);
        MonetaryAmount converted = Money.of(amount, currencyType).with(conversion);
        this.convertTo = converted;
        MonetaryAmount localStorage = Money.of(cs.getCashStorage().get(localCurrency).getTotalStorage(), localCurrency);
        if (!cs.getCashStorage().containsKey(currencyType)) {
            return false;
        } else {
            return converted.isGreaterThan(localStorage);
        }
    }

    /**
     * Return the amount given a list of strings.
     */
    private int getAmount(String[] line) {
        int amount = 0;
        int i = 0;
        while (i < line.length && line[i+2] != null) {
            amount += Integer.valueOf(line[i+1]) * Integer.valueOf(line[i+2]);
            i++;
        }
        return amount;
    }



}
