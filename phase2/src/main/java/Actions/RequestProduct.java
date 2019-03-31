package Actions;

import ATM.BankProductsEmployee;

public class RequestProduct extends UserActions {
    public RequestProduct(int userID, BankProductsEmployee bpe){
        super(userID,bpe);
    }
    @Override
    public void execute(){
        BankProductsEmployee bankProductsEmployee = getBankProductsEmployee();
    }
}
