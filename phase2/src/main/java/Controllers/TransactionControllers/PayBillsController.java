package Controllers.TransactionControllers;

import ATM.*;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.PayBills;
import Actions.ViewAccount;
import Controllers.Helpers.ConfirmBoxController;
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
    private ComboBox<String> recipientCombo;
    @FXML
    private ComboBox<String> amountCombo;

    private User currentUser;

    public static int amountChoice;

    public static Account accountChoice;

    public static String recipient;

    public void goToTransactionList() throws Exception{
        recipientCombo.getItems().clear();
        payBillCombo.getItems().clear();
        main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void payBills() throws Exception{
        main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            accountChoice = payBillCombo.getValue();
            amountChoice = Integer.parseInt(amountCombo.getValue());
            recipient = recipientCombo.getValue();

            payBills = new PayBills(currentUserID, bankManager);
            boolean enoughMoney = payBills.executePayBill(accountChoice, amountChoice);
            if (!enoughMoney) {
                main.showNotEnoughMoney();
            } else {
                if(!currentUser.getPreviousPayees().contains(recipient)) {
                    currentUser.addPayee(recipient);
                }
                recipientCombo.getItems().clear();
                payBillCombo.getItems().clear();
                currentUser.addTransactions(payBills);
                main.showNewBorderPane("/HelperBoxes/PaidBillBox.fxml");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        currentUser = bankManager.getUser(currentUserID);
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printDepositAccounts(currentUser.getAccountList());
        ObservableList<Account> allDepositAccounts = viewAccount.getDepositAccounts();
        payBillCombo.setItems(allDepositAccounts);
        try {
            currentUser.readPayees(currentUser.getPreviousPayees());
            ObservableList<String> previousPayees = currentUser.getPrevPayees();
            recipientCombo.setItems(previousPayees);
        }catch (NullPointerException ex){
            System.out.println("No previous payees");
        }
    }
}
