package Actions;

import ATM.BankManager;
import ATM.BankProductsEmployee;

import java.io.Serializable;

public abstract class UserActions implements Serializable {
    private static final long serialVersionUID = 6249042975266438798L;

    private int userID;
    private BankManager bankManager;
    private BankProductsEmployee bankProductsEmployee;
    private int accountID;

    public UserActions(int userID, BankManager bm){
        this.userID = userID;
        bankManager = bm;
    }
    public UserActions(int userID, BankProductsEmployee bpe, int id){
        this.userID = userID;
        bankProductsEmployee = bpe;
        accountID = id;
    }

    public int getUserID(){
        return userID;
    }

    public BankManager getBankManager(){
        return bankManager;
    }
    public BankProductsEmployee getBankProductsEmployee(){return bankProductsEmployee;}

    public int getAccountID() {
        return accountID;
    }

    public abstract void execute();

}
