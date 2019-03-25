package Accounts;

public class Chequing extends AssetAccount {

    /**
     * Creates a Chequing Account.
     */
    public Chequing(String currency) {
        super(currency);
    }

    @Override
    public int getAccountType() {
        return 1;
    }

    @Override
    public String toString(){
        return "Chequing";
    }

    @Override
    public void decreaseBalance(double money) {
        balance -= money;
    }
}
