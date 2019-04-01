package Accounts;
import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;


public abstract class DebtAccount extends Account {

    /**
     * The maximal debt this account can incur.
     */
    private MonetaryAmount maximalDebt;


    /**
     * Creates a Debt Account.
     */
    public DebtAccount(String currency) {
        super(currency);
        maximalDebt = Money.of(50000, getPrimaryCurrency());
    }

    @Override
    public int getAccountType() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public MonetaryAmount getCurrencyBalance() {
        MonetaryAmount currencyBalance = super.getCurrencyBalance();
        return currencyBalance.negate();
    }

    @Override
    public boolean decreaseCurrencyBalance(MonetaryAmount amount) {
        MonetaryAmount currencyBalance = getCurrencyBalance();
        MonetaryAmount newDebt = currencyBalance.add(amount);
        if (newDebt.isLessThanOrEqualTo(maximalDebt)) {
            setCurrencyBalance(newDebt);
            return true;
        }
        return false;
    }
}

