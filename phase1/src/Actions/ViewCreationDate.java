package Actions;

import ATM.*;
import Accounts.Account;

import java.util.ArrayList;

public class ViewCreationDate extends UserActions {


    public ViewCreationDate(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method print out the date of creation of an account.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentAccounts = currentUser.getAccountList();
        for(Account a: currentAccounts){
            System.out.println("\nAccount ID" + a.getAccountID() + "was created on " + a.getDateOfCreation() +".");
        }
    }
}
