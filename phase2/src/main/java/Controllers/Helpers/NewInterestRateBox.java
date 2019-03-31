package Controllers.Helpers;

import ATM.ATM_Machine;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class NewInterestRateBox implements Initializable {

    private Main main;

    @FXML
    private Label newTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = main.getCurrentATM();
        Map<String, Double> interestAccounts = atm.getATMBankManager().getInterestAccountList();
        StringBuilder table = new StringBuilder();
        table.append("Interest Rate table: \n");
        Iterator<Map.Entry<String, Double>> entries = interestAccounts.entrySet().iterator();
        Map.Entry<String, Double> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            table.append(mapKey).append(" - ").append(interestAccounts.get(mapKey)).append("\n");
        }
        newTable.setText(table.toString());
    }

    public void okPressed() throws Exception{
        main.showNewBorderPane("/AdminActionResources/SetInterestRatePage.fxml");
    }

}
