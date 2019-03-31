package BankProducts;
import Accounts.*;

/**
 * random a num between 0-10
 * 0-3 interest rate 20%
 * 3-6 interest rate 3%
 * 6-10 interest rate -10%
 * with fixed month - 1 month
 */

public class HighRiskInvestment extends InvestmentProduct {
    public HighRiskInvestment(int amount,Account account){
        super(amount, 1,0, 0,account);
        Double min = 0.0; //  Set To Your Desired Min Value
        Double max = 10.0; //    Set To Your Desired Max Value
        double rate = 0.0;
        double x = (Math.random() * ((max - min) + 1)) + min;
        if (0<=x && x<3){
            rate = 0.2;
        }else{
            rate = 0.01;
        }
        setInterestRate(rate);
        setRisk(x);

    }
    @Override
    public int getProductType() {
        return 3;
    }
}
