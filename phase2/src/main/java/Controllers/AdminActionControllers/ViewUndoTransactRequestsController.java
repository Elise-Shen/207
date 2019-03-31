package Controllers.AdminActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.AccountToAccount;
import Actions.DepositMoney;
import Actions.Transactions;
import Actions.WithdrawMoney;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ViewUndoTransactRequestsController implements Initializable {
    private BankManager bankManager;
    private ObservableList<Transactions> listOfTransaction;

    @FXML private TableView<Transactions> undoTransacReq;
    @FXML private TableColumn<Transactions, Integer> accountIdCol;
    @FXML private TableColumn<Transactions, Account> accountCol;
    @FXML private TableColumn<Transactions, Transactions> transactionCol;
    @FXML private TableColumn<Transactions, MonetaryAmount> amountCol;
    @FXML private TableColumn<Transactions, LocalDate> dateCol;
    @FXML private TableColumn<Transactions, Integer> userIDCol;

    public void toAdminActions()throws Exception{
        undoTransacReq.getItems().clear();
        Main.showNewBorderPane("/AdminMainPage.fxml");
    }

    public void deleteSelection(){
        ObservableList<Transactions> selectedTransaction;
        selectedTransaction = undoTransacReq.getSelectionModel().getSelectedItems();
        for(Transactions t:selectedTransaction){
            bankManager.undoTransaction(t);
            bankManager.getUndoTransactionRequest().get(t.getUserID()).clear();
            LocalDate date = t.getDate();
            Account currentAccount = t.getCurrentAccount();
            currentAccount.getTransactionsList().remove(date);
            User currentUser = t.getBankManager().getUser(t.getUserID());
            currentUser.getTransactionsList().remove(t);

            System.out.println("Deleted");
        }
        listOfTransaction.removeAll(selectedTransaction);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        undoTransacReq.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ATM_Machine atm = Main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        int currentUserID = atm.getCurrentUserID();
        User currentUser= bankManager.getUser(currentUserID);

        listOfTransaction  = bankManager.getObservableUndoTransacRequest();

        accountIdCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCurrentAccountID()));

        accountCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCurrentAccount()));

        transactionCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()));

        amountCol.setCellValueFactory(data -> {
            Transactions temp = data.getValue();
            if (temp instanceof DepositMoney){
                return new ReadOnlyObjectWrapper<>(((DepositMoney)temp).getAmountDeposited());
            }else if(temp instanceof WithdrawMoney){
                return new ReadOnlyObjectWrapper<>(((WithdrawMoney)temp).getAmountWithdrawn());
            }else{
                return new ReadOnlyObjectWrapper<>(((AccountToAccount)temp).getAmountTransferred());
            }
        });

        dateCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDate()));

        userIDCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getUserID()));

        undoTransacReq.setItems(listOfTransaction);
    }
}
