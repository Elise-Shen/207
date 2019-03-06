package Accounts;
import java.util.Date;

public abstract class DebtAccount extends Account {

    public DebtAccount() {

    }

    public double getBalance() {
        return 0 - balance;
    }
}
