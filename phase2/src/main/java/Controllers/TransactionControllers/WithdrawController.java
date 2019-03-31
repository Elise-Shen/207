package Controllers.TransactionControllers;

import ATM.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawController implements Initializable {
    private Main main;

    public void goToTransactionList() throws Exception{
        main.showNewBorderPane("/TransactionPage.fxml");
    }

    public void withdrawButton(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
