package hostel;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import database.DBConnection;

public class RoomAllocation extends JFrame implements ActionListener {

    JTextField studentIdField, roomIdField;
    JButton allocate;

    RoomAllocation() {
        setTitle("Room Allocation");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Student ID");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        studentIdField = new JTextField();
        studentIdField.setBounds(150, 50, 150, 30);
        add(studentIdField);

        JLabel l2 = new JLabel("Room ID");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        roomIdField = new JTextField();
        roomIdField.setBounds(150, 100, 150, 30);
        add(roomIdField);

        allocate = new JButton("Allocate Room");
        allocate.setBounds(120, 170, 150, 35);
        allocate.addActionListener(this);
        add(allocate);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO room_allocation(student_id, room_id) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, Integer.parseInt(studentIdField.getText()));
            pst.setInt(2, Integer.parseInt(roomIdField.getText()));

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Room Allocated Successfully");

            studentIdField.setText("");
            roomIdField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in Room Allocation");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RoomAllocation();
    }
}