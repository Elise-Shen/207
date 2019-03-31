package Actions;
import ATM.*;
import Accounts.*;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.util.List;
import java.util.Locale;

public class WithdrawMoney extends Transactions {

    /**
     * The current account ID.
     */
    private int currentAccountID;

    private MonetaryAmount amountWithdrawn;
    /**
     * The CashStorage of the ATM that performs this action.
     */
    private CashStorage cashStorage;

    private Account currentAccount;

    /**
     * Construct a WithdrawMoney action instance.
     */
    public WithdrawMoney(int userID, BankManager bm, CashStorage cs) {
        super(userID, bm);
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
        List<Account> currentUserAccounts = currentUser.getAccountList();
        Keypad keyPad = new Keypad();
        while (!validInput) {
            ViewAccount.printAccounts(currentUserAccounts);
            int accountChoice = keyPad.getIntInput("\nPlease type in the ID of the account that you want " +
                    "to withdraw money from.");
            Account myAccount = currentUser.getAccount(accountChoice);
            if (myAccount == null || myAccount.getAccountType() == 3 || myAccount.getAccountType() == 4) {
                System.out.println("Invalid input. You can only withdraw money from Asset Accounts.");
            } else {
                validInput = true;
                currentAccountID = accountChoice;
                Account currentAccount = currentUser.getAccount(accountChoice);
                int cashWithdrawn = keyPad.getIntInput("Please enter the amount of money that you " +
                        "want to withdraw.");
                amountWithdrawn = createMoney(cashWithdrawn);
                boolean enoughBalance = currentAccount.decreaseCurrencyBalance(amountWithdrawn);
                boolean enoughCash = cashStorage.withdrawal(currentAccount.getPrimaryCurrency().toString(), cashWithdrawn);
                if (!enoughBalance || !enoughCash) {
                    System.out.println("Transaction failed.");
                }
            }
        }
    }

    public boolean executeWithdraw(Account account, int amount){
        currentAccount = account;
        currentAccountID = currentAccount.getAccountID();
        amountWithdrawn = currentAccount.createMoney(amount);
        return currentAccount.decreaseCurrencyBalance(amountWithdrawn);


    }

    public Account getCurrentAccount(){
        return currentAccount;
    }


    @Override
    public int getCurrentAccountID() {
        return currentAccountID;
    }

    public MonetaryAmount getAmountWithdrawn(){
        return amountWithdrawn;
    }

    @Override
    public String toString() {
        return "Withdrawal of " + amountWithdrawn /*+ "from account " + currentAccountID*/;
    }
}



