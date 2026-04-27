package hostel;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import database.DBConnection;

public class AddRoom extends JFrame implements ActionListener {

    JTextField t1, t2, t3, t4;
    JButton save;

    AddRoom() {
        setTitle("Add Room");
        setSize(450, 400);
        setLayout(null);

        JLabel l1 = new JLabel("Room ID");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180, 50, 180, 30);
        add(t1);

        JLabel l2 = new JLabel("Room Number");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180, 100, 180, 30);
        add(t2);

        JLabel l3 = new JLabel("Room Type");
        l3.setBounds(50, 150, 100, 30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(180, 150, 180, 30);
        add(t3);

        JLabel l4 = new JLabel("Capacity");
        l4.setBounds(50, 200, 100, 30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(180, 200, 180, 30);
        add(t4);

        save = new JButton("Save Room");
        save.setBounds(150, 270, 120, 35);
        save.addActionListener(this);
        add(save);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO room VALUES (?, ?, ?, ?, 0)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, Integer.parseInt(t1.getText()));
            pst.setString(2, t2.getText());
            pst.setString(3, t3.getText());
            pst.setInt(4, Integer.parseInt(t4.getText()));

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Room Added Successfully");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}