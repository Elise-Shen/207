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

    private JLabel inputOptions;
    private JLabel inputMessage;
    private JButton submitButton;
    private JTextField userInput;

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

        add(userInput);
        add(inputMessage);
        add(submitButton);
        add(inputOptions);

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
        exitButton.setLocation(450, 400);
        exitButton.setSize(100, 25);

        inputOptions = new JLabel();
        inputOptions.setLocation(200, 0);
        inputOptions.setSize(300, 200);
        inputOptions.setVisible(false);

        userInput = new JTextField();
        userInput.setSize(200,25);
        userInput.setLocation(150, 300);
        userInput.setVisible(false);

        inputMessage = new JLabel("Please enter your choice");
        inputMessage.setLocation(200, 250);
        inputMessage.setSize(300,50);
        inputMessage.setVisible(false);

        submitButton = new JButton("Submit");
        submitButton.setLocation(400, 300);
        submitButton.setSize(100, 25);
        submitButton.setVisible(false);
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
                setUpPage();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void setUpPage(){
        inputOptions.setVisible(true);
        userInput.setText("");
        userInput.setVisible(true);
        inputMessage.setText("Please enter your choice");
        inputMessage.setVisible(true);
        submitButton.setVisible(true);
    }

    public void setInputOptions(String text){
        inputOptions.setText(text);
    }

    public void setSubmitButton(ActionListener actionListener){
        submitButton.addActionListener(actionListener);
    }

    public void setInputMessage(String text){
        inputMessage.setText(text);
    }

    public String getUserInput(){
        return userInput.getText();
    }

    public void initializeUserInput(){
        userInput.setText("");
    }



    public static void main(String[] args) { new Screen().setVisible(true); }
}
