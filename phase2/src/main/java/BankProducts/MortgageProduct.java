package BankProducts;

import Accounts.*;

public abstract class MortgageProduct extends BankProduct{

    public MortgageProduct(int amount, int months, double rate) {
        product_amount = amount;
        product_month = months;
        interest_rate = rate;
        setDateEnd(months);

    }
    public void giveLoan(Account account){
        account.increaseBalance(product_amount);
    }
    public void returnloan(Account account){
        account.decreaseBalance(product_amount*(1+product_month*interest_rate));
    }
    @Override
    public int getProductType() {
        return 0;
    }

    @Override
    public double getInterestRate(){return interest_rate;}

}
