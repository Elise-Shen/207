package Accounts;
import java.util.Date;

public class Saving extends AssetAccount {
    int accountType;
    public Saving(Date creationDate) {
        accountType = 2;
    }

    @Override
    public int getAccountType() {
        return accountType;
    }

    public void AddInterest(){
        balance = balance * (1 + 0.001);
    }
}
