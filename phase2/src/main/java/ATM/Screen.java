package ATM;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screen extends JFrame{

    private static final String TITLE = "ATM";

    private JButton exitButton;

    public Screen(){

        createView();

        setTitle(TITLE);
        setBounds(300, 200, 600, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit the program.");
                System.exit(0);
            }
        });
        add(exitButton);
        setResizable(false);

        startUpPage();
    }

    private void createView(){

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit the program.");
                System.exit(0);
            }
        });
        exitButton.setLocation(400, 400);
        exitButton.setSize(100, 25);
    }

    private void startUpPage(){
        JLabel startUp = new JLabel("<html>ATM starting Up ...<br>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "</html>");
        startUp.setLocation(200,100);
        startUp.setSize(150, 100);
        add(startUp);
        Timer timer = new Timer(3000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startUp.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private int loginPage(String message){
        JLabel login = new JLabel(message);
        login.setLocation(200, 100);
        login.setSize(300,150);
        JTextField userInput = new JTextField(10);
        userInput.setLocation(200, 300);
        add(login);
        add(userInput);
        return new Integer(userInput.getText());
    }

    public static void main(String[] args) { new Screen().setVisible(true); }
}
