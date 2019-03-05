package Actions;

import ATM.User;

public class ChangePin extends UserActions {

    private User user;
    private String password;

    public ChangePin(User user, String password){
        this.user = user;
        this.password = password;
    }

    /**
     * This method change the password.
     */
    @Override
    public void execute() {
        user.setPassword(password);
    }
}
