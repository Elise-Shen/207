package Controllers;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backToBankManagerLogin() throws Exception{
        Main.showNewBorderPane("/BankManagerLoginPage.fxml");
    }

    public void restockATMButton()throws Exception{
        Main.showNewBorderPane("/AdminActionResources/RestockATMPage.fxml");
    }

    public void setInterestRateButton()throws Exception{
        Main.showNewBorderPane("/AdminActionResources/SetInterestRatePage.fxml");
    }

    public void viewAccountRequestsButton()throws Exception{
        Main.showNewBorderPane("/AdminActionResources/ViewAccountRequests.fxml");
    }

    public void viewUndoTransactButton() throws Exception{
        Main.showNewBorderPane("/AdminActionResources/ViewUndoTransactPage.fxml");
    }
}
