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

@WebServlet("/RoomManagementServlet")
public class RoomManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "hr";
    private final String PASS = "hr123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            if ("addRoom".equals(action)) {
                addRoom(request, response);

            } else if ("deleteRoom".equals(action)) {
                deleteRoom(request, response);

            } else if ("addStudentToRoom".equals(action)) {
                addStudentToRoom(request, response);

            } else if ("vacateStudent".equals(action)) {
                vacateStudent(request, response);

            } else if ("editRoom".equals(action)) {
                editRoom(request, response);

            } else {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Invalid action"));
            }

        } catch (Exception e) {
            response.sendRedirect("roomManagement.jsp?err=" + encode(e.getMessage()));
        }
    }

    private void addRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        int hostelId = Integer.parseInt(request.getParameter("hostel_id"));
        String roomNo = request.getParameter("room_no");
        String roomType = request.getParameter("room_type");
        int totalBeds = Integer.parseInt(request.getParameter("total_beds"));
        int availableBeds = Integer.parseInt(request.getParameter("available_beds"));
        int floorNo = Integer.parseInt(request.getParameter("floor_no"));

        if (availableBeds > totalBeds) {
            response.sendRedirect("roomManagement.jsp?err=" + encode("Available beds cannot be greater than total beds"));
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement checkRoom = con.prepareStatement("SELECT COUNT(*) FROM ROOM WHERE ROOM_ID = ?");
            checkRoom.setInt(1, roomId);
            ResultSet rs = checkRoom.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room ID already exists"));
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

        response.sendRedirect("roomManagement.jsp?msg=" + encode("Room added successfully"));
    }

    private void editRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        int hostelId = Integer.parseInt(request.getParameter("hostel_id"));
        String roomNo = request.getParameter("room_no");
        String roomType = request.getParameter("room_type");
        int totalBeds = Integer.parseInt(request.getParameter("total_beds"));
        int availableBeds = Integer.parseInt(request.getParameter("available_beds"));
        int floorNo = Integer.parseInt(request.getParameter("floor_no"));

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement checkRoom = con.prepareStatement(
                "SELECT TOTAL_BEDS, AVAILABLE_BEDS FROM ROOM WHERE ROOM_ID = ?"
            );
            checkRoom.setInt(1, roomId);
            ResultSet rs = checkRoom.executeQuery();

            if (!rs.next()) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room not found"));
                return;
            }

            int oldTotalBeds = rs.getInt("TOTAL_BEDS");
            int oldAvailableBeds = rs.getInt("AVAILABLE_BEDS");
            int occupiedBeds = oldTotalBeds - oldAvailableBeds;

            if (availableBeds > totalBeds) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Available beds cannot be greater than total beds"));
                return;
            }

            if (totalBeds < occupiedBeds) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Total beds cannot be less than occupied beds"));
                return;
            }

            PreparedStatement updateRoom = con.prepareStatement(
                "UPDATE ROOM SET HOSTEL_ID = ?, ROOM_NO = ?, ROOM_TYPE = ?, TOTAL_BEDS = ?, AVAILABLE_BEDS = ?, FLOOR_NO = ? WHERE ROOM_ID = ?"
            );
            updateRoom.setInt(1, hostelId);
            updateRoom.setString(2, roomNo);
            updateRoom.setString(3, roomType);
            updateRoom.setInt(4, totalBeds);
            updateRoom.setInt(5, availableBeds);
            updateRoom.setInt(6, floorNo);
            updateRoom.setInt(7, roomId);

            int rows = updateRoom.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("roomManagement.jsp?msg=" + encode("Room updated successfully"));
            } else {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room update failed"));
            }
        }
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int roomId = Integer.parseInt(request.getParameter("room_id"));

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement checkActive = con.prepareStatement(
                "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE ROOM_ID = ? AND STATUS = 'Active'"
            );
            checkActive.setInt(1, roomId);
            ResultSet rs = checkActive.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Cannot delete room. Students are still allocated"));
                return;
            }

            PreparedStatement ps = con.prepareStatement("DELETE FROM ROOM WHERE ROOM_ID = ?");
            ps.setInt(1, roomId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("roomManagement.jsp?msg=" + encode("Room deleted successfully"));
            } else {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room not found"));
            }
        }
    }

    private void addStudentToRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement checkStudent = con.prepareStatement(
                "SELECT COUNT(*) FROM STUDENT WHERE STUDENT_ID = ?"
            );
            checkStudent.setInt(1, studentId);
            ResultSet rsStudent = checkStudent.executeQuery();
            rsStudent.next();

            if (rsStudent.getInt(1) == 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Student does not exist"));
                return;
            }

            PreparedStatement checkActiveAllocation = con.prepareStatement(
                "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE STUDENT_ID = ? AND STATUS = 'Active'"
            );
            checkActiveAllocation.setInt(1, studentId);
            ResultSet rsAlloc = checkActiveAllocation.executeQuery();
            rsAlloc.next();

            if (rsAlloc.getInt(1) > 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Student already has an active room"));
                return;
            }

            PreparedStatement checkBeds = con.prepareStatement(
                "SELECT AVAILABLE_BEDS FROM ROOM WHERE ROOM_ID = ?"
            );
            checkBeds.setInt(1, roomId);
            ResultSet rsBeds = checkBeds.executeQuery();

            if (!rsBeds.next()) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room not found"));
                return;
            }

            int availableBeds = rsBeds.getInt("AVAILABLE_BEDS");

            if (availableBeds <= 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Room is full"));
                return;
            }

            PreparedStatement nextIdStmt = con.prepareStatement(
                "SELECT NVL(MAX(ALLOCATION_ID), 0) + 1 FROM ROOM_ALLOCATION"
            );
            ResultSet rsNext = nextIdStmt.executeQuery();
            rsNext.next();
            int nextAllocationId = rsNext.getInt(1);

            PreparedStatement insertAlloc = con.prepareStatement(
                "INSERT INTO ROOM_ALLOCATION (ALLOCATION_ID, STUDENT_ID, ROOM_ID, ALLOCATION_DATE, VACATE_DATE, STATUS) VALUES (?, ?, ?, ?, ?, ?)"
            );
            insertAlloc.setInt(1, nextAllocationId);
            insertAlloc.setInt(2, studentId);
            insertAlloc.setInt(3, roomId);
            insertAlloc.setDate(4, new Date(System.currentTimeMillis()));
            insertAlloc.setNull(5, java.sql.Types.DATE);
            insertAlloc.setString(6, "Active");
            insertAlloc.executeUpdate();

            PreparedStatement updateRoom = con.prepareStatement(
                "UPDATE ROOM SET AVAILABLE_BEDS = AVAILABLE_BEDS - 1 WHERE ROOM_ID = ?"
            );
            updateRoom.setInt(1, roomId);
            updateRoom.executeUpdate();
        }

        response.sendRedirect("roomManagement.jsp?msg=" + encode("Student added to room successfully"));
    }

    private void vacateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int allocationId = Integer.parseInt(request.getParameter("allocation_id"));
        int roomId = Integer.parseInt(request.getParameter("room_id"));

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement checkAlloc = con.prepareStatement(
                "SELECT COUNT(*) FROM ROOM_ALLOCATION WHERE ALLOCATION_ID = ? AND STATUS = 'Active'"
            );
            checkAlloc.setInt(1, allocationId);
            ResultSet rs = checkAlloc.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                response.sendRedirect("roomManagement.jsp?err=" + encode("Active allocation not found"));
                return;
            }

            PreparedStatement updateAlloc = con.prepareStatement(
                "UPDATE ROOM_ALLOCATION SET STATUS = 'Inactive', VACATE_DATE = ? WHERE ALLOCATION_ID = ?"
            );
            updateAlloc.setDate(1, new Date(System.currentTimeMillis()));
            updateAlloc.setInt(2, allocationId);
            updateAlloc.executeUpdate();

            PreparedStatement updateRoom = con.prepareStatement(
                "UPDATE ROOM SET AVAILABLE_BEDS = AVAILABLE_BEDS + 1 WHERE ROOM_ID = ?"
            );
            updateRoom.setInt(1, roomId);
            updateRoom.executeUpdate();
        }

        response.sendRedirect("roomManagement.jsp?msg=" + encode("Student vacated successfully"));
    }

    private String encode(String text) {
        if (text == null) return "";
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}