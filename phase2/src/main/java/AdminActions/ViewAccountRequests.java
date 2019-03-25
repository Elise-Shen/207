package AdminActions;

import ATM.BankManager;
import ATM.Keypad;

import java.util.*;

public class ViewAccountRequests extends AdminAction {
    private final BankManager bankManager = getBankManager();

    public ViewAccountRequests(BankManager bankManager){
        super(bankManager);
    }
    public void execute(){
        Map<Integer, Map<String, Integer>> accountRequests = bankManager.getAccountRequests();
        boolean exited = false;
        while (!exited) {
            if (!accountRequests.isEmpty()) {
                int count = displayAccountCreationRequests();
                boolean isValid = false;
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number of the request you wish to " +
                                "approve or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            handleAccountCreationRequests(choice);
                            isValid = true;
                        }else if(choice == 0){
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }
                    } catch (InputMismatchException | NullPointerException ex) {
                        System.out.println("Invalid input. Please try again.");
                        ex.printStackTrace();
                    }
                }
            }exited = true;
        }//while loop end

        if(accountRequests.isEmpty()) {
            System.out.println("\nThere are no account requests at the moment");
        }else{
            System.out.println("Exited");
        }

    }


    /**
     * Display all the account creation requests.
     */
    private int displayAccountCreationRequests() {
        Map<Integer, Map<String, Integer>> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<Integer, Map<String, Integer>>> entries = accountRequests.entrySet().iterator();
        Map.Entry<Integer, Map<String, Integer>> entry;
        Integer mapKey;
        int count = 0;
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            Map<String, Integer> value = accountRequests.get(mapKey);
            Integer accountType =value.get(mapIterator(value));
            System.out.println("\n" + count + " - User " + mapKey + " requested a " +
                    bankManager.getAccountName(accountType) + " account.");
            //keeps iterating until the last item
            //sets map-key to last item
        }
        return count;
    }

    /**
     *
     * @param map that stores Account's currency, and account type
     * @return the map key, account currency
     */
    private String mapIterator(Map<String, Integer> map){
        Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
        Map.Entry<String, Integer> entry;
        String mapKey = "";
        while (entries.hasNext()){
            entry = entries.next();
            mapKey = entry.getKey();
        }

        return mapKey;
    }

    /**
     * Handles the account creation requests.
     */
    private void handleAccountCreationRequests(int choice) {
        Map<Integer, Map<String, Integer>> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<Integer, Map<String, Integer>>> entries = accountRequests.entrySet().iterator();
        Map.Entry<Integer, Map<String, Integer>> entry;
        Integer mapKey = 0;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        Map<String, Integer> value = accountRequests.get(mapKey);
        String currency = mapIterator(value);
        int accountType = accountRequests.get(mapKey).get(currency);
        bankManager.createAccount(mapKey, accountType, currency);
        bankManager.getAccountRequests().remove(mapKey);
    }
}
