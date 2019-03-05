package Actions;

import Accounts.*;

public class ViewBalance extends UserActions {

    private Account account;

    public ViewBalance(Account account){
        this.account = account;
    }

    /**
     * This method print out the account balance.
     */
    @Override
    public void execute(){
        double balance = account.getBalance();
        int accountId = account.getID();
        System.out.println("Account id: " + accountId + " has balance " + balance);
    }
}
