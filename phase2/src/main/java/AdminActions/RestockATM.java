package AdminActions;

import ATM.BankManager;
import ATM.CashStorage;

public class RestockATM extends AdminAction {

    private CashStorage cashStorage;

    public RestockATM(BankManager bankManager, CashStorage cashStorage){
        super(bankManager);
        this.cashStorage = cashStorage;
    }

    @Override
    public void execute() {
        cashStorage.setToMaxStock();
    }
}
