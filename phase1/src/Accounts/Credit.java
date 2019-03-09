package Accounts;

public class Credit extends DebtAccount {

    /**
     * Creates a Credit Card Account.
     */
    public Credit() {
        super();
    }

    @Override
    public int getAccountType() {
        return 3;
    }

    @Override
    public String toString() {
        return "Credit";
    }
}
