package Actions;

import ATM.BankManager;
import Accounts.*;

import java.util.Scanner;

public class ViewBalance extends UserActions {

    private Account account;
    private final int CHEQUING = 1;
    private final int SAVING = 2;
    private final int CREDIT = 3;
    private final int LINEOFCREDIT = 4;

    public ViewBalance(int currentId, BankManager bankManager){
        super(currentId, bankManager);
        //this.account = account;
    }

    /**
     * This method print out the account balance.
     */
    @Override
    public void execute(){
        BankManager bankManager = getBankManager();
        int currentUser = getUserID();
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhich account's balance do you want to view?");
        System.out.println("1 - Chequing");
        System.out.println("2 - Savings");
        System.out.println("3 - Credit");
        System.out.println("4 - Line of Credit");
        int accountChoice = input.nextInt();

        switch (accountChoice){
            case CHEQUING:
                account = chooseChequingAccount();
                //account = bankManager.getUser(currentUser).getAccount();
                break;
            case SAVING:
                account = chooseSavingsAccount();
        }

        double balance = account.getBalance();
        int accountId = account.getID();
        System.out.println("Account id: " + accountId + " has balance " + balance);
        System.out.println("Working");
    }

    public Account chooseChequingAccount(){

        //if number of chequing accounts >= 2
        //then ask them which account they want to choose
        //return the account
        //else, return the only account
        return null;//placeholder
    }

    public Account chooseSavingsAccount(){
        //same idea as chooseChequingAccount
        return null;
    }
}
