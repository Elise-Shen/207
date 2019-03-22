package AdminActions;

import ATM.BankManager;
import ATM.Keypad;
import Actions.Transactions;

import java.util.*;

public class ViewUndoTransactRequests extends AdminAction{

    public ViewUndoTransactRequests(BankManager bankManager){
        super(bankManager);
    }

    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        Map<Integer, Transactions> undoRequests = bankManager.getUndoTransactionRequest();
        boolean exited = false;
        while(!exited){
            if(!undoRequests.isEmpty()){
                Iterator<Map.Entry<Integer, Transactions>> entries = undoRequests.entrySet().iterator();
                Iterator<Map.Entry<Integer, Transactions>> entries2 = undoRequests.entrySet().iterator();
                Map.Entry<Integer, Transactions> entry;
                Integer mapKey = 0;
                int count = 0;
                System.out.println("\nCurrent Requests");
                while (entries.hasNext()) {
                    count++;
                    entry = entries.next();
                    mapKey = entry.getKey();
                    System.out.println("\n" + count + " - User " + mapKey + " requested to undo" +
                            " their previous transaction: " + undoRequests.get(mapKey));
                    //keeps iterating until the last item
                    //sets map-key to last item
                }
                boolean isValid = false;
                System.out.println();
                while (!isValid) {
                    try {
                        Keypad keyPad = new Keypad();
                        int choice = keyPad.getIntInput("Enter the number of the request you wish to " +
                                "approve or enter 0 to exit");
                        if (choice > 0 && choice <= count) {
                            int choiceCount = 0;
                            isValid = true;
                            while (entries2.hasNext() && choiceCount != choice) {
                                choiceCount++;
                                entry = entries2.next();
                                mapKey = entry.getKey();
                            }
                            bankManager.undoTransaction(undoRequests.get(mapKey));
                            bankManager.getUndoTransactionRequest().remove(mapKey);
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

        if(undoRequests.isEmpty()) {
            System.out.println("\nThere are no undo transaction requests at the moment");
        }else{
            System.out.println("Exited");
        }


    }
}
