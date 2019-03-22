package Actions;

import ATM.BankManager;
import ATM.Keypad;

import java.io.Serializable;

public abstract class Transactions implements Serializable{
// remember to update bank manager.recent transaction from/to/amount 3 variables
    private static final long serialVersionUID = 1942709330913532485L;

    private int userID;
    private BankManager bankManager;
    Keypad keyPad = new Keypad();
    public Transactions(int userID, BankManager bm){
        this.userID = userID;
        bankManager = bm;
    }

    public int getUserID(){
        return userID;
    }

    public abstract int getCurrentAccountID();
    public abstract String toString();
    public BankManager getBankManager(){
        return bankManager;
    }

    public abstract void execute();

    public int getRecipientAccountID(){return 0;}
}
