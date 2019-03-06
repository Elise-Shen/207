package Actions;

import ATM.BankManager;
import ATM.User;

public class ChangePin extends UserActions {


    public ChangePin(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method change the password.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());

        System.out.println("Enter a new password.");

        currentUser.setPassword(password);
        System.out.println("The passward is changed.");
    }
}
