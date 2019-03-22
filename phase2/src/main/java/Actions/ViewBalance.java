package Actions;

import ATM.BankManager;
import Accounts.Account;
import org.javamoney.moneta.Money;

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
        Money currencyBalance = currentAccount.getCurrencyBalance();
        System.out.println("Account id: " + currentId + " has balance $" + balance);
        System.out.println("Account id: " + currentId + " has balance $" + currencyBalance);
    }
}
