package BankProducts;

import Accounts.*;

public abstract class InvestmentProduct extends BankProduct{

    public InvestmentProduct(int amount, int months, double rate) {
        product_amount = amount;
        product_month = months;
        interest_rate = rate;
    }
    public void do_investment(Account account){
        account.increaseBalance(product_amount);
    }
    public void take_revenue(Account account){
        account.decreaseBalance(product_amount*(1+product_month*interest_rate));
    }

    public int getProductType() {
        return 0;
    }

}
