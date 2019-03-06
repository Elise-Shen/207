package Accounts;
import java.util.Date;

public class Saving extends AssetAccount {
    private int accountType;
    public Saving() {
        accountType = 2;
    }

    @Override
    public int getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Savings";
    }

    public void AddInterest(){
        balance = balance * (1 + 0.001);
    }
}
