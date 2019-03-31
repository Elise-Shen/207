package BankProducts;

import Accounts.*;

public abstract class MortgageProduct extends BankProduct{
    /*
    create mortage product
     */
    public MortgageProduct(int amount, int months, double rate, Account account) {
        super(account);
        product_amount = createMoney(amount);
        product_month = months;
        interest_rate = rate;
        setDateEnd(months);

    }
    /*
    give loan to certain account
    increase balance of the account
     */
    public void giveLoan(){
        this.account.increaseCurrencyBalance(product_amount);
    }

    /*
    the account pay back the loan
    which reduce the balance and give a message if the balance become negative
     */
    public void returnLoan(){
        boolean status = this.account.decreaseCurrencyBalance(product_amount.multiply(1+product_month*interest_rate));
        if (status == false){
            System.out.println("Aware! Your balance in this account is not enough!");
        } else { is_finish=true; }
    }
    public abstract int getLimit();
}



