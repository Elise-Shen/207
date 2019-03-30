package BankProducts;
import Accounts.*;

public class LongTermMortgage extends MortgageProduct {

    public LongTermMortgage(int amount, int months, Account account){
        super(amount, months, 0.06,account);// interest rate for each term
    }
    @Override
    public int getProductType() {
        return 1;
    }
    @Override
    public double getInterestRate(){return 0.06;};


}
