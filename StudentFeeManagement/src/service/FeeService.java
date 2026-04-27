package service;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

public class FeeService {

    public double calculateFee(Student s) {
        double extraCharges = 2000; // lab/library/exam
        return s.getBaseFee() + extraCharges - s.getDiscount();
    }

    public void generateMonthlyFees(List<Student> students, String month) throws Exception {
        String sql = "INSERT INTO Fee_Payment(student_id, month, total_fee, status, payment_date) VALUES (?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        for (Student s : students) {
            double total = calculateFee(s);
            ps.setInt(1, s.getStudentId());
            ps.setString(2, month);
            ps.setDouble(3, total);
            ps.setString(4, "Pending");
            ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            ps.addBatch();
        }

        ps.executeBatch();
        con.close();
    }
}
