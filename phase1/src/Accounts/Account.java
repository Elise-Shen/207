package Accounts;
import Actions.Transactions;

import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;
import java.util.*;

public abstract class Account {

    static int numAccount;
    double balance;
    private int accountID;
    private LocalDate dateOfCreation;
    private int accountType;
    private Map<LocalDate, Transactions> listOfTransactions = new LinkedHashMap<>();

    public Account() {
        numAccount += 1;
        this.accountID = numAccount;
        this.dateOfCreation = LocalDate.now();
    }

    public void addTransaction(Transactions t){
        listOfTransactions.put(LocalDate.now(), t);
    }

    public Map<LocalDate, Transactions> getTransactionsList(){
        return listOfTransactions;
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

    public int getAccountID() {
        return accountID;
    }

    public abstract String toString();

    public abstract int getAccountType();

    public LocalDate getDateOfCreation() {
        return this.dateOfCreation;
    }
}
