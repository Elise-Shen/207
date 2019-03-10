package AdminActions;

import ATM.BankManager;

import java.io.Serializable;

public abstract class AdminAction implements Serializable {

    private BankManager bankManager;

    public AdminAction(BankManager bankManager){
        this.bankManager = bankManager;
    }

    public abstract void execute();

    public String getBankName(){
        return this.bankManager.getBankName();
    }

    public BankManager getBankManager(){
        return this.bankManager;
    }
}
