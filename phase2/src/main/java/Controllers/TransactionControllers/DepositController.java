package Controllers.TransactionControllers;

import ATM.*;
import Accounts.Account;
import Actions.DepositMoney;
import Actions.Transactions;
import Actions.ViewAccount;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    private Main main;
    private int currentUserID;
    private BankManager bankManager;
    private CashStorage cashStorage;
    public static Account depositChoice;
    private DepositMoney deposit;
    public static MonetaryAmount amountDeposited;

    @FXML
    private ChoiceBox<Account> depositChoiceBox;

    public void goToTransactionList()throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }


    public void confirmDepositButton() throws Exception{
        depositChoice = depositChoiceBox.getValue();
        System.out.println(depositChoice + ", " + depositChoice.getAccountID() + ", "+ depositChoice.getOwnerID());
        deposit = new DepositMoney(currentUserID, bankManager, cashStorage);
        List<String[]>deposits = deposit.readFromCSV("phase2/deposits.txt");
        deposit.checkDeposit(deposits, depositChoice);
        depositChoice.addTransaction(deposit);
        amountDeposited = deposit.getAmountDeposited();
        System.out.println(amountDeposited);
        depositChoiceBox.getItems().clear();
        bankManager.getUser(currentUserID).addTransactions(deposit);
        main.showNewBorderPane("/HelperBoxes/DepositMessageBox.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        cashStorage = atm.getCashStorage();
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printDepositAccounts(currentUser.getAccountList());
        ObservableList<Account> allDepositAccounts = viewAccount.getDepositAccounts();
        depositChoiceBox.setItems(allDepositAccounts);
    }
}
