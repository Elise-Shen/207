package Accounts;

public class Saving extends AssetAccount {

    /**
     * Creates a Savings Account.
     */
    public Saving() {
        super();
    }

    @Override
    public int getAccountType() {
        return 2;
    }

    @Override
    public String toString() {
        return "Savings";
    }

    /**
     * Make this savings account's balance increase by a factor of 0.1%.
     */
    public void AddInterest(){
        balance = balance * (1 + 0.001);
    }
}
