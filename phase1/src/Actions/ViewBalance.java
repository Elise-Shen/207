package Actions;

import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewBalance extends UserActions {

    private Account currentAccount;
    private User currentUser;

    public ViewBalance(int currentId, BankManager bankManager){
        super(currentId, bankManager);
    }

    /**
     * This method print out the account balance.
     */
    @Override
    public void execute(){
        boolean validInput = false;
        int accountChoice = 0;
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
            if(currentUser.getAccount(accountChoice) != null){
                validInput = true;
            }else{System.out.println("Invalid Input. Please try again!");}
        }

        currentAccount = currentUser.getAccount(accountChoice);
        double balance = currentAccount.getBalance();
        System.out.println("Account id: " + accountChoice + " has balance $" + balance);
        System.out.println("Working");
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }

}
