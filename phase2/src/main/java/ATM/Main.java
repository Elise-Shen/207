package ATM;

import Controllers.Helpers.ConfirmBoxController;
import Controllers.Helpers.CreatedAccountController;
import Controllers.Helpers.NotEnoughMoneyController;
import Controllers.Helpers.ReachRequestLimitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

import static javafx.application.Application.launch;

public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private static ATM_Machine atm;

    public static ATM_Machine getCurrentATM(){
        return atm;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ATM");
        showMainView();
    }

    public static void showMainView() throws  Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/StartUpPage.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        updateData();
    }

    public static void showNewBorderPane(String filename) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(filename));
        BorderPane page = loader.load();
        mainLayout.setCenter(page);
        updateData();

    }

    public static void showConfirmBox()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/HelperBoxes/ConfirmBox.fxml"));
        BorderPane confirmBox = loader.load();
        Stage window = ConfirmBoxController.getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning");
        window.setMinWidth(250);
        Scene scene = new Scene(confirmBox);
        window.setScene(scene);
        window.showAndWait();
        updateData();
    }

    public static void showNotEnoughMoney()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/HelperBoxes/NotEnoughMoneyPage.fxml"));
        BorderPane confirmBox = loader.load();
        Stage window = NotEnoughMoneyController.getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning");
        window.setMinWidth(250);
        Scene scene = new Scene(confirmBox);
        window.setScene(scene);
        window.showAndWait();
        updateData();
    }

    public static void showCreatedUser()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/HelperBoxes/CreatedAccountBox.fxml"));
        BorderPane confirmBox = loader.load();
        Stage window = CreatedAccountController.getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Message");
        window.setMinWidth(250);
        Scene scene = new Scene(confirmBox);
        window.setScene(scene);
        window.showAndWait();
        updateData();
    }

    public static void main(String[] args) {

        atm = readATM("phase2/ATM.ser");

        launch(args);
    }

    private static void updateData() throws Exception{
        String filePath = "ATM.ser";
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);


        // serialize the Map
        output.writeObject(atm);
        output.close();

    }

    private static ATM_Machine readATM(String filePath){
        ATM_Machine temp = null;
        try {
            InputStream fileIn = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(fileIn);
            ObjectInput input = new ObjectInputStream(buffer);
            temp = (ATM_Machine) input.readObject();
            input.close();
            //fileIn.close();
        }catch (IOException ex){
            ex.printStackTrace();
            temp = new ATM_Machine();
            System.out.print("Ser file not found. Creating fresh ATM");
        }catch(ClassNotFoundException ex){
            System.out.print("ATM not found!");
            ex.printStackTrace();
        }

        return temp;
    }


    public static void showReachRequestLimit() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/HelperBoxes/ReachRequestLimit.fxml"));
        BorderPane confirmBox = loader.load();
        Stage window = ReachRequestLimitController.getWindow();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning");
        window.setMinWidth(250);
        Scene scene = new Scene(confirmBox);
        window.setScene(scene);
        window.showAndWait();
        updateData();
    }
}
