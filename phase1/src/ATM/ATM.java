package ATM;

import java.util.Scanner;

public class ATM {

    private final int UserAction

    private final int Balance = 1; //these can be non-static as we never use them without an instance of ATM?
    private final int Deposit = 2;
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

        userAuthenticated = bankManager.authenticateUser(currentID, pass); //bankmanger needs this method

        if (userAuthenticated){
            currentUserID = currentID;
        }else{System.out.println("Wrong Password!");}

    }

    private void doActions(){
        System.out.println("User Action or Transaction?");
        System.out.println("1. User Action");
        System.out.println("2. Transaction");
    }

}

