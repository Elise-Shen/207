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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<Account> accountChoiceBox;
    @FXML
    private GridPane accountInfoPane;
    @FXML
    private Label dateLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Label previousTransLabel;

    public void goToUserActionList() throws Exception{
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    public void viewAccountButton(){
        Account choice = accountChoiceBox.getValue();
        accountInfoPane.setVisible(true);
        dateLabel.setText(choice.getDateOfCreation().toString());
        accountBalanceLabel.setText(choice.getCurrencyBalance().toString());

        ViewMostRecentTransaction mostRecentTransaction = new ViewMostRecentTransaction(currentUserID, bankManager);

        Map<LocalDate, Transactions> recent = mostRecentTransaction.viewMostRecentTransaction(choice.getTransactionsList());
        Iterator<Map.Entry<LocalDate, Transactions>> entries = recent.entrySet().iterator();
        Map.Entry<LocalDate, Transactions> entry;
        LocalDate mapKey = null;
        while(entries.hasNext()){
            entry = entries.next();
            mapKey = entry.getKey();
            //keeps iterating until the last item
            //sets map-key to last item
        }
        LocalDate date = mapKey;
        Transactions transactions = recent.get(mapKey);
        if (transactions != null) {
            previousTransLabel.setText("Most recent transaction is " + transactions + " on " + date);
        }else{
            previousTransLabel.setText("No recent transactions");
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
        accountChoiceBox.setItems(allAccounts);
        accountInfoPane.setVisible(false);



    }
}
