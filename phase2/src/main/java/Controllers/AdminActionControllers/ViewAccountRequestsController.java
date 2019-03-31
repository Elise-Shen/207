package Controllers.AdminActionControllers;

import ATM.ATM_Machine;
import ATM.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

import ATM.BankManager;

public class ViewAccountRequestsController implements Initializable {

    @FXML
    private ChoiceBox<String> requestChoiceBox;

    @FXML
    private Label requestedAccount;

    @FXML
    private Label accountRequests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ATM_Machine atm = Main.getCurrentATM();
        String text = getAccountRequests(atm.getATMBankManager());
        accountRequests.setText(text);
    }

    public void okPressed() {
        Integer choice = Integer.valueOf(requestChoiceBox.getValue());
        BankManager bankManager = Main.getCurrentATM().getATMBankManager();
        Map<List<Integer>, Map<String, Integer>> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<List<Integer>, Map<String, Integer>>> entries = accountRequests.entrySet().iterator();
        Map.Entry<List<Integer>, Map<String, Integer>> entry;
        List<Integer> mapKey = null;
        int choiceCount = 0;
        while (entries.hasNext() && choiceCount != choice) {
            choiceCount++;
            entry = entries.next();
            mapKey = entry.getKey();
        }
        Map<String, Integer> value = accountRequests.get(mapKey);
        String firstKey = ((NavigableMap<String, Integer>) value).firstEntry().getKey();
        int accountType = accountRequests.get(mapKey).get(firstKey);
        bankManager.createAccount(mapKey, accountType, firstKey);
        bankManager.getAccountRequests().remove(mapKey);

        requestedAccount.setText("Created a(n) " + bankManager.getAccountName(accountType) + "account for user(s)" +
                getUsers(mapKey));
    }


    public void goBack() throws Exception {
        requestChoiceBox.getItems().clear();
        Main.showNewBorderPane("/AdminMainPage.fxml");
    }

    /**
     * Return a String representing all the account requests.
     */
    private String getAccountRequests(BankManager bankManager) {
        ObservableList<String> choices = FXCollections.observableArrayList();
        Map<List<Integer>, Map<String, Integer>> accountRequests = bankManager.getAccountRequests();
        Iterator<Map.Entry<List<Integer>, Map<String, Integer>>> entries = accountRequests.entrySet().iterator();
        Map.Entry<List<Integer>, Map<String, Integer>> entry;
        List<Integer> mapKey;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Current Requests");
        while (entries.hasNext()) {
            count++;
            entry = entries.next();
            mapKey = entry.getKey();
            Map<String, Integer> value = accountRequests.get(mapKey);
            String firstKey = ((NavigableMap<String, Integer>) value).firstEntry().getKey();
            Integer accountType =value.get(firstKey);
            sb.append("\n").append(count).append(" - User ").append(getUsers(mapKey)).append(" requested a ").append(
                    bankManager.getAccountName(accountType)).append(" account.");

            choices.add(String.valueOf(count));
        }
        return sb.toString();
    }

    /**
     * Return all the users requesting the same account.
     */
    private StringJoiner getUsers(List<Integer> userList) {
        StringJoiner sj = new StringJoiner(", ");
        for (Integer i : userList) {
            sj.add(i.toString());
        }
        return sj;
    }

}
