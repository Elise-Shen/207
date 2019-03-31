package Actions;
import ATM.*;
import Accounts.*;
import org.javamoney.moneta.Money;


import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.util.List;

import java.io.*;
import java.util.Locale;

public class PayBills extends Transactions{
    private int currentAccountID;
    private MonetaryAmount amountPaid;
    private String recipientID;
    private Account currentAccount;

    public PayBills(int currentId, BankManager bankManager){
        super(currentId,bankManager);
    }
    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        currentAccount = null;
        int accountID = 0;
        List<Account> currentUserAccounts = bankManager.getAccountArrayList(currentUser);//want to return a list of all accounts
        Keypad keyPad = new Keypad();
        ViewAccount.printAccounts(currentUserAccounts);
        boolean isValid = false;
        while(!isValid) {
            accountID = keyPad.getIntInput("\nType in the ID of the account you want to use to pay bill.");
            currentAccount = currentUser.getAccount(accountID);
            if(currentAccount != null){
                currentAccountID = accountID;
                isValid = true;
            }else{System.out.println("Try Again");}
        }
        recipientID = keyPad.getStringInput("\nType in the ID of the non-user account that you want to pay the bill to.");

        amountPaid = currentAccount.createMoney(keyPad.getIntInput("\nType in the amount of bill"));
        // reduce the balance of the account

        boolean valid = currentAccount.decreaseCurrencyBalance(amountPaid);
        if (valid) {
            String result = "A bill of $" + amountPaid + " is paid from user account " + accountID +
                    " to non-user account " + recipientID;
            writeToOutGoingFile(result);
            System.out.println(result);
        } else {
            System.out.println("Transaction failed");
        }


    }

    public boolean executePayBill(Account account, int amount){
        currentAccount = account;
        amountPaid = account.createMoney(amount);
        return account.decreaseCurrencyBalance(amountPaid);
    }

    public MonetaryAmount getAmountPaid(){
        return amountPaid;
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

