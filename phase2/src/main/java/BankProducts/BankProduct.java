package BankProducts;
import java.io.Serializable;
public abstract class BankProduct implements Serializable{
    public int product_amount ;// borrowed amount for each type of mortgage
    public int product_month; // number of days borrowed
    public double interest_rate;


}
