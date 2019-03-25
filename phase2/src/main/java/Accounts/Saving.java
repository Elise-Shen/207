package Accounts;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;

public class Saving extends AssetAccount {

    /**
     * Creates a Savings Account.
     */
    public Saving(String currency) {
        super(currency);
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
        balance = balance * (1 + 0.001);
    }

    @Override
    public boolean decreaseCurrencyBalance(int amount) {
        Money currencyBalance = getCurrencyBalance();
        CurrencyUnit unit = getPrimaryCurrency();
        if (currencyBalance.isLessThan(Money.of(0, unit))) {
            return false;
        } else if (currencyBalance.isGreaterThanOrEqualTo(Money.of(amount, unit))) {
            Money newBalance = currencyBalance.subtract(Money.of(amount, unit));
            setCurrencyBalance(newBalance);
        }
        return false;
    }
}

