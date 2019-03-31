package BankProducts;

import Accounts.*;
import java.util.Random;

public abstract class InvestmentProduct extends BankProduct{
    private double risk;

    /*
    initialize the investment product
     */
    public InvestmentProduct(int amount, int months, double rate,double r, Account account) {
        super(account);
        product_amount = createMoney(amount);
        product_month = months;
        interest_rate = rate;
        risk = r;
        setDateEnd(months);
    }

    /*
    remove the money used to invest from account
    show a warning message if the balance become negative
     */
    public boolean doInvestment(){
        boolean status = this.account.decreaseCurrencyBalance(product_amount);
        if (status == false){
            System.out.println("Aware! Your balance in this account is negative!");
        }
        return status;
    }
    /*
    return the revenue of the investment product
     */
    public void returnRevenue(){
        this.account.increaseCurrencyBalance(product_amount.multiply(1+product_month*interest_rate));
        is_finish = true;
    }


    public void setInterestRate(double rate){interest_rate = rate;}
    public void setRisk(double r){risk = r;}

}
