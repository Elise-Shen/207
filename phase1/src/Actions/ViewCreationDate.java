package Actions;

import ATM.BankManager;
import Accounts.Account;

public class ViewCreationDate extends ViewAccount {

    public ViewCreationDate(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method print out the date of creation of an account.
     */
    @Override
    public void execute() {

        Account currentAccount = readCurrentAccount();
        System.out.println("\nAccount ID" + currentAccount.getAccountID() + "was created on "
                + currentAccount.getDateOfCreation() +".");
    }
}
