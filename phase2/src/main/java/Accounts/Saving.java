package Accounts;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

public class Saving extends AssetAccount {

    /**
     * Interest Rate
     */
    private static double interestRate;

    /**
     * Creates a Savings Account.
     */
    public Saving(String currency) {
        super(currency);
        interestRate = 0.001;
    }

    @Override
    public int getAccountType() {
        return 2;
    }

    @Override
    public String toString() {
        return "Savings";
    }

    @Override
    public void addInterest(){
        MonetaryAmount newBalance = getCurrencyBalance().multiply(1 + interestRate);
        setCurrencyBalance(newBalance);
    }


    public static void setInterestRate(double rate){interestRate = rate;}

    public static double getInterestRate(){return interestRate;}

    @Override
    public boolean decreaseCurrencyBalance(MonetaryAmount amount) {
        MonetaryAmount currencyBalance = getCurrencyBalance();
        CurrencyUnit unit = getPrimaryCurrency();
        if (currencyBalance.isLessThan(Money.of(0, unit))) {
            return false;
        } else if (currencyBalance.isGreaterThanOrEqualTo(amount)) {
            MonetaryAmount newBalance = currencyBalance.subtract(amount);
            setCurrencyBalance(newBalance);
        }
        return false;
    }
}


