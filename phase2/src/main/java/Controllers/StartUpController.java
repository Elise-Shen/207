package Controllers;



import ATM.Main;
import Controllers.Helpers.ConfirmBoxController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StartUpController implements Initializable {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label dateTimeLabel;

    @FXML
    public void loadUserLogin() throws Exception{
        Main.showNewBorderPane("/UserLoginPage.fxml");
    }

    public void loadBankManagerLogin() throws Exception{
        Main.showNewBorderPane("/BankManagerLoginPage.fxml");
    }

    public void bankEmployeeLogin()throws Exception{
        Main.showNewBorderPane("/BankEmployeeMainPage.fxml");
    }

    private void initClock(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            /*int second = LocalDateTime.now().getSecond();
            int minute = LocalDateTime.now().getMinute();
            int hour = LocalDateTime.now().getHour();
            currentTimeLabel.setText(hour + ":" + minute + ":" + second);*/
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTimeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
    }

}
