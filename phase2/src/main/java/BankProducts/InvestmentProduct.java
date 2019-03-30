package BankProducts;

import Accounts.*;
import java.util.Random;

public abstract class InvestmentProduct extends BankProduct{
    private double risk;

    public InvestmentProduct(int amount, int months, double rate,double r, Account account) {
        super(account);
        product_amount = createMoney(amount);
        product_month = months;
        interest_rate = rate;
        risk = r;
        setDateEnd(months);
    }
    public void do_investment(){
        this.account.increaseCurrencyBalance(product_amount);
    }
    public void returnRevenue(){
        this.account.decreaseCurrencyBalance(product_amount.multiply(1+product_month*interest_rate));
        is_finish = true;
    }

    @Override
    public abstract int getProductType();

    @Override
    public double getInterestRate(){return interest_rate;}

    public void setInterestRate(double rate){interest_rate = rate;}
    public void setRisk(double r){risk = r;}

}
