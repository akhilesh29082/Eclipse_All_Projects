import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/AddHostelServlet")
public class AddHostelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO HOSTEL (ID, NAME, TYPE, ROOMS, WARDEN, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setInt(1, Integer.parseInt(req.getParameter("id")));
            ps.setString(2, req.getParameter("name"));
            ps.setString(3, req.getParameter("type"));
            ps.setInt(4, Integer.parseInt(req.getParameter("rooms")));
            ps.setString(5, req.getParameter("warden"));
            ps.setString(6, req.getParameter("phone"));
            ps.setString(7, req.getParameter("address"));

            int i = ps.executeUpdate();

            if (i > 0) {
                res.sendRedirect("dashboard.jsp?msg=Hostel Added Successfully");
            } else {
                res.sendRedirect("addHostel.jsp?msg=Error Adding Hostel");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("addHostel.jsp?msg=Error");
        }
    }
}