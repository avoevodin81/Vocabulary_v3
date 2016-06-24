package listeners;

import frame.NewUserFrame;
import managers.NewUserManager;
import managers.SendEmail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Test on 13.06.2016.
 */
public class NewUserFrameListener implements ActionListener, FocusListener {
    private NewUserFrame frame;

    public NewUserFrameListener(NewUserFrame frame) {
        this.frame = frame;
        frame.getLoginButton().addActionListener(this);
        frame.getEmail().addFocusListener(this);
        frame.getPassword().addFocusListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frame.getLoginButton())) {
            if (!NewUserManager.isEmail(frame.getEmail().getText())) {
                JOptionPane.showMessageDialog(null, "Input the correct email!");
                frame.getEmail().setForeground(Color.RED);
            } else if (frame.getPassword().getText().equals("password") || frame.getPassword().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Input the password! Any symbols, any quantity of symbols.");
                frame.getPassword().setForeground(Color.RED);
            } else {
                NewUserManager manager = new NewUserManager(frame.getEmail().getText(), frame.getPassword().getText());
                if (manager.isNewEmail()) {
                    SendEmail email = new SendEmail(frame.getEmail().getText());
                    email.checkSend();
                    while (true) {
                        try {
                            String check = JOptionPane.showInputDialog("Input the registration password.");
                            if (check.equals(email.getCode())) {
                                manager.insert(manager.getUser());
                                JOptionPane.showMessageDialog(null, "The registration is successfull!");
                                frame.setVisible(false);
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "The registration password is not correct!");
                        } catch (NullPointerException e1) {
                            break;
                        }
                    }
                } else if (manager.isCorrectPassword()) {
                    JOptionPane.showMessageDialog(null, "The login is successfull!");
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "The email is correct, check the password!");
                    frame.getPassword().setForeground(Color.RED);
                }
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(frame.getEmail())) {
            if (frame.getEmail().getText().equals("email")) {
                frame.getEmail().setForeground(Color.BLACK);
                frame.getEmail().setText("");
            }
        } else if (e.getSource().equals(frame.getPassword())) {
            if (frame.getPassword().getText().equals("password")) {
                frame.getPassword().setForeground(Color.BLACK);
                frame.getPassword().setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource().equals(frame.getEmail())) {
            if (frame.getEmail().getText().equals("")) {
                frame.getEmail().setForeground(Color.GRAY);
                frame.getEmail().setText("email");
            }
        } else if (e.getSource().equals(frame.getPassword())) {
            if (frame.getPassword().getText().equals("")) {
                frame.getPassword().setForeground(Color.GRAY);
                frame.getPassword().setText("password");
            }
        }
    }
}
