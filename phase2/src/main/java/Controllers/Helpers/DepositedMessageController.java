package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.DepositMoney;
import Controllers.TransactionControllers.DepositController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class DepositedMessageController implements Initializable {
    private Main main;
    private BankManager bankManager;
    private int currentUserID;

    @FXML
    private Label depositAmount;
    @FXML
    private Label depositAccount;

    public void okPressed() throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        Account account = DepositController.depositChoice;
        MonetaryAmount amount = DepositController.amountDeposited;
        depositAccount.setText(account.toString());
        depositAmount.setText(amount.toString());

    }
}
