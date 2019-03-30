package BankProducts;
import Accounts.*;


import java.io.Serializable;
public class BankProductFactory implements Serializable {

    public BankProduct getBankProduct(int productType, int amount, int months,Account account) {
        if (productType == 1) {
            return new LongTermMortgage(amount, months,account);
        } else if (productType == 2) {
            return new ShortTermMortgage(amount, months, account);
        } else if (productType == 3) {
            return new HighRiskInvestment(amount, account);
        } else if (productType == 4) {
            return new LowRiskInvestment(amount, account);
        }
        return null;

    }
}
