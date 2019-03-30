package Controllers;



import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartUpController implements Initializable {

    private Main main;

    @FXML
    private BorderPane rootPane;

    @FXML
    public void loadUserLogin() throws Exception{
        main.showNewBorderPane("/UserLoginPage.fxml");
    }

    public void loadBankManagerLogin() throws Exception{
        main.showNewBorderPane("/BankManagerLoginPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
