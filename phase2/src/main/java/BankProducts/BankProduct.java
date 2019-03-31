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
import Accounts.*;
public abstract class BankProduct implements Serializable{
    public MonetaryAmount product_amount ;// borrowed amount for each type of mortgage
    public int product_month; // number of days borrowed
    public double interest_rate;
    public boolean is_finish = false;

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
    private int ownerID;
    protected Account account;


    public BankProduct(Account account){
        increaseNumProduct();
        this.productID = numProduct;
        this.dateCreation = LocalDate.now();
        this.account = account;
    }

    /*
    set the date the product ends
     */
    public void setDateEnd (int product_month){
        dateEnd = dateCreation.plusMonths(product_month);
    }
    /*
    get the date the proudct ends
     */
    public LocalDate getDateEnd(){return dateEnd;}

    /*
    increase the number of product created
     */
    public void increaseNumProduct(){
        numProduct += 1;
    }

    /*
    check if the product has ended
     */

    public boolean getIsFinished(){return is_finish;}

    /*
    create moneey of the currency type, prepare for furture usage
     */
    public MonetaryAmount createMoney(double amount){
        return account.createMoney(amount);
    }



















}
