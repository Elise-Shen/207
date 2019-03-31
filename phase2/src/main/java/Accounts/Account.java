package Accounts;
import Actions.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Account implements Serializable {

    private static final long serialVersionUID = -4907187760305421196L;

    /**
     * The total number of accounts created.
     */
    public static int numAccount;

    /**
     * The balance of this account.
     */
    double balance;
    private CurrencyUnit primaryCurrency;
    private MonetaryAmount currencyBalance;

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

    private static ObservableMap<LocalDate, Transactions> observableListOfTransactions = FXCollections.observableHashMap();

    /**
     * Owner's user ID
     */
    private List<Integer> ownerID;


    /**
     * Creates an account with unique ID.
     */

    public Account(String currency) {
        //numAccount += 1;
        incrementNumAccount();
        this.accountID = numAccount;
        this.dateOfCreation = LocalDate.now();

        setPrimaryCurrency(currency);
        this.currencyBalance = Money.of(0, this.primaryCurrency);
        ownerID = new ArrayList<>();
    }

    public void setPrimaryCurrency(String unit){
        this.primaryCurrency = Monetary.getCurrency(unit);
    }

    private void incrementNumAccount(){
        numAccount += 1;
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

    public ObservableMap<LocalDate, Transactions> getObservableListOfTransactions(){
        return observableListOfTransactions;
    }

    public static void readListOfTransactions(Map<LocalDate, Transactions> map){
        observableListOfTransactions.putAll(map);
    }

    /**
     * Return the balance of this account.
     */
    public double getBalance() {
        return this.balance;
    }

    public MonetaryAmount createMoney(double amount){
        return Money.of(amount, primaryCurrency);
    }

    public MonetaryAmount getCurrencyBalance(){
        return this.currencyBalance;
    }

    public void increaseCurrencyBalance(MonetaryAmount amount){
        this.currencyBalance = this.currencyBalance.add(amount);
    }

    public boolean decreaseCurrencyBalance(MonetaryAmount amount){
        this.currencyBalance = this.currencyBalance.subtract(amount);
        return true;
    }

    void setCurrencyBalance(MonetaryAmount money) {
        this.currencyBalance = money;
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

    public void setOwnerID(int i){
        ownerID.add(i);
    }

    public StringJoiner getOwnerID(){
        StringJoiner sj = new StringJoiner(", ");
        for (Integer id : ownerID) {
            sj.add(id.toString());
        }
        return sj;
    }

    /**
     * Return the creation date of this account.
     */
    public LocalDate getDateOfCreation() {
        return this.dateOfCreation;
    }

    /**
     * Add interest to this account.
     */
    public void addInterest() {}

    public CurrencyUnit getPrimaryCurrency(){
        return this.primaryCurrency;
    }

}

