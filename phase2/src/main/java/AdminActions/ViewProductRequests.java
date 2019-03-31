package AdminActions;

import ATM.BankProductsEmployee;
import ATM.Keypad;

import java.util.*;

public class ViewProductRequests extends AdminAction {

    private Map<Integer, List<Integer>> productRequests;

    private final BankProductsEmployee bankProductsEmployee = getBankProductsEmployee();

    public ViewProductRequests(BankProductsEmployee bankProductsEmployee) {
        super(bankProductsEmployee);
        productRequests = bankProductsEmployee.getProductRequests();
    }

    @Override
    public void execute() {
        boolean exited = false;
        while (!exited) {
            if (!productRequests.isEmpty()) {
                int count = displayProductCreationRequests();
                boolean isValid = false;
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number of the request you wish to " +
                                "approve or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            handleProductCreationRequests(choice);
                            isValid = true;
                        } else if (choice == 0) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please try again");
                        }
                    } catch (InputMismatchException | NullPointerException ex) {
                        System.out.println("Invalid input. Please try again.");
                        ex.printStackTrace();
                    }
                }
            }
            exited = true;
        }//while loop end

        if (productRequests.isEmpty()) {
            System.out.println("\nThere are no account requests at the moment");
        } else {
            System.out.println("Exited");
        }
    }


    private int displayProductCreationRequests() {
        Iterator<Map.Entry<Integer, List<Integer>>> entries = productRequests.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> entry;
        Integer mapKey;
        int count = 0;
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            List<Integer> productStat = productRequests.get(mapKey);
            int accountID = productStat.get(0);
            int productType = productStat.get(1);
            int productAmount = productStat.get(2);
            int productLength = productStat.get(3);
            System.out.println("\n" + count + " - User " + mapKey + " requested a " +
                    bankProductsEmployee.getProductName(productType) + " in account " + accountID + " with amount " +
                    productAmount + " duration " + productLength + " month.");
            //keeps iterating until the last item
            //sets map-key to last item
        }
        return count;
    }


    private void handleProductCreationRequests(int choice) {
        Iterator<Map.Entry<Integer, List<Integer>>> entries = productRequests.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> entry;
        Integer mapKey = 0;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        List<Integer> productStat = productRequests.get(mapKey);
        int accountID = productStat.get(0);
        int productType = productStat.get(1);
        int productAmount = productStat.get(2);
        int productLength = productStat.get(3);
        bankProductsEmployee.createProduct(mapKey, accountID, productType, productAmount, productLength);
        productRequests.remove(mapKey);
    }

}
