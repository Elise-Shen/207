package ATM;

/**
 * Simulates the cash storage component of the ATM
 */
public class CashStorage {

    private int numFive; //number of five dollar bills in ATM
    private int numTen; // number of ten dollar bills in ATM
    private int numTwenty; // Number of twenty dollar bills in ATM
    private int numFifty; // number of fifty dollar bills in ATM
    public final int MAXSTOCK = 1000;//maximum stock of each type of bills the ATM can hold

    public CashStorage(){

        numFive = MAXSTOCK;
        numTen = MAXSTOCK;
        numTwenty = MAXSTOCK;
        numFifty = MAXSTOCK;
    }

    /**
     *
     * @param amt amount of dollars withdrawn
     */
    public void dispenseCash(int amt){



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


    private boolean NotEnough5(){ return numFive < 20; }

    private boolean NotEnough10() { return numTen < 20; }

    private boolean NotEnough20() { return numTwenty < 20; }

    private boolean NotEnough50() {return numFifty < 20; }

    /**
     * adds more bills
     * called when User deposits bills
     * @param type
     * @param amt
     */
    public void addBills(int type, int amt){
        switch (type){
            case 5:
                numFive += amt;
                System.out.println("Added 5 dollar bills.");
                break;
            case 10:
                numTen += amt;
                System.out.println("Added 10 dollar bills.");
                break;
            case 20:
                numTwenty += amt;
                System.out.println("Added 20 dollar bills.");
                break;
            case 50:
                numFifty += amt;
                System.out.println("Added 50 dollar bills.");
                break;
            default:
                System.out.println("No bill is added.");
        }
    }

    /**
     * add bills under 20 to MAXSTOCK.
     * called when BankManager restocks the bills.
     */
    public void setToMaxStock(){
        if (NotEnough5()) { numFive = MAXSTOCK; }
        if (NotEnough10()) { numTen = MAXSTOCK; }
        if (NotEnough20()) { numTwenty = MAXSTOCK; }
        if (NotEnough50()) { numFifty = MAXSTOCK;}
    }

}
