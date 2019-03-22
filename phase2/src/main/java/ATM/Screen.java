package ATM;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Screen {
    private JFrame f;
    private JButton exit;

    private Screen(){
        f = new JFrame("ATM");
        f.setBounds(300, 200, 600, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                System.out.println("Exit the program.");
            }
        });

        exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit the program.");
                System.exit(0);
            }
        });
        exit.setLocation(400, 400);
        exit.setSize(100, 25);
        f.add(exit);

        run();
    }

    private void run(){
        startUpPage();
        loginPage();
    }

    private void startUpPage(){
        JLabel label = new JLabel("ATM starting Up ...");
        label.setLocation(200,0);
        label.setSize(100, 50);
        f.add(label);
        Timer timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
            }
        });
        timer.start();
    }

    public void loginPage(){

    }

    public static void main(String[] args) { new Screen(); }
}
