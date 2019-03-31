package AdminActions;

import ATM.BankManager;
import ATM.BankProductsEmployee;

import java.io.Serializable;

public abstract class AdminAction implements Serializable {

    private static final long serialVersionUID = 6249042975266438798L;

    private BankManager bankManager;

    private BankProductsEmployee bankProductsEmployee;

    public AdminAction(BankManager bankManager){
        this.bankManager = bankManager;
    }

    public AdminAction(BankProductsEmployee bankProductsEmployee){this.bankProductsEmployee = bankProductsEmployee;}

    public abstract void execute();

    //public String getBankName(){
        //return this.bankManager.getBankName();
    //}

    public BankManager getBankManager(){
        return this.bankManager;
    }
}
