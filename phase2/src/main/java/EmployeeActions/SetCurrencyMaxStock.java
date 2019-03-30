package EmployeeActions;
import ATM.Keypad;
import ATM.CashStorage;
import java.util.InputMismatchException;


public class SetCurrencyMaxStock extends EmployeeAction {

    /**
     * The cash storage associated with this action.
     */
    private CashStorage cashStorage;

    /**
     * Constructs a SetCurrencyMaxStock instance.
     */
    public SetCurrencyMaxStock(CashStorage cashStorage) {
        this.cashStorage = cashStorage;
    }

    /**
     * Set maximum storage for a certain currency.
     */
    @Override
    public void execute() {
        String currencyType = getCurrencyType();
        int amount = getMaximumStorage();
        cashStorage.getCashStorage().get(currencyType).setMaxStock(amount);
    }


    /**
     * Return the currency type the employee wants to set maximum stock for.
     */
    private String getCurrencyType() {
        Keypad keyPad = new Keypad();
        boolean valid = false;
        String result = "";
        while (!valid) {
            result = keyPad.getStringInput("Please type in the currency type you " +
                    "want to set maximum stock for.");
            if (cashStorage.getCashStorage().containsKey(result)) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return result;
    }

    /**
     * Return the maximum storage the employee wants to set.
     */
    private int getMaximumStorage() {
        Keypad keyPad = new Keypad();
        boolean valid = false;
        int result = 0;
        while (!valid) {
            try {
                result = keyPad.getIntInput("Please enter the maximum stock you want to set.");
            } catch (InputMismatchException e) {
                //
            }
            if (result > 0) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return result;
    }
}
