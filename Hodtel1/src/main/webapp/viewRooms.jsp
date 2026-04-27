<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

    <h2>Room Details</h2>

    <%
    String msg = request.getParameter("msg");
    String err = request.getParameter("err");
    %>

    <% if(msg != null) { %>
        <p style="color:green; font-weight:600; margin-bottom:15px;"><%= msg %></p>
    <% } %>

    <% if(err != null) { %>
        <p style="color:red; font-weight:600; margin-bottom:15px;"><%= err %></p>
    <% } %>

    <!-- SEARCH BAR -->
    <form method="get" style="margin-bottom:20px;">
        <input type="text" name="search" placeholder="Search by Room No or Type">
        <button type="submit">Search</button>
    </form>

    <table class="table">
        <tr>
            <th>Room ID</th>
            <th>Hostel ID</th>
            <th>Room No</th>
            <th>Type</th>
            <th>Total Beds</th>
            <th>Available</th>
            <th>Floor</th>
            <th>Status</th>
            <th colspan="2">Actions</th>
        </tr>

<%
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:xe",
        "hr",
        "hr123"
    );

    String search = request.getParameter("search");

    String query = "SELECT * FROM ROOM ";

    if(search != null && !search.trim().equals("")) {
        query += "WHERE LOWER(ROOM_NO) LIKE ? OR LOWER(ROOM_TYPE) LIKE ? ORDER BY ROOM_ID";
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + search.toLowerCase() + "%");
        ps.setString(2, "%" + search.toLowerCase() + "%");
    } else {
        query += "ORDER BY ROOM_ID";
        ps = con.prepareStatement(query);
    }

    rs = ps.executeQuery();

    boolean hasData = false;

    while(rs.next()) {
        hasData = true;

        int totalBeds = rs.getInt("TOTAL_BEDS");
        int availableBeds = rs.getInt("AVAILABLE_BEDS");
        String status = availableBeds == 0 ? "Full" : "Available";
%>

        <tr>
            <td><%= rs.getInt("ROOM_ID") %></td>
            <td><%= rs.getInt("HOSTEL_ID") %></td>
            <td><%= rs.getString("ROOM_NO") %></td>
            <td><%= rs.getString("ROOM_TYPE") %></td>
            <td><%= totalBeds %></td>
            <td><%= availableBeds %></td>
            <td><%= rs.getInt("FLOOR_NO") %></td>

            <!-- STATUS BADGE -->
            <td>
                <span class="status <%= status.equals("Full") ? "resolved" : "pending" %>">
                    <%= status %>
                </span>
            </td>

            <!-- EDIT -->
            <td>
                <form action="editRoom.jsp" method="get" style="margin:0;">
                    <input type="hidden" name="room_id" value="<%= rs.getInt("ROOM_ID") %>">
                    <button type="submit" class="btn update">Edit</button>
                </form>
            </td>

            <!-- DELETE -->
            <td>
                <form action="DeleteRoomServlet" method="post" style="margin:0;">
                    <input type="hidden" name="room_id" value="<%= rs.getInt("ROOM_ID") %>">
                    <button type="submit" class="btn delete"
                        onclick="return confirm('Are you sure you want to delete this room?')">
                        Delete
                    </button>
                </form>
            </td>
        </tr>

<%
    }

    if(!hasData) {
%>
        <tr>
            <td colspan="10" style="text-align:center; color:#666;">
                No rooms found.
            </td>
        </tr>
<%
    }

} catch(Exception e) {
%>
        <tr>
            <td colspan="10" style="color:red; text-align:center;">
                Error: <%= e.getMessage() %>
            </td>
        </tr>
<%
} finally {
    try { if(rs != null) rs.close(); } catch(Exception e) {}
    try { if(ps != null) ps.close(); } catch(Exception e) {}
    try { if(con != null) con.close(); } catch(Exception e) {}
}
%>

    </table>

</div>
</div>