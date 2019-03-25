package Accounts;

public class Saving extends AssetAccount {

    /**
     * Creates a Savings Account.
     */
    public Saving(String currency) {
        super(currency);
    }

    @Override
    public int getAccountType() {
        return 2;
    }

    @Override
    public String toString() {
        return "Savings";
    }

    @Override
    public void addInterest(){
        balance = balance * (1 + 0.001);
    }
}
