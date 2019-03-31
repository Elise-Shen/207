package Controllers;

import ATM.ATM_Machine;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BankManagerLoginController implements Initializable {


    @FXML
    private PasswordField passwordField;


    public void goBack() throws  Exception{
        Main.showMainView();
    }

    @FXML
    public void loginPressed() throws  Exception{
        String password = passwordField.getText();
        ATM_Machine atm = Main.getCurrentATM();
        if(atm.managerLogin(password)){
            Main.showNewBorderPane("/AdminMainPage.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
