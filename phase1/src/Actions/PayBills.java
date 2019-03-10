package Actions;
import ATM.*;
import Accounts.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PayBills extends Transactions{
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
        System.out.println("\nType in the ID of the account you want to use to pay bill.");
        for(Account a: currentUserAccounts){
            if (a != null) {
                System.out.println( a.getAccountID() + " - " + a);
            }
        }
        int accountID = input.nextInt();
        Account account = currentUser.getAccount(accountID);
        currentAccountID = accountID;

        Scanner input1 = new Scanner(System.in);
        System.out.println("\nType in the ID of the non-user account that you want to pay the bill to.");
        String nonUserAccountID = input1.nextLine();

        System.out.println("\nType in the amount of bill");
        int amount = input.nextInt();
        // reduce the balance of the account
        account.decreaseBalance(amount);
        String result = "A bill of $" + amount + " is paid from user account " + accountID +
                " to non-user account " + nonUserAccountID;
        writeToOutGoingFile(result);
        System.out.println(result);

    }

    @Override
    public int getCurrentAccountID() {
        return currentAccountID;
    }

    @Override
    public String toString() {
        return "Pay Bill";
    }

    /**
     * Write to outgoing.txt.
     */
    private void writeToOutGoingFile(String result) {
        BufferedWriter writer = null;
        try {
            File outgoing = new File("outgoing.txt");

            writer = new BufferedWriter(new FileWriter(outgoing));
            writer.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}

