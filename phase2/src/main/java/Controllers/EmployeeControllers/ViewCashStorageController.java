package Controllers.EmployeeControllers;

import ATM.CashStorage;
import ATM.CurrencyStorage;
import ATM.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewCashStorageController implements Initializable {

    @FXML
    private Label cashStorage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CashStorage cs = Main.getCurrentATM().getCashStorage();
        Map<String, CurrencyStorage> cashStorage = cs.getCashStorage();
        Iterator<Map.Entry<String, CurrencyStorage>> entries = cashStorage.entrySet().iterator();
        Map.Entry<String, CurrencyStorage> entry;
        String mapKey;
        StringBuilder sb = new StringBuilder();
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            sb.append(mapKey).append(":").append("\nBill Type - Bill Amount\n");
            sb.append(getCurrencyStorage(cashStorage.get(mapKey)));
        }
        this.cashStorage.setText(sb.toString());
    }

    public void goBack() throws Exception {
        Main.showNewBorderPane("/EmployeeActionPage.fxml");
    }

    /**
     * Return the storage detail of a specified currency type.
     */
    private StringBuilder getCurrencyStorage(CurrencyStorage cs) {
        Map<Integer, Integer> currencyStorage = cs.getCurrencyStorage();
        Iterator<Map.Entry<Integer, Integer>> entries = currencyStorage.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        Integer mapKey;
        StringBuilder result = new StringBuilder();
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            result.append(mapKey).append(" - ").append(currencyStorage.get(mapKey)).append("\n");
        }
        return result;
    }
}
