package Controllers;

import ATM.*;
import Actions.DepositMoney;
import javafx.fxml.Initializable;
import sun.reflect.annotation.ExceptionProxy;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

    public static DepositMoney quickDeposit;

    public void goToUserMenu() throws Exception{
        Main.showNewBorderPane("/UserMainMenuPage.fxml");
    }

    /**
     * Deposits directly to the primary accounts
     * @throws Exception
     */

    public void quickDeposit()throws Exception{
        ATM_Machine tempATM = Main.getCurrentATM();
        BankManager bankManager = tempATM.getATMBankManager();
        int currentUserID = tempATM.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        CashStorage cashStorage = tempATM.getCashStorage();
        quickDeposit = new DepositMoney(currentUserID, bankManager, cashStorage);
        List<String[]> deposits = quickDeposit.readFromCSV("phase2/deposits.txt");
        quickDeposit.checkDeposit(deposits, currentUser.getPrimaryAccount());
        currentUser.addTransactions(quickDeposit);
        currentUser.getPrimaryAccount().addTransaction(quickDeposit);
        Main.showQuickDeposit();

    }

    public void depositButton() throws  Exception{
        Main.showNewBorderPane("/Transactions/DepositPage.fxml");
    }

    public void withdrawButton() throws Exception{
        Main.showNewBorderPane("/Transactions/WithdrawPage.fxml");
    }

    public void payBillsButton()throws Exception{
        Main.showNewBorderPane("/Transactions/PayBillsPage.fxml");
    }

    public void transferMoneyButton()throws Exception{
        Main.showNewBorderPane("/Transactions/TransferMoneyPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
