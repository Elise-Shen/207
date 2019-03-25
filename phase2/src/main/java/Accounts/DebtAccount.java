package Accounts;

public abstract class DebtAccount extends Account {

    /**
     * Creates a Debt Account.
     */
    public DebtAccount(String currency) {
        super(currency);
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
