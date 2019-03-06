package Accounts;
import java.util.Date;

public class Chequing extends AssetAccount {

    private int accountType;

    public Chequing() {
        accountType = 1;
    }


    public void decreaseBalance(double money) {
        if (balance - money < -100) {
            balance -= money;
        } else {
            System.out.println("Not enough balance!");
        }
    }
}
