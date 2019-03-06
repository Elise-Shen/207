package Accounts;

public class Credit extends DebtAccount {

    int accountType;
    public Credit() {
        accountType = 3;
    }

    @Override
    public int getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Credit";
    }
}
