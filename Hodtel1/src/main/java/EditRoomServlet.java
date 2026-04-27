import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditRoomServlet")
public class EditRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "hr";
    private final String PASS = "hr123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            int roomId = Integer.parseInt(request.getParameter("room_id"));
            int hostelId = Integer.parseInt(request.getParameter("hostel_id"));
            String roomNo = request.getParameter("room_no");
            String roomType = request.getParameter("room_type");
            int totalBeds = Integer.parseInt(request.getParameter("total_beds"));
            int availableBeds = Integer.parseInt(request.getParameter("available_beds"));
            int floorNo = Integer.parseInt(request.getParameter("floor_no"));

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement check = con.prepareStatement(
                    "SELECT TOTAL_BEDS, AVAILABLE_BEDS FROM ROOM WHERE ROOM_ID=?"
                );
                check.setInt(1, roomId);
                ResultSet rs = check.executeQuery();

                if (!rs.next()) {
                    response.sendRedirect("viewRooms.jsp?err=" + encode("Room not found"));
                    return;
                }

                int oldTotalBeds = rs.getInt("TOTAL_BEDS");
                int oldAvailableBeds = rs.getInt("AVAILABLE_BEDS");
                int occupiedBeds = oldTotalBeds - oldAvailableBeds;

                if (availableBeds > totalBeds) {
                    response.sendRedirect("viewRooms.jsp?err=" + encode("Available beds cannot be greater than total beds"));
                    return;
                }

                if (totalBeds < occupiedBeds) {
                    response.sendRedirect("viewRooms.jsp?err=" + encode("Total beds cannot be less than occupied beds"));
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE ROOM SET HOSTEL_ID=?, ROOM_NO=?, ROOM_TYPE=?, TOTAL_BEDS=?, AVAILABLE_BEDS=?, FLOOR_NO=? WHERE ROOM_ID=?"
                );
                ps.setInt(1, hostelId);
                ps.setString(2, roomNo);
                ps.setString(3, roomType);
                ps.setInt(4, totalBeds);
                ps.setInt(5, availableBeds);
                ps.setInt(6, floorNo);
                ps.setInt(7, roomId);

                ps.executeUpdate();
            }

            response.sendRedirect("viewRooms.jsp?msg=" + encode("Room updated successfully"));

        } catch (Exception e) {
            response.sendRedirect("viewRooms.jsp?err=" + encode(e.getMessage()));
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}