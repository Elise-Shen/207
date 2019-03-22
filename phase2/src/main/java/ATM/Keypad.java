package ATM;
import java.util.Scanner;

public class Keypad {

    /**
     * Return the integer input entered by the current user.
     * @param promptedMessage the message prompted to the current user.
     */
    public int getIntInput(String promptedMessage) {
        Scanner input = new Scanner(System.in);
        System.out.println(promptedMessage);
        return input.nextInt();
    }

    /**
     * Return the String input entered by the current user.
     * @param promptedMessage the message prompted to the current user.
     */
    public String getStringInput(String promptedMessage) {
        Scanner input = new Scanner(System.in);
        System.out.println(promptedMessage);
        return input.nextLine();
    }
}
