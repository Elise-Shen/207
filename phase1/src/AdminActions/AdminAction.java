package AdminActions;

import ATM.BankManager;

import java.io.Serializable;

public abstract class AdminAction implements Serializable {

    private static final long serialVersionUID = 6249042975266438798L;

    private BankManager bankManager;

    public AdminAction(BankManager bankManager){
        this.bankManager = bankManager;
    }

    public abstract void execute();

    //public String getBankName(){
        //return this.bankManager.getBankName();
    //}

    public BankManager getBankManager(){
        return this.bankManager;
    }
}
