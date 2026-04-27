package hostel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1;

    LoginPage() {
        setTitle("Hostel Management System - Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Username:");
        l1.setBounds(50, 70, 100, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150, 70, 150, 30);
        add(tf1);

        l2 = new JLabel("Password:");
        l2.setBounds(50, 120, 100, 30);
        add(l2);

        pf1 = new JPasswordField();
        pf1.setBounds(150, 120, 150, 30);
        add(pf1);

        b1 = new JButton("Login");
        b1.setBounds(130, 180, 100, 30);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String username = tf1.getText();
        String password = pf1.getText();

        if(username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            new Dashboard();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }
}