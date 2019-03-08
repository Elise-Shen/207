package ATM;

/**
 * Simulates the cash storage component of the ATM
 * 1. check if there's enough money for withdrawl
 * 2. withdrawl money
 * 3. check if each bill have 20
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
     * Step 1. Check if there;s enough money left for withdrawl
     * checkStorage(int amount)->boolean
     */
    public boolean checkStorage(int amount){
        int currentSum = numFive*5+numTen*10+numTwenty*20+numFifty*50;
        if(currentSum>=amount)
            return true;
        else{
            System.out.println("Sorry, the ATM has not enough cash right now");
            return false;
        }

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
