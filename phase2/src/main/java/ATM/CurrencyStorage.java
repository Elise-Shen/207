package ATM;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * The storage of one type of currency.
 */
class CurrencyStorage {

    /**
     * The storage of one type of currency.
     */
    private Map<Integer, Integer> currencyStorage;

    /**
     * The maximum storage of any denomination in this currency.
     */
    private int MAX_STOCK;


    /**
     * Constructs a new CurrencyStorage.
     */
    CurrencyStorage() {
        currencyStorage = new TreeMap<>();
        MAX_STOCK = 1000;
    }

    /**
     * Set the default denominations and amount.
     * The default denominations are 5, 10, 20, 50. The default amount for each denomination is 1000.
     */
    void setDefault() {
        currencyStorage.put(5, MAX_STOCK);
        currencyStorage.put(10, MAX_STOCK);
        currencyStorage.put(20, MAX_STOCK);
        currencyStorage.put(50, MAX_STOCK);
    }

    /**
     * Set the maximum storage.
     */
    void setMaxStock(int maxStock) {
        MAX_STOCK = maxStock;
    }

    /**
     * Return the maximum storage.
     */
    int getMaxStock() {
        return MAX_STOCK;
    }


    /**
     * Add to this Currency Storage, given a cash type and the corresponding amount.
     */
    void addCash(Integer cashType, Integer amount) {
        if (!hasCashType(cashType)) {
            currencyStorage.put(cashType, amount);
        } else {
            Integer previousAmount = currencyStorage.get(cashType);
            currencyStorage.put(cashType, amount + previousAmount);
        }
    }

    /**
     * Return whether this currency has the specific cash type at all.
     */
    private boolean hasCashType(Integer cashType) {
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            if (mapKey.equals(cashType)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Withdraw from this Currency Storage if possible.
     */
    boolean withdrawCash(Integer currentKey, int amount) {
        if (getTotalStorage() < amount || amount % 5 != 0) {
            return false;
        } else {
            ((NavigableMap<Integer, Integer>) currencyStorage).descendingMap();
            Integer nextKey = ((NavigableMap<Integer, Integer>) currencyStorage).higherKey(currentKey);
            Integer preAmount = currencyStorage.get(currentKey);
            int num = amount / currentKey;
            if (amount % currentKey == 0) {
                if (num <= preAmount) {
                    currencyStorage.put(currentKey, preAmount - num);
                    return true;
                } else {
                    currencyStorage.put(currentKey, 0);
                    return withdrawCash(nextKey, amount - (currentKey * preAmount));
                }
            } else {
                if (num <= preAmount) {
                    currencyStorage.put(currentKey, preAmount - num);
                    return withdrawCash(nextKey, amount - currentKey * num);
                } else {
                    currencyStorage.put(currentKey, 0);
                    return withdrawCash(nextKey, amount - (currentKey * preAmount));
                }
            }

        }
    }

    /**
     * Return the largest denomination in the Currency Storage.
     */
    Integer getLargestDenomination() {
        ((NavigableMap<Integer, Integer>) currencyStorage).descendingMap();
        Map.Entry<Integer, Integer> firstEntry = ((NavigableMap<Integer, Integer>) currencyStorage).firstEntry();
        return firstEntry.getKey();
    }

    /**
     * Check if any cash type is about to run out of storage.
     * Returns false if any of the cash type is of amount less than 20.
     */
    boolean hasEnoughStorage() {
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            if (currencyStorage.get(mapKey) < 20) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the amount of money stored in this Currency Storage.
     */
    private int getTotalStorage() {
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        int amount = 0;
        while (entries.hasNext()) {
            entry = entries.next();
            Integer denomination = entry.getKey();
            amount += denomination * currencyStorage.get(denomination);
        }
        return amount;
    }

    /**
     * Set the currency to maximum storage.
     */
    void setToMaxStock() {
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            Integer denomination = entry.getKey();
            currencyStorage.put(denomination, MAX_STOCK);
        }
    }
}
