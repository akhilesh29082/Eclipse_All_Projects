import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import java.sql.*;

public class DownloadReceiptServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.txt");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "hr",
                    "hr123"
            );

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM FEES_PAYMENT WHERE Student_ID=?"
            );

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            out.println("===== HOSTEL PAYMENT RECEIPT =====\n");

            while (rs.next()) {
                out.println("Student ID: " + rs.getInt("Student_ID"));
                out.println("Amount: " + rs.getInt("Amount"));
                out.println("Date: " + rs.getDate("Payment_Date"));
                out.println("Mode: " + rs.getString("Payment_Mode"));
                out.println("Receipt No: " + rs.getString("Receipt_No"));
                out.println("-----------------------------");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }
}