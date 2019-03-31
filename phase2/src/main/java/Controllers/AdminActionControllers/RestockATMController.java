package Controllers.AdminActionControllers;

import ATM.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RestockATMController implements Initializable {

    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBack() throws Exception{
        main.showNewBorderPane("/AdminMainPage.fxml");
    }

    public void restockButtonPressed() throws Exception{
        ATM_Machine atm = main.getCurrentATM();
        atm.getCashStorage().setToMaxStock();
        main.showNewBorderPane("/HelperBoxes/RestockedMessageBox.fxml");
    }
}
