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

@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
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
            int floorNo = Integer.parseInt(request.getParameter("floor_no"));
            int availableBeds = totalBeds;

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement check = con.prepareStatement("SELECT COUNT(*) FROM ROOM WHERE ROOM_ID=?");
                check.setInt(1, roomId);
                ResultSet rs = check.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    response.sendRedirect("addRoom.jsp?err=" + encode("Room ID already exists"));
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ROOM (ROOM_ID, HOSTEL_ID, ROOM_NO, ROOM_TYPE, TOTAL_BEDS, AVAILABLE_BEDS, FLOOR_NO) VALUES (?, ?, ?, ?, ?, ?, ?)"
                );
                ps.setInt(1, roomId);
                ps.setInt(2, hostelId);
                ps.setString(3, roomNo);
                ps.setString(4, roomType);
                ps.setInt(5, totalBeds);
                ps.setInt(6, availableBeds);
                ps.setInt(7, floorNo);

                ps.executeUpdate();
            }

            response.sendRedirect("addRoom.jsp?msg=" + encode("Room added successfully"));

        } catch (Exception e) {
            response.sendRedirect("addRoom.jsp?err=" + encode(e.getMessage()));
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}