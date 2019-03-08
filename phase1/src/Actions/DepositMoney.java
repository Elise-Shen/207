package Actions;
import java.io.*;
import ATM.*;
import Accounts.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DepositMoney extends Transactions {
    /**
     * The CashStorage of the ATM that performs this action.
     */
    CashStorage cashStorage;

    /**
     * Create an instance of DepositMoney action.
     * @param userID the ID of the current user
     * @param bm the BankManager of the ATM
     * @param cs the CashStorage of the ATM
     */
    public DepositMoney(int userID, BankManager bm, CashStorage cs) {
        super(userID, bm);
        this.cashStorage = cs;
    }

    /**
     * Deposit money into the chosen account of current user, if applicable.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        boolean validInput = false;
        int accountChoice = 0;
        ArrayList<Account> currentUserAccounts = currentUser.getAccountList();
        while (!validInput) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nPlease type in the ID of the account what you want to deposit money in.");
            for (Account a : currentUserAccounts) {
                System.out.println(a.getAccountID() + " - " + a.toString());
            }
            accountChoice = input.nextInt();
            Account myAccount = currentUser.getAccount(accountChoice);
            if (myAccount != null && myAccount.getAccountType() != 3 && myAccount.getAccountType() != 4) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
            Account currentAccount = currentUser.getAccount(accountChoice);
            boolean validInput1 = false;
            while (!validInput1) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("deposits.txt"));
                    String line = "";
                    String previous_line = "";
                    while (line != null) {
                        previous_line = line;
                        line = bufferedReader.readLine();
                        previous_line.replaceAll("\\s+", "");
                        String[] split = previous_line.split(",");
                        if (split[0].equalsIgnoreCase("Cash")) {
                            int numFive = Integer.valueOf(split[1]);
                            int numTen = Integer.valueOf(split[2]);
                            int numTwenty = Integer.valueOf(split[3]);
                            int numFifty = Integer.valueOf(split[4]);
                            currentAccount.increaseBalance(numFive * 5 + numTen * 10 + numTwenty * 20 + numFifty * 50);
                            cashStorage.addBills(5, numFive);
                            cashStorage.addBills(10, numTen);
                            cashStorage.addBills(20, numTwenty);
                            cashStorage.addBills(50, numFifty);
                            validInput1 = true;
                        } else if (split[0].equalsIgnoreCase("Cheque")) {
                            currentAccount.increaseBalance(Integer.valueOf(split[1]));
                            validInput1 = true;
                        } else {
                            System.out.println("Invalid input.Please enter again in deposits.txt.");
                        }
                        bufferedReader.close();
                    }
                }
                catch(FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file '" +
                                    "deposits.txt" + "'");
                }
                catch(IOException ex) {
                    System.out.println(
                            "Error reading file '"
                                    + "deposits.txt" + "'");
                }




            }
        }
    }
}
