package Actions;

import ATM.*;

import java.time.*;
import java.util.Currency;
import java.util.Set;

public class RequestAccount extends UserActions {
    private static final int CHEQUING = 1;
    private static final int SAVINGS = 2;
    private static final int CREDIT = 3;
    private static final int LINE_OF_CREDIT = 4;

    private static final int LIMIT = 1;

    public RequestAccount(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method ask the BankManager to create a new account.
     */
    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        User currentUser = bankManager.getUser(getUserID());
        LocalTime currentTime = LocalTime.now();
        LocalTime countResetTime = LocalTime.MIDNIGHT;
        if(currentTime == countResetTime){
            currentUser.resetCount();
        }
        if(currentUser.getCount() < LIMIT ) {
            int accountType = getAccountType();
            String currency = getCurrency();
            if (accountType != 0) {
                if (getWhetherJoint() == 1) {
                    int otherUser = getOtherUser(bankManager.getUserList().size());
                    bankManager.getUser(otherUser).incrementCount();
                    bankManager.requestAccount(getUserID(), otherUser, accountType, currency);
                }
                currentUser.incrementCount();
                bankManager.requestAccount(getUserID(), accountType, currency);
                System.out.println("\nRequested a " + getTypeString(accountType) + " account.");
            } else {
                System.out.println("Returning to previous page");
            }
        } else {System.out.println("Reached Daily Account Request Limit");}
    }

    private int getAccountType() {
        boolean isValid = false;
        int typeChoice = 0;
        while (!isValid) {
            Keypad keyPad = new Keypad();
            typeChoice = keyPad.getIntInput("\nWhat type of account do you wish to create?" +
                    "\n1 - Chequing\n2 - Savings\n3 - Credit\n4 - Line of Credit\n0 - Exit");
            if (typeChoice <= 4 && typeChoice >= 0) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
        }
        return typeChoice;
    }

    private String getCurrency() {
        String accountCurrency = "";
        boolean isValid = false;
        while(!isValid){
            Keypad keypad = new Keypad();
            String currencyChoice = keypad.getStringInput("\nChoose the currency. \nMust follow ISO 4217 format");
            Set<Currency> currencyCodes = Currency.getAvailableCurrencies();
            try {
                if (currencyCodes.contains(Currency.getInstance(currencyChoice))) {
                    accountCurrency = currencyChoice;
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            }catch(IllegalArgumentException ex){
                ex.printStackTrace();
                System.out.println("Invalid input. Please try again");
            }
        }
        return accountCurrency;
    }

    private String getTypeString(int accountType) {
        String typeString = null;
        switch (accountType) {
            case CHEQUING:
                typeString = "Chequing";
                break;
            case SAVINGS:
                typeString = "Saving";
                break;
            case CREDIT:
                typeString = "Credit";
                break;
            case LINE_OF_CREDIT:
                typeString = "Line of Credit";
                break;
            case 0:
                break;
        }
        return typeString;
    }

    private int getWhetherJoint() {
        boolean isValid = false;
        int typeChoice = 0;
        while (!isValid) {
            Keypad keyPad = new Keypad();
            typeChoice = keyPad.getIntInput("\nDo you want to make it a joint account?\n" +
                    "1 - Yes\n2 - No");
            if (typeChoice <= 2 && typeChoice >= 1) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
        }
        return typeChoice;
    }

    private int getOtherUser(int maximumUserID) {
        boolean isValid = false;
        int userChoice;
        int result = 0;
        while (!isValid) {
            Keypad keyPad = new Keypad();
            userChoice = keyPad.getIntInput("\nPlease enter the ID of the user you wish to share this " +
                    "account with.");
            if (1 <= userChoice && userChoice <= maximumUserID && userChoice != getUserID()) {
                result = userChoice;
                isValid = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return result;
    }
}

//Add choice to choose currency type