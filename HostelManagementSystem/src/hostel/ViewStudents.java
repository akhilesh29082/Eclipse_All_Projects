package hostel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import database.DBConnection;

public class ViewStudents extends JFrame {

    JTable table;
    DefaultTableModel model;

    ViewStudents() {
        setTitle("View Students");
        setSize(700, 400);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Mobile No");
        model.addColumn("Course");
        model.addColumn("Year");

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("mobile_no"),
                    rs.getString("course"),
                    rs.getString("year")
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}