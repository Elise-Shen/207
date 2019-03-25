package Accounts;

import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;

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
    public boolean decreaseCurrencyBalance(int amount) {
        Money currencyBalance = getCurrencyBalance();
        CurrencyUnit unit = getPrimaryCurrency();
        if (currencyBalance.isLessThan(Money.of(0, unit))) {
            return false;
        } else if (currencyBalance.isGreaterThanOrEqualTo(Money.of(amount - 100, unit))) {
            Money newBalance = currencyBalance.subtract(Money.of(amount, unit));
            setCurrencyBalance(newBalance);
            return true;
        }
        return false;
    }
}

