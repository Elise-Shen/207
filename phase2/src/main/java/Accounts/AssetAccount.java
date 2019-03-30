package Accounts;


public abstract class AssetAccount extends Account {
    /**
     * Creates an Asset Account.
     */
    public AssetAccount(String currency) {
        super(currency);
    }

    @Override
    public abstract int getAccountType();

    @Override
    public abstract String toString();
}
