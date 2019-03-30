package Controllers;

import ATM.Main;
import javafx.fxml.Initializable;
import sun.reflect.annotation.ExceptionProxy;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    private Main main;

    public void goToUserMenu() throws Exception{
        main.showNewBorderPane("/UserMainMenuPage.fxml");
    }

    public void depositButton() throws  Exception{
        main.showNewBorderPane("/Transactions/DepositPage.fxml");
    }

    public void withdrawButton() throws Exception{
        main.showNewBorderPane("/Transactions/WithdrawPage.fxml");
    }

    public void payBillsButton()throws Exception{
        main.showNewBorderPane("/Transactions/PayBillsPage.fxml");
    }

    public void transferMoneyButton()throws Exception{
        main.showNewBorderPane("/Transactions/TransferMoneyPage.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
