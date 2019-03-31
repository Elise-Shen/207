package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.*;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransactionHistoryController implements Initializable {
    private BankManager bankManager;
    private User currentUser;



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


    public void toUserActionsList()throws Exception{
        transactionTable.getItems().clear();
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = Main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUser = bankManager.getUser(atm.getCurrentUserID());
        currentUser.readTransactions();
        ObservableList<Transactions> allTransactions = currentUser.getTransactionsObservableList();

        dateTableColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDate()));

        transactionsTableColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()));

        accountIDTableColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCurrentAccountID()));

        accountTableColumn.setCellValueFactory(data -> {
            int currentAccountID = data.getValue().getCurrentAccountID();
            return new ReadOnlyObjectWrapper<>(data.getValue().getBankManager().getOneAccount(currentAccountID));
        });

        amountTableColumn.setCellValueFactory(data ->{
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
        });

        transactionTable.setItems(allTransactions);
    }
}
