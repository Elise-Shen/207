package Actions;

import ATM.*;

public class RequestAccount extends UserActions {

    private User user;
    private BankManager bm;
    private int AccountType;

    public RequestAccount(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method ask the BankManager to create a new account.
     */
    @Override
    public void execute() {
        bm.createAccount(user, AccountType);
        System.out.println("Requested an " + AccountType + " account.");
    }
}
