package BankProducts;
import Accounts.*;

public class ShortTermMortgage extends MortgageProduct {

    private final int MAXTERM = 12;

    public ShortTermMortgage(int amount, int months, Account account){
        super(amount, months, 0.04,account);// interest rate for each term
    }
    @Override
    public int getProductType() {
        return 2;
    }

    @Override
    public int getLimit(){ return MAXTERM; }
}
