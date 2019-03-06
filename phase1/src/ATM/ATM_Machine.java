package ATM;
import Actions.*;


import java.util.Scanner;

public class ATM_Machine {

    private static final int USERACTION = 1;
    private static final int TRANSACTION = 2;

    private final int BALANCE = 1;
    private final int PREVIOUS_TRANSACTION = 2;
    private final int NET_TOTAL = 3;
    private final int CHANGE_PSWD = 4;
    private final int CREATION_DATE = 5;//
    private final int REQ_ACCNT = 6;
    private final int ACCNT_SUMMARY = 7;
    private final int DEPOSIT = 1;
    private final int WITHDRAW = 2;
    private final int PAYBILL = 3;
    private final int TRANSFERMONEY = 4;

    private final int EXIT = 0;

    private boolean userAuthenticated;
    private int currentUserID;
    private CashStorage cashStorage;
    private BankManager bankManager;


    public ATM_Machine(){

        userAuthenticated = false;
        currentUserID = 0;
        cashStorage = new CashStorage();
        bankManager = new BankManager();
    }

    public void run(){

        while (true) {

            while (!userAuthenticated){
                System.out.println("\nWelcome!");
                authenticateUser();

            }
            doActions();
            userAuthenticated = false;
            currentUserID = 0;
            System.out.println("\nGoodBye!");

        }


    }

    private void authenticateUser() {
        Scanner input0 = new Scanner(System.in);
        // check password
        System.out.println("\nPlease enter your User ID");
        int currentID = input0.nextInt();
        Scanner input1 = new Scanner(System.in);
        System.out.println("\nPlease enter your password");
        String pass = input1.nextLine();

        userAuthenticated = bankManager.checkPassword(currentID, pass); //bankmanager needs this method

        if (userAuthenticated){
            currentUserID = currentID;
        }else{System.out.println("Wrong Password!");}

    }

    /**
     * User chooses whether to do User Actions or Transactions
     * Keeps running until use chooses to exit to the previous list of options
     */
    private void doActions(){

        boolean exited = false;
        while (!exited){
            Scanner input = new Scanner(System.in);
            System.out.println("User Action or Transaction?");
            System.out.println("1 - User Action");
            System.out.println("2 - Transaction");
            System.out.println("0 - Exit");
            int actionChoice = input.nextInt();

            switch (actionChoice) {
                case USERACTION:
                    doUserAction();
                    break;
                case TRANSACTION:
                    doTransaction();
                    break;
                case EXIT:
                    exited = true;
                    break;
                default:
                    System.out.println("\nInvalid Input. Please try again");
                    break;
            }//switch
        }//while loop ends
    }//doAction ends

    /**
     * Does User Actions
     * keeps running until user choose to exit
     * exits to doActions()
     */
    private void doUserAction(){
        boolean exited = false;
        UserActions currentAction = null;
        while (!exited) { //keeps running until user wants to go back to the previous options
            Scanner input = new Scanner(System.in);
            System.out.println("Choose your Action");
            System.out.println("1 - View Balance Summary");
            System.out.println("2 - View Previous Transactions");
            System.out.println("3 - Net Total");
            System.out.println("4 - Change Password");
            System.out.println("5 - View Date of Account Creation");
            System.out.println("6 - Request New Account");
            System.out.println("7 - View Account Summary");
            System.out.println("0 - Return to previous page");
            int userChoice = input.nextInt();

            switch (userChoice) {
                case BALANCE:
                case PREVIOUS_TRANSACTION:
                case NET_TOTAL:
                case CHANGE_PSWD:
                case CREATION_DATE:
                case REQ_ACCNT:
                case ACCNT_SUMMARY:
                    currentAction = createUserAction(userChoice);
                    currentAction.execute();
                    //remember to add action and transaction to list in bankmanager
                    break;
                case EXIT:
                    exited = true;
                    System.out.println("\nReturning to previous Options");
                    break;
                default:
                    System.out.println("\nInvalid input. Please try again.");
                    break;
            }//switch
        }//while loop ends

    }//doUserAction ends

    private void doTransaction(){
        boolean exited = false;
        Transactions currentTransaction = null;
        while(!exited){
            Scanner input = new Scanner(System.in);
            System.out.println("\nChoose your transaction");
            System.out.println("1 - Deposit");
            System.out.println("2 - Withdrawal");
            System.out.println("3 - Pay Bills"); //to non-user accounts
            System.out.println("4 - Transfer Money");//user-user transactions. can be between the same user
            System.out.println("0 - EXIT");
            int transactionChoice = input.nextInt();

            switch(transactionChoice){
                case DEPOSIT:
                case WITHDRAW:
                case PAYBILL:
                case TRANSFERMONEY:
                    currentTransaction = createTransaction(transactionChoice);
                    bankManager.addTransaction(currentTransaction);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    exited = true;
                    System.out.println("\nReturning to previous options");
                default:
                    System.out.println("\nInvalid input. Please try again!");
            }



        }

    }

    /**
     *
     * @param type gets int from doUserAction()
     * @return the specific User Action object
     */
    private UserActions createUserAction(int type) {
        UserActions temp = null;
        switch (type){
            case BALANCE:
                temp = new ViewBalance(currentUserID, bankManager);
                break;
            case PREVIOUS_TRANSACTION:
                temp = new ViewMostRecentTransaction(currentUserID, bankManager);
                break;
            case NET_TOTAL:
                temp = new NetTotal(currentUserID, bankManager);
                break;
            case CHANGE_PSWD:
                temp = new ChangePin(currentUserID, bankManager);
                break;
            case CREATION_DATE:
                temp = new ViewCreationDate(currentUserID, bankManager);
                break;
            case REQ_ACCNT:
                temp = new Request_Account(currentUserID, bankManager);
            case ACCNT_SUMMARY:
                temp = new AccountSummary(currentUserID, bankManager);
        }
        return temp;
    }

    private Transactions createTransaction(int type) {
        Transactions temp = null;
        switch(type){
            case DEPOSIT:
                temp = new DepositMoney(currentUserID, bankManager, cashStorage);
                break;
            case WITHDRAW:
                temp = new WithdrawMoney(currentUserID, bankManager, cashStorage);
                break;
            case PAYBILL:
                temp = new PayBills(currentUserID, bankManager );
                break;
            case TRANSFERMONEY:
                temp = new AccountToAccount(currentUserID, bankManager);
                break;
        }

        return temp; //placeholder
    }

}

