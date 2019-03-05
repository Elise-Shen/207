package ATM;

import Accounts.Accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class User {
    private int userID;
    // transRecord: Map Date to an ArrayList
    private HashMap <Date, ArrayList> trasRecord = new HashMap <Date,ArrayList>();
    private ArrayList<Accounts> accounts = new ArrayList<Accounts>;
    private Account primaryAccount;
    private int password;
    public User (int userID, int password){
        this.userID = userID;
        this.password = password;
    }
// getter and setter
    public int getUserID(){
        return this.userID;
    }
    public void setPassword(int newPassword){
        this.password = newPassword;
    }
    public int getPassword(){
        return this.password;
    }

    public Account getPrimaryAccount() {
        return primaryAccount;
    }
// view account

    public addAcc(Accounts account){
        accounts.add(account);

    }

}
