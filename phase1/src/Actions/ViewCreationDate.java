package Actions;

import ATM.*;
import Accounts.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewCreationDate extends UserActions {

    private User currentUser;
    private Account currentAccount;

    public ViewCreationDate(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method print out the date of creation of an account.
     */
    @Override
    public void execute() {

        boolean validInput = false;
        int accountChoice;
        BankManager bankManager = getBankManager();
        currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentUserAccounts = currentUser.getAccountList();//want to return a list of all accounts
        while (!validInput) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nType in the ID of the account you want to view");
            for (Account a : currentUserAccounts) {
                System.out.println(a.getAccountID() + " - " + a.toString());
            }
            accountChoice = input.nextInt();
            currentAccount = currentUser.getAccount(accountChoice);
            if(currentAccount != null){
                validInput = true;
            }else{System.out.println("Invalid Input. Please try again!");}
        }

        System.out.println("\nAccount ID" + currentAccount.getAccountID() + "was created on "
                + currentAccount.getDateOfCreation() +".");
    }
}
