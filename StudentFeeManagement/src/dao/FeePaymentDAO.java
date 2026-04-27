package dao;

import db.DBConnection;

import java.sql.*;

public class FeePaymentDAO {

    public void payFee(int studentId, String month) throws Exception {
        String sql = "UPDATE Fee_Payment SET status='Paid', payment_date=CURDATE() WHERE student_id=? AND month=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, studentId);
        ps.setString(2, month);

        if (ps.executeUpdate() == 0)
            throw new Exception("Payment record not found");

        con.close();
        System.out.println("Payment Successful!");
    }

    public void feeHistory(int studentId) throws Exception {
        String sql = "SELECT * FROM Fee_Payment WHERE student_id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("month") + " | " +
                    rs.getDouble("total_fee") + " | " +
                    rs.getString("status"));
        }
        con.close();
    }
}
