package ATM;
import Actions.*;
import AdminActions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM_Machine {

    private static final int CUSTOMER = 1;
    private static final int BANK_MANAGER = 2;
    /**
     * Admin Actions
     */
    private final int VIEW_ACCOUNT_REQUEST = 1;
    private final int VIEW_UNDO_TRANSAC = 2;
    private final int RESTOCK = 3;

    private static final int USER_ACTION = 1;
    private static final int TRANSACTION = 2;
    /**
     * All userActions identifiers
     */
    private final int BALANCE = 1;
    private final int PREVIOUS_TRANSACTION = 2;
    private final int NET_TOTAL = 3;
    private final int CHANGE_PSWD = 4;
    private final int CREATION_DATE = 5;//
    private final int REQ_ACCNT = 6;
    private final int ACCNT_SUMMARY = 7;
    private final int SET_PRIM_ACCNT = 8;
    /**
     * All transaction identifiers
     */
    private final int DEPOSIT = 1;
    private final int WITHDRAW = 2;
    private final int PAY_BILL = 3;
    private final int TRANSFER_MONEY = 4;

    private final int EXIT = 0;

    private boolean userAuthenticated;
    private boolean bankManagerAuthenticated;
    private int currentUserID;
    private CashStorage cashStorage;
    private BankManager bankManager;

    /**
     * Indicates whether the interest is added to all savings account at the end of every month.
     */
    private boolean interestAdded = false;

    /**
     * The current date when this ATM is running.
     */
    private LocalDate currentDate = LocalDate.now();


    public ATM_Machine(){

        userAuthenticated = false;
        bankManagerAuthenticated = false;
        currentUserID = 0;
        cashStorage = new CashStorage();
        bankManager = new BankManager("TD Bank", "abc123");
    }

    void run(){

        while (true) {
            boolean isValid = false;
            System.out.println("\nATM Starting Up");
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("\nAre you a Customer or a Bank Manager?");
            System.out.println("\n1 - Customer");
            System.out.println("2 - Bank Manager");
            while(!isValid){
                try {
                    Scanner bmOrCust = new Scanner(System.in);
                    int choice = bmOrCust.nextInt();
                    switch (choice){
                        case CUSTOMER:
                            customerLogin();
                            isValid = true;
                            break;
                        case BANK_MANAGER:
                            bankManagerLogin();
                            isValid = true;
                            break;
                        default:
                            System.out.println("Invalid input. Please try again");
                            break;
                    }
                }catch(InputMismatchException ex){System.out.println("Invalid input. Please try again");}

            }
            addToSavingsAccounts();
        }
    }

    private void customerLogin(){
        while (!userAuthenticated){
            System.out.println("\nWelcome to " + bankManager.getBankName() +"'s ATM!");
            currentUserID = bankManager.checkLogin();
            userAuthenticated = true;
        }
        doActions();
        userAuthenticated = false;
        currentUserID = 0;
        System.out.println("\nGoodBye!");
    }

    private void bankManagerLogin(){
        Scanner pass = new Scanner(System.in);
        while (!bankManagerAuthenticated){
            System.out.println("Please enter your password");
            String input = pass.nextLine();
            if (bankManager.getPassword().equals(input)){
                bankManagerAuthenticated = true;
            }
        }
        doAdminActions();
        bankManagerAuthenticated = false;

    }

    private void doAdminActions(){
        boolean exited = false;
        AdminAction currentAdminAction = null;
        while(!exited) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("\nChoose your action");
                System.out.println("\n1 - View Account Creation Requests");
                System.out.println("2 - View Undo Transaction Requests");
                System.out.println("3 - Restock this ATM");
                System.out.println("0 - Exit");
                int choice = input.nextInt();

                switch (choice){
                    case VIEW_ACCOUNT_REQUEST:
                    case VIEW_UNDO_TRANSAC:
                    case RESTOCK:
                        currentAdminAction = createAdminAction(choice);
                        currentAdminAction.execute();
                        break;
                    case EXIT:
                        exited = true;
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again");
                }
            }catch (InputMismatchException ex){System.out.println("Invalid input. Please try again.");}
        }


    }

    /**
     * User chooses whether to do User Actions or Transactions
     * Keeps running until use chooses to exit to the previous list of options
     */
    private void doActions(){

        boolean exited = false;
        while (!exited){
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("\nUser Action or Transaction?");
                System.out.println("1 - User Action");
                System.out.println("2 - Transaction");
                System.out.println("0 - Exit");
                int actionChoice = input.nextInt();

                switch (actionChoice) {
                    case USER_ACTION:
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
            }catch (InputMismatchException ex){System.out.println("Invalid Input. Please try again.");}
        }//while loop ends
    }//doAction ends

    /**
     * Does User Actions
     * keeps running until user choose to exit
     * exits to doActions()
     */
    private void doUserAction(){
        boolean exited = false;
        UserActions currentAction;
        while (!exited) { //keeps running until user wants to go back to the previous options
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Choose your Action");
                System.out.println("1 - View Balance Summary");
                System.out.println("2 - View Previous Transactions");
                System.out.println("3 - Net Total");
                System.out.println("4 - Change Password");
                System.out.println("5 - View Date of Account Creation");
                System.out.println("6 - Request New Account");
                System.out.println("7 - View Account Summary");
                System.out.println("8 - Set Primary Account");
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
                    case SET_PRIM_ACCNT:
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
            }catch (InputMismatchException ex){System.out.println("Invalid Input. Please try again.");}
        }//while loop ends

    }//doUserAction ends

    private void doTransaction(){
        boolean exited = false;
        Transactions currentTransaction;
        while(!exited){
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("\nChoose your transaction");
                System.out.println("1 - Deposit");
                System.out.println("2 - Withdrawal");
                System.out.println("3 - Pay Bills"); //to non-user accounts
                System.out.println("4 - Transfer Money");//user-user transactions. can be between the same user
                System.out.println("0 - EXIT");
                int transactionChoice = input.nextInt();

                switch (transactionChoice) {
                    case DEPOSIT:
                    case WITHDRAW:
                    case PAY_BILL:
                    case TRANSFER_MONEY:
                        currentTransaction = createTransaction(transactionChoice);
                        currentTransaction.execute();
                        bankManager.getUser(currentUserID).getAccount(currentTransaction.getAccountID()).addTransaction(currentTransaction);
                        break;
                    case EXIT:
                        exited = true;
                        System.out.println("\nReturning to previous options");
                        break;
                    default:
                        System.out.println("\nInvalid input. Please try again!");
                }
            }catch (InputMismatchException ex){System.out.println("Invalid Input. Please try again");}
        }

    }

    private AdminAction createAdminAction(int type){
        AdminAction temp = null;
        switch (type){
            case VIEW_ACCOUNT_REQUEST:
                temp = new ViewAccountRequests(bankManager);
                break;
            case VIEW_UNDO_TRANSAC:
                temp = new ViewAccountRequests(bankManager);
                break;
            case RESTOCK:
                temp = new RestockATM(bankManager, cashStorage);
                break;
        }
        return temp;
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
                temp = new RequestAccount(currentUserID, bankManager);
                break;
            case ACCNT_SUMMARY:
                temp = new AccountSummary(currentUserID, bankManager);
                break;
            case SET_PRIM_ACCNT:
                temp = new SetPrimaryAccount(currentUserID, bankManager);
                break;
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
            case PAY_BILL:
                temp = new PayBills(currentUserID, bankManager );
                break;
            case TRANSFER_MONEY:
                temp = new AccountToAccount(currentUserID, bankManager);
                break;
        }

        return temp; //placeholder
    }

    private boolean isEndOfMonth() {
        LocalDate end = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        return currentDate.equals(end);
    }


    private void addToSavingsAccounts() {
        if (isEndOfMonth() && !interestAdded) {
            bankManager.addInterestToSavingsAccounts();
            interestAdded = true;
        }
        if (!isEndOfMonth()) {
            interestAdded = false;
        }
    }



}

