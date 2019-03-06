package Actions;

import ATM.BankManager;
import ATM.User;

import java.util.Scanner;

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
        boolean validInput = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a new password.");
        String newpass = input.nextLine();
        currentUser.setPassword(newpass);
        System.out.println("The passward is changed.");
    }
}
