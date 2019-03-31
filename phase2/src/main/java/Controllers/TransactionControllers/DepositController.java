package Controllers.TransactionControllers;

import ATM.*;
import Accounts.Account;
import Actions.DepositMoney;

import Actions.ViewAccount;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;


import javax.money.MonetaryAmount;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    private int currentUserID;
    private BankManager bankManager;
    private CashStorage cashStorage;
    public static Account depositChoice;
    private DepositMoney deposit;
    public static MonetaryAmount amountDeposited;

    @FXML
    private ChoiceBox<Account> depositChoiceBox;

    public void goToTransactionList()throws Exception{
        depositChoiceBox.getItems().clear();
        Main.showNewBorderPane("/TransactionPage.fxml");
    }


    public void confirmDepositButton() throws Exception{
        depositChoice = depositChoiceBox.getValue();
        System.out.println(depositChoice + ", " + depositChoice.getAccountID() + ", "+ depositChoice.getOwnerID());
        deposit = new DepositMoney(currentUserID, bankManager, cashStorage);
        List<String[]>deposits = deposit.readFromCSV("deposits.txt");
        deposit.checkDeposit(deposits, depositChoice);
        depositChoice.addTransaction(deposit);
        amountDeposited = deposit.getAmountDeposited();
        System.out.println(amountDeposited);
        depositChoiceBox.getItems().clear();
        bankManager.getUser(currentUserID).addTransactions(deposit);
        Main.showNewBorderPane("/HelperBoxes/DepositMessageBox.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ATM_Machine atm = Main.getCurrentATM();
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
