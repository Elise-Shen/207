package Accounts;
import Actions.Transactions;

import java.time.LocalDate;
import java.util.*;

public abstract class Account {

    /**
     * The total number of accounts created.
     */
    private static int numAccount;

    /**
     * The balance of this account.
     */
    double balance;

    /**
     * The Id of this account.
     */
    private final int accountID;

    /**
     * The date of creation of this account.
     */
    private final LocalDate dateOfCreation;

    /**
     * The transaction record of this account.
     */
    private Map<LocalDate, Transactions> listOfTransactions = new LinkedHashMap<>();

    /**
     * Creates an account with unique ID.
     */
    public Account() {
        numAccount += 1;
        this.accountID = numAccount;
        this.dateOfCreation = LocalDate.now();
    }

    /**
     * Add a transaction to this account's transaction record.
     *
     * @param t the transaction to be added.
     */
    public void addTransaction(Transactions t){
        listOfTransactions.put(LocalDate.now(), t);
    }

    /**
     * Return the transaction record of this account.
     */
    public Map<LocalDate, Transactions> getTransactionsList(){
        return listOfTransactions;
    }

    /**
     * Return the balance of this account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Increase the balance of this account.
     *
     * @param money the amount of money added to the balance.
     */
    public void increaseBalance(double money) {
        this.balance += money;
    }

    /**
     * Decrease the balance of this account.
     *
     * @param money the amount of money reduced from the balance.
     */
    public void decreaseBalance(double money) {
        this.balance -= money;
    }

    /**
     * Return the ID of this account.
     */
    public int getAccountID() {
        return accountID;
    }

    @Override
    public abstract String toString();

    /**
     * Return an integer representing a specific account type.
     */
    public abstract int getAccountType();

    /**
     * Return the creation date of this account.
     */
    public LocalDate getDateOfCreation() {
        return this.dateOfCreation;
    }
}

