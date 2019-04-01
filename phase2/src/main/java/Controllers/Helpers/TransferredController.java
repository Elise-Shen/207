package Controllers.Helpers;

import ATM.Main;
import ATM.User;
import Accounts.Account;
import Controllers.TransactionControllers.TransferMoneyController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferredController implements Initializable {

    @FXML
    private Label result;

    public void okPressed()throws Exception{
        Main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Account from = TransferMoneyController.from;
        Account to = TransferMoneyController.to;
        User otherUser = TransferMoneyController.toUser;
        MonetaryAmount transferredAmount = TransferMoneyController.amountTransferred;
        result.setText("Transferred " + transferredAmount + " from " + from + " to " + otherUser + "'s " + to);

    }
}
