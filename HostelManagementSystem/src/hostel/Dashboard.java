package hostel;

import javax.swing.*;

public class Dashboard extends JFrame {

    JButton addStudent, viewStudent, addRoom, allocateRoom, fees, complaints;

    Dashboard() {
        setTitle("Dashboard");
        setSize(600, 500);
        setLayout(null);

        addStudent = new JButton("Add Student");
        addStudent.setBounds(180, 50, 200, 40);
        add(addStudent);

        viewStudent = new JButton("View Students");
        viewStudent.setBounds(180, 110, 200, 40);
        add(viewStudent);

        addRoom = new JButton("Add Room");
        addRoom.setBounds(180, 170, 200, 40);
        add(addRoom);

        allocateRoom = new JButton("Room Allocation");
        allocateRoom.setBounds(180, 230, 200, 40);
        add(allocateRoom);

        fees = new JButton("Fee Management");
        fees.setBounds(180, 290, 200, 40);
        add(fees);

        complaints = new JButton("Complaint Management");
        complaints.setBounds(180, 350, 200, 40);
        add(complaints);

        setVisible(true);
    }
}