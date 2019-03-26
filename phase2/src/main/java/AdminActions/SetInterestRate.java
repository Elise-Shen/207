package AdminActions;
import ATM.BankManager;
import Accounts.*;
import ATM.Keypad;

public class SetInterestRate extends AdminAction{

    /**
     * Constructs a SetInterestRate Instance.
     */
    public SetInterestRate (BankManager bankManager) {
        super(bankManager);
    }

    /**
     * Set the interest rate of different types of account.
     */
    @Override
    public void execute() {
        printInterestTable();
        int choice = getAccountChoice();
        double newRate = getNewInterestRate();

        switch (choice) {
            case 1:
                Credit.setInterestRate(newRate);
                break;
            case 2:
                LineOfCredit.setInterestRate(newRate);
                break;
            case 3:
                Saving.setInterestRate(newRate);
        }

    }


    private void printInterestTable() {

        System.out.println("\nInterest Rate Table: " + "\n1 - Credit: " + Credit.getInterestRate() +
                 "\n2 - LineOfCredit: " + LineOfCredit.getInterestRate() +
                 "\n3 - SavingsAccount: " + Saving.getInterestRate());
    }

    private int getAccountChoice() {
        boolean valid = false;
        Keypad keyPad = new Keypad();
        int choice = 0;
        while (!valid) {
            String message = "Please type in an integer corresponding to one of the accounts displayed.";
            int input = keyPad.getIntInput(message);
            if (0 < input && input < 4) {
                choice = input;
                valid = true;
            } else {
                System.out.println("Invalid.");
            }
        }
        return choice;
    }

    private double getNewInterestRate() {
        Keypad keyPad = new Keypad();
        String message = "\nNew interest rate: ";
        return keyPad.getDoubleInput(message);
    }

}
