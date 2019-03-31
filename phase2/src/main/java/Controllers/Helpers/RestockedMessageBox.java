package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.money.Monetary;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class RestockedMessageBox implements Initializable {

    private Main main;

    @FXML
    private Label cashStorage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String localCurrency = Monetary.getCurrency(Locale.getDefault()).toString();
        ATM_Machine atm = main.getCurrentATM();
        int localCashStorage = atm.getCashStorage().getCashStorage().get(localCurrency).getTotalStorage();
        cashStorage.setText(String.valueOf(localCashStorage));

    }

    public void okPressed() throws Exception{
        main.showNewBorderPane("/AdminActionResources/RestockATMPage.fxml");
    }


}
