package ATM;

import java.util.Scanner;

public class ATM {

    private final int USERACTION = 1;
    private final int TRANSACTION = 2;

    private final int BALANCE = 1; //these can be non-static as we never use them without an instance of ATM?
    private final int PREVIOUS_TRANSACTION = 2;
    private final int NET_TOTAL = 3;
    private final int CHANGE_PSWD = 4;
    private final int CREATION_DATE = 5;
    private final int REQ_ACCNT = 6;
    private final int DEPOSIT = 2;
    private final int WITHDRAW = 3;

    private boolean userAuthenticated;
    private int currentUserID;
    private CashStorage cashStorage;
    private BankManager bankManager;


    public ATM(){

        userAuthenticated = false;
        currentUserID = 0;
        cashStorage = new CashStorage();
        bankManager = new BankManager();
    }

    public void run(){

        while (true) {

            while (!userAuthenticated){
                System.out.println("Welcome!");
                authenticateUser();

            }
            doActions();
            userAuthenticated = false;
            currentUserID = 0;
            System.out.println("GoodBye!");

        }


    }

    private void authenticateUser() {
        Scanner input0 = new Scanner(System.in);
        System.out.println("Please enter your User ID");
        int currentID = input0.nextInt();
        Scanner input1 = new Scanner(System.in);
        System.out.println("Please enter your password");
        String pass = input1.nextLine();

        userAuthenticated = bankManager.authenticateUser(currentID, pass); //bankmanager needs this method

        if (userAuthenticated){
            currentUserID = currentID;
        }else{System.out.println("Wrong Password!");}

    }

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
                case 1:
                    doUserAction();
                    break;
                case 2:
                    doTransaction();
                    break;
                case 0:
                    exited = true;
                    break;
                default:
                    System.out.println("Invalid Input. Please try again");
                    break;
            }
        }
    }

    private void doUserAction(){
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your Action");
        System.out.println("1 - View Balance Summary");
        System.out.println("2 - View Previous Transactions");
        System.out.println("3 - Net Total");
        System.out.println("4 - Change Password");
        System.out.println("5 - View Date of Account Creation");
        System.out.println("6 - Request New Account");
        int user




    }

    private void doTransaction(){

    }

    public UserActions createUserAction(int type){
        return null;//place holder
    }

    public Transactions createTransaction(int type){
        return null; //placeholder
    }

}

