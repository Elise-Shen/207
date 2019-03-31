package Controllers.UserActionControllers;

import ATM.*;
import Accounts.Account;
import Accounts.Saving;
import Actions.ViewAccount;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class BankProductController implements Initializable {

    private Main main;
    private int currentUserID;
    private User currentUser;
    private BankManager bankManager;
    private BankProductsEmployee bankProductsEmployee;

    @FXML
    private TextField productAmount;
    @FXML
    private TextField productLength;
    @FXML
    private ComboBox<Account> accountCombo;
    @FXML
    private Label longTermWarning;
    @FXML
    private Label shortTermWarning;
    @FXML
    private Label sucessLable;

    private int productType;
    private int amount;
    private int length;

    public void longTermMortgage() throws Exception{
        productType = 1;
        productLength.clear();
        productLength.setPromptText("Please input an integer");
        productLength.setEditable(true);
    }

    public void shortTermMortgage() throws Exception {
        productType = 2;
        productLength.clear();
        productLength.setPromptText("Please input an integer");
        productLength.setEditable(true);
    }

    public void highRiskInvestment() throws Exception {
        productType = 3;
        productLength.setText("0");
        productLength.setEditable(false);
    }

    public void lowRiskInvestment() throws Exception {
        productType = 4;
        productLength.setText("0");
        productLength.setEditable(false);
    }

    public void goToUserActionList() throws Exception{
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    public void submitButton() throws Exception{
        try{
            boolean isValid = true;
            longTermWarning.setVisible(false);
            shortTermWarning.setVisible(false);
            amount = new Integer(productAmount.getText());
            length = new Integer(productLength.getText());
            if (productType == 1){
                if (length <= 12){
                    productLength.clear();
                    longTermWarning.setVisible(true);
                    isValid = false;
                }
            }
            else if (productType == 2){
                if (length > 12){
                    productLength.clear();
                    shortTermWarning.setVisible(true);
                    isValid = false;
                }
            }
            if(isValid && currentUser.product_getCount()<1) {
                currentUser.product_incrementCount();
                int accountID = accountCombo.getValue().getAccountID();
                bankProductsEmployee.requestProducts(currentUserID,accountCombo.getValue().getAccountID(),
                        productType,amount,length);
                sucessLable.setText("User " + currentUserID + " request a " +
                        bankProductsEmployee.getProductName(productType) + " on account " + accountID + " with amount " + amount);
            }else { main.showReachRequestLimit();}
        } catch (NumberFormatException e){}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        bankProductsEmployee = atm.getBankProductsEmployee();
        currentUserID = atm.getCurrentUserID();
        currentUser = bankProductsEmployee.getUser(currentUserID);
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printSavingAccounts(currentUser.getAccountList());
        ObservableList<Account> allSavingAccounts = viewAccount.getSavingAccounts();
        accountCombo.setItems(allSavingAccounts);
        longTermWarning.setVisible(false);
        shortTermWarning.setVisible(false);

        LocalTime currentTime = LocalTime.now();
        LocalTime countResetTime = LocalTime.MIDNIGHT;
        if(currentTime == countResetTime){
            currentUser.product_resetCount();
        }
    }
}

