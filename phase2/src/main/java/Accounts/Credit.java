package Accounts;

import javax.money.MonetaryAmount;

public class Credit extends DebtAccount {

    /**
     * Creates a Credit Card Account.
     */
    public Credit(String currency) {
        super(currency);
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
        MonetaryAmount newBalance = getCurrencyBalance().multiply(getInterestRate());
        setCurrencyBalance(newBalance);
    }

}
