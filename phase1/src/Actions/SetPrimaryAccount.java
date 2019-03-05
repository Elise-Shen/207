package Actions;

import ATM.User;
import Accounts.*;

public class SetPrimaryAccount extends UserActions {

    private User user;
    private Account account;

    public SetPrimaryAccount(User user, Account account){
        this.user = user;
        this.account = account;
    }

    /**
     * This method reset the user's primary Chequing Account.
     */
    @Override
    public void execute() {
        user.setPrimaryAccount(account);
        System.out.println("Changed the primary account to: " + account.getID());
    }
}
