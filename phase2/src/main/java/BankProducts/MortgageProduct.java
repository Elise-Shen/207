package BankProducts;

import Accounts.*;

public abstract class MortgageProduct extends BankProduct{

    public MortgageProduct(int amount, int months, double rate, Account account) {
        super(account);
        product_amount = createMoney(amount);
        product_month = months;
        interest_rate = rate;
        setDateEnd(months);

    }
    public void giveLoan(){
        this.account.increaseCurrencyBalance(product_amount);
    }
    public void returnloan(){
        this.account.decreaseCurrencyBalance(product_amount.multiply(1+product_month*interest_rate));
        is_finish=true;
    }
    @Override
    public int getProductType() {
        return 0;
    }

    @Override
    public double getInterestRate(){return interest_rate;}

}
