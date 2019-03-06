package Actions;

import ATM.BankManager;
public abstract class Transactions {
// remember to update bankmanagement.recent transaction from/to/amount 3 variables
    private int userID;
    private BankManager bankManager;
    public Transactions(int userid, BankManager bm){
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
