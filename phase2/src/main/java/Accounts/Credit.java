package Accounts;

import javax.money.MonetaryAmount;

public class Credit extends DebtAccount {

    /**
     * Interest Rate
     */
    private static double interestRate;

    /**
     * Creates a Credit Card Account.
     */
    public Credit(String currency) {
        super(currency);
        interestRate = 0.20;
    }

    @Override
    public int getAccountType() {
        return 3;
    }

    @Override
    public String toString() {
        return "Credit";
    }

    @Override
    public void addInterest() {
        MonetaryAmount newBalance = getCurrencyBalance().multiply(1 + interestRate);
        setCurrencyBalance(newBalance);
    }

    public static void setInterestRate(double rate){interestRate = rate;}

    public static double getInterestRate(){return interestRate;}
}
