package Controllers.TransactionControllers;

import ATM.*;
import Accounts.Account;
import Actions.AccountToAccount;
import Actions.ViewAccount;
import Controllers.Helpers.ConfirmBoxController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferMoneyController implements Initializable {
    private Main main;
    private BankManager bankManager;
    private int currentUserID;

    @FXML
    private ComboBox<Account> transferOut;
    @FXML
    private ComboBox<Account> transferIn;
    @FXML
    private ComboBox<String> transferAmount;

    public static MonetaryAmount amountTransferred;
    public static Account from;
    public static Account to;

    public void toTransactionList() throws Exception{
        transferOut.getItems().clear();
        transferIn.getItems().clear();
        main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void transferButton()throws Exception{
        main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            from = transferOut.getValue();
            to = transferIn.getValue();

            AccountToAccount transfer = new AccountToAccount(currentUserID, bankManager);

            amountTransferred = from.createMoney(Integer.parseInt(transferAmount.getValue()));

            boolean enoughMoney = transfer.executeTransfer(from, to, amountTransferred);
            if(enoughMoney){
                from.addTransaction(transfer);
                to.addTransaction(transfer);
                transferOut.getItems().clear();
                transferIn.getItems().clear();
                main.showNewBorderPane("/HelperBoxes/TransferredBox.fxml");


            }else{
                main.showNotEnoughMoney();
            }




        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printTransferableOutAccounts(currentUser.getAccountList());
        viewAccount.printAccounts(currentUser.getAccountList());
        ObservableList<Account> allTranserableOutAccounts = viewAccount.getTransferOutAccounts();
        ObservableList<Account> allAccounts = viewAccount.getAllAccounts();
        transferIn.setItems(allTranserableOutAccounts);
        transferOut.setItems(allAccounts);

    }
}
