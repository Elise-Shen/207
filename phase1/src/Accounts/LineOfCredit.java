package Accounts;
import java.util.Date;

public class LineOfCredit extends DebtAccount {

    /**
     * Creates a Line of Credit account.
     */
    public LineOfCredit() {
        super();
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
