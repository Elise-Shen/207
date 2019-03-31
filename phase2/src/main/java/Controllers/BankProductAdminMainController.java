package Controllers;

import ATM.Main;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class BankProductAdminMainController implements Initializable {

    public void goBack() throws Exception{
        Main.showNewBorderPane("/BankEmployeeMainPage.fxml");
    }

    public void viewProductButton() throws  Exception{
        Main.showNewBorderPane("/AdminActionResources/ViewProductRequest.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
