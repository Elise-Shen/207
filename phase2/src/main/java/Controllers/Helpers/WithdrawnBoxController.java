package Controllers.Helpers;

import ATM.Main;
import Accounts.Account;

import Controllers.TransactionControllers.WithdrawController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawnBoxController implements Initializable {

    private Main Main;

    @FXML
    private Label withdrawAccount;
    @FXML
    private Label withdrawAmount;

    public void okPressed()throws Exception{
        Main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account account = WithdrawController.withdrawAccount;
        MonetaryAmount amount = WithdrawController.amountWithdrawn;
        withdrawAccount.setText(account.toString());
        withdrawAmount.setText(amount.toString());

    }
}
