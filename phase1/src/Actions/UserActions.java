package Actions;

import ATM.BankManager;

import java.io.Serializable;

public abstract class UserActions implements Serializable {
    private int userID;
    private BankManager bankManager;

    public UserActions(int userID, BankManager bm){
        this.userID = userID;
        bankManager = bm;
    }

    public int getUserID(){

        return userID;
    }

    public BankManager getBankManager(){
        return bankManager;
    }

    public abstract void execute();

}
