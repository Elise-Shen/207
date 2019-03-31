package Controllers;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backToBankManagerLogin() throws Exception{
        main.showNewBorderPane("/BankManagerLoginPage.fxml");
    }

    public void restockATMButton()throws Exception{
        main.showNewBorderPane("/AdminActionResources/RestockATMPage.fxml");
    }

    public void setInterestRateButton()throws Exception{
        main.showNewBorderPane("/AdminActionResources/SetInterestRatePage.fxml");
    }

    public void viewAccountRequestsButton()throws Exception{
        main.showNewBorderPane("/AdminActionResources/ViewAccountRequestsPage.fxml");
    }

    public void viewProductsRequestsButton() throws Exception{
        main.showNewBorderPane("/AdminActionResources/ViewProductsRequestsPage.fxml");
    }

    public void viewUndoTransactButton() throws Exception{
        main.showNewBorderPane("/AdminActionResources/ViewUndoTransactPage.fxml");
    }
}
