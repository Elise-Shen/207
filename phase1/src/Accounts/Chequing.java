package Accounts;
import java.util.Date;

public class Chequing extends AssetAccount {

    public Chequing(Date creationDate) {
        super(creationDate);
    }

    public void decreaseBalance(double money) {
        if (balance - money < -100) {
            balance -= money;
        } else {
            System.out.println("Not enough balance!");
        }
    }
}
