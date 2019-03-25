package Accounts;


import javax.money.MonetaryAmount;

public class LineOfCredit extends DebtAccount {

    /**
     * Creates a Line of Credit account.
     */
    public LineOfCredit(String currency) {
        super(currency);
    }

    @Override
    public String toString() {
        return "Line of Credit";
    }

    @Override
    public int getAccountType() {
        return 4;
    }

    @Override
    public void addInterest() {
        MonetaryAmount newBalance = getCurrencyBalance().multiply(getInterestRate());
        setCurrencyBalance(newBalance);
    }
}
