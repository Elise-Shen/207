package Accounts;
import java.util.Date;

public class DebtAccount extends Account {

    public DebtAccount(Date creationDate) {
        super(creationDate);
    }

    public double getBalance() {
        return 0 - balance;
    }
}
