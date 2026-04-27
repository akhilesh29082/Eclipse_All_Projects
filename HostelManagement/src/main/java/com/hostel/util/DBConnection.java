package com.hostel.util;
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // For newer Oracle versions use: oracle.jdbc.OracleDriver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // double-check your Port (1521) and SID (xe)
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr123");
            
        } catch (ClassNotFoundException e) {
            System.out.println("DRIVER ERROR: Did you put the JAR in WEB-INF/lib?");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: Check if Oracle Service is running or if password is correct.");
            e.printStackTrace();
        }
        return con;
    }
}