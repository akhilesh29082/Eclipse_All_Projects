<%@ page import="java.sql.*" %>
<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

<h2>Students Details</h2>

<form method="get">
    <input type="text" name="search" placeholder="Search by name">
    <button type="submit">Search</button>
</form>

<br>

<table class="table">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Course</th>
    <th>Hostel</th>
    <th>Room No</th>
</tr>

<%
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");

    con = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr123");

    String search = request.getParameter("search");

    String query = "SELECT s.STUDENT_ID, s.NAME, s.COURSE, " +
                   "h.HOSTEL_NAME, r.ROOM_NO " +
                   "FROM STUDENT s " +
                   "LEFT JOIN HOSTEL h ON s.HOSTEL_ID = h.HOSTEL_ID " +
                   "LEFT JOIN ROOM r ON s.ROOM_ID = r.ROOM_ID ";

    if(search != null && !search.trim().equals("")) {
        query += "WHERE LOWER(s.NAME) LIKE ?";
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + search.toLowerCase() + "%");
    } else {
        ps = con.prepareStatement(query);
    }

    rs = ps.executeQuery();

    while(rs.next()){
%>

<tr>
<td><%=rs.getInt(1)%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(3)%></td>
<td><%=rs.getString(4)%></td>
<td><%=rs.getString(5)%></td>
</tr>

<%
    }

} catch(Exception e){
%>
<tr>
<td colspan="5" style="color:red;"><%= e.getMessage() %></td>
</tr>
<%
} finally {
    try { if(rs!=null) rs.close(); } catch(Exception e){}
    try { if(ps!=null) ps.close(); } catch(Exception e){}
    try { if(con!=null) con.close(); } catch(Exception e){}
}
%>

</table>

</div>
</div>