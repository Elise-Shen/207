package AdminActions;

import ATM.BankProductsEmployee;
import ATM.Keypad;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
public class ViewProductRequests extends AdminAction {

    private final BankProductsEmployee bankProductsEmployee = getBankProductsEmployee();

    public ViewProductRequests(BankProductsEmployee bankProductsEmployee) {
        super(bankProductsEmployee);
    }

    @Override
    public void execute() {
        Map<Integer, Map<Integer, Map<Integer, List<Integer>>>> productRequests = bankProductsEmployee.getProductRequests();
        boolean exited = false;
        while (!exited) {
            if (!productRequests.isEmpty()) {
                int count = displayProductCreationRequests(productRequests);
                boolean isValid = false;
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number of the request you wish to " +
                                "approve or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            handleAccountCreationRequests(choice);
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

        if (accountRequests.isEmpty()) {
            System.out.println("\nThere are no account requests at the moment");
        } else {
            System.out.println("Exited");
        }
    }


    private int displayProductCreationRequests(Map<Integer, Map<Integer, Map<Integer, List<Integer>>>> productRequests) {
        Iterator<Map.Entry<Integer, Map<Integer, Map<Integer, List<Integer>>>>> entries = productRequests.entrySet().iterator();
        Map.Entry<Integer, Map<Integer, Map<Integer, List<Integer>>>> entry;
        Integer mapKey;
        int count = 0;
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            Map<Integer, Map<Integer, List<Integer>>> accountToProduct = productRequests.get(mapKey);
            Integer accountID = accountToProductIterator(accountToProduct);
            Map<Integer, List<Integer>> product = accountToProduct.get(accountID);
            Integer productType = productIterator(product);
            List<Integer> productStat = product.get(productType);
            System.out.println("\n" + count + " - User " + mapKey + " requested a " +
                    bankProductsEmployee.getAccountName(productType) + "in account " + accountID + "with amount " +
                    productStat.get(0) + "duration " + productStat.get(1) + "month.");
            //keeps iterating until the last item
            //sets map-key to last item
        }
        return count;
    }

    private Integer accountToProductIterator(Map<Integer, Map<Integer, List<Integer>>> map){
        Iterator<Map.Entry<Integer, Map<Integer, List<Integer>>>> entries = map.entrySet().iterator();
        Map.Entry<Integer, Map<Integer, List<Integer>>> entry;
        Integer mapKey = 0;
        while (entries.hasNext()){
            entry = entries.next();
            mapKey = entry.getKey();
        }
        return mapKey;
    }

    private Integer productIterator(Map<Integer, List<Integer>> map){
        Iterator<Map.Entry<Integer, List<Integer>>> entries = map.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> entry;
        Integer mapKey = 0;
        while (entries.hasNext()){
            entry = entries.next();
            mapKey = entry.getKey();
        }
        return mapKey;
    }

}**/