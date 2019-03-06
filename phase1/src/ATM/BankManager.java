package ATM;

import Accounts.Account;
import Accounts.AccountFactory;
import java.util.Random;

import java.util.ArrayList;

public class BankManager {

    private ArrayList<User>  userArrayList = new ArrayList<User>();
    private ArrayList<Account>  accountArrayList = new ArrayList<Account>();
    private AccountFactory accountFactory = new AccountFactory();
    public int userNumExample=3;

    public BankManager(){
        // create 3 users for testing purpose
        createUserExample(userNumExample);
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

//    public void createAccount(User user, int type) {
//
//        if (approveAccount()){
//            Accounts newAcc = accountFactory.create(type);
//            user.addAcc(newAcc);
//
//        }
//    }
//
//    private boolean approveAccount(){return true;}



//    public ArrayList<User> getUsers() {
//        return users;
//    }
    public void undoTransaction (){

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
            if (currentUser.getPassword() == inputPassword) {
                System.out.println("Successful Login");
                result = true;
            } else {
                System.out.println("Wrong Password");

            }
        }
        return result;
    }

}

