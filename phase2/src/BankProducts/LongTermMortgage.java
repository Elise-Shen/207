package BankProducts;

public class LongTermMortgage extends MortgageProduct {

    public LongTermMortgage(int amount, int months){
        super(amount, months, 0.06);// interest rate for each term
    }
    @Override
    public int getProductType() {
        return 1;
    }


}
