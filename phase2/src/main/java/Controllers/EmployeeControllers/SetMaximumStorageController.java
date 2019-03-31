package Controllers.EmployeeControllers;

import ATM.ATM_Machine;
import ATM.CashStorage;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SetMaximumStorageController implements Initializable {

    @FXML
    private Label currentMaxStock;

    @FXML
    private TextField text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CashStorage cs = Main.getCurrentATM().getCashStorage();

    }

    public void goBack() throws Exception {
        text.clear();
        Main.showNewBorderPane("/EmployeeActionPage.fxml");
    }

    


}
