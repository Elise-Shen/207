package Actions;

import ATM.BankManager;

public abstract class UserActions {
    private int userID;
    private BankManager bankManager;

    public UserActions(int userid, BankManager bm){
        userID = userid;
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
