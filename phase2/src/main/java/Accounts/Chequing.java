package Accounts;

import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

public class Chequing extends AssetAccount {

    /**
     * Creates a Chequing Account.
     */
    public Chequing(String currency) {
        super(currency);
    }

    @Override
    public int getAccountType() {
        return 1;
    }

    @Override
    public String toString(){
        return "Chequing";
    }

    @Override
    public void decreaseBalance(double money) {
        balance -= money;
    }

    @Override
    public boolean decreaseCurrencyBalance(MonetaryAmount amount) {
        MonetaryAmount currencyBalance = getCurrencyBalance();
        CurrencyUnit unit = getPrimaryCurrency();
        if (currencyBalance.isLessThan(Money.of(0, unit))) {
            return false;
        } else if (currencyBalance.isGreaterThanOrEqualTo(amount.subtract(Money.of(100, unit)))) {
            MonetaryAmount newBalance = currencyBalance.subtract(amount);
            setCurrencyBalance(newBalance);
            return true;
        }
        return false;
    }
}

