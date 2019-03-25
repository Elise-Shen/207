package Accounts;

public class AssetAccount extends Account {

    /**
     * Creates an Asset Account.
     */
    public AssetAccount(String currency) {
        super(currency);
    }

    @Override
    public int getAccountType() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
