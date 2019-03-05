package ATM;

import Accounts.Accounts;

import java.util.ArrayList;

public class BankManager {

    private ArrayList<User>  users = new ArrayList<User>;
    private AccountFactory accountFactory = new AccountFactory();





    public void createAccount(User user, String type) {
        if (approveAccount()){
            Accounts newAcc = accountFactory.create(type);
            user.addAcc(newAcc);


        }
    }

    private boolean approveAccount(){return true}



    public ArrayList<User> getUsers() {
        return users;
    }
}
