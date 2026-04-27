<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>

<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

<h2>Room Management</h2>

<%
String msg = request.getParameter("msg");
String err = request.getParameter("err");

if(msg != null) {
%>
    <p style="color:green; font-weight:bold;"><%= msg %></p>
<%
}
if(err != null) {
%>
    <p style="color:red; font-weight:bold;"><%= err %></p>
<%
}
%>

<!-- ADD ROOM FORM -->
<h3>Add New Room</h3>
<form action="RoomManagementServlet" method="post" class="form-box">
    <input type="hidden" name="action" value="addRoom">

    <label>Room ID:</label>
    <input type="number" name="room_id" required>

    <label>Hostel ID:</label>
    <input type="number" name="hostel_id" required>

    <label>Room No:</label>
    <input type="text" name="room_no" required>

    <label>Room Type:</label>
    <select name="room_type" required>
        <option value="Single">Single</option>
        <option value="Double">Double</option>
        <option value="Triple">Triple</option>
    </select>

    <label>Total Beds:</label>
    <input type="number" name="total_beds" min="1" required>

    <label>Available Beds:</label>
    <input type="number" name="available_beds" min="0" required>

    <label>Floor No:</label>
    <input type="number" name="floor_no" min="1" required>

    <button type="submit" class="btn update">Add Room</button>
</form>

<br><hr><br>

<!-- ADD STUDENT TO ROOM -->
<h3>Allocate Student to Room</h3>
<form action="RoomManagementServlet" method="post" class="form-box">
    <input type="hidden" name="action" value="addStudentToRoom">

    <label>Student ID:</label>
    <input type="number" name="student_id" required>

    <label>Room ID:</label>
    <input type="number" name="room_id" required>

    <button type="submit" class="btn update">Allocate Student</button>
</form>

<br><hr><br>

<h3>All Rooms</h3>

<table class="complaint-table">
<tr>
    <th>Room ID</th>
    <th>Hostel ID</th>
    <th>Room No</th>
    <th>Room Type</th>
    <th>Total Beds</th>
    <th>Available Beds</th>
    <th>Floor No</th>
    <th>Status</th>
    <th>Edit Room</th>
    <th>Delete</th>
</tr>

<%
Connection con = null;
Statement st = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:xe",
        "hr",
        "hr123"
    );

    st = con.createStatement();
    rs = st.executeQuery("SELECT * FROM ROOM ORDER BY ROOM_ID");

    while(rs.next()) {
        int roomId = rs.getInt("ROOM_ID");
        int hostelId = rs.getInt("HOSTEL_ID");
        String roomNo = rs.getString("ROOM_NO");
        String roomType = rs.getString("ROOM_TYPE");
        int totalBeds = rs.getInt("TOTAL_BEDS");
        int availableBeds = rs.getInt("AVAILABLE_BEDS");
        int floorNo = rs.getInt("FLOOR_NO");

        String status = (availableBeds == 0) ? "Full" : "Available";
%>

<tr>
    <td><%= roomId %></td>
    <td><%= hostelId %></td>
    <td><%= roomNo %></td>
    <td><%= roomType %></td>
    <td><%= totalBeds %></td>
    <td><%= availableBeds %></td>
    <td><%= floorNo %></td>

    <td>
        <span class="status <%= status.equals("Full") ? "resolved" : "pending" %>">
            <%= status %>
        </span>
    </td>

    <!-- EDIT ROOM -->
    <td class="actions">
        <form action="RoomManagementServlet" method="post">
            <input type="hidden" name="action" value="editRoom">
            <input type="hidden" name="room_id" value="<%= roomId %>">

            <input type="number" name="hostel_id" value="<%= hostelId %>" required style="width:70px;">
            <input type="text" name="room_no" value="<%= roomNo %>" required style="width:80px;">

            <select name="room_type" required>
                <option value="Single" <%= "Single".equals(roomType) ? "selected" : "" %>>Single</option>
                <option value="Double" <%= "Double".equals(roomType) ? "selected" : "" %>>Double</option>
                <option value="Triple" <%= "Triple".equals(roomType) ? "selected" : "" %>>Triple</option>
            </select>

            <input type="number" name="total_beds" value="<%= totalBeds %>" min="1" required style="width:70px;">
            <input type="number" name="available_beds" value="<%= availableBeds %>" min="0" required style="width:70px;">
            <input type="number" name="floor_no" value="<%= floorNo %>" min="1" required style="width:70px;">

            <button class="btn update" type="submit">Edit</button>
        </form>
    </td>

    <!-- DELETE ROOM -->
    <td class="actions">
        <form action="RoomManagementServlet" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteRoom">
            <input type="hidden" name="room_id" value="<%= roomId %>">
            <button class="btn delete" onclick="return confirm('Delete this room?')" type="submit">Delete</button>
        </form>
    </td>
</tr>

<%
    }
} catch(Exception e) {
    out.println("<p style='color:red;'>Error loading rooms: " + e.getMessage() + "</p>");
} finally {
    try { if(rs != null) rs.close(); } catch(Exception e) {}
    try { if(st != null) st.close(); } catch(Exception e) {}
    try { if(con != null) con.close(); } catch(Exception e) {}
}
%>

</table>

<br><hr><br>

<h3>Active Room Allocations</h3>

<table class="complaint-table">
<tr>
    <th>Allocation ID</th>
    <th>Student ID</th>
    <th>Room ID</th>
    <th>Allocation Date</th>
    <th>Status</th>
    <th>Actions</th>
</tr>

<%
PreparedStatement ps = null;
ResultSet rs2 = null;
Connection con2 = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con2 = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:xe",
        "hr",
        "hr123"
    );

    ps = con2.prepareStatement(
        "SELECT * FROM ROOM_ALLOCATION WHERE STATUS = 'Active' ORDER BY ALLOCATION_ID"
    );
    rs2 = ps.executeQuery();

    while(rs2.next()) {
%>

<tr>
    <td><%= rs2.getInt("ALLOCATION_ID") %></td>
    <td><%= rs2.getInt("STUDENT_ID") %></td>
    <td><%= rs2.getInt("ROOM_ID") %></td>
    <td><%= rs2.getDate("ALLOCATION_DATE") %></td>
    <td>
        <span class="status pending"><%= rs2.getString("STATUS") %></span>
    </td>
    <td class="actions">
        <!-- REMOVE STUDENT / VACATE -->
        <form action="RoomManagementServlet" method="post">
            <input type="hidden" name="action" value="vacateStudent">
            <input type="hidden" name="allocation_id" value="<%= rs2.getInt("ALLOCATION_ID") %>">
            <input type="hidden" name="room_id" value="<%= rs2.getInt("ROOM_ID") %>">
            <button class="btn delete" onclick="return confirm('Remove this student from room?')" type="submit">Remove</button>
        </form>
    </td>
</tr>

<%
    }
} catch(Exception e) {
    out.println("<p style='color:red;'>Error loading allocations: " + e.getMessage() + "</p>");
} finally {
    try { if(rs2 != null) rs2.close(); } catch(Exception e) {}
    try { if(ps != null) ps.close(); } catch(Exception e) {}
    try { if(con2 != null) con2.close(); } catch(Exception e) {}
}
%>

</table>

</div>
</div>