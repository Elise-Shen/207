package ATM;

import Controllers.Helpers.ConfirmBoxController;
import Controllers.Helpers.NotEnoughMoneyController;
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
    }

    public static void main(String[] args) {

        /*CurrencyUnit usd = Monetary.getCurrency("USD");
        CurrencyUnit cad = Monetary.getCurrency(Locale.getDefault());
        System.out.println(usd.getDefaultFractionDigits() + " " + usd.getNumericCode());
        MonetaryAmount amt = Monetary.getDefaultAmountFactory().setCurrency(usd).setNumber(200.03).create();
        Money usdMoney = Money.of(1.00, usd);

        Money cadMoney = Money.of(0, cad);
        Money added = Money.of(1000, cad);
        cadMoney = cadMoney.add(added);
        System.out.println(cadMoney);

        CurrencyConversion conversionCAD = MonetaryConversions.getConversion(cad);

        MonetaryAmount converted = usdMoney.with(conversionCAD);

        System.out.println(converted.toString());
*/
        //ATM_Machine atm = new ATM_Machine();
        //atm.run();
        atm = readATM("phase2/ATM.ser");
        //atm.run();
        launch(args);
    }

    private static void updateData() throws Exception{
        String filePath = "phase2/ATM.ser";
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


}
