package Accounts;
import java.util.Date;

public class Account {

    static int numAccount;
    double balance;
    private int accountID;
    private Date dateOfCreation;

    public Account(Date dateOfCreation) {
        numAccount += 1;
        this.accountID = numAccount;
        this.dateOfCreation = dateOfCreation;
    }

    public double getBalance() {
        return this.balance;
    }

    public void increaseBalance(double money) {
        this.balance += money;
    }

    public void decreaseBalance(double money) {
        this.balance -= money;
    }

    public int getID() {
        return accountID;
    }

    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }
}
