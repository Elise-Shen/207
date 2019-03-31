package Controllers.Helpers;

import Controllers.TransactionsController;
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

public class QuickDepositController implements Initializable {

    private static Stage window;
    @FXML private Label quickAmount;

    public void closeWindow(){
        window.close();
    }

    public static Stage getWindow(){
        return window;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window = new Stage();
        quickAmount.setText(TransactionsController.quickDeposit.getAmountDeposited().toString());
    }
    
}
