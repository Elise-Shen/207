package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import Accounts.Account;
import Actions.PayBills;
import Controllers.TransactionControllers.DepositController;
import Controllers.TransactionControllers.PayBillsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class PaidBillController implements Initializable {

    private Main main;

    @FXML
    private Label paidAmount;
    @FXML
    private Label paidAccount;
    @FXML
    private Label recipientAccount;

    public void okPressed() throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account account = PayBillsController.accountChoice;
        MonetaryAmount amount = account.createMoney(PayBillsController.amountChoice);
        paidAccount.setText(account.toString());
        paidAmount.setText(amount.toString());
        recipientAccount.setText(PayBillsController.recipient);


    }
}
