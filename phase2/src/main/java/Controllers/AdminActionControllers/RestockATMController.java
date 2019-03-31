package Controllers.AdminActionControllers;

import ATM.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.Monetary;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class RestockATMController implements Initializable {

    @FXML
    private Label newCashStorage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBack() throws Exception{
        Main.showNewBorderPane("/AdminMainPage.fxml");
    }

    public void restockButtonPressed() {
        ATM_Machine atm = Main.getCurrentATM();
        atm.getCashStorage().setToMaxStock();
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        int localCashStorage = atm.getCashStorage().getCashStorage().get(localCurrency).getTotalStorage();
        newCashStorage.setText("Currenct local currency storage: " + String.valueOf(localCashStorage));
    }
}
