package ATM;

import Accounts.Accounts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Date;

public class User {
    private int userID;
    // transRecord: Map Date to an ArrayList
    private LinkedHashMap <Date, ArrayList> trasRecord = new LinkedHashMap <Date,ArrayList>();
    private ArrayList<Accounts> accounts = new ArrayList<Accounts>();
    private Accounts primaryAccount;
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

    public Accounts getPrimaryAccount() {
        return primaryAccount;
    }
// view account

//    public addAcc(Accounts account){
//        accounts.add(account);
//
//    }

}
