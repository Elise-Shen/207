package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Actions.ViewAccount;
import Controllers.Helpers.ConfirmBoxController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SetPrimAccountController implements Initializable {
    @FXML  private ComboBox<Account> setPrimAccount;

    private User currentUser;



    public void setPrimaryAccount()throws Exception{
        Main.showConfirmBox();
        if(ConfirmBoxController.getConfirm()) {
            currentUser.setPrimaryAccount(setPrimAccount.getValue());
            setPrimAccount.getItems().clear();
            Main.showNewBorderPane("/UserActionsPage.fxml");

        }
    }

    public void toUserActionsList()throws Exception{
        setPrimAccount.getItems().clear();
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ATM_Machine atm = Main.getCurrentATM();
        BankManager bankManager = atm.getATMBankManager();
        int currentuserID = atm.getCurrentUserID();
        currentUser = bankManager.getUser(currentuserID);
        ObservableList<Account> chequingAccounts = ViewAccount.printChequingAccounts(currentUser.getAccountList());
        setPrimAccount.setItems(chequingAccounts);
    }
}
