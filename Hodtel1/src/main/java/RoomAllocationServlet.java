import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
import java.io.IOException;

@WebServlet("/RoomAllocationServlet")
public class RoomAllocationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String allocationDate = request.getParameter("allocationDate");

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Start transaction

            // 1. Insert Allocation (Using your sequence logic)
            // Note: If you haven't created a sequence for allocation yet, 
            // use: CREATE SEQUENCE allocation_seq START WITH 1;
            String sql1 = "INSERT INTO ROOM_ALLOCATION (ALLOCATION_ID, STUDENT_ID, ROOM_ID, ALLOCATION_DATE, STATUS) " +
                          "VALUES (allocation_seq.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), 'Active')";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, studentId);
            ps1.setInt(2, roomId);
            ps1.setString(3, allocationDate);
            ps1.executeUpdate();

            // 2. Update Available Beds in ROOM table
            String sql2 = "UPDATE ROOM SET AVAILABLE_BEDS = AVAILABLE_BEDS - 1 WHERE ROOM_ID = ?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, roomId);
            ps2.executeUpdate();

            // 3. Get Details for the Result Card
            String sql3 = "SELECT s.NAME, s.COURSE, r.ROOM_NO FROM STUDENT s, ROOM r WHERE s.STUDENT_ID=? AND r.ROOM_ID=?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setInt(1, studentId);
            ps3.setInt(2, roomId);
            ResultSet rs = ps3.executeQuery();

            if (rs.next()) {
                request.setAttribute("msg", "Allocation Successful!");
                request.setAttribute("studentName", rs.getString("NAME"));
                request.setAttribute("course", rs.getString("COURSE"));
                request.setAttribute("roomNo", rs.getString("ROOM_NO"));
            }

            con.commit(); // Save changes
            request.getRequestDispatcher("allocateRoom.jsp").forward(request, response);

        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (SQLException se) { se.printStackTrace(); }
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}