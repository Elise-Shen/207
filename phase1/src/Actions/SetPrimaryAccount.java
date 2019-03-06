package Actions;

import ATM.BankManager;
import ATM.User;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SetPrimaryAccount extends UserActions {

    public SetPrimaryAccount(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method reset the user's primary Chequing Account.
     */
    @Override
    public void execute() {
        Account newPrimary = null;
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentAccounts = currentUser.getAccountList();
        boolean isValid = false;
        while(!isValid){
            Scanner input = new Scanner(System.in);
            System.out.println("Which chequing account do you wish to set as Primary");
            for(Account a: currentAccounts){
                if(a instanceof Chequing){
                    System.out.println(a.getAccountID() + " - " + a.toString());
                }
            }

            int accountChoice = input.nextInt();
            if (currentUser.getAccount(accountChoice) instanceof Chequing){
                newPrimary = currentUser.getAccount(accountChoice);
                isValid = true;
            }else{System.out.println("Invalid Input. Please try again!");}
        }
        currentUser.setPrimaryAccount(newPrimary);
        System.out.println("Changed the primary account to: " + newPrimary.getAccountID());
    }
}
