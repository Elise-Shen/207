package ATM;

import Accounts.Account;
import Accounts.AccountFactory;
import Actions.Transactions;

import java.util.Random;

import java.util.ArrayList;

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
        // given userID -> return the user object
        User result = null;
        for(User u: userArrayList){
            if (u.getUserID()==userID){
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


    public boolean checkPassword (int userID, String inputPassword) {
        // pre: user exist, inputPassword is string
        // check if the password inputted from keyboard eq. to the user's password
        // find user obj.
        User currentUser = null;
        boolean result = false;
        // check if user exist
        for (int counter = 0; counter < userArrayList.size(); counter++) {
            if (userArrayList.get(counter).getUserID() == userID) {
                currentUser = userArrayList.get(counter);
            } else {
                System.out.println("User not exist");
            }

        }
        // check password
        if (currentUser != null) {
            if (currentUser.getPassword().equals(inputPassword)) {
                System.out.println("Successful Login");
                result = true;
            } else {
                System.out.println("Wrong Password");

            }
        }
        return result;
    }

    public void addTransaction(Transactions t){
        listOfTransactions.add(0, t);
    }

}

