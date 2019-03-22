package Actions;

import ATM.BankManager;
import ATM.User;
import Accounts.*;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NetTotal extends UserActions {

    public NetTotal(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method calculate and print out the user's net total by subtracting debt account balance from asset account balance.
     * Calculates net total by converting all different types of currency to the local currency
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        List<Account> accounts = currentUser.getAccountList();
        CurrencyUnit currencyUnit = Monetary.getCurrency(Locale.getDefault());
        MonetaryAmount netTotal = Money.of(0, currencyUnit);
        for(Account account: accounts){
            if (account instanceof AssetAccount) {
                CurrencyConversion currencyConversion = MonetaryConversions.getConversion(currencyUnit);
                MonetaryAmount converted = account.getCurrencyBalance().with(currencyConversion);
                netTotal = netTotal.add(converted);
            }
            else if (account instanceof DebtAccount){
                CurrencyConversion currencyConversion = MonetaryConversions.getConversion(currencyUnit);
                MonetaryAmount converted = account.getCurrencyBalance().with(currencyConversion);
                netTotal = netTotal.subtract(converted);
            }
        }
        System.out.println("The net total is: " + netTotal);
    }
}
