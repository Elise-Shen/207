package Actions;

import ATM.*;
import Accounts.*;

import java.util.*;
import java.util.Scanner;

/**
 * Can only transfer to own accounts, or other user's chequing accounts.
 */
public class AccountToAccount extends Transactions {
    // Transfer money from one account to another
    // ask user to input 2 account numbers
    private int currentAccountID;
    private int recipientAccountID;
    private int amountTransferred;

    public AccountToAccount(int currentId, BankManager bankManager){
        super(currentId,bankManager);
    }

    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        List<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts
        List<Account> allAccounts = bankManager.getAllAccounts();

        Scanner input = new Scanner(System.in);
        for (Account a : currentUserAccounts) {
            if (a != null && !(a instanceof Credit)){
                System.out.println(a.getAccountID() + " - " + a);
            }
        }
        System.out.println("\nType in the accountID money transfer out from");

        int accountID_from = input.nextInt();
        Account account_from = currentUser.getAccount(accountID_from);
        currentAccountID = accountID_from;


        for (Account a : allAccounts){
            System.out.println(a.getAccountID() + " - User " + a.getOwnerID() + "'s " + a);
        }
        System.out.println("\nType in the accountID money transfer out to");
        int accountID_to = input.nextInt();
        recipientAccountID = accountID_to;
        Account account_to = bankManager.getOneAccount(accountID_to);

        System.out.println("\nType in the amount of money to transfer");
        int amount = input.nextInt();
        amountTransferred = amount;

        // increase, decrease amount
        account_from.decreaseBalance(amount);
        account_to.increaseBalance(amount);
        // update recent transaction

        System.out.println("A transaction of amount $"+ amount+" is completed");
        System.out.println("from " + accountID_from + account_from+" to " + accountID_to + account_to);
    }

    @Override
    public int getCurrentAccountID() {
        return currentAccountID;
    }

    @Override
    public int getRecipientAccountID(){
        return recipientAccountID;
    }

    public int getAmountTransferred(){
        return  amountTransferred;
    }

    @Override
    public String toString() {
        return "Transfer $" + amountTransferred + "from account " +
                currentAccountID + " to account " + recipientAccountID;
    }
}

