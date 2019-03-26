package ATM;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Screen extends JFrame{

    private static final String TITLE = "ATM";

    private JButton buttonExit;
    private JLabel label;

    private Screen(){

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
        add(buttonExit);
        setResizable(false);

        run();
    }

    private void createView(){

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit the program.");
                System.exit(0);
            }
        });
        buttonExit.setLocation(400, 400);
        buttonExit.setSize(100, 25);
    }

    private void run(){
        startUpPage();
        loginPage();
    }

    private void startUpPage(){
        label = new JLabel("ATM starting Up ...");
        label.setLocation(200,0);
        label.setSize(150, 50);
        add(label);
        Timer timer = new Timer(3000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ATM starting up ...");
                label.setVisible(false);

            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void loginPage(){

    }

    public static void main(String[] args) { new Screen().setVisible(true);; }
}
