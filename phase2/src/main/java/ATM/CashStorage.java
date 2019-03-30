package ATM;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import javax.money.Monetary;


/**
 * The storage of all kinds of cash.
 */
public class CashStorage implements Serializable {

    /**
     * Stores a map mapping from currency type to its storage.
     */
    private Map<String, CurrencyStorage> cashStorage;

    /**
     * The ISO 4217 currency code for the local currency.
     */
    private String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();



    /**
     * Constructs a Cash Storage.
     * The default currency type is CAD.
     */
    public CashStorage() {
        cashStorage = new HashMap<>();
        CurrencyStorage currencyStorage = new CurrencyStorage();
        currencyStorage.setDefault();
        cashStorage.put(localCurrency, currencyStorage);
    }


    /**
     * Add a new type of currency to this Cash Storage with specified denomination and amount.
     */
    public void addBills(String currencyType, Integer cashType, Integer amount) {
        if (hasCurrency(currencyType)) {
            CurrencyStorage currencyStorage1 = cashStorage.get(currencyType);
            currencyStorage1.addCash(cashType, amount);
            cashStorage.put(currencyType, currencyStorage1);
        } else {
            CurrencyStorage currencyStorage2 = new CurrencyStorage();
            currencyStorage2.addCash(cashType, amount);
            cashStorage.put(currencyType, currencyStorage2);
        }
    }

    /**
     * Withdraw currency from this Cash Storage if possible.
     */
    public boolean withdrawal(String currency, int amount) {
        if (hasCurrency(currency)) {
            CurrencyStorage currencyStorage = cashStorage.get(currency);
            Integer firstKey = currencyStorage.getLargestDenomination();
            if (firstKey != null) {
                return currencyStorage.withdrawCash(firstKey, amount);
            }
        }
        return false;
    }


    /**
     * Returns whether the Cash Storage has this type of currency.
     */
    private boolean hasCurrency(String currencyType) {
        Iterator<Map.Entry<String, CurrencyStorage>> entries = cashStorage.entrySet().iterator();
        Map.Entry<String, CurrencyStorage> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            if (mapKey.equals(currencyType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Send alert information to alert.txt when the amount of any denomination in local currency goes below 20.
     */
    void sendAlert(String currentDate) {
        if (hasAlert()) {
            BufferedWriter writer;
            try {
                File outgoing = new File("alert.txt");
                writer = new BufferedWriter(new FileWriter(outgoing, true));
                String result = currentDate + ": " + localCurrency + "is insufficient!";
                writer.write(result);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Check if Canadian dollar has enough storage.
     */
    private boolean hasAlert() {
        if (hasCurrency(localCurrency)) {
            CurrencyStorage cs = cashStorage.get(localCurrency);
            if (cs.hasEnoughStorage()) {
                return false;
            }
        }
        return false;
    }

    /**
     * Enable the Bank Manager to set Canadian Dollar to max stock.
     */
    public void setToMaxStock() {
        if (hasCurrency(localCurrency)) {
            CurrencyStorage cs = cashStorage.get(localCurrency);
            cs.setToMaxStock();
        }
    }

    /**
     * Return the cash storage.
     */
    public Map<String, CurrencyStorage> getCashStorage() {
        return this.cashStorage;
    }
}

