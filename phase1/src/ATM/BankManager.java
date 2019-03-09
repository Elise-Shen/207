package ATM;

import Accounts.Account;
import Accounts.AccountFactory;
import Actions.Transactions;

import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;

public class BankManager {

    private ArrayList<User>  userArrayList = new ArrayList<>();
    private AccountFactory accountFactory = new AccountFactory();
    private ArrayList<Transactions> listOfTransactions = new ArrayList<>();
    public int userNumExample=3;


    public BankManager(){
        // create 3 users for testing purpose
        User user1 = new User(1, "abc123");
        userArrayList.add(user1);
        //createUserExample(userNumExample);
    }


    public User getUser(int userID){
        User result = null;
        for (User u: userArrayList) {
            if (u.getUserID() == userID) {
                result = u;
                break;
            }
        }
        return result;
    }
    public ArrayList<Account> getAccountArrayList(User user) {
        // given user obj -> return a list of account in correspond to theuser
        return user.getAccountList();
    }

    private void createUserExample(int userNum){
        for (int i=1;i<=userNum;i++){
            int userID = 100000+i;
            Random rand = new Random();
            int password = rand.nextInt(50);
            User user = new User (userID,Integer.toString(password));
            userArrayList.add(user);
        }

    }
    public void setPassword(User user, String newPassword){
        user.setPassword(newPassword);
    }

    public void createAccount(int userID, int accountType) {
        User user = getUser(userID);
        user.addAccount(accountFactory.getAccount(accountType));
    }


    public void undoTransaction (int userID){
        // recentTransaction_to -> recentTransaction_from
        // bills can't undo
        // this 2 variables are updated in every transaction

        // search user
        User user =  getUser(userID);
        // get recent transaction of this user
        Account from = user.getRecentTransaction_from();
        Account to = user.getRecentTransaction_to();
        double amount = user.getRecentTransaction_amount();
        from.increaseBalance(amount);
        to.decreaseBalance(amount);
        System.out.println("Transaction is Undo");
    }


    /**
     * Determine whether the user successfully login according to the password entered.
     */
    int checkLogin () {
        User currentUser;
        // check if user exist
        int currentID = 0;
        boolean validInput0 = false;
        boolean validInput1 = false;
        while (!validInput0) {
            Scanner input0 = new Scanner(System.in);
            System.out.println("\nPlease enter your User ID");
            currentID = input0.nextInt();
            currentUser = checkUserID(currentID);
            if (currentUser == null) {
                System.out.println("User does not exist. Please try again.");
            } else {
                validInput0 = true;
                while (!validInput1) {
                    // check if password is correct.
                    Scanner input1 = new Scanner(System.in);
                    System.out.println("\nPlease enter your password");
                    String pass = input1.nextLine();
                    if (currentUser.getPassword().equals(pass)) {
                        System.out.println("Successfully Login!");
                        validInput1 = true;
                    } else {
                        System.out.println("Wrong Password. Please try again.");
                    }
                }
            }
        }
        return currentID;
    }

    public void addTransaction(Transactions t){
        listOfTransactions.add(0, t);
    }

    /**
     * Check if the user with ID userID exists.
     * @param userID the ID to check.
     */
    private User checkUserID(int userID) {
        for (int counter = 0; counter < userArrayList.size(); counter++) {
            if (userArrayList.get(counter).getUserID() == userID) {
                return userArrayList.get(counter);
            }
        }
        return null;
    }

}

