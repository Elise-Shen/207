package Actions;

import ATM.BankManager;

import java.io.Serializable;

public abstract class UserActions implements Serializable {
    private static final long serialVersionUID = 6249042975266438798L;

    private int userID;
    private BankManager bankManager;
    private BankProductEmployee bpe;

    public UserActions(int userID, BankManager bm){
        this.userID = userID;
        bankManager = bm;
    }
    public UserAction(int userID, BankProductEmployee bpe){

    }

    public int getUserID(){
        return userID;
    }

    public BankManager getBankManager(){
        return bankManager;
    }

    public abstract void execute();

}
