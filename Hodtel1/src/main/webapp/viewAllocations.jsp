<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

    <h2>Active Room Allocations</h2>

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

    <form method="get" style="margin-bottom:20px;">
        <input type="text" name="search" placeholder="Search by Student ID or Room ID">
        <button type="submit">Search</button>
    </form>

    <table class="table">
        <tr>
            <th>Allocation ID</th>
            <th>Student ID</th>
            <th>Room ID</th>
            <th>Allocation Date</th>
            <th>Status</th>
            <th>Action</th>
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

    String query = "SELECT * FROM ROOM_ALLOCATION WHERE STATUS='Active' ";

    if(search != null && !search.trim().equals("")) {
        query += "AND (TO_CHAR(STUDENT_ID) LIKE ? OR TO_CHAR(ROOM_ID) LIKE ?) ORDER BY ALLOCATION_ID";
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + search.trim() + "%");
        ps.setString(2, "%" + search.trim() + "%");
    } else {
        query += "ORDER BY ALLOCATION_ID";
        ps = con.prepareStatement(query);
    }

    rs = ps.executeQuery();

    boolean hasData = false;

    while(rs.next()) {
        hasData = true;
%>
        <tr>
            <td><%= rs.getInt("ALLOCATION_ID") %></td>
            <td><%= rs.getInt("STUDENT_ID") %></td>
            <td><%= rs.getInt("ROOM_ID") %></td>
            <td><%= rs.getDate("ALLOCATION_DATE") %></td>
            <td>
                <span class="status pending"><%= rs.getString("STATUS") %></span>
            </td>
            <td>
                <form action="VacateRoomServlet" method="post" style="margin:0;">
                    <input type="hidden" name="allocation_id" value="<%= rs.getInt("ALLOCATION_ID") %>">
                    <input type="hidden" name="room_id" value="<%= rs.getInt("ROOM_ID") %>">
                    <button type="submit" class="btn delete"
                        onclick="return confirm('Are you sure you want to vacate this room allocation?')">
                        Vacate
                    </button>
                </form>
            </td>
        </tr>
<%
    }

    if(!hasData) {
%>
        <tr>
            <td colspan="6" style="text-align:center; color:#666;">No active allocations found.</td>
        </tr>
<%
    }

} catch(Exception e) {
%>
        <tr>
            <td colspan="6" style="color:red; text-align:center;">Error: <%= e.getMessage() %></td>
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