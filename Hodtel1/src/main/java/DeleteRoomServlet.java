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

@WebServlet("/DeleteRoomServlet")
public class DeleteRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "hr";
    private final String PASS = "hr123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            int roomId = Integer.parseInt(request.getParameter("room_id"));

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement check = con.prepareStatement(
                    "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE ROOM_ID=? AND STATUS='Active'"
                );
                check.setInt(1, roomId);
                ResultSet rs = check.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    response.sendRedirect("viewRooms.jsp?err=" + encode("Cannot delete room. Active allocation exists"));
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM ROOM WHERE ROOM_ID=?");
                ps.setInt(1, roomId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    response.sendRedirect("viewRooms.jsp?msg=" + encode("Room deleted successfully"));
                } else {
                    response.sendRedirect("viewRooms.jsp?err=" + encode("Room not found"));
                }
            }

        } catch (Exception e) {
            response.sendRedirect("viewRooms.jsp?err=" + encode(e.getMessage()));
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}
