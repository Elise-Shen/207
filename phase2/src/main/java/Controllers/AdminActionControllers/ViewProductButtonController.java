package Controllers.AdminActionControllers;

import ATM.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class ViewProductButtonController implements Initializable {

    private Map<Integer, List<Integer>> productRequests;

    private BankProductsEmployee bankProductsEmployee;

    @FXML
    private Label Requests;

    @FXML
    private Label sucessfulMessage;

    @FXML
    private ChoiceBox<String> requestChoiceBox;

    public void goBack() throws Exception {
        requestChoiceBox.getItems().clear();
        Main.showNewBorderPane("/BankProductAdminMainPage.fxml");
    }

    public void okButton() throws Exception {
        int choice = new Integer(requestChoiceBox.getValue());
        Iterator<Map.Entry<Integer, List<Integer>>> entries = productRequests.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> entry;
        Integer mapKey = 0;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        List<Integer> productStat = productRequests.get(mapKey);
        int accountID = productStat.get(0);
        int productType = productStat.get(1);
        int productAmount = productStat.get(2);
        int productLength = productStat.get(3);
        bankProductsEmployee.createProduct(mapKey, accountID, productType, productAmount, productLength);
        productRequests.remove(mapKey);
        sucessfulMessage.setText("User " + mapKey + " has a " + bankProductsEmployee.getProductName(productType) +
                " in account " + accountID + " with amount " + productAmount + " duration " + productLength + " month.");
        System.out.println("Success");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = Main.getCurrentATM();
        bankProductsEmployee = atm.getBankProductsEmployee();
        productRequests = bankProductsEmployee.getProductRequests();
        Requests.setText(getProductRequests());
    }

    private String getProductRequests() {
        ObservableList<String> choices = FXCollections.observableArrayList();
        Iterator<Map.Entry<Integer, List<Integer>>> entries = productRequests.entrySet().iterator();
        Map.Entry<Integer, List<Integer>> entry;
        Integer mapKey;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Current Requests:");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            List<Integer> productStat = productRequests.get(mapKey);
            int accountID = productStat.get(0);
            int productType = productStat.get(1);
            int productAmount = productStat.get(2);
            int productLength = productStat.get(3);
            sb.append("\n").append(count).append(" - User ").append(mapKey).append(" requested a ").append(
                    bankProductsEmployee.getProductName(productType)).append(" in account ").append(accountID).
                    append(" with amount ").append(productAmount).append(" duration ").append(productLength).append(" months.");
            choices.add(String.valueOf(count));
        }
        requestChoiceBox.setItems(choices);
        return sb.toString();
    }
}
