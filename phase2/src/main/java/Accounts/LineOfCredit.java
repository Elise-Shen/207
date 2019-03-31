package Accounts;


import javax.money.MonetaryAmount;

public class LineOfCredit extends DebtAccount {

    /**
     * Interest Rate
     */
    private static double interestRate = 0.004;

    /**
     * Creates a Line of Credit account.
     */
    public LineOfCredit(String currency) {
        super(currency);
    }

    @Override
    public String toString() {
        return "Account ID " + getAccountID() + ": Line of Credit";
    }

    @Override
    public int getAccountType() {
        return 4;
    }

    @Override
    public void addInterest() {
        MonetaryAmount newBalance = getCurrencyBalance().multiply(1 + interestRate);
        setCurrencyBalance(newBalance);
    }

    public static void setInterestRate(double rate){interestRate = rate;}

    public static double getInterestRate(){return interestRate;}

}
