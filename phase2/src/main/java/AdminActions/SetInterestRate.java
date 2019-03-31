package AdminActions;
import ATM.BankManager;
import Accounts.*;
import ATM.Keypad;
/*
public class SetInterestRate extends AdminAction{

    /**
     * Constructs a SetInterestRate Instance.
     *
    public SetInterestRate (BankManager bankManager) {
        super(bankManager);
    }

    /**
     * Set the interest rate of different types of account.
     *
    @Override
    public void execute() {
        printInterestTable();
        int choice = getAccountChoice();
        double newRate = getNewInterestRate();
        switch (choice) {
            case 2:
                Saving.setInterestRate(newRate);
                break;
            case 3:
                Credit.setInterestRate(newRate);
                break;
            case 4:
                LineOfCredit.setInterestRate(newRate);
        }
        System.out.println("Set new interest rate " + newRate + " to " + getBankManager().getAccountName(choice));
    }

    private void printInterestTable() {
        String message = getBankManager().getInterestAccounts();
        System.out.println(message);
    }

    private int getAccountChoice() {
        boolean valid = false;
        Keypad keyPad = new Keypad();
        int choice = 0;
        while (!valid) {
            String message = "Please type in an integer corresponding to one of the account types displayed.";
            int input = keyPad.getIntInput(message);
            if (1 < input && input < 5) {
                choice = input;
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter again.");
            }
        }
        return choice;
    }

    private double getNewInterestRate() {
        Keypad keyPad = new Keypad();
        String message = "\nNew interest rate: ";
        return keyPad.getDoubleInput(message);
    }

}*/
