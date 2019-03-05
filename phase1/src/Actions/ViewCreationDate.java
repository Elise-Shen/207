package Actions;

import Accounts.Account;

public class ViewCreationDate extends UserActions {

    private Account account;

    public ViewCreationDate(Account account){
        this.account = account;
    }

    /**
     * This method print out the date of creation of an account.
     */
    @Override
    public void execute() {
        System.out.println("Account ID: " + account.getID() + " Date of Creation: " + account.getDateOfCreation());
    }
}
