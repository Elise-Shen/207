package Controllers;

import ATM.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BankManagerLoginController implements Initializable {

    private Main main;

    public void goBack() throws  Exception{
        main.showMainView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
