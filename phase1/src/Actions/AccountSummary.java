package Actions;

import ATM.User;
import Accounts.Account;

import java.util.ArrayList;

public class AccountSummary extends UserActions {

    private User user;

    public AccountSummary(User user) {
        this.user = user;
    }

    /**
     * This method print out a summary of all the user's account balances.
     */
    @Override
    public void execute() {
        ArrayList<Account> accounts = user.getAccounts();
        int accountNum = 0;
        for (Account account : accounts) {
            accountNum++;
            System.out.println("Account Number " + accountNum + ": Account ID: " + account.getID() + " balance: " + account.getBalance());
        }
    }
}
