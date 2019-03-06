package ATM;

import Accounts.Account;
import Actions.Transactions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Date;

public class User {
    private int userID;
    // transRecord: Map Date to an ArrayList
    private LinkedHashMap <Date, ArrayList<Transactions>> trasRecord = new LinkedHashMap <Date,ArrayList<Transactions>>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account primaryAccount;
    private String password;
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
     * Add new account to account list.
     * @param a
     */

    public void addAccount(Account a){
        accounts.add(a);
    }
}
