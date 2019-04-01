package Controllers.TransactionControllers;

import ATM.ATM_Machine;
import ATM.*;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.ViewAccount;
import Actions.WithdrawMoney;
import Controllers.Helpers.ConfirmBoxController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javax.money.MonetaryAmount;
import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawController implements Initializable {
    private BankManager bankManager;
    private int currentUserID;
    private CashStorage cashStorage;

    @FXML
    private Label withdrawAmountLabel;
    @FXML
    private ComboBox<Account> withdrawChoice;

    private WithdrawMoney withdrawMoney;
    public static Account withdrawAccount;
    public static MonetaryAmount amountWithdrawn;

    public String getWithdrawAmountText(){
        return withdrawAmountLabel.getText();
    }

    public void goToTransactionList() throws Exception{
        withdrawChoice.getItems().clear();
        Main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void withdrawButton()throws Exception{
        Main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            int amount = Integer.parseInt(getWithdrawAmountText());
            if (amount % 5 == 0) {
                withdrawAccount = withdrawChoice.getValue();

                withdrawMoney = new WithdrawMoney(currentUserID, bankManager, cashStorage);
                boolean enoughMoney = withdrawMoney.executeWithdraw(withdrawAccount, amount);
                if (enoughMoney) {
                    withdrawAccount.addTransaction(withdrawMoney);
                    amountWithdrawn = withdrawMoney.getAmountWithdrawn();
                    bankManager.getUser(currentUserID).addTransactions(withdrawMoney);
                    Main.showNewBorderPane("/HelperBoxes/WithdrawnBox.fxml");

                } else {
                    Main.showNotEnoughMoney();
                }
            }else{
                withdrawAmountLabel.setText("");
                Main.showMultipleOfFive();
        }
        }

    }

    public void backSpace(){
        withdrawAmountLabel.setText(getWithdrawAmountText().substring(0, getWithdrawAmountText().length()-1));
    }

    public void oneB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "1");
    }

    public void twoB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "2");
    }
    public void threeB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "3");
    }
    public void fourB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "4");
    }
    public void fiveB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "5");
    }
    public void sixB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "6");
    }
    public void sevenB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "7");
    }
    public void eightB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "8");
    }
    public void nineB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "9");
    }
    public void zeroB(){
        withdrawAmountLabel.setText(getWithdrawAmountText() + "0");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        withdrawAmountLabel.setText("");

        ATM_Machine atm = Main.getCurrentATM();
        bankManager = atm.getATMBankManager();
        currentUserID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(currentUserID);
        cashStorage = atm.getCashStorage();
        ViewAccount viewAccount = new ViewAccount(currentUserID, bankManager);
        viewAccount.printDepositAccounts(currentUser.getAccountList());
        ObservableList<Account> allDepositAccounts = viewAccount.getDepositAccounts();
        withdrawChoice.setItems(allDepositAccounts);

    }
}
