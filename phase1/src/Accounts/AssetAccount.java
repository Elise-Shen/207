package Accounts;
import java.util.Date;
import java.time.LocalDate;
public class AssetAccount extends Account {
    static int numAssetAccounts;

    public AssetAccount(int type) {
        super(type);
        numAssetAccounts +=1;
    }
}
