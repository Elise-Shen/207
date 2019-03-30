package EmployeeActions;

import ATM.CashStorage;

import java.util.Iterator;
import java.util.Map;
import ATM.CurrencyStorage;

public class ViewCurrencyMaxStock extends EmployeeAction {

    /**
     * The cash storage associated with this action.
     */
    private CashStorage cashStorage;

    public ViewCurrencyMaxStock(CashStorage cashStorage) {
        this.cashStorage = cashStorage;
    }

    /**
     * Display all the currency types in the cash storage and their maximum storage.
     */
    @Override
    public void execute() {
        Iterator<Map.Entry<String, CurrencyStorage>> entries = cashStorage.getCashStorage().entrySet().iterator();
        Map.Entry<String, CurrencyStorage> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            System.out.println(mapKey + " - " + cashStorage.getCashStorage().get(mapKey).getMaxStock());
        }
    }
}
