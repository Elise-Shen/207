package BankProducts;

public class ShortTermMortgage extends MortgageProduct {
    public ShortTermMortgage(int amount, int months){
        super(amount, months, 0.04);// interest rate for each term
    }
    @Override
    public int getProductType() {
        return 2;
    }
}
