package Actions;
import ATM.*;
import Accounts.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WithdrawMoney extends Transactions {

    /**
     * The CashStorage of the ATM that performs this action.
     */
    CashStorage cashStorage;
    public WithdrawMoney(int userid, BankManager bm, CashStorage cs) {
        super(userid, bm);
        this.cashStorage = cs;
    }

    /**
     * Withdraw money from the chosen account of current user, if applicable.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        boolean validInput = false;
        ArrayList<Account> currentUserAccounts = currentUser.getAccountList();
        while (!validInput) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nPlease type in the ID of the account that you want to withdraw money from.");
            for (Account a : currentUserAccounts) {
                System.out.println(a.getAccountID() + " - " + a.toString());
            }
            int accountChoice = input.nextInt();
            Account myAccount = currentUser.getAccount(accountChoice);
            if (myAccount != null && myAccount.getAccountType() != 3 && myAccount.getAccountType() != 4) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
            Account currentAccount = currentUser.getAccount(accountChoice);
            boolean validInput1 = false;
            while (!validInput1) {
                Scanner input1 = new Scanner(System.in);
                System.out.println("Please enter the amount of money that you want to withdraw.");
                double balance = currentAccount.getBalance();
                int cashWithdrawn = input1.nextInt();
                if (currentAccount.getAccountType() == 2 && (balance - cashWithdrawn) > -1) {
                    boolean withdrawn = cashStorage.withdrawal(cashWithdrawn);
                    if (withdrawn) {
                        currentAccount.decreaseBalance(cashWithdrawn);
                        validInput1 = true;
                    }
                } else if (currentAccount.getAccountType() == 1) {
                    if (balance > -1 && balance - cashWithdrawn > -101) {
                        boolean withdrawn = cashStorage.withdrawal(cashWithdrawn);
                        if (withdrawn) {
                            currentAccount.decreaseBalance(cashWithdrawn);
                            validInput1 = true;
                        }
                    } else {
                        System.out.println("Insufficient balance. Please enter again!");
                    }
                }
            }
        }
    }
}


