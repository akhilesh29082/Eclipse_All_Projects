<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">
    <h2>Edit Room</h2>

<%
String roomIdParam = request.getParameter("room_id");

if(roomIdParam == null) {
    out.println("<p style='color:red;'>Room ID missing</p>");
} else {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        int roomId = Integer.parseInt(roomIdParam);

        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr123");

        ps = con.prepareStatement("SELECT * FROM ROOM WHERE ROOM_ID=?");
        ps.setInt(1, roomId);
        rs = ps.executeQuery();

        if(rs.next()) {
%>
<p>Context Path: <%= request.getContextPath() %></p>

<form action="<%= request.getContextPath() %>/EditRoomServlet" method="post">
    <button type="submit">Test Servlet</button>
</form>
    <form action="EditRoomServlet" method="post" class="form-box">
        <input type="hidden" name="room_id" value="<%= rs.getInt("ROOM_ID") %>">

        <label>Hostel ID:</label>
        <input type="number" name="hostel_id" value="<%= rs.getInt("HOSTEL_ID") %>" required>

        <label>Room No:</label>
        <input type="text" name="room_no" value="<%= rs.getString("ROOM_NO") %>" required>

        <label>Room Type:</label>
        <select name="room_type" required>
            <option value="Single" <%= "Single".equals(rs.getString("ROOM_TYPE")) ? "selected" : "" %>>Single</option>
            <option value="Double" <%= "Double".equals(rs.getString("ROOM_TYPE")) ? "selected" : "" %>>Double</option>
            <option value="Triple" <%= "Triple".equals(rs.getString("ROOM_TYPE")) ? "selected" : "" %>>Triple</option>
        </select>

        <label>Total Beds:</label>
        <input type="number" name="total_beds" value="<%= rs.getInt("TOTAL_BEDS") %>" required>

        <label>Available Beds:</label>
        <input type="number" name="available_beds" value="<%= rs.getInt("AVAILABLE_BEDS") %>" required>

        <label>Floor No:</label>
        <input type="number" name="floor_no" value="<%= rs.getInt("FLOOR_NO") %>" required>

        <button type="submit" class="btn update">Update Room</button>
    </form>

<%
        } else {
            out.println("<p style='color:red;'>Room not found</p>");
        }
    } catch(Exception e) {
        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
    } finally {
        try { if(rs != null) rs.close(); } catch(Exception e) {}
        try { if(ps != null) ps.close(); } catch(Exception e) {}
        try { if(con != null) con.close(); } catch(Exception e) {}
    }
}
%>
</div>
</div>