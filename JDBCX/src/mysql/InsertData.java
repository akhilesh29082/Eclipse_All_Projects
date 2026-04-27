package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertData {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "hr";
        String password = "hr123";

        try {
            // Load Oracle Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to Oracle!");

            // Insert into departments_1
            String deptQuery = "INSERT INTO departments_1 VALUES (?, ?)";
            PreparedStatement deptStmt = con.prepareStatement(deptQuery);

            deptStmt.setInt(1, 3);
            deptStmt.setString(2, "Civil");
            deptStmt.executeUpdate();

            deptStmt.setInt(1, 4);
            deptStmt.setString(2, "Electrical");
            deptStmt.executeUpdate();

            // Insert into Student_1
            String studentQuery = "INSERT INTO Student_1 VALUES (?, ?, ?, ?)";
            PreparedStatement studentStmt = con.prepareStatement(studentQuery);

            studentStmt.setInt(1, 103);
            studentStmt.setString(2, "Rahul");
            studentStmt.setInt(3, 22);
            studentStmt.setInt(4, 3);
            studentStmt.executeUpdate();

            studentStmt.setInt(1, 104);
            studentStmt.setString(2, "Priya");
            studentStmt.setInt(3, 21);
            studentStmt.setInt(4, 4);
            studentStmt.executeUpdate();

            System.out.println("Data Inserted Successfully!");

            deptStmt.close();
            studentStmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}