package ATM;

import Accounts.Account;
import Accounts.AccountFactory;
import Actions.AccountToAccount;
import Actions.DepositMoney;
import Actions.Transactions;
import Actions.WithdrawMoney;

import java.io.*;
import java.util.InputMismatchException;

import java.util.*;
import java.util.Scanner;



public class BankManager {

    private String bankName;
    private Map<Integer, Integer> accountRequests = new HashMap<>();
    private Map<Integer, Transactions> undoTransactionRequest = new HashMap<>();
    private List<User>  userArrayList = new ArrayList<>();
    private AccountFactory accountFactory = new AccountFactory();
    private List<Transactions> listOfTransactions = new ArrayList<>(); //for part 2
    private List<Account> allAccounts = new ArrayList<>();
    private String password;

    private final int CHEQUING = 1;
    private final int SAVINGS = 2;
    private final int CREDIT = 3;
    private final int LINE_OF_CREDIT = 4;

    public BankManager(String bankName, String password){
        this.bankName = bankName;
        this.password = password;

        // create 3 users for testing purpose
        User user1 = new User(1, "abc123", this);
        User user2 = new User(2, "abc123", this);
        userArrayList.add(user1);
        userArrayList.add(user2);
        //createUserExample(userNumExample);
    }

    public void addAllAccountsList(Account a){
        allAccounts.add(a);
    }

    public String getBankName(){
        return this.bankName;
    }

    public User getUser(int userID){
        User result = null;
        for (User u: userArrayList) {
            if (u.getUserID() == userID) {
                result = u;
                break;
            }
        }
        return result;
    }
    public ArrayList<Account> getAccountArrayList(User user) {
        // given user obj -> return a list of account in correspond to the user
        return user.getAccountList();
    }

    public List<Account> getAllAccounts(){
        return allAccounts;
    }

    public Account getOneAccount(int id){
        Account temp = null;
        for (Account a: allAccounts){
            if (a.getAccountID() == id){
                temp = a;
                break;
            }
        }
        return temp;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(User user, String newPassword){
        user.setPassword(newPassword);
    }

    public void requestAccount(int userID, int accountType){
        accountRequests.put(userID, accountType);
    }

    public void createAccount(int userID, int accountType) {
        Account newAccount = accountFactory.getAccount(accountType);

        addAccount(userID, newAccount);
        System.out.println("Created a new " + newAccount + " for User " + userID + ".");
    }

    /**
     * Not private because may use this for other reasons in Phase 2
     * @param userID the ID of the user
     * @param newAccount the Account object of account being requested
     */
    public void addAccount(int userID, Account newAccount){
        User user = getUser(userID);
        newAccount.setOwnerID(userID);
        user.addAccount(newAccount);
        addAllAccountsList(newAccount);
    }

    public Map<Integer, Integer> getAccountRequests(){
        return accountRequests;
    }

    public Map<Integer, Transactions> getUndoTransactionRequest(){
        return undoTransactionRequest;
    }

    public void addUndoTransactionRequest(int userID, Transactions t){
        undoTransactionRequest.put(userID, t);
    }


    public void undoTransaction (Transactions t){
        // recentTransaction_to -> recentTransaction_from
        // bills can't undo
        // this 2 variables are updated in every transaction
        int amountMoved = 0;
        // search user
        Account currentAccount = getOneAccount(t.getCurrentAccountID());
        // get recent transaction of this user
        if(t instanceof WithdrawMoney){
            amountMoved = ((WithdrawMoney) t).getAmountWithdrawn();
            currentAccount.increaseBalance(amountMoved);
            System.out.println("Returned $" + amountMoved +" to the account");
        }else if(t instanceof DepositMoney){
            amountMoved = ((DepositMoney) t).getAmountDeposited();
            currentAccount.decreaseBalance(amountMoved);
            System.out.println("Removed money from the account");

        }else if (t instanceof AccountToAccount){

            int recipientID = ((AccountToAccount) t).getRecipientAccountID();
            Account recipientAccount = getOneAccount(recipientID);
            amountMoved = ((AccountToAccount) t).getAmmountTransferred();
            currentAccount.increaseBalance(amountMoved);
            recipientAccount.decreaseBalance(amountMoved);
            System.out.println("Returned money to original account");
        }
        System.out.println("Transaction is Undone");
    }


    /**
     * Determine whether the user successfully login according to the password entered.
     */
    int checkLogin () {
        User currentUser;
        // check if user exist
        int currentID = 0;
        boolean validInput0 = false;
        boolean validInput1 = false;
        while (!validInput0) {
            try{
            Scanner input0 = new Scanner(System.in);
            System.out.println("\nPlease enter your User ID");
            currentID = input0.nextInt();
            currentUser = checkUserID(currentID);
            if (currentUser == null) {
                System.out.println("User does not exist. Please try again.");
            } else {
                validInput0 = true;
                while (!validInput1) {
                    // check if password is correct.
                    Scanner input1 = new Scanner(System.in);
                    System.out.println("\nPlease enter your password");
                    String pass = input1.nextLine();
                    if (currentUser.getPassword().equals(pass)) {
                        System.out.println("Successfully Login!");
                        validInput1 = true;
                    } else {
                        System.out.println("Wrong Password. Please try again.");
                    }
                }
            }
        }catch(InputMismatchException ex){System.out.println("Invalid input. Please try again!");}
        }
        return currentID;
    }

    /**
     * Used for part 2
     * @param t transaction
     */
    public void addTransaction(Transactions t){
        listOfTransactions.add(0, t);
    }

    /**
     * Check if the user with ID userID exists.
     * @param userID the ID to check.
     */
    private User checkUserID(int userID) {
        for (User user : userArrayList) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public String getAccountName(int type){
        String name= "";
        switch (type){
            case CHEQUING:
                name = "Chequing";
                break;
            case SAVINGS:
                name = "Savings";
                break;
            case CREDIT:
                name = "Credit";
                break;
            case LINE_OF_CREDIT:
                name = "Line of Credit";
                break;
        }
        return name;
    }


    /**
     * Add interest to all savings accounts in this BankManager's account list.
     */
    void addInterestToSavingsAccounts() {
        for (Account a : allAccounts) {
            if (a != null && a.getAccountType() == 2) {
                a.addInterest();
             }
        }
    }

    public void updateData(){

    }





}

