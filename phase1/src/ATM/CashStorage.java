package ATM;

/**
 * Simulates the cash storage component of the ATM
 * 1. check amount & multiple of 5
 * 2. withdrawl
 * 3. check if amount to add
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
     * 1. check if money is enouth
     * called from withdrawl
     * @param amount
     * @return
     */
    public boolean checkAmount(int amount){
        int sum = numFifty*50+numTwenty*20+numTen*10+numFive*5;
        if(sum>=amount && amount%5 == 0){
            return true;
        }
        else{
            System.out.println("Sorry, no available");// amout%5==0 or storage not enough
            return false;
        }
    }
    /**
     * 2. withdrawal money
     * reduce cash storage
     */
    public boolean withdrawal(int amount){
        int num50=0,num20=0,num10=0,num5=0;
        int makeup;
        int rest;
        boolean validAmount = checkAmount(amount);
        // calculate bills
        if (validAmount){
            num50 = amount / 50;
            makeup = checkBillStorage(num50,numFifty);
            rest = amount %50+makeup*50;
            if (rest != 0){
                num20 = amount/20;
                makeup = checkBillStorage(num20,numTwenty);
                rest = amount %20+makeup*20;
                if (rest != 0){
                    num10 = amount/10;
                    makeup = checkBillStorage(num10,numTen);
                    rest = amount %10+makeup*10;
                    if (rest != 0){
                        num5 = amount/5;
                        makeup = checkBillStorage(num5,numFive);
                        rest = amount %5+makeup*5;
                        rest = amount % 5;
                    }
                }
            }
        }
        // change storage
        numFive = numFive-num5;
        numTen = numTen-num10;
        numTwenty=numTwenty-num20;
        numFifty=num5-num50;
        System.out.println("You will get: ");
        System.out.println("num of $50:" + num50+",num of $20: "+num20+",num of $10:"+num10+",num of 5:"+num5);
        return validAmount;
    }
    public int checkBillStorage(int numBill,int numBillLeft){
        //numBill: needed numBillLeft: current storage in ATM
        if (numBill>numBillLeft)
            return (numBill-numBillLeft);
        else
            return 0;
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
