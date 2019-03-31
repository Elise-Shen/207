package Controllers;



import ATM.ATM_Machine;
import ATM.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StartUpController implements Initializable {

    @FXML
    private Label dateTimeLabel;

    @FXML
    public void loadUserLogin() throws Exception{
        Main.showNewBorderPane("/UserLoginPage.fxml");
    }

    public void createUserButton(){

    }

    public void loadBankManagerLogin() throws Exception{
        Main.showNewBorderPane("/BankManagerLoginPage.fxml");
    }

    public void bankEmployeeLogin()throws Exception{
        Main.showNewBorderPane("/BankEmployeeMainPage.fxml");
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTimeLabel.setText(LocalDateTime.now().format(formatter));
            ATM_Machine atm = Main.getCurrentATM();

            try {
                if (LocalTime.now() == LocalTime.MIDNIGHT) {
                    Thread.sleep(300);
                }
            } catch (Exception e1) {
                //
            }
            atm.addInterests();
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
    }

}
