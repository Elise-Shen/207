package Actions;

import ATM.*;
import Accounts.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class ViewAccount extends UserActions {

    private Account currentAccount;
    private User currentUser;
    private static ObservableList<Account> allAccounts = FXCollections.observableArrayList();

    public ViewAccount(int currentId, BankManager bankManager){
        super(currentId, bankManager);
    }

    public ObservableList<Account> getAllAccounts(){
        return allAccounts;
    }

    /**
     * This method ask the user to choose which account to view.
     * @return currentAccount is the account that the user is interested in at this time.
     */
    Account readCurrentAccount() {
        boolean validInput = false;
        int accountChoice;
        BankManager bankManager = getBankManager();
        currentUser = bankManager.getUser(getUserID());
        List<Account> currentUserAccounts = currentUser.getAccountList();
        while (!validInput) {
            printAccounts(currentUserAccounts);
            Keypad keyPad = new Keypad();
            accountChoice = keyPad.getIntInput("\nType in the ID of the account you want to view");
            currentAccount = currentUser.getAccount(accountChoice);
            if (currentAccount != null) {
                validInput = true;
            } else {
                System.out.println("Invalid Input. Please try again!");
            }
        }
        return currentAccount;
    }

    public static void printAccounts(List<Account> currentUserAccounts) {
        for (Account a : currentUserAccounts) {
            if(a != null){
                allAccounts.add(a);
                System.out.println(a.getAccountID() + " - " + a);
            }
        }
    }

    @Override
    public void execute() {}

    public User getCurrentUser(){
        return currentUser;
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }
}
