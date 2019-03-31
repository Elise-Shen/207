package Controllers.Helpers;

import ATM.Main;
import Accounts.Account;
import Controllers.TransactionControllers.DepositController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class DepositedMessageController implements Initializable {


    @FXML
    private Label depositAmount;
    @FXML
    private Label depositAccount;

    public void okPressed() throws Exception{
        Main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account account = DepositController.depositChoice;
        MonetaryAmount amount = DepositController.amountDeposited;
        depositAccount.setText(account.toString());
        depositAmount.setText(amount.toString());

    }
}
