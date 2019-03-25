package Accounts;


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
}
