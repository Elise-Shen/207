package AdminActions;

import ATM.BankManager;
import ATM.Keypad;

import java.util.*;

public class ViewAccountRequests extends AdminAction {

<<<<<<< HEAD
=======
    int mapKey = 0;
    int count = 0;

>>>>>>> c42a3c5f01523d185fecc5bddeb0383c40745d75
    public ViewAccountRequests(BankManager bankManager){
        super(bankManager);
    }
    public void execute(){
        BankManager bankManager = getBankManager();
        Map<Integer, Integer> accountRequests = bankManager.getAccountRequests();
        boolean exited = false;
        while (!exited) {
            if (!accountRequests.isEmpty()) {
<<<<<<< HEAD
                int count = displayAccountCreationRequests(bankManager);
=======
                Iterator<Map.Entry<Integer, Integer>> entries = accountRequests.entrySet().iterator();
                Iterator<Map.Entry<Integer, Integer>> entries2 = accountRequests.entrySet().iterator();

                Map.Entry<Integer, Integer> entry;
                mapKey = 0;
                count = 0;

                System.out.println("\nCurrent Requests");
                while (entries.hasNext()) {
                    count++;
                    entry = entries.next();
                    mapKey = entry.getKey();
                    System.out.println("\n" + count + " - User " + mapKey + " requested a " +
                            bankManager.getAccountName(accountRequests.get(mapKey)) + " account.");
                    //keeps iterating until the last item
                    //sets map-key to last item
                }

>>>>>>> c42a3c5f01523d185fecc5bddeb0383c40745d75
                boolean isValid = false;
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number of the request you wish to " +
                                "approve or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            handleAccountCreationRequests(bankManager, choice);
                            isValid = true;
                        }else if(choice == 0){
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }
                    } catch (InputMismatchException | NullPointerException ex) {
                        System.out.println("Invalid input. Please try again.");
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

<<<<<<< HEAD
    /**
     * Display all the account creation requests.
     */
    private int displayAccountCreationRequests(BankManager bankManager) {
        Map<Integer, Integer> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<Integer, Integer>> entries = accountRequests.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey;
        int count = 0;
=======

    private void ListRequests(Iterator<Map.Entry<Integer, Integer>> entries){
        BankManager bankManager = getBankManager();
        Map<Integer, Integer> accountRequests = bankManager.getAccountRequests();
        Map.Entry<Integer, Integer> entry;

>>>>>>> c42a3c5f01523d185fecc5bddeb0383c40745d75
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            System.out.println("\n" + count + " - User " + mapKey + " requested a " +
                    bankManager.getAccountName(accountRequests.get(mapKey)) + " account.");
            //keeps iterating until the last item
            //sets map-key to last item
        }
<<<<<<< HEAD
        return count;
    }

    /**
     * Handles the account creation requests.
     */
    private void handleAccountCreationRequests(BankManager bankManager, int choice) {
        Map<Integer, Integer> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<Integer, Integer>> entries = accountRequests.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey = 0;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        bankManager.createAccount(mapKey, accountRequests.get(mapKey));
        bankManager.getAccountRequests().remove(mapKey);
    }

=======

    }
>>>>>>> c42a3c5f01523d185fecc5bddeb0383c40745d75
}
