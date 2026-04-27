import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "hr",
                "hr123"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // ✅ TEST METHOD
    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            System.out.println("✅ Connection Successful");
        } else {
            System.out.println("❌ Connection Failed");
        }
    }
}