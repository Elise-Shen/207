package AdminActions;

import ATM.BankManager;

import java.util.*;

public class ViewAccountRequests extends AdminAction {




    public ViewAccountRequests(BankManager bankManager){
        super(bankManager);
    }
    public void execute(){

        BankManager bankManager = getBankManager();
        Map<Integer, Integer> accountRequests;
        accountRequests = bankManager.getAccountRequests();
        boolean exited = false;
        while (!exited) {
            if (!accountRequests.isEmpty()) {
                Iterator<Map.Entry<Integer, Integer>> entries = accountRequests.entrySet().iterator();
                Iterator<Map.Entry<Integer, Integer>> entries2 = accountRequests.entrySet().iterator();
                Map.Entry<Integer, Integer> entry;
                Integer mapKey = 0;
                int count = 0;
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
                boolean isValid = false;
                System.out.println("Enter the number of the request you wish to approve or enter 0 to exit");
                while (!isValid) {
                    try {
                        Scanner input = new Scanner(System.in);
                        int choice = input.nextInt();
                        if (choice > 0 && choice <= count) {
                            int choiceCount = 0;
                            isValid = true;
                            while (entries2.hasNext() && choiceCount != choice) {
                                choiceCount++;
                                entry = entries2.next();
                                mapKey = entry.getKey();
                            }
                            bankManager.createAccount(mapKey, accountRequests.get(mapKey));
                            bankManager.getAccountRequests().remove(mapKey);
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
}
