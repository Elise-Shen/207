package Actions;
import ATM.*;
import java.io.*;
import Accounts.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;


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
        Account currentAccount = null;
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
                currentAccount = currentUser.getAccount(accountChoice);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
        }
        System.out.println("Please enter things into deposits.txt");
        //currentAccount = currentUser.getAccount(accountChoice); moved up to previous if statement
        boolean validInput1 = false;
        while (!validInput1) {

            List<String[]> deposits = readFromCSV("phase1\\deposits.txt");
            String[] lastLine = null;
            // let's print all the person read from CSV file
            for (String[] s : deposits) {
                lastLine = s;

            }
            System.out.println(Arrays.toString(lastLine));
            validInput1 = true;
            if (lastLine[0].equalsIgnoreCase("Cash")) {
                int numFive = Integer.valueOf(lastLine[1]);
                int numTen = Integer.valueOf(lastLine[2]);
                int numTwenty = Integer.valueOf(lastLine[3]);
                int numFifty = Integer.valueOf(lastLine[4]);
                currentAccount.increaseBalance(numFive * 5 + numTen * 10 + numTwenty * 20 + numFifty * 50);
                cashStorage.addBills(5, numFive);
                cashStorage.addBills(10, numTen);
                cashStorage.addBills(20, numTwenty);
                cashStorage.addBills(50, numFifty);
                validInput1 = true;
            } else if (lastLine[0].equalsIgnoreCase("Cheque")) {
                currentAccount.increaseBalance(Integer.valueOf(lastLine[1]));
                validInput1 = true;
            } else {
                System.out.println("Invalid input.Please enter again in deposits.txt.");
            }
        }
    }

    private static List<String[]> readFromCSV(String fileName) {
        List<String[]> deposits = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                for (int i = 0; i < attributes.length; i++){
                    attributes[i] = attributes[i].replaceAll("\\s+","");
                }
                deposits.add(attributes);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return deposits;
    }
}
