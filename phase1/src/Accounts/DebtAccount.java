package Accounts;
import java.util.Date;

public abstract class DebtAccount extends Account {

    public DebtAccount() {

    }

    @Override
    public int getAccountType() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    public double getBalance() {
        return 0 - balance;
    }
}
