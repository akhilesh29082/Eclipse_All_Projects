package hostel;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import database.DBConnection;

public class AddStudent extends JFrame implements ActionListener {

    JTextField t1, t2, t3, t4, t5;
    JButton save;

    AddStudent() {
        setTitle("Add Student");
        setSize(500, 500);
        setLayout(null);

        JLabel l1 = new JLabel("Student ID");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180, 50, 200, 30);
        add(t1);

        JLabel l2 = new JLabel("Name");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180, 100, 200, 30);
        add(t2);

        JLabel l3 = new JLabel("Mobile No");
        l3.setBounds(50, 150, 100, 30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(180, 150, 200, 30);
        add(t3);

        JLabel l4 = new JLabel("Course");
        l4.setBounds(50, 200, 100, 30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(180, 200, 200, 30);
        add(t4);

        JLabel l5 = new JLabel("Year");
        l5.setBounds(50, 250, 100, 30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(180, 250, 200, 30);
        add(t5);

        save = new JButton("Save");
        save.setBounds(180, 320, 100, 30);
        save.addActionListener(this);
        add(save);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, Integer.parseInt(t1.getText()));
            pst.setString(2, t2.getText());
            pst.setString(3, t3.getText());
            pst.setString(4, t4.getText());
            pst.setString(5, t5.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Added Successfully");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}