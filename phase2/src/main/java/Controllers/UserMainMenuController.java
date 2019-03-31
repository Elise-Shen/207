package Controllers;

import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class UserMainMenuController implements Initializable {

    @FXML
    public void backToUserLogin() throws Exception{
        Main.showNewBorderPane("/UserLoginPage.fxml");
    }

    @FXML
    public void userActionButton() throws Exception {
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @FXML
    public void transactionButton() throws Exception{
        Main.showNewBorderPane("/TransactionPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
