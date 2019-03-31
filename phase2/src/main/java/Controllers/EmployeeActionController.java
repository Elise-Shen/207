package Controllers;

import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeActionController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void goBack() throws Exception {
        Main.showNewBorderPane("/EmployeeMainMenuPage.fxml");
    }

    @FXML
    public void buyForeignCurrency() throws Exception {
        Main.showNewBorderPane("/EmployeeActionResources/BuyForeignCurrencyPage.fxml");
    }

    @FXML
    public void setCurrencyMaxStock() throws Exception {
        Main.showNewBorderPane("/EmployeeActionResources/SetMaxStock.fxml");
    }

    @FXML
    public void viewCashStorage() throws Exception {
        Main.showNewBorderPane("/EmployeeActionResources/ViewCashStorage.fxml");
    }
}
