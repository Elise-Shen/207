package Actions;

import ATM.BankManager;
public abstract class Transactions {
// remember to update bank manager.recent transaction from/to/amount 3 variables
    private int userID;
    private BankManager bankManager;
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
}
