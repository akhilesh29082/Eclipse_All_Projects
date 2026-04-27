import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        res.getWriter().println("<h2>AddStudentServlet is working</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String gender = req.getParameter("gender");
            String dob = req.getParameter("dob");
            String mobile = req.getParameter("mobile");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            String course = req.getParameter("course");
            String year = req.getParameter("year");
            String parentName = req.getParameter("parent_name");
            String parentContact = req.getParameter("parent_contact");
            String bloodGroup = req.getParameter("blood_group");
            String roomId = req.getParameter("room_id");
            String hostelId = req.getParameter("hostel_id");

            if (id == null || id.trim().isEmpty()) throw new Exception("Student ID is missing");
            if (name == null || name.trim().isEmpty()) throw new Exception("Name is missing");
            if (gender == null || gender.trim().isEmpty()) throw new Exception("Gender is missing");
            if (dob == null || dob.trim().isEmpty()) throw new Exception("Date of Birth is missing");
            if (mobile == null || mobile.trim().isEmpty()) throw new Exception("Mobile number is missing");
            if (email == null || email.trim().isEmpty()) throw new Exception("Email is missing");
            if (address == null || address.trim().isEmpty()) throw new Exception("Address is missing");
            if (course == null || course.trim().isEmpty()) throw new Exception("Course is missing");
            if (year == null || year.trim().isEmpty()) throw new Exception("Year is missing");
            if (parentName == null || parentName.trim().isEmpty()) throw new Exception("Parent name is missing");
            if (parentContact == null || parentContact.trim().isEmpty()) throw new Exception("Parent contact is missing");
            if (bloodGroup == null || bloodGroup.trim().isEmpty()) throw new Exception("Blood group is missing");

            Connection con = DBConnection.getConnection();
            if (con == null) {
                throw new Exception("Database connection is null. Check DBConnection.java");
            }

            int studentId = Integer.parseInt(id);

            // Check duplicate STUDENT_ID
            PreparedStatement checkId = con.prepareStatement(
                "SELECT COUNT(*) FROM STUDENT WHERE STUDENT_ID = ?"
            );
            checkId.setInt(1, studentId);
            ResultSet rsId = checkId.executeQuery();
            rsId.next();

            if (rsId.getInt(1) > 0) {
                rsId.close();
                checkId.close();
                con.close();
                res.sendRedirect("addStudent.jsp?err=" + encode("Student ID already exists"));
                return;
            }
            rsId.close();
            checkId.close();

            // Check duplicate EMAIL
            PreparedStatement checkEmail = con.prepareStatement(
                "SELECT COUNT(*) FROM STUDENT WHERE LOWER(EMAIL) = LOWER(?)"
            );
            checkEmail.setString(1, email);
            ResultSet rsEmail = checkEmail.executeQuery();
            rsEmail.next();

            if (rsEmail.getInt(1) > 0) {
                rsEmail.close();
                checkEmail.close();
                con.close();
                res.sendRedirect("addStudent.jsp?err=" + encode("Email already exists"));
                return;
            }
            rsEmail.close();
            checkEmail.close();

            // Check duplicate MOBILE_NO
            PreparedStatement checkMobile = con.prepareStatement(
                "SELECT COUNT(*) FROM STUDENT WHERE MOBILE_NO = ?"
            );
            checkMobile.setString(1, mobile);
            ResultSet rsMobile = checkMobile.executeQuery();
            rsMobile.next();

            if (rsMobile.getInt(1) > 0) {
                rsMobile.close();
                checkMobile.close();
                con.close();
                res.sendRedirect("addStudent.jsp?err=" + encode("Mobile number already exists"));
                return;
            }
            rsMobile.close();
            checkMobile.close();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO STUDENT " +
                "(STUDENT_ID, NAME, GENDER, DATE_OF_BIRTH, MOBILE_NO, EMAIL, ADDRESS, COURSE, YEAR, PARENT_NAME, PARENT_CONTACT, BLOOD_GROUP, ROOM_ID, HOSTEL_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setInt(1, studentId);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setDate(4, java.sql.Date.valueOf(dob));
            ps.setString(5, mobile);
            ps.setString(6, email);
            ps.setString(7, address);
            ps.setString(8, course);
            ps.setInt(9, Integer.parseInt(year));
            ps.setString(10, parentName);
            ps.setString(11, parentContact);
            ps.setString(12, bloodGroup);

            if (roomId == null || roomId.trim().isEmpty()) {
                ps.setNull(13, java.sql.Types.INTEGER);
            } else {
                ps.setInt(13, Integer.parseInt(roomId));
            }

            if (hostelId == null || hostelId.trim().isEmpty()) {
                ps.setNull(14, java.sql.Types.INTEGER);
            } else {
                ps.setInt(14, Integer.parseInt(hostelId));
            }

            ps.executeUpdate();

            ps.close();
            con.close();

            res.sendRedirect("viewStudents.jsp?msg=" + encode("Student added successfully"));

        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (message != null && message.contains("ORA-00001")) {
                res.sendRedirect("addStudent.jsp?err=" + encode("Duplicate value found. Student ID, Email, or Mobile may already exist."));
            } else {
                res.sendRedirect("addStudent.jsp?err=" + encode(e.toString()));
            }
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}