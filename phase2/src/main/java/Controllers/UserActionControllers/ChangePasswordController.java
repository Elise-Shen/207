package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField currentPass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField newPassAgain;

    public void goToUserActionList() throws Exception{
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    public void confirmChangePass() throws Exception{
        String currentPassword = currentPass.getText();
        String newPassword = newPass.getText();
        String newPasswordAgain = newPassAgain.getText();
        ATM_Machine atm = Main.getCurrentATM();
        BankManager bankManager = atm.getATMBankManager();
        User currentUser = bankManager.getUser(atm.getCurrentUserID());
        if(currentUser.getPassword().equals(currentPassword) && newPassword.equals(newPasswordAgain) && !newPassword.isEmpty()){
            bankManager.setPassword(currentUser, newPasswordAgain);
            System.out.println("New Password: " + newPasswordAgain);
        }
        goToUserActionList();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
