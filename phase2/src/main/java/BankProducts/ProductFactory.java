package BankProducts;


import java.io.Serializable;
public class ProductFactory implements Serializable {
    public BankProduct getBankProduct(int productType, int amount, int months) {
        if (productType == 1) {
            return new LongTermMortgage(amount, months);
        } else if (productType == 2) {
            return new ShortTermMortgage(amount, months);
        } else if (productType == 3) {
            return new HighRiskInvestment(amount);
        } else if (productType == 4) {
            return new LowRiskInvestment(amount);
        }
        return null;

    }
}
