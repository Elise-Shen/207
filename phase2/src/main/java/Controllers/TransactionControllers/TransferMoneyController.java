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
    @FXML
    private ComboBox<User> transferUser;

    public static MonetaryAmount amountTransferred;
    public static Account from;
    public static Account to;
    public static User toUser;

    public void toTransactionList() throws Exception{
        transferOut.getItems().clear();
        transferIn.getItems().clear();
        transferUser.getItems().clear();
        main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void transferButton()throws Exception{
        main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            from = transferOut.getValue();
            to = transferIn.getValue();
            toUser = transferUser.getValue();
            AccountToAccount transfer = new AccountToAccount(currentUserID, bankManager);

            amountTransferred = from.createMoney(Integer.parseInt(transferAmount.getValue()));

            boolean enoughMoney = transfer.executeTransfer(to, from, amountTransferred);
            if(enoughMoney){
                from.addTransaction(transfer);
                to.addTransaction(transfer);
                main.showNewBorderPane("/HelperBoxes/TransferredBox.fxml");
                transferUser.getItems().clear();
                transferOut.getItems().clear();
                transferIn.getItems().clear();

            }else{
                main.showNotEnoughMoney();
            }
        }
    }

    public void loadOutAccounts(){
        transferIn.getItems().clear();
        try {
            toUser = transferUser.getValue();
            ViewAccount viewOtherUserAccount = new ViewAccount(toUser.getUserID(), bankManager);
            viewOtherUserAccount.printAccounts(toUser.getAccountList());

            ObservableList<Account> allAccounts = viewOtherUserAccount.getAllAccounts();
            transferIn.setItems(allAccounts);
        }catch (NullPointerException ex){
            System.out.println("False Alarm");
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
        bankManager.readUserList();
        ObservableList<User> allUsers = bankManager.getAllUsers();
        ObservableList<Account> allTranserableOutAccounts = viewAccount.getTransferOutAccounts();
        transferOut.setItems(allTranserableOutAccounts);
        transferUser.setItems(allUsers);

    }
}
