package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.Transactions;
import Actions.UserActions;
import Actions.ViewAccount;
import Actions.ViewMostRecentTransaction;
import Controllers.Helpers.ConfirmBoxController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import sun.awt.image.ImageWatched;

import javax.jws.soap.SOAPBinding;
import javax.swing.text.View;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewBalanceController implements Initializable {
    private Main main;
    private int currentUserID;
    private BankManager bankManager;


    @FXML
    private ComboBox<Account> accountComboBox;
    @FXML
    private GridPane accountInfoPane;
    @FXML
    private Label dateLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Label previousTransLabel;
    @FXML
    private Button undoTransactionButton;

    public void goToUserActionList() throws Exception{
        accountComboBox.getItems().clear();
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    /**
     * not dones yet
     * @throws Exception
     */
    public void undoTransactionPressed() throws Exception{
        main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()){
            //send request
        }else{

        }

    }

    public void viewAccountButton(){
            Account choice = accountComboBox.getValue();
        accountInfoPane.setVisible(true);
        try {
            dateLabel.setText(choice.getDateOfCreation().toString());
            accountBalanceLabel.setText(choice.getCurrencyBalance().toString());

            ViewMostRecentTransaction mostRecentTransaction = new ViewMostRecentTransaction(currentUserID, bankManager);

            Map<LocalDate, Transactions> recent = mostRecentTransaction.viewMostRecentTransaction(choice.getTransactionsList());
            Iterator<Map.Entry<LocalDate, Transactions>> entries = recent.entrySet().iterator();
            Map.Entry<LocalDate, Transactions> entry;
            LocalDate mapKey = null;
            while (entries.hasNext()) {
                entry = entries.next();
                mapKey = entry.getKey();
                //keeps iterating until the last item
                //sets map-key to last item
            }
            LocalDate date = mapKey;
            Transactions transactions = recent.get(mapKey);
            if (transactions != null) {
                previousTransLabel.setText("Most recent transaction is " + transactions + " on " + date);
                undoTransactionButton.setVisible(true);
            } else {
                previousTransLabel.setText("No recent transactions");
                undoTransactionButton.setVisible(false);
            }
        }catch (Exception ex){
            System.out.println("Nothing's going wrong");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printAccounts(currentUser.getAccountList());
        ObservableList<Account> allAccounts = viewAccount.getAllAccounts();
        accountComboBox.setItems(allAccounts);
        accountInfoPane.setVisible(false);
        undoTransactionButton.setVisible(false);



    }
}
