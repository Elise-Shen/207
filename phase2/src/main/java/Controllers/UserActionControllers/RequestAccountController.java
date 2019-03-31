package Controllers.UserActionControllers;

import ATM.*;
import Accounts.Account;
import Controllers.Helpers.ConfirmBoxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javax.money.CurrencyUnit;
import java.net.URL;
import java.util.Currency;
import java.util.ResourceBundle;

public class RequestAccountController implements Initializable {

    @FXML
    private ComboBox<String> isJointAccount;
    @FXML
    private ComboBox<CurrencyUnit> selectCurrency;
    @FXML
    private ComboBox<User> jointUser;
    @FXML
    private ComboBox<String>accountType;
    private static boolean joint = false;

    private static String type;
    private static CurrencyUnit currencyUnit;
    private static User jointUserChoice;
    private static User currentUser;

    private BankManager bankManager;
    private int currentUserID;


    public void goToUserActionsList()throws Exception{
        selectCurrency.getItems().clear();
        jointUser.getItems().clear();
        accountType.getItems().clear();
        isJointAccount.getItems().clear();
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    public void isJoint(){
        if(isJointAccount.getValue().equals("True")){
            joint = true;
            jointUser.setVisible(true);
        }else{
            jointUser.setVisible(false);
        }

    }

    public static User getJointUserChoice(){
        return jointUserChoice;
    }

    public static boolean getJoint(){
        return joint;
    }

    public static String getRequestType(){
        return type;
    }

    public void requestButton()throws Exception{
        Main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            joint = isJointAccount.getValue().equals("True");
            type = accountType.getValue();
            currencyUnit = selectCurrency.getValue();
            jointUserChoice = jointUser.getValue();
            if (joint) {
                switch (type) {
                    case "Chequing":
                        bankManager.requestAccount(currentUserID, jointUserChoice.getUserID(), 1, currencyUnit.toString());
                        break;
                    case "Savings":
                        bankManager.requestAccount(currentUserID, jointUserChoice.getUserID(), 2, currencyUnit.toString());
                        break;
                    case "Credit":
                        bankManager.requestAccount(currentUserID, jointUserChoice.getUserID(), 3, currencyUnit.toString());
                        break;
                    case "Line of Credit":
                        bankManager.requestAccount(currentUserID, jointUserChoice.getUserID(), 4, currencyUnit.toString());
                        break;
                    case "Student Loan":
                        bankManager.requestAccount(currentUserID, jointUserChoice.getUserID(), 5, currencyUnit.toString());
                        break;
                }
                currentUser.incrementCount();
                jointUserChoice.incrementCount();

            } else {
                switch (type) {
                    case "Chequing":
                        bankManager.requestAccount(currentUserID, 1, currencyUnit.toString());
                        break;
                    case "Savings":
                        bankManager.requestAccount(currentUserID, 2, currencyUnit.toString());
                        break;
                    case "Credit":
                        bankManager.requestAccount(currentUserID, 3, currencyUnit.toString());
                        break;
                    case "Line of Credit":
                        bankManager.requestAccount(currentUserID, 4, currencyUnit.toString());
                        break;
                    case "Student Loan":
                        bankManager.requestAccount(currentUserID, 5, currencyUnit.toString());
                        break;
                }
                currentUser.incrementCount();
            }

            Main.showNewBorderPane("/HelperBoxes/RequestedAccountBox.fxml");
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jointUser.setVisible(false);
        ObservableList<String> booleans = FXCollections.observableArrayList();
        booleans.add("True");
        booleans.add("False");
        isJointAccount.setItems(booleans);

        ObservableList<String> accounts = FXCollections.observableArrayList();
        accounts.add("Chequing");
        accounts.add("Savings");
        accounts.add("Credit");
        accounts.add("Line Of Credit");
        accounts.add("Student Loan");
        accountType.setItems(accounts);

        ObservableList<CurrencyUnit> currencies = FXCollections.observableArrayList();
        currencies.addAll(HelperMethods.getAvailableCurrencyUnit());
        selectCurrency.setItems(currencies);


        ATM_Machine atm = Main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        currentUser = bankManager.getUser(currentUserID);
        bankManager.readUserList();
        ObservableList<User> allUsers= bankManager.getAllUsers();
        allUsers.remove(currentUser);
        jointUser.setItems(allUsers);



    }
}
