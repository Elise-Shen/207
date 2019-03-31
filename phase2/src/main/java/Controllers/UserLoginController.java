package Controllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @FXML private Label wrongPasswordLabel;

    @FXML
    public void goBack() throws  Exception{
        Main.showMainView();
    }

    @FXML
    public void loginButtonPressed() throws  Exception{
        String userID = text.getText();
        String password = passwordField.getText();
        ATM_Machine atm = Main.getCurrentATM();
        if(atm.userLogin(userID, password)){
            if (atm.getATMBankManager().getUser(Integer.valueOf(userID)).isEmployee()) {
                Main.showNewBorderPane("/EmployeeMainMenuPage.fxml");
            } else {
                Main.showNewBorderPane("/UserMainMenuPage.fxml");
            }
        }else{
            wrongPasswordLabel.setVisible(true);
        }





    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wrongPasswordLabel.setVisible(false);

    }
}
