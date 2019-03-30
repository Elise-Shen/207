package Controllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLoginController implements Initializable {

    @FXML
    private TextField text;

    @FXML
    private PasswordField passwordField;

    private Main main;

    @FXML
    public void goBack() throws  Exception{
        main.showMainView();
    }

    @FXML
    public void loginButtonPressed() throws  Exception{
        String userID = text.getText();
        String password = passwordField.getText();
        ATM_Machine atm = main.getCurrentATM();
        if(atm.userLogin(userID, password)){
            main.showNewBorderPane("/UserMainMenuPage.fxml");
        }





    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
