package Actions;

import ATM.BankManager;
import ATM.User;
import Accounts.*;

import java.util.ArrayList;

public class NetTotal extends UserActions {

    public NetTotal(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method calculate and print out the user's net total by subtracting debt account balance from asset account balance.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        ArrayList<Account> accounts = currentUser.getAccountList();
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
