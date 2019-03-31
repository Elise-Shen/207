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
    private TextField text;

    @FXML
    private PasswordField passwordField;

    private Main main;

    public void goBack() throws  Exception{
        main.showMainView();
    }

    @FXML
    public void loginPressed() throws  Exception{
        String password = passwordField.getText();
        ATM_Machine atm = main.getCurrentATM();
        if(atm.managerLogin(password)){
            main.showNewBorderPane("/AdminMainPage.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
