package Controllers.Helpers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MultipleOfFiveController implements Initializable {
    private static Stage window;

    public static Stage getWindow(){
        return window;
    }

    public void okButton(){
        window.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        window = new Stage();
    }
}
