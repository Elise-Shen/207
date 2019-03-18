package ATM;

import Accounts.Account;
import Accounts.Chequing;
import Actions.Transactions;

import java.io.Serializable;
import java.util.*;
import java.time.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1163641386372217670L;

    private int userID;
    // transRecord: Map Date to an ArrayList
    private Map <LocalDate, Transactions> transRecord = new LinkedHashMap <>();
    private List<Account> accounts = new ArrayList<>();
    private Account primaryAccount;
    private String password;
    private BankManager bankManager;



    private int accountRequestCount = 0;

    public User (int userID, String password, BankManager bm){
        this.userID = userID;
        this.password = password;
        this.bankManager = bm;
        Account account1 = new Chequing();
        account1.setOwnerID(this.userID);
        accounts.add(account1);
        this.bankManager.addAllAccountsList(account1);
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
     * Return the Array list of account.
     */
    public List<Account> getAccountList(){ return accounts;}

    /**
     * Given account ID, return the corresponding account.
     */
    public Account getAccount(int accountID){
        Account result = null;
        for(Account a: accounts){
            if (a != null && a.getAccountID() == accountID) {
                result = a;
                break;
            }
        }
        return result;

    }

    public BankManager getBankManager(){
        return this.bankManager;
    }

    /**
     * Adds the transaction to the linked HashMap
     * @param t current transaction
     */
    public void addTransactions(Transactions t){

        transRecord.put(LocalDate.now(), t);

    }
    public Map<LocalDate, Transactions> getTransactionsList(){
        return transRecord;
    }

    /**
     * Add new account to account list.
     * @param a the account to be added
     */
    public void addAccount(Account a){
        accounts.add(a);
    }

    // recent transaction record


    public void incrementCount(){
        accountRequestCount += 1;
    }

    public void resetCount(){
        accountRequestCount = 0;
    }

    public int getCount(){
        return accountRequestCount;
    }
}

