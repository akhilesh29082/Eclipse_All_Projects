import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/VacateRoomServlet")
public class VacateRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "hr";
    private final String PASS = "hr123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            int allocationId = Integer.parseInt(request.getParameter("allocation_id"));
            int roomId = Integer.parseInt(request.getParameter("room_id"));

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement check = con.prepareStatement(
                    "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE ALLOCATION_ID=? AND STATUS='Active'"
                );
                check.setInt(1, allocationId);
                ResultSet rs = check.executeQuery();
                rs.next();

                if (rs.getInt(1) == 0) {
                    response.sendRedirect("viewAllocations.jsp?err=" + encode("Active allocation not found"));
                    return;
                }

                PreparedStatement updateAlloc = con.prepareStatement(
                    "UPDATE ROOM_ALLOCATION SET STATUS='Inactive', VACATE_DATE=? WHERE ALLOCATION_ID=?"
                );
                updateAlloc.setDate(1, new Date(System.currentTimeMillis()));
                updateAlloc.setInt(2, allocationId);
                updateAlloc.executeUpdate();

                PreparedStatement updateRoom = con.prepareStatement(
                    "UPDATE ROOM SET AVAILABLE_BEDS = AVAILABLE_BEDS + 1 WHERE ROOM_ID=?"
                );
                updateRoom.setInt(1, roomId);
                updateRoom.executeUpdate();
            }

            response.sendRedirect("viewAllocations.jsp?msg=" + encode("Student vacated successfully"));

        } catch (Exception e) {
            response.sendRedirect("viewAllocations.jsp?err=" + encode(e.getMessage()));
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}