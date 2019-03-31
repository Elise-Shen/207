package Controllers;

import ATM.ATM_Machine;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import sun.security.util.Password;

import java.net.URL;
import java.util.ResourceBundle;

public class BankEmployeeMainPageController implements Initializable {

    @FXML
    private PasswordField passwordField;

    public void loginPressed() throws Exception{
        String password = passwordField.getText();
        ATM_Machine atm = Main.getCurrentATM();
        if (atm.bankProductEmployeeLogin(password)){
            Main.showNewBorderPane("/BankProductAdminMainPage.fxml");
        }
    }

    public void goBack() throws Exception{
        Main.showMainView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
