package ATM;


import Actions.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM_Machine implements Serializable{

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
    private BankProductsEmployee bankProductsEmployee;

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
        bankProductsEmployee = new BankProductsEmployee(bankManager, "abc123");
        //bankManager = getBankManager("phase2/Bankmanager.ser");

    }

    public BankManager getATMBankManager(){
        return bankManager;
    }

    public int getCurrentUserID(){
        return currentUserID;
    }

    public CashStorage getCashStorage(){
        return cashStorage;
    }

    public boolean userLogin(String userID, String password){
        if(bankManager.authenticateUser(userID, password)){
            currentUserID = Integer.parseInt(userID);
            System.out.println("Current UserID is: " + currentUserID);
            return true;
        }else{
            System.out.println("Something went wrong!");
            return false;
        }
    }

    public boolean managerLogin(String password) {
        if (this.bankManager.getPassword().equals(password)) {
            return true;
        } else {
            System.out.println("Wrong password.");
            return false;
        }
    }


    public boolean bankProductEmployeeLogin(String password) {
        if (this.bankProductsEmployee.getPassword().equals(password)) {
            return true;
        } else {
            System.out.println("Wrong password.");
            return false;
        }
    }


    /**
     * Return if the current day is the end of the current month.
     */
    private boolean isStartOfMonth() {
        LocalDate begin = LocalDate.now().withDayOfMonth(1);
        return LocalDate.now().equals(begin);
    }

    /**
     * Call Manager to add interest to all savings accounts on the last day of the every month.
     */
    public void addInterests() {
        if (isStartOfMonth() && !interestAdded) {
            bankManager.addInterests();
            interestAdded = true;
        }
        if (!isStartOfMonth()) {
            interestAdded = false;
        }
    }

    /**
     * Check if the current time is midnight.
     */
    boolean isMidnight() {
        return LocalTime.now() == LocalTime.MIDNIGHT;
    }

    /**
     * Updates serialization file data for bankmanager related to file.
     * @throws IOException
     */
    public void updateData() throws  IOException{
        String filePath = "phase2/Bankmanager.ser";
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(bankManager);
        output.close();
    }


    public BankProductsEmployee getBankProductsEmployee() {
        return bankProductsEmployee;
    }
}

