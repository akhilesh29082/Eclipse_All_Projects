<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>

<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

    <h2>All Complaints</h2>

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

    <!-- SEARCH FORM -->
    <form method="get" style="margin-bottom:20px;">
        <input type="text" name="search" placeholder="Search by Complaint Type or Status">
        <button type="submit">Search</button>
    </form>

    <table class="table">
        <tr>
            <th>ID</th>
            <th>Student</th>
            <th>Room</th>
            <th>Type</th>
            <th>Description</th>
            <th>Date</th>
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

    String query = "SELECT * FROM COMPLAINT ";

    if(search != null && !search.trim().equals("")) {
        query += "WHERE LOWER(COMPLAINT_TYPE) LIKE ? OR LOWER(STATUS) LIKE ? ORDER BY COMPLAINT_ID";
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + search.toLowerCase() + "%");
        ps.setString(2, "%" + search.toLowerCase() + "%");
    } else {
        query += "ORDER BY COMPLAINT_ID";
        ps = con.prepareStatement(query);
    }

    rs = ps.executeQuery();

    boolean hasData = false;

    while(rs.next()) {
        hasData = true;
        String status = rs.getString("status");
%>

        <tr>
            <td><%= rs.getInt("complaint_id") %></td>
            <td><%= rs.getInt("student_id") %></td>
            <td><%= rs.getInt("room_id") %></td>
            <td><%= rs.getString("complaint_type") %></td>
            <td><%= rs.getString("description") %></td>
            <td><%= rs.getDate("complaint_date") %></td>

            <!-- STATUS BADGE -->
            <td>
                <span class="status <%= "Resolved".equalsIgnoreCase(status) ? "resolved" : "pending" %>">
                    <%= status %>
                </span>
            </td>

            <!-- DELETE -->
            <td>
                <form action="ComplaintServlet" method="post" style="margin:0;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= rs.getInt("complaint_id") %>">
                    <button class="btn delete"
                        onclick="return confirm('Are you sure you want to delete this complaint?')">
                        Delete
                    </button>
                </form>
            </td>

            <!-- UPDATE -->
            <td>
                <form action="ComplaintServlet" method="post" style="margin:0;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= rs.getInt("complaint_id") %>">

                    <select name="status" style="margin-bottom:8px;">
                        <option <%= "Pending".equalsIgnoreCase(status) ? "selected" : "" %>>Pending</option>
                        <option <%= "Resolved".equalsIgnoreCase(status) ? "selected" : "" %>>Resolved</option>
                    </select>

                    <button class="btn update">Update</button>
                </form>
            </td>
        </tr>

<%
    }

    if(!hasData) {
%>
        <tr>
            <td colspan="9" style="text-align:center; color:#666;">No complaints found.</td>
        </tr>
<%
    }

} catch(Exception e) {
%>
        <tr>
            <td colspan="9" style="color:red; text-align:center;">Error: <%= e.getMessage() %></td>
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