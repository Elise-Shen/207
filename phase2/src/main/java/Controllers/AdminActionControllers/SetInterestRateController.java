package Controllers.AdminActionControllers;
import ATM.ATM_Machine;
import ATM.Main;
import Accounts.Credit;
import Accounts.LineOfCredit;
import Accounts.Saving;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Map;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class SetInterestRateController implements Initializable {

    private Main main;

    @FXML
    private ChoiceBox<String> accountTypeChoiceBox;

    @FXML
    private TextField newInterestRate;

    @FXML
    private Label interestTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        ObservableList<String> choices = FXCollections.observableArrayList();
        Map<String, Double> interestAccounts = atm.getATMBankManager().getInterestAccountList();
        StringBuilder table = new StringBuilder();
        table.append("Interest Rate table: \n");
        Iterator<Map.Entry<String, Double>> entries = interestAccounts.entrySet().iterator();
        Map.Entry<String, Double> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            choices.add(mapKey);
            table.append(mapKey).append(" - ").append(interestAccounts.get(mapKey)).append("\n");
        }
        interestTable.setText(table.toString());
        accountTypeChoiceBox.setItems(choices);
    }

    public void okPressed() throws Exception{
        try {
            String accountChoice = accountTypeChoiceBox.getValue();
            double newRate = Double.valueOf(newInterestRate.getText());
            setInterestRate(accountChoice, newRate);
            accountTypeChoiceBox.getItems().clear();
            newInterestRate.clear();
        } catch (Exception e) {
            //
        }
        main.showNewBorderPane("/HelperBoxes/NewInterestRateBox.fxml");
    }

    public void goBack() throws Exception{
        main.showNewBorderPane("/AdminMainPage.fxml");
    }

    private void setInterestRate(String account, double newRate) {
        switch (account) {
            case "Saving" :
                Saving.setInterestRate(newRate);
                break;
            case "Credit" :
                Credit.setInterestRate(newRate);
                break;
            case "LineOfCredit" :
                LineOfCredit.setInterestRate(newRate);
                break;
        }
    }


}
