package Accounts;
import java.util.Date;

public class Account {

    static int numAccount;
    private double balance;
    private int accountID;
    private Date dateOfCreation;

    public Account(dateOfCreation: Date) {
        numAccount += 1;
        this.accountID = numAccount;
        this.dateOfCreation = dateOfCreation;
    }

    public double getBalance() {
        return this.balance;
    }

    void increaseBalance(double money) {
        this.balance += money;
    }

    void increaseBalance(double money) {
        this.balance -= money;
    }

    public int getID() {
        return accountID;
    }

    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }
}
