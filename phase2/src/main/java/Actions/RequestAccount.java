package Actions;

import ATM.*;

import java.time.*;

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
        int currentCount = currentUser.getCount();
        LocalTime currentTime = LocalTime.now();
        LocalTime countResetTime = LocalTime.MIDNIGHT;
        if(currentTime == countResetTime){
            currentUser.resetCount();
        }
        if(currentCount < LIMIT ) {
            int accountType = 0;
            boolean isValid = false;
            String typeString = null;
            while (!isValid) {

                int typeChoice = keyPad.getIntInput("\nWhat type of account do you wish to create?" +
                        "\n1 - Chequing\n2 - Savings\n3 - Credit\n4 - Line of Credit\n0 - Exit");
                if (typeChoice <= 4 && typeChoice >= 0) {
                    accountType = typeChoice;
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please try again!");
                }
            }
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
            if (accountType != 0) {
                currentUser.incrementCount();
                bankManager.requestAccount(getUserID(), accountType);
                System.out.println("\nRequested an " + typeString + " account.");
            } else {
                System.out.println("Returning to previous page");
            }
        }else{System.out.println("Reached Daily Account Request Limit");}
    }//end execute
}
