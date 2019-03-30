package Controllers;

import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class UserMainMenuController implements Initializable {

    private Main main;

    @FXML
    public void backToUserLogin() throws Exception{
        main.showNewBorderPane("/UserLoginPage.fxml");
    }

    @FXML
    public void userActionButton() throws Exception {
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @FXML
    public void transactionButton(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
