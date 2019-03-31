package Controllers.UserActionControllers;

import ATM.ATM_Machine;
import ATM.BankManager;
import ATM.Main;
import ATM.User;
import Accounts.Account;
import Accounts.AssetAccount;
import Accounts.DebtAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NetTotalController implements Initializable {

    @FXML
    private Label NetTotalLabel;

    public void goToUserActionList() throws Exception{
        Main.showNewBorderPane("/UserActionsPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = Main.getCurrentATM();
        BankManager bankManager = atm.getATMBankManager();
        int userID = atm.getCurrentUserID();
        User currentUser = bankManager.getUser(userID);
        List<Account> accounts = currentUser.getAccountList();
        CurrencyUnit currencyUnit = Monetary.getCurrency("CAD");
        MonetaryAmount netTotal = Money.of(0, currencyUnit);
        for(Account account: accounts){
            if (account instanceof AssetAccount) {
                CurrencyConversion currencyConversion = MonetaryConversions.getConversion(currencyUnit);
                MonetaryAmount converted = account.getCurrencyBalance().with(currencyConversion);
                netTotal = netTotal.add(converted);
            }
            else if (account instanceof DebtAccount){
                CurrencyConversion currencyConversion = MonetaryConversions.getConversion(currencyUnit);
                MonetaryAmount converted = account.getCurrencyBalance().with(currencyConversion);
                netTotal = netTotal.subtract(converted);
            }
        }
        NetTotalLabel.setText("Net total: " + netTotal.toString());
    }
}
