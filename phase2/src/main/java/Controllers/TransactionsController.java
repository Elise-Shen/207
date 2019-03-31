package Controllers;

import ATM.Main;
import javafx.fxml.Initializable;
import sun.reflect.annotation.ExceptionProxy;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {


    public void goToUserMenu() throws Exception{
        Main.showNewBorderPane("/UserMainMenuPage.fxml");
    }

    public void depositButton() throws  Exception{
        Main.showNewBorderPane("/Transactions/DepositPage.fxml");
    }

    public void withdrawButton() throws Exception{
        Main.showNewBorderPane("/Transactions/WithdrawPage.fxml");
    }

    public void payBillsButton()throws Exception{
        Main.showNewBorderPane("/Transactions/PayBillsPage.fxml");
    }

    public void transferMoneyButton()throws Exception{
        Main.showNewBorderPane("/Transactions/TransferMoneyPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
