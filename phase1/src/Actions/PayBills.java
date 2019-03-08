package Actions;
import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PayBills extends Transactions{
    //PayBills(currentUserID, bankManager );
    // pay a bill to non user account
    // transaction can't undo -> don't update recent transaction for user
    private int currentAccountID;

    public PayBills(int currentId, BankManager bankManager){
        super(currentId,bankManager);
    }
    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts

        Scanner input = new Scanner(System.in);
        for(Account a: currentUserAccounts){
            System.out.println( a.getAccountID() + " - " + a.toString());
        }
        System.out.println("\nType in the ID of the account you want to use to pay bill");
        // reduce the ammount of account
        int accountID = input.nextInt();
        Account account = currentUser.getAccount(accountID);
        currentAccountID = accountID;
        System.out.println("\nType in the amount of bill");
        int amount = input.nextInt();

        account.increaseBalance(amount);
        System.out.println("A bill of $" + amount + " is paid from accountID " + accountID);

    }

    @Override
    public int getAccountID() {
        return currentAccountID;
    }

    @Override
    public String toString() {
        return "Pay Bill";
    }
}
