package Actions;

import ATM.BankManager;

public class BorrowMortgage extends Transactions {

    public BorrowMortgage(int userID, BankManager bm) {
        super(userID, bm);
    }

    @Override
    public int getCurrentAccountID() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void execute() {

    }
}
