package Accounts;

public class AccountFactory {
    public Account getAccount(int accountType) {
        if (accountType == 1) {
            return new Chequing();
        } else if (accountType == 2) {
            return new Saving();
        } else if (accountType == 3) {
            return new Credit();
        } else if (accountType == 4) {
            return new LineOfCredit();
        }
        return null;
    }

}
