package ATM;

/**
 * Simulates the cash storage component of the ATM
 */
public class CashStorage {

    private int numFive; //number of five dollar bills in ATM
    private int numTen; // number of ten dollar bills in ATM
    private int numTwenty; // Number of twenty dollar bills in ATM
    private int numFifty; // number of fifty dollar bills in ATM
    private int numHun; // number of hundred dollar bills in ATM
    public final int MAXSTOCK = 1000;//maximum stock of each type of bills the ATM can hold

    public CashStorage(){

        numFive = MAXSTOCK;
        numTen = MAXSTOCK;
        numTwenty = MAXSTOCK;
        numFifty = MAXSTOCK;
        numHun = MAXSTOCK;
    }

    /**
     *
     * @param amt amount of dollars withdrawn
     */
    public void dispensCash(int amt){



    }

    /**
     *
     * @param type type of bill
     * @param amt amt of dollars
     * @return whether the atm has enough bills
     */
    public boolean enoughBills(int type, int amt){

        return true;//place holder

    }

    /**
     *
     * @param type type of bill
     * @return return (number of type of bill) > 20
     */
    public boolean enoughBills(int type){
        return true;//placeholder
    }

    /**
     * adds more bills
     * called when User deposits bills
     * or when BankManager restocks the bills
     * @param type
     * @param amt
     */
    public void addBills(int type, int amt){

    }

}
