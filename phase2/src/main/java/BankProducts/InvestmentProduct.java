package BankProducts;

import Accounts.*;
import java.util.Random;

public abstract class InvestmentProduct extends BankProduct{
    private double risk;
    public InvestmentProduct(int amount, int months, double rate,double r) {
        product_amount = amount;
        product_month = months;
        interest_rate = rate;
        risk = r;
        setDateEnd(months);
    }
    public void do_investment(Account account){
        account.increaseBalance(product_amount);
    }
    public void return_revenue(Account account){
        account.decreaseBalance(product_amount*(1+product_month*interest_rate));
    }

    @Override
    public int getProductType() {
        return 0;
    }

    @Override
    public double getInterestRate(){return interest_rate;}

    public void setInterestRate(double rate){interest_rate = rate;}
    public void setRisk(double r){risk = r;}

}
