package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import Actions.UserActions;
import Actions.ViewCreationDate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class DateOfCreationController implements Initializable {

    private Main main;
    @FXML
    private Label dateLabel;

    public void goToUserActionList() throws Exception{
        main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        BankManager bankManager = atm.getATMBankManager();
        UserActions viewDates = new ViewCreationDate(atm.getCurrentUserID(), bankManager);

        //StringBuilder msg = new StringBuilder();
        dateLabel.setText("Test");



    }
}
