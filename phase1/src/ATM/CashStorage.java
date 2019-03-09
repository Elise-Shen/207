package ATM;

/**
 * Simulates the cash storage component of the ATM
 * 1. check amount & multiple of 5
 * 2. withdrawal
 * 3. check if amount to add
 */
public class CashStorage {

    private int numFive; //number of five dollar bills in ATM
    private int numTen; // number of ten dollar bills in ATM
    private int numTwenty; // Number of twenty dollar bills in ATM
    private int numFifty; // number of fifty dollar bills in ATM
    private final int MAXSTOCK = 1000;//maximum stock of each type of bills the ATM can hold

    public CashStorage(){

        numFive = MAXSTOCK;
        numTen = MAXSTOCK;
        numTwenty = MAXSTOCK;
        numFifty = MAXSTOCK;
    }

    /**
     * 1. check if money is enough
     * called from withdrawal
     * @param amount is the amount of money user want to withdrawal
     * @return whether the ATM has enough bill to give
     */
    private boolean checkAmount(int amount){
        int sum = numFifty*50+numTwenty*20+numTen*10+numFive*5;
        if (sum >= amount && amount%5 == 0) {
            return true;
        } else if (sum < amount){
            System.out.println("Money Withdrawal is not available due to insufficient cash storage." +
                    "\nWe are sorry for the inconvenience");// amount is not a multiple of 5 or storage not enough
        } else if (amount%5 != 0) {
            System.out.println("The amount of money to withdraw should be a multiple of 5. Please enter again.");
        }
        return false;
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
            num50 -= makeup;
            rest = amount %50+makeup*50;
            if (rest != 0){
                num20 = amount/20;
                makeup = checkBillStorage(num20,numTwenty);
                num20 -= makeup;
                rest = amount %20+makeup*20;
                if (rest != 0){
                    num10 = amount/10;
                    makeup = checkBillStorage(num10,numTen);
                    num10 -= makeup;
                    rest = amount %10+makeup*10;
                    if (rest != 0){
                        num5 = amount/5;
                    }
                }
            }
        }
        // change storage
        numFive = numFive-num5;
        numTen = numTen-num10;
        numTwenty=numTwenty-num20;
        numFifty=numFifty-num50;
        System.out.println("You will get: ");
        System.out.println(num50+" 50$ bills, "+num20+" 20$ bills, " + num10+" 10$ bills, "+num5 + " 5$ bills");
        return validAmount;
    }

    private int checkBillStorage(int numBill, int numBillLeft){
        //numBill: needed numBillLeft: current storage in ATM
        if (numBill>numBillLeft)
            return (numBill-numBillLeft);
        else
            return 0;
    }

    private boolean NotEnough5(){ return numFive < 20; }

    private boolean NotEnough10() { return numTen < 20; }

    private boolean NotEnough20() { return numTwenty < 20; }

    private boolean NotEnough50() {return numFifty < 20; }

    /**
     * adds more bills
     * called when User deposits bills
     * @param type the type of bill added to ATM
     * @param amt the amount of that type of bill
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
