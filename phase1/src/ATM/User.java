package ATM;

import Accounts.Account;
import Actions.Transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Date;
import java.time.LocalDate;

public class User {
    private int userID;
    // transRecord: Map Date to an ArrayList
    private LinkedHashMap <LocalDate, Transactions> transRecord = new LinkedHashMap <>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account primaryAccount;
    private String password;
    //for transaction purpose
    private Account recentTransaction_from;
    private Account recentTransaction_to;
    private double recentTransaction_amount;

    public User (int userID, String password){
        this.userID = userID;
        this.password = password;
    }
// getter and setter

    public int getUserID(){
        return this.userID;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
        System.out.println("The password is reset (in user.java)");
    }
    public String getPassword(){
        return this.password;
    }
    public void setPrimaryAccount(Account a){
        primaryAccount=a;
    }
    public Account getPrimaryAccount() {
        return primaryAccount;
    }

    /**
     *
     * return the Array list of account
     */
    public ArrayList<Account> getAccountList(){ return accounts;}

    /** 
    Given account ID -> Accout obj. correspond to this accountID
     */
    public Account getAccount(int accountID){
        Account result = null;
        for(Account a: accounts){
            if (a.getAccountID()==accountID){
                result = a;
                break;
            }
        }
        return result;

    }

    /**
     * Adds the transaction to the linked hashmap
     * @param t current transaction
     */
    public void addTransactions(Transactions t){

        transRecord.put(LocalDate.now(), t);

    }
    public HashMap<LocalDate, Transactions> getTransactionsList(){
        return transRecord;
    }

    /**
     * Add new account to account list.
     * @param a
     */

    public void addAccount(Account a){
        accounts.add(a);
    }

    // recent transaction record
    public void set_recentTransaction_from(Account from){
        recentTransaction_from = from;
    }
    public void set_recentTransaction_to(Account to){
        recentTransaction_to = to;
    }
    public void set_recentTransaction_amount(int amount){
        recentTransaction_amount = amount;
    }
    public Account getRecentTransaction_from(){return recentTransaction_from;}
    public Account getRecentTransaction_to(){return recentTransaction_to;}
    public double getRecentTransaction_amount(){return recentTransaction_amount;}
}

