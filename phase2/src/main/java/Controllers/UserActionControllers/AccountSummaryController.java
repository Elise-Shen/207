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

        accountCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, Account>, ObservableValue<Account>>() {
            @Override
            public ObservableValue<Account> call(TableColumn.CellDataFeatures<Account, Account> data) {
                return new ReadOnlyObjectWrapper<>(data.getValue());
            }
        });

        idCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Account, Integer> data) {
                Integer value = data.getValue().getAccountID();
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        balanceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, MonetaryAmount>, ObservableValue<MonetaryAmount>>() {
            @Override
            public ObservableValue<MonetaryAmount> call(TableColumn.CellDataFeatures<Account, MonetaryAmount> data) {
                MonetaryAmount value = data.getValue().getCurrencyBalance();
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        summaryTable.setItems(allAccounts);

    }
}
