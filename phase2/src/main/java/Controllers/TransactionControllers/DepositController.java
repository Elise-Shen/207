package Controllers.TransactionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.ViewAccount;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    private Main main;
    private int currentUserID;
    private BankManager bankManager;

    @FXML
    private ChoiceBox<Account> depositChoiceBox;

    public void goToTransactionList()throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }


    public void confirmDepositButton(){

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
        depositChoiceBox.setItems(allDepositAccounts);
    }
}
