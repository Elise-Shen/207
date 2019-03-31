package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import Accounts.Account;
import Controllers.TransactionControllers.DepositController;
import Controllers.TransactionControllers.WithdrawController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawnBoxController implements Initializable {

    private Main main;

    @FXML
    private Label withdrawAccount;
    @FXML
    private Label withdrawAmount;

    public void okPressed()throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account account = WithdrawController.withdrawAccount;
        MonetaryAmount amount = WithdrawController.amountWithdrawn;
        withdrawAccount.setText(account.toString());
        withdrawAmount.setText(amount.toString());

    }
}
