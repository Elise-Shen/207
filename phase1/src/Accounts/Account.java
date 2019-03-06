package Accounts;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;

public abstract class Account {

    static int numAccount;
    double balance;
    private int accountID;
    private LocalDate dateOfCreation;
    private int accountType;

    public Account(int type) {
        this.accountType = type;
        numAccount += 1;
        this.accountID = numAccount;
        this.dateOfCreation = LocalDate.now();
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

    public LocalDate getDateOfCreation() {
        return this.dateOfCreation;
    }
}
