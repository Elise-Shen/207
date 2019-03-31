package Actions;

import ATM.BankProductsEmployee;
import ATM.Keypad;
import ATM.User;

import java.time.LocalTime;

public class RequestProduct extends UserActions {
    private static final int LONGTERMMORTGAGE = 1;
    private static final int SHORTTERMMORTGAGE = 2;

    private static final int MORTGAGEBOUNDARY = 12;

    private static final int LIMIT = 1;
    public RequestProduct(int userID, BankProductsEmployee bpe, int accountID){
        super(userID,bpe,accountID);
    }
    @Override
    public void execute(){
        BankProductsEmployee bankProductsEmployee = getBankProductsEmployee();

        User currentUser = bankProductsEmployee.getUser(getUserID());


        LocalTime currentTime = LocalTime.now();
        LocalTime countResetTime = LocalTime.MIDNIGHT;
        if(currentTime == countResetTime){
            currentUser.product_resetCount();
        }

        if(currentUser.product_getCount() < LIMIT ) {
            int productType = getProductType();

            int productAmount = getProductAmount();
            int puductLength = getProductLength(productType);
            currentUser.product_incrementCount();
            bankProductsEmployee.requestProducts(getUserID(),getAccountID(),productType,productAmount,puductLength);
        }else {System.out.println("Reached Daily Account Request Limit");}
    }
    public int getProductLength(int type){
        boolean isValid = false;
        int length = 0;
        if (type==LONGTERMMORTGAGE || type==SHORTTERMMORTGAGE) {
            while (!isValid) {
                Keypad keypad = new Keypad();
                length = keypad.getIntInput("\nHow many money would you like to hold your mortgage?"
                        + "\n please input an integer");
                if (type == LONGTERMMORTGAGE){
                    if (length > MORTGAGEBOUNDARY){ isValid = true;}
                    else {System.out.println("Time length for Long Term Mortgage need to be longer than 12 month. " +
                            "Please try again!");}
                }else{
                    if (length <= MORTGAGEBOUNDARY){ isValid = true;}
                    else {System.out.println("Time length for Short Term Mortgage cannot exceed 12 month. " +
                            "Please try again!");}
                }
            }
        }
        return length;
    }

    public int getProductAmount(){
        Keypad keypad = new Keypad();
        int amount;
        amount = keypad.getIntInput("\nHow much money would you like to put into the bank product?"
        + "\n please input an integer");
        return amount;
    }

    public int getProductType(){
        boolean isValid = false;
        int typeChoice = 0;
        while (!isValid) {
            Keypad keyPad = new Keypad();
            typeChoice = keyPad.getIntInput("\nWhat type of bank product do you wish to create?" +
                    "\n1 - Long Term Mortgage \n2 - Short Term Mortgage\n3 - High Risk Investment\n4 - Low Risk Investment\n0 - Exit");
            if (typeChoice <= 4 && typeChoice >= 0) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
        }
        return typeChoice;
    }
}
