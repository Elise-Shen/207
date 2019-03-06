package Actions;

import ATM.BankManager;
import ATM.User;
import Accounts.Account;

import java.time.LocalDate;
import java.util.*;

public class ViewMostRecentTransaction extends UserActions {

    public ViewMostRecentTransaction(int userID, BankManager bm){
        super(userID, bm);
    }

    @Override
    public void execute() {
        Account currentAcount = null;
        boolean isValid = false;
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentAccounts = currentUser.getAccountList();
        Map<LocalDate, Transactions> recent = null;
        while(!isValid) {
            System.out.println("\nWhich account's most recent transaction do you want to view?");
            for (Account a : currentAccounts) {
                Scanner input = new Scanner(System.in);
                System.out.println(a.getAccountID() + " - " + a.toString());
                int choice = input.nextInt();
                if (currentUser.getAccount(choice) != null){
                    currentAcount = currentUser.getAccount(choice);
                    isValid = true;
                }else{System.out.println("Invalid input. Please try again!");}
            }
        }

        recent = currentAcount.getTransactionsList();
        Iterator<Map.Entry<LocalDate, Transactions>> entries = recent.entrySet().iterator();
        Map.Entry<LocalDate, Transactions> entry;
        LocalDate mapkey = null;
        while(entries.hasNext()){
            entry = entries.next();
            mapkey = entry.getKey();
            //keeps iterating until the last item
            //sets mapkey to last item
        }
        System.out.println("THe most recent transaction is: " + recent.get(mapkey) + " on " + mapkey);


    }
}
