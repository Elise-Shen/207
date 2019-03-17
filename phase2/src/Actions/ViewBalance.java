package Actions;

import ATM.BankManager;
import Accounts.Account;

public class ViewBalance extends ViewAccount {

    public ViewBalance(int currentId, BankManager bankManager){
        super(currentId, bankManager);
    }

    /**
     * This method print out the account balance.
     */
    @Override
    public void execute(){
        Account currentAccount = readCurrentAccount();
        int currentId = currentAccount.getAccountID();
        double balance = currentAccount.getBalance();
        System.out.println("Account id: " + currentId + " has balance $" + balance);
    }
}
