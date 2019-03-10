package ATM;

public class Main {

    public static void main(String[] args) {

        ATM_Machine atm = new ATM_Machine();
        atm.run();

        if (atm.isMidnight()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //
            }
        }

    }
}
