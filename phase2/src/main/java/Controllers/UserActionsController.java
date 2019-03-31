package Controllers;

import ATM.BankManager;
import ATM.Main;
import ATM.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserActionsController implements Initializable {

    private Main main;
    private User currentUser;

    public void goToUserMainMenu() throws Exception{
        main.showNewBorderPane("/UserMainMenuPage.fxml");
    }

    public void changePasswordButton()throws Exception{
        main.showNewBorderPane("/UserActionResources/ChangePasswordPage.fxml");
        //done

    }

    public void netTotalButton()throws Exception{
        if(currentUser.getCount() == 0) {
            main.showNewBorderPane("/UserActionResources/NetTotalPage.fxml");
        }else{
            main.showReachRequestLimit();
        }

    }

    public void requestAccountButton()throws Exception{
        if(currentUser.getCount() == 0)
        main.showNewBorderPane("/UserActionResources/RequestAccountPage.fxml");

    }

    public void requestProductButton() throws Exception {
        main.showNewBorderPane("/UserActionResources/BankProductPage.fxml");
    }

    public void setPrimAccountButton() throws Exception{
        main.showNewBorderPane("/UserActionResources/SetPrimAccountPage.fxml");

    }

    public void accountSummaryButton() throws Exception{
        main.showNewBorderPane("/UserActionResources/AccountSummaryPage.fxml");

    }

    public void transactionHistoryButton() throws Exception{
        main.showNewBorderPane("/UserActionResources/TransactionHistory.fxml");
    }

    public void balanceButton() throws Exception{
        main.showNewBorderPane("/UserActionResources/ViewBalancePage.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int currentUserID = main.getCurrentATM().getCurrentUserID();
        BankManager bankManager = main.getCurrentATM().getATMBankManager();
        currentUser = bankManager.getUser(currentUserID);

    }
}
