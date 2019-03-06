package Actions;

import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewBalance extends UserActions {

    private Account account;

    public ViewBalance(int currentId, BankManager bankManager){
        super(currentId, bankManager);
    }

    /**
     * This method print out the account balance.
     */
    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts
        Scanner input = new Scanner(System.in);
        System.out.println("\nType in the ID of the account you want to view");
        for(Account a: currentUserAccounts){
            System.out.println( a.getAccountID() + " - " + a.getAccountType());
        }
        int accountChoice = input.nextInt();
        account = currentUser.getAccount(accountChoice);
        double balance = account.getBalance();
        int accountId = account.getAccountID();
        System.out.println("Account id: " + accountId + " has balance " + balance);
        System.out.println("Working");
    }

}
