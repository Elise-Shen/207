package BankProducts;
import java.io.Serializable;

import Actions.Transactions;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
public abstract class BankProduct implements Serializable{
    public MonetaryAmount product_amount ;// borrowed amount for each type of mortgage
    public int product_month; // number of days borrowed
    public double interest_rate;

    private final int productID;
    private final LocalDate dateCreation;
    /**
     * the date that product ended
     */
    private LocalDate dateEnd;

    // overall
    /**
     * The total number of product created
     */
    private static int numProduct;
    private Map<LocalDate, Transactions> listOfTransactions = new LinkedHashMap<>();
    private int ownerID;

    public BankProduct(){
        increaseNumProduct();
        this.productID = numProduct;
        this.dateCreation = LocalDate.now();
    }
    public void setDateEnd (int product_month){
        dateEnd = dateCreation.plusMonths(product_month);
    }
    public void increaseNumProduct(){
        numProduct += 1;
    }

    public Map<LocalDate, Transactions> getTransactionsList(){
        return listOfTransactions;
    }
    public int getProductID() {
        return productID;
    }

    public void setOwnerID(int i){
        ownerID = i;
    }
    public int getOwnerID(){
        return ownerID;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }
    public LocalDate getDateEnd(){return dateEnd;}

    public double getInterestRate(){return 0;};
    public int getProductType(){return 0;};

    public MonetaryAmount createMoney(double amount){
        CurrencyUnit currencyUnit = Monetary.getCurrency(Locale.getDefault());
        return Money.of(amount, currencyUnit);
    }



}
