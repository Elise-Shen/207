package Actions;

import ATM.BankManager;
import ATM.User;
import Accounts.Account;

import java.util.List;

public class AccountSummary extends UserActions {

    public AccountSummary(int userID, BankManager bm) {
        super(userID, bm);
    }

    /**
     * This method print out a summary of all the user's account balances.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        List<Account> accounts = currentUser.getAccountList();
        int accountNum = 0;
        for (Account account : accounts) {
            accountNum++;
            if (account != null){
                System.out.println("Account Number " + accountNum + ": Account ID: " + account.getAccountID()
                       + " balance: " + account.getCurrencyBalance());
            }
        }
    }
}
