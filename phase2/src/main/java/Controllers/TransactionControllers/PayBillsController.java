package Controllers.TransactionControllers;

import ATM.*;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.PayBills;
import Actions.ViewAccount;
import Controllers.Helpers.NotEnoughMoneyController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PayBillsController implements Initializable {
    private Main main;
    private BankManager bankManager;
    private int currentUserID;
    private PayBills payBills;
    @FXML
    private ComboBox<Account> payBillCombo;
    @FXML
    private ComboBox<String> recipitentCombo;
    @FXML
    private ComboBox<String> amountCombo;

    public void goToTransactionList() throws Exception{
        payBillCombo.getItems().clear();
        main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void payBills() throws Exception{
        Account accountChoice = payBillCombo.getValue();
        int amountChoice = Integer.parseInt(amountCombo.getValue());
        String recipitent = recipitentCombo.getValue();

        payBills = new PayBills(currentUserID, bankManager);
        boolean enoughMoney = payBills.executePayBill(accountChoice,amountChoice);
        if(!enoughMoney){
            main.showNotEnoughMoney();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printDepositAccounts(currentUser.getAccountList());
        ObservableList<Account> allDepositAccounts = viewAccount.getDepositAccounts();
        payBillCombo.setItems(allDepositAccounts);
    }
}
