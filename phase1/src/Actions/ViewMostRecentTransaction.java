package Actions;

import ATM.BankManager;
import Accounts.Account;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class ViewMostRecentTransaction extends ViewAccount {

    public ViewMostRecentTransaction(int userID, BankManager bm){
        super(userID, bm);
    }

    @Override
    public void execute() {
        Map<LocalDate, Transactions> recent;
        Account currentAccount = readCurrentAccount();
        recent = currentAccount.getTransactionsList();
        Iterator<Map.Entry<LocalDate, Transactions>> entries = recent.entrySet().iterator();
        Map.Entry<LocalDate, Transactions> entry;
        LocalDate mapkey = null;
        while(entries.hasNext()){
            entry = entries.next();
            mapkey = entry.getKey();
            //keeps iterating until the last item
            //sets map-key to last item
        }
        if (recent.get(mapkey) == null) {
            System.out.println("No transaction recently.");
        } else {
            System.out.println("THe most recent transaction is: " + recent.get(mapkey) + " on " + mapkey);
        }
    }
}
