package Accounts;
import java.util.Date;
import java.time.LocalDate;
public class AssetAccount extends Account {
    static int numAssetAccounts;

    public AssetAccount() {
        numAssetAccounts +=1;
    }
}
