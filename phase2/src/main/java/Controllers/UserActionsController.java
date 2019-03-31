package Controllers;

import ATM.BankManager;
import ATM.Main;
import ATM.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserActionsController implements Initializable {
    private User currentUser;

    private Main main;
    public void goToUserMainMenu() throws Exception{
        Main.showNewBorderPane("/UserMainMenuPage.fxml");
    }

    public void changePasswordButton()throws Exception{
        Main.showNewBorderPane("/UserActionResources/ChangePasswordPage.fxml");
        //done

    }

    public void netTotalButton()throws Exception{
        Main.showNewBorderPane("/UserActionResources/NetTotalPage.fxml");

    }

    public void requestAccountButton()throws Exception{
        if(currentUser.getCount() == 0) {
            Main.showNewBorderPane("/UserActionResources/RequestAccountPage.fxml");
        }else{
            main.showReachRequestLimit();
        }
    }

    public void requestProductButton() throws Exception {
        Main.showNewBorderPane("/UserActionResources/BankProductPage.fxml");
    }

    public void setPrimAccountButton() throws Exception{
        Main.showNewBorderPane("/UserActionResources/SetPrimAccountPage.fxml");

    }

    public void accountSummaryButton() throws Exception{
        Main.showNewBorderPane("/UserActionResources/AccountSummaryPage.fxml");

    }

    public void transactionHistoryButton() throws Exception{
        Main.showNewBorderPane("/UserActionResources/TransactionHistory.fxml");
    }

    public void balanceButton() throws Exception{
        Main.showNewBorderPane("/UserActionResources/ViewBalancePage.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int currentUserID = Main.getCurrentATM().getCurrentUserID();
        BankManager bankManager = Main.getCurrentATM().getATMBankManager();
        currentUser = bankManager.getUser(currentUserID);

    }
}
