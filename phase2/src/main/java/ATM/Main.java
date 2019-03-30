package ATM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.util.Locale;

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
        atm.updateData();
    }

    public static void showNewBorderPane(String filename) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(filename));
        BorderPane userLogin = loader.load();
        mainLayout.setCenter(userLogin);
        atm.updateData();

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
        atm = new ATM_Machine();
        launch(args);
    }


}
