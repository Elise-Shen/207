package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.Main;
import Controllers.StartUpController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatedAccountController implements Initializable {
    private static Stage window;

    @FXML private Label userID;
    @FXML private Label userPassword;

    public void okButton(){
        window.close();

    }

    public static Stage getWindow(){
        return window;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window = new Stage();
        ATM_Machine atm = Main.getCurrentATM();
        userID.setText(StartUpController.newUser.getUserID() + "");
        userPassword.setText(StartUpController.newUser.getPassword());


    }
}
