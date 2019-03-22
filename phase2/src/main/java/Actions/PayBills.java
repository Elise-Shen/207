package Actions;
import ATM.*;
import Accounts.*;


import java.util.List;

import java.io.*;

public class PayBills extends Transactions{
    private int currentAccountID;
    private int amountPaid;
    private String recipientID;

    public PayBills(int currentId, BankManager bankManager){
        super(currentId,bankManager);
    }
    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        List<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts

        for(Account a: currentUserAccounts){
            if (a != null) {
                System.out.println( a.getAccountID() + " - " + a);
            }
        }
        Keypad keyPad = new Keypad();
        int accountID = keyPad.getIntInput("\nType in the ID of the account you want to use to pay bill.");
        Account account = currentUser.getAccount(accountID);
        currentAccountID = accountID;

        recipientID = keyPad.getStringInput("\nType in the ID of the non-user account that you want to pay the bill to.");

        amountPaid = keyPad.getIntInput("\nType in the amount of bill");
        // reduce the balance of the account
        account.decreaseBalance(amountPaid);
        String result = "A bill of $" + amountPaid + " is paid from user account " + accountID +
                " to non-user account " + recipientID;
        writeToOutGoingFile(result);
        System.out.println(result);

    }

    @Override
    public int getCurrentAccountID() {
        return currentAccountID;
    }

    @Override
    public String toString() {
        return "Pay bills of amount $" + amountPaid + "from user account " + currentAccountID +
                " to non-user account " + recipientID;
    }

    /**
     * Write to outgoing.txt.
     */
    private void writeToOutGoingFile(String result) {
        BufferedWriter writer;
        try {
            File outgoing = new File("outgoing.txt");

            writer = new BufferedWriter(new FileWriter(outgoing, true));
            writer.write(result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

