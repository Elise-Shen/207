package Controllers.Helpers;

import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NotEnoughMoneyController implements Initializable {
    private static Stage window;
    @FXML
    public Label labelMessage;


    public void notEnoughOkPressed(){
        window.close();
    }

    public static Stage getWindow(){
        return window;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window = new Stage();
        labelMessage.setText("Not Enough Money!");
    }

}
