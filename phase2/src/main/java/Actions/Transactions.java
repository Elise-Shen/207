package Actions;

import ATM.BankManager;
import org.javamoney.moneta.Money;
import Accounts.Account;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

public abstract class Transactions implements Serializable{
// remember to update bank manager.recent transaction from/to/amount 3 variables
    private static final long serialVersionUID = 1942709330913532485L;

    private int userID;
    private BankManager bankManager;
    private LocalDate date;

    public Transactions(int userID, BankManager bm){
        this.userID = userID;
        bankManager = bm;
        this.date = LocalDate.now();
    }

    public LocalDate getDate(){
        return date;
    }
    public int getUserID(){
        return userID;
    }
    public abstract int getCurrentAccountID();
    public abstract String toString();
    public BankManager getBankManager(){
        return bankManager;
    }
    public Account getCurrentAccount(){
        return bankManager.getOneAccount(getCurrentAccountID());
    }


    public abstract void execute();

    MonetaryAmount createMoney(double amount){
        CurrencyUnit currencyUnit = getBankManager().getOneAccount(getCurrentAccountID()).getPrimaryCurrency();
        return Money.of(amount, currencyUnit);
    }

    public int getRecipientAccountID(){return 0;}
}
