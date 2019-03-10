package Actions;

import ATM.*;

import java.util.Scanner;

public class RequestAccount extends UserActions {
    private static final int CHEQUING = 1;
    private static final int SAVINGS = 2;
    private static final int CREDIT = 3;
    private static final int LINE_OF_CREDIT = 4;

    public RequestAccount(int userID, BankManager bm){
        super(userID, bm);
    }

    /**
     * This method ask the BankManager to create a new account.
     */
    @Override
    public void execute() {
        boolean userExited = false;
        int accountType = 0;
        boolean isValid = false;
        String typeString = null;
        BankManager bankManager = getBankManager();
        while (!isValid) {
            Scanner input = new Scanner(System.in);
            System.out.println("\nWhat type of account do you wish to create?");
            System.out.println("1 - Chequing");
            System.out.println("2 - Savings");
            System.out.println("3 - Credit");
            System.out.println("4 - Line of Credit");
            System.out.println("0 - Exit");
            int typeChoice = input.nextInt();
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
        if(accountType != 0) {
            bankManager.requestAccount(getUserID(), accountType);
            System.out.println("\nRequested an " + typeString + " account.");
        }else{System.out.println("Returning to previous page");}
    }
}
