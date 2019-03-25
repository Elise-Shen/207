package Accounts;

import java.io.Serializable;

public class AccountFactory implements Serializable {
    private static final long serialVersionUID = 5062984395789419811L;

    /**
     * Return a new account according to accountType.
     *
     * @param accountType the integer corresponding to a specific type of account.
     */

    public Account getAccount(int accountType, String currency) {
        if (accountType == 1) {
            return new Chequing(currency);
        } else if (accountType == 2) {
            return new Saving(currency);
        } else if (accountType == 3) {
            return new Credit(currency);
        } else if (accountType == 4) {
            return new LineOfCredit(currency);
        }
        return null;
    }

}
