package BankProducts;
import Accounts.*;
/**
 * random a num between 0-10
 * 0-7 interest rate 3%
 *
 * 7-10 interest rate 4%
 * with fixed month - 10 month
 */

public class LowRiskInvestment extends InvestmentProduct {
    public LowRiskInvestment(int amount,Account account){
        super(amount, 1,0, 0,account);
        Double min = 0.0; //  Set To Your Desired Min Value
        Double max = 10.0; //    Set To Your Desired Max Value
        double rate = 0.0;
        double x = (Math.random() * ((max - min) + 1)) + min;
        if (0<=x && x<7){
            rate = 0.03;
        }else{
            rate = 0.04;
        }
        setInterestRate(rate);
        setRisk(x);


    }
    @Override
    public int getProductType() {
        return 4;
    }
}
