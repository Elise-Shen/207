package Actions;

import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewAccount extends UserActions {

    private Account currentAccount;
    private User currentUser;

    ViewAccount(int currentId, BankManager bankManager){
        super(currentId, bankManager);
    }

    /**
     * This method ask the user to choose which account to view.
     * @return currentAccount is the account that the user is interested in at this time.
     */
    Account readCurrentAccount() {
        boolean validInput = false;
        int accountChoice;
        BankManager bankManager = getBankManager();
        currentUser = bankManager.getUser(getUserID());
        List<Account> currentUserAccounts = currentUser.getAccountList();
        while (!validInput) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nType in the ID of the account you want to view");
            for (Account a : currentUserAccounts) {
                if(a != null){
                    System.out.println(a.getAccountID() + " - " + a.toString());
                }
            }
            accountChoice = input.nextInt();
            currentAccount = currentUser.getAccount(accountChoice);
            if (currentAccount != null) {
                validInput = true;
            } else {
                System.out.println("Invalid Input. Please try again!");
            }
        }
        return currentAccount;
    }

    @Override
    public void execute() {}

    public User getCurrentUser(){
        return currentUser;
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }
}
