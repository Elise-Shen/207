package Controllers.Helpers;

import ATM.Main;

import Controllers.UserActionControllers.RequestAccountController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestedAccountController implements Initializable {

    @FXML private Label doubleUser;
    @FXML private Label singleUser;
    @FXML private Label requestedAccount;
    @FXML private Label secondAccount;
    @FXML private Label with;

    public void okButton() throws Exception{
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doubleUser.setVisible(false);
        singleUser.setVisible(false);
        secondAccount.setVisible(false);
        with.setVisible(false);

        requestedAccount.setText(RequestAccountController.getRequestType());

        if(RequestAccountController.getJoint()){
            doubleUser.setVisible(true);
            secondAccount.setVisible(true);
            with.setVisible(true);
            secondAccount.setText(RequestAccountController.getJointUserChoice().toString());
        }else{
            singleUser.setVisible(true);
        }

    }
}
