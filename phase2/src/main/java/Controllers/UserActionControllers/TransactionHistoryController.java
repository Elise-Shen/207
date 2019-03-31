package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.*;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransactionHistoryController implements Initializable {
    private Main main;
    private BankManager bankManager;
    private User currentUser;

    public void toUserActionsList()throws Exception{
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @FXML
    private TableView<Transactions> transactionTable;
    @FXML
    private TableColumn<Transactions, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<Transactions, Transactions> transactionsTableColumn;
    @FXML
    private TableColumn<Transactions, Integer> accountIDTableColumn;
    @FXML
    private TableColumn<Transactions, Account> accountTableColumn;
    @FXML
    private TableColumn<Transactions, MonetaryAmount> amountTableColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUser = bankManager.getUser(atm.getCurrentUserID());
        currentUser.readTransactions();
        ObservableList<Transactions> allTransactions = currentUser.getTransactionsObservableList();
        ObservableList<LocalDate> keys = FXCollections.observableArrayList();

        /*allTransactions.addListener((MapChangeListener.Change<? extends LocalDate, ? extends Transactions> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    keys.remove(change.getKey());
                } else {
                    keys.add(change.getKey());
                }
            }
        }); */

        dateTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transactions, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<Transactions, LocalDate> data) {
                LocalDate value = data.getValue().getDate();
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        transactionsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transactions, Transactions>, ObservableValue<Transactions>>() {
            @Override
            public ObservableValue<Transactions> call(TableColumn.CellDataFeatures<Transactions, Transactions> data) {
                Transactions value = data.getValue();
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        accountIDTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transactions, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Transactions, Integer> data) {
                Integer value = data.getValue().getCurrentAccountID();
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        accountTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transactions, Account>, ObservableValue<Account>>() {
            @Override
            public ObservableValue<Account> call(TableColumn.CellDataFeatures<Transactions, Account> data) {
                int currentAccountID = data.getValue().getCurrentAccountID();
                Account value = data.getValue().getBankManager().getOneAccount(currentAccountID);
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        amountTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transactions, MonetaryAmount>, ObservableValue<MonetaryAmount>>() {
            @Override
            public ObservableValue<MonetaryAmount> call(TableColumn.CellDataFeatures<Transactions, MonetaryAmount> data) {
                Transactions temp = data.getValue();
                MonetaryAmount value = null;
                if(temp instanceof DepositMoney) {
                    value = ((DepositMoney) temp).getAmountDeposited();
                }else if (temp instanceof AccountToAccount){
                    value = ((AccountToAccount)temp).getAmountTransferred();
                }else if(temp instanceof WithdrawMoney){
                    value = ((WithdrawMoney)temp).getAmountWithdrawn();
                }else if(temp instanceof PayBills){
                    value = ((PayBills)temp).getAmountPaid();
                }
                return new ReadOnlyObjectWrapper<>(value);
            }
        });

        transactionTable.setItems(allTransactions);
    }
}
