import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Connection con = DBConnection.getConnection();

            // ADD
            if ("add".equals(action)) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO COMPLAINT VALUES (complaint_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE, 'Pending')"
                );

                ps.setInt(1, Integer.parseInt(request.getParameter("studentId")));
                ps.setInt(2, Integer.parseInt(request.getParameter("roomId")));
                ps.setString(3, request.getParameter("type"));
                ps.setString(4, request.getParameter("issue"));

                ps.executeUpdate();
            }

            // DELETE
            else if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM COMPLAINT WHERE complaint_id=?"
                );

                ps.setInt(1, id);
                ps.executeUpdate();
            }

            // UPDATE
            else if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                String status = request.getParameter("status");

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE COMPLAINT SET status=? WHERE complaint_id=?"
                );

                ps.setString(1, status);
                ps.setInt(2, id);

                ps.executeUpdate();
            }

            response.sendRedirect("viewComplaints.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}