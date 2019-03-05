package Accounts;
import java.util.Date;

public class Saving extends AssetAccount {

    public Saving(Date creationDate) {
        super(creationDate);
    }

    public void AddInterest(){
        balance = balance * (1 + 0.001);
    }
}
