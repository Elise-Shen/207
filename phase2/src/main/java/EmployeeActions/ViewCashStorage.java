package EmployeeActions;
import ATM.CashStorage;
import ATM.CurrencyStorage;
import java.util.Iterator;
import java.util.Map;

public class ViewCashStorage extends EmployeeAction{

    /**
     * The cash storage associated with this action.
     */
    private CashStorage cashStorage;

    /**
     * Constructs a ViewCashStorage instance.
     */
    public ViewCashStorage(CashStorage cashStorage) {
        this.cashStorage = cashStorage;
    }

    /**
     * Display all the currency and its corresponding storage in this ATM's cash storage.
     */
    @Override
    public void execute() {
        Map<String, CurrencyStorage> cashStorage = this.cashStorage.getCashStorage();
        Iterator<Map.Entry<String, CurrencyStorage>> entries = cashStorage.entrySet().iterator();
        Map.Entry<String, CurrencyStorage> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            System.out.println(mapKey + ":" + "\nBill Type - Bill Amount");
            System.out.println(getCurrencyStorage(cashStorage.get(mapKey)));
        }
    }


    /**
     * Return the storage detail of a specified currency type.
     */
    private StringBuilder getCurrencyStorage(CurrencyStorage cs) {
        Map<Integer, Integer> currencyStorage = cs.getCurrencyStorage();
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey;
        StringBuilder result = new StringBuilder();
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            result.append(mapKey).append(" - ").append(currencyStorage.get(mapKey)).append("\n");
        }
        return result;
    }
}
