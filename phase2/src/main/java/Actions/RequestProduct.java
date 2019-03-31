package Actions;

import ATM.BankProductsEmployee;
import ATM.Keypad;

public class RequestProduct extends UserActions {
    public RequestProduct(int userID, BankProductsEmployee bpe, int accountID){
        super(userID,bpe,accountID);
    }
    @Override
    public void execute(){
        BankProductsEmployee bankProductsEmployee = getBankProductsEmployee();
        int productType = getProductType();
        int productAmount = getProductAmount();
        int puductLength = getProductLength(productType);
        bankProductsEmployee.requestProducts(getUserID(),getAccountID(),productType,productAmount,puductLength);
    }
    public int getProductLength(int type){
        int length = 0;
        if (type==1 || type==2){
            Keypad keypad = new Keypad();
            length = keypad.getIntInput("\nHow long would you like to hold your mortgage?"
                    + "\n please input an integer");
        }
        return length;
    }

    public int getProductAmount(){
        Keypad keypad = new Keypad();
        int amount = 0;
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
                    "\n1 - Long Term Mortgage \n2 - Short Term Mortgage\n3 - High Rist Investment\n4 - Low Risk Investment\n0 - Exit");
            if (typeChoice <= 4 && typeChoice >= 0) {
                isValid = true;
            } else {
                System.out.println("Invalid input. Please try again!");
            }
        }
        return typeChoice;
    }
}
