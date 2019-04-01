package Controllers.EmployeeControllers;

import ATM.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.money.CurrencyUnit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.Iterator;

public class SetMaximumStorageController implements Initializable {

    @FXML
    private ComboBox<String> selectCurrency;

    @FXML
    private Label result;

    @FXML
    private TextField text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> currencies = FXCollections.observableArrayList();
        Iterator<Map.Entry<String, CurrencyStorage>> entries = Main.getCurrentATM().getCashStorage().
                getCashStorage().entrySet().iterator();
        Map.Entry<String, CurrencyStorage> entry;
        String mapKey;
        while (entries.hasNext()) {
            entry = entries.next();
            mapKey = entry.getKey();
            currencies.add(mapKey);
        }
        selectCurrency.setItems(currencies);
    }

    public void goBack() throws Exception {
        text.clear();
        Main.showNewBorderPane("/EmployeeActionPage.fxml");
    }

    public void okPressed() {
        String currencyUnit = selectCurrency.getValue();
        try {
            int newMaxStock = Integer.parseInt(text.getText());
            if (newMaxStock >= 0) {
                CurrencyStorage cs = Main.getCurrentATM().getCashStorage().getCashStorage().get(currencyUnit);
                int oldMaxStock = cs.getMaxStock();
                cs.setMaxStock(newMaxStock);
                result.setText("The maximum stock for any cash type in " + currencyUnit + " was changed from " +
                        oldMaxStock + " to " + newMaxStock);
            } else {
                result.setText("Invalid input.");
            }
        } catch (Exception e) {
            //
        }
    }




}
