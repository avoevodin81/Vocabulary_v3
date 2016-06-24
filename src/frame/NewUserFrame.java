package frame;

import listeners.NewUserFrameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Test on 13.06.2016.
 */
public class NewUserFrame extends JFrame{
    private JTextField email = new JTextField("email");
    private JTextField password = new JTextField("password");
    private JButton loginButton = new JButton("Login/Registration");

    public NewUserFrame() throws HeadlessException {
        super("Login/Registration");
        setFrame();
        setElements();
        addListeners();
        setVisible(true);
    }

    private void setFrame(){
        setSize(500, 70);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setElements(){
        email.setForeground(Color.GRAY);
        email.setHorizontalAlignment(JTextField.CENTER);
        password.setForeground(Color.GRAY);
        password.setHorizontalAlignment(JTextField.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10));
        panel.add(email);
        panel.add(password);
        panel.add(loginButton);
        getContentPane().add(new JTextField());

        getContentPane().add(panel);
    }

    private void addListeners(){
        new NewUserFrameListener(this);
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getPassword() {
        return password;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
