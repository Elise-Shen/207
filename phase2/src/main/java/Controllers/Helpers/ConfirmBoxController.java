package Controllers.Helpers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmBoxController implements Initializable {

    private static boolean confirmed = false;
    private static Stage window;

    public static Stage getWindow(){
        return  window;
    }


    public void confirmButton(){
        confirmed = true;
        window.close();


    }

    public void rejectButton(){
        confirmed = false;
        window.close();
    }

    public static boolean getConfirm(){
        return confirmed;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmed = false;
        window = new Stage();

    }
}
