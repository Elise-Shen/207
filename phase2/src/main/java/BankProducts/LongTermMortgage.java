package BankProducts;
import Accounts.*;

public class LongTermMortgage extends MortgageProduct {

    private final int MINTERM = 12;

    /*
    create Long Term Mortgage product,
    with fixed rate
     */
    public LongTermMortgage(int amount, int months, Account account){
        super(amount, months, 0.06,account);// interest rate for each term
    }


    /*
    the minimum length of taking mortgage is 12 months
     */
    @Override
    public int getLimit(){ return MINTERM; }
}
