package dao;

import db.DBConnection;
import model.Student;

import java.sql.*;

public class StudentDAO {

    public void addStudent(Student s) throws Exception {
        String sql = "INSERT INTO Student(name, course, base_fee, discount) VALUES (?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, s.getName());
        ps.setString(2, s.getCourse());
        ps.setDouble(3, s.getBaseFee());
        ps.setDouble(4, s.getDiscount());

        ps.executeUpdate();
        con.close();
    }

    public void viewStudents() throws Exception {
        String sql = "SELECT * FROM Student";
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("student_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course"));
        }
        con.close();
    }

    public Student getStudent(int id) throws Exception {
        String sql = "SELECT * FROM Student WHERE student_id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next())
            throw new Exception("Invalid Student ID");

        Student s = new Student();
        s.setStudentId(id);
        s.setName(rs.getString("name"));
        s.setCourse(rs.getString("course"));
        s.setBaseFee(rs.getDouble("base_fee"));
        s.setDiscount(rs.getDouble("discount"));
        con.close();

        return s;
    }

    public void deleteStudent(int id) throws Exception {
        String sql = "DELETE FROM Student WHERE student_id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0)
            throw new Exception("Student not found");

        con.close();
    }
}
