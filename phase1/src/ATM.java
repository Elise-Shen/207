import java.util.Scanner;

public class ATM {

    private static final int Balance = 1;
    private static final int Deposit = 2;

    private boolean userAuthenticated;
    private int currendUserID;
    private CashStorage cashStorage;
    private BankManager bankmanager;


    public ATM(){




    }

    public void run(){

        while (true) {

            while (!userAuthenticated){
                System.out.println("Welcome user " + currendUserID + "!");
                authenticateUser();

            }

            doTransactions();
            userAuthenticated = false;
            currendUserID = 0;

        }


    }

    public boolean authenticateUser() {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your password");
        String pass = input.nextLine();

        return bankManager.authenticateUser(currendUserID, pass);
    }

    public void doActions(){

        Action currAction = null; //current action
        boolean exited = false;

        while (!exited){

            System.out.println("Choose your action");
            System.out.println("1 - View Balance");
            System.out.println("2 - Deposit");
            System.out.println("0 - exit");

            Scanner input = new Scanner(System.in);
            System.out.println("Enter your choice");
            int type = input.nextInt();

            switch (type){
                case Balance:
                    currAction = new ViewBalance(currendUserID, bankmanager);
                    break;

                case Deposit:
                    currAction = new DepositMoney(currendUserID, bankmanager, cashStorage);
                    break;

                case 0:
                    exited = true;

                    default:
                        System.out.println("please enter again");

            }

            currAction.exucute();


        }

    }


    public static void main(String[] args) {

        }
    }
}

