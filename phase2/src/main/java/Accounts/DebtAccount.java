package Accounts;
import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;


public abstract class DebtAccount extends Account {

    /**
     * The maximal debt this account can incur.
     */
    private Money maximalDebt;

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
    public double getBalance() {
        return 0 - balance;
    }

    @Override
    public Money getCurrencyBalance(){
        Money currencyBalance = super.getCurrencyBalance();
        return currencyBalance.negate();
    }

    @Override
    public boolean decreaseCurrencyBalance(int amount) {
        Money currencyBalance = getCurrencyBalance();
        CurrencyUnit unit = getPrimaryCurrency();
        Money newDebt = currencyBalance.add(Money.of(amount, unit));
        if (newDebt.isLessThanOrEqualTo(maximalDebt)) {
            setCurrencyBalance(newDebt);
            return true;
        }
        return false;
    }
}
