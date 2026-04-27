import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class PaymentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO FEES_PAYMENT VALUES (?, ?, ?, SYSDATE, ?, ?, ?)");

            ps.setInt(1, (int)(Math.random()*1000));
            ps.setInt(2, Integer.parseInt(req.getParameter("studentId")));
            ps.setInt(3, Integer.parseInt(req.getParameter("amount")));
            ps.setString(4, req.getParameter("mode"));
            ps.setString(5, "RCP" + System.currentTimeMillis());
            ps.setInt(6, 0);

            ps.executeUpdate();

            res.sendRedirect("payment.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}