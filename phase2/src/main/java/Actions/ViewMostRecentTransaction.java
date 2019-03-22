package Actions;

import ATM.BankManager;
import Accounts.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ViewMostRecentTransaction extends ViewAccount {

    public ViewMostRecentTransaction(int userID, BankManager bm){
        super(userID, bm);
    }

    @Override
    public void execute() {
        BankManager bankManager = getBankManager();
        Map<LocalDate, Transactions> recent;
        Account currentAccount = readCurrentAccount();
        recent = currentAccount.getTransactionsList();
        Iterator<Map.Entry<LocalDate, Transactions>> entries = recent.entrySet().iterator();
        Map.Entry<LocalDate, Transactions> entry;
        LocalDate mapKey = null;
        Transactions previousTransaction = null;
        while(entries.hasNext()){
            entry = entries.next();
            mapKey = entry.getKey();
            //keeps iterating until the last item
            //sets map-key to last item
        }
        if (recent.get(mapKey) == null) {
            System.out.println("No transaction recently.");
        } else {
            previousTransaction = recent.get(mapKey);
            System.out.println("The most recent transaction is: " + previousTransaction + " on " + mapKey);
        }
        boolean recipientIsCredit = (previousTransaction instanceof AccountToAccount)
                && recipientIsCredit(previousTransaction);
        if(!(previousTransaction instanceof PayBills) && !recipientIsCredit){
            System.out.println("\nDo you wish to undo this transaction?");
            Scanner input = new Scanner(System.in);
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            try{
                int yesNo = input.nextInt();
                if(yesNo == 1){
                    bankManager.addUndoTransactionRequest(getUserID(), previousTransaction);
                    System.out.println("Sent request to bank manager to undo this transaction\n");
                }else{System.out.println("Returning to previous page");}
            }catch (InputMismatchException ex) {
                System.out.println("Invalid Input. Try Again");
            }
        }
    }

    /**
     * Check if the previous transaction is transferring money to a Credit account.
     */
    private boolean recipientIsCredit(Transactions t) {
        BankManager bankManager = getBankManager();
        int recipientID = t.getRecipientAccountID();
        Account recipient = bankManager.getOneAccount(recipientID);
        return recipient instanceof Credit;
    }
}
