package Actions;

import ATM.BankManager;
import Accounts.Account;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

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
        MonetaryAmount currencyBalance = currentAccount.getCurrencyBalance();
        System.out.println("Account id: " + currentId + " has balance of " + currencyBalance);
    }
}
