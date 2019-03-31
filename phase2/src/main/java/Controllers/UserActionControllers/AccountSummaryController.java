package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountSummaryController implements Initializable {
    private BankManager bankManager;

    @FXML private TableView<Account> summaryTable;
    @FXML private TableColumn<Account, Account> accountCol;
    @FXML private TableColumn<Account, Integer> idCol;
    @FXML private TableColumn<Account, MonetaryAmount> balanceCol;

    public void toUserActionsList()throws Exception{
        summaryTable.getItems().clear();
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = Main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        int currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        currentUser.readAllAccounts();
        ObservableList<Account> allAccounts = currentUser.getObservableAccounts();


        accountCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()));

        idCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAccountID()));

        balanceCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCurrencyBalance()));

        summaryTable.setItems(allAccounts);

    }
}
