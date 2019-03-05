package Actions;

import ATM.User;
import Accounts.*;

import java.util.ArrayList;

public class NetTotal extends UserActions {

    private User user;

    public NetTotal(User user){
        this.user = user;
    }

    /**
     * This method calculate and print out the user's net total by subtracting debt account balance from asset account balance.
     */
    @Override
    public void execute() {
        ArrayList<Account> accounts = user.getAccounts();
        int netTotal = 0;
        for(Account account: accounts){
            if (account instanceof AssetAccount) {
                netTotal += account.getBalance();
            }
            else if (account instanceof DebtAccount){
                netTotal -= account.getBalance();
            }
        }
        System.out.println("The net total is: " + netTotal);
    }
}
