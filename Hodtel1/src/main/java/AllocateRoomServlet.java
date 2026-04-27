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

@WebServlet("/AllocateRoomServlet")
public class AllocateRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "hr";
    private final String PASS = "hr123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            int studentId = Integer.parseInt(request.getParameter("student_id"));
            int roomId = Integer.parseInt(request.getParameter("room_id"));

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement checkStudent = con.prepareStatement(
                    "SELECT COUNT(*) FROM STUDENT WHERE STUDENT_ID=?"
                );
                checkStudent.setInt(1, studentId);
                ResultSet rsStudent = checkStudent.executeQuery();
                rsStudent.next();

                if (rsStudent.getInt(1) == 0) {
                    response.sendRedirect("allocateRoom.jsp?err=" + encode("Student does not exist"));
                    return;
                }

                PreparedStatement checkAllocation = con.prepareStatement(
                    "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE STUDENT_ID=? AND STATUS='Active'"
                );
                checkAllocation.setInt(1, studentId);
                ResultSet rsAlloc = checkAllocation.executeQuery();
                rsAlloc.next();

                if (rsAlloc.getInt(1) > 0) {
                    response.sendRedirect("allocateRoom.jsp?err=" + encode("Student already has an active room"));
                    return;
                }

                PreparedStatement checkRoom = con.prepareStatement(
                    "SELECT AVAILABLE_BEDS FROM ROOM WHERE ROOM_ID=?"
                );
                checkRoom.setInt(1, roomId);
                ResultSet rsRoom = checkRoom.executeQuery();

                if (!rsRoom.next()) {
                    response.sendRedirect("allocateRoom.jsp?err=" + encode("Room not found"));
                    return;
                }

                int availableBeds = rsRoom.getInt("AVAILABLE_BEDS");
                if (availableBeds <= 0) {
                    response.sendRedirect("allocateRoom.jsp?err=" + encode("Room is full"));
                    return;
                }

                PreparedStatement nextId = con.prepareStatement(
                    "SELECT NVL(MAX(ALLOCATION_ID), 0) + 1 FROM ROOM_ALLOCATION"
                );
                ResultSet rsNext = nextId.executeQuery();
                rsNext.next();
                int allocationId = rsNext.getInt(1);

                PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO ROOM_ALLOCATION (ALLOCATION_ID, STUDENT_ID, ROOM_ID, ALLOCATION_DATE, VACATE_DATE, STATUS) VALUES (?, ?, ?, ?, ?, ?)"
                );
                insert.setInt(1, allocationId);
                insert.setInt(2, studentId);
                insert.setInt(3, roomId);
                insert.setDate(4, new Date(System.currentTimeMillis()));
                insert.setNull(5, java.sql.Types.DATE);
                insert.setString(6, "Active");
                insert.executeUpdate();

                PreparedStatement updateRoom = con.prepareStatement(
                    "UPDATE ROOM SET AVAILABLE_BEDS = AVAILABLE_BEDS - 1 WHERE ROOM_ID=?"
                );
                updateRoom.setInt(1, roomId);
                updateRoom.executeUpdate();
            }

            response.sendRedirect("allocateRoom.jsp?msg=" + encode("Student allocated successfully"));

        } catch (Exception e) {
            response.sendRedirect("allocateRoom.jsp?err=" + encode(e.getMessage()));
        }
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}