package Accounts;
import java.util.Date;

public abstract class DebtAccount extends Account {

    /**
     * Creates a Debt Account.
     */
    public DebtAccount() {
        super();
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
}
