package Actions;

import ATM.*;

public class RequestAccount extends UserActions {

    private User user;
    private BankManager bm;
    private String AccountType;

    public RequestAccount(User user, BankManager bm, String AccountType){
        this.user = user;
        this.bm = bm;
        this.AccountType = AccountType;
    }

    /**
     * This method ask the BankManager to create a new account.
     */
    @Override
    public void execute() {
        bm.createAccount(user, AccountType);
    }
}
