package AdminActions;

import ATM.BankManager;
import ATM.Keypad;
import Actions.Transactions;

import java.util.*;

public class ViewUndoTransactRequests extends AdminAction{
    private final BankManager bankManager = getBankManager();

    public ViewUndoTransactRequests(BankManager bankManager){
        super(bankManager);
    }

    @Override
    public void execute() {
        //Map<Integer, Transactions> undoRequests = bankManager.getUndoTransactionRequest();
        Map<Integer, List<Transactions>> undoRequests = bankManager.getUndoTransactionRequest();
        boolean exited = false;
        while(!exited){
            if(!undoRequests.isEmpty()){
                int count = displayUndoTransactionRequests();
                boolean isValid = false;
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number corresponding to a user, " +
                                "or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            handleUndoTransactionRequests(choice);
                            isValid = true;
                        }else if(choice == 0){
                            break;
                        }else {
                            System.out.println("Invalid input. Please try again");
                        }
                    } catch (InputMismatchException e) {// | NullPointerException ex) {
                        System.out.println("null Invalid input. Please try again.");
                    }
                }
            }exited = true;
        }//while loop end

        if(undoRequests.isEmpty()) {
            System.out.println("\nThere are no undo transaction requests at the moment");
        }else{
            System.out.println("Exited");
        }
    }

    /**
     * Display all the account creation requests.
     */
    int displayUndoTransactionRequests() {
        Map<Integer, List<Transactions>> undoRequests = bankManager.getUndoTransactionRequest();
        Iterator<Map.Entry<Integer, List<Transactions>>> entries = undoRequests.entrySet().iterator();
        Map.Entry<Integer, List<Transactions>> entry;
        Integer mapKey;
        int count = 0;
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            System.out.println("\n" + count + " - User " + mapKey + " requested to undo" +
                    " their previous transaction(s): " + getTransactions(undoRequests.get(mapKey)));
            //keeps iterating until the last item
            //sets map-key to last item
        }
        return count;
    }

    private void handleUndoTransactionRequests(int choice) {
        Map<Integer, List<Transactions>> undoRequests = bankManager.getUndoTransactionRequest();
        Iterator<Map.Entry<Integer, List<Transactions>>> entries = undoRequests.entrySet().iterator();
        Map.Entry<Integer, List<Transactions>> entry;
        Integer mapKey = 0;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        int n = getNumOfTransactions(undoRequests.get(mapKey).size());
        for (int i = 0; i < n; i++) {
            bankManager.undoTransaction(undoRequests.get(mapKey).get(i));
        }
        if (n == undoRequests.get(mapKey).size()) {
            bankManager.getUndoTransactionRequest().remove(mapKey);
        } else {
            bankManager.getUndoTransactionRequest().get(mapKey).subList(0, n).clear();
        }
    }

    /**
     * Prompt the bank manager to choose the number of transactions to undo for some user.
     */
    private int getNumOfTransactions(int maximal) {
        Keypad keyPad = new Keypad();
        boolean valid = false;
        int result = 0;
        while (!valid) {
            int numChosen = keyPad.getIntInput("Enter the number of" +
                    " transactions you wish to undo for this user.");
            if (numChosen <= maximal) {
                result = numChosen;
                valid = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return result;
    }

    /**
     * Return all the transactions that the user wants to undo.
     */
    private StringJoiner getTransactions(List<Transactions> transactions) {
        StringJoiner sj = new StringJoiner("; ");
        for (Transactions t : transactions) {
            sj.add(t.toString());
        }
        return sj;
    }







    /**
     * Display all the account creation requests.
     *
    private int displayUndoTransactionRequests() {
        Map<Integer, Transactions> undoRequests = bankManager.getUndoTransactionRequest();
        Iterator<Map.Entry<Integer, Transactions>> entries = undoRequests.entrySet().iterator();
        Map.Entry<Integer, Transactions> entry;
        Integer mapKey;
        int count = 0;
        System.out.println("\nCurrent Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            System.out.println("\n" + count + " - User " + mapKey + " requested to undo" +
                    " their previous transaction(s): " + undoRequests.get(mapKey));
            //keeps iterating until the last item
            //sets map-key to last item
        }
        return count;
    }*/


    /**
     * Handles the account creation requests.
     *
    private void handleUndoTransactionRequests(int choice) {
        Map<Integer, Transactions> undoRequests = bankManager.getUndoTransactionRequest();
        Iterator<Map.Entry<Integer, Transactions>> entries = undoRequests.entrySet().iterator();

        Map.Entry<Integer, Transactions> entry;
        Integer mapKey;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount < choice ) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
            Integer temp = mapKey;
            bankManager.undoTransaction(undoRequests.get(temp));
            bankManager.getUndoTransactionRequest().remove(temp);
        }
    }*/


}
