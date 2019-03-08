package Actions;

import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountToAccount extends Transactions {
    // Transfer money from one account to another
    // ask user to input 2 account numbers
    private int currentAccountID;

    public AccountToAccount(int currentId, BankManager bankManager){
        super(currentId,bankManager);
    }

    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts

        Scanner input = new Scanner(System.in);
        for(Account a: currentUserAccounts){
            System.out.println( a.getAccountID() + " - " + a.getAccountType());
        }
        System.out.println("\nType in the accountID money transfer out from");

        int accountID_from = input.nextInt();
        Account account_from = currentUser.getAccount(accountID_from);
        currentAccountID = accountID_from;

        System.out.println("\nType in the accountID money transfer out to");
        int accountID_to = input.nextInt();
        Account account_to = currentUser.getAccount(accountID_to);

        System.out.println("\nType in the amount of money to transfer");
        int amount = input.nextInt();

        // increase, decrease amount
        account_from.decreaseBalance(amount);
        account_to.increaseBalance(amount);
        // update recent transaction
        currentUser.set_recentTransaction_amount(amount);
        currentUser.set_recentTransaction_from(account_from);
        currentUser.set_recentTransaction_to(account_to);

        System.out.println("A transaction of amount $"+ amount+" is completed");
        System.out.println("from accountID "+ account_from+" to accountID "+ account_to);
    }

    @Override
    public int getAccountID() {
        return currentAccountID;
    }

    @Override
    public String toString() {
        return "Money Transfer";
    }
}

