<%@ page import="java.sql.*" %>
<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

<h2>Hostel List</h2>

<table class="table">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Location</th>
    <th>Action</th>
</tr>

<%
Connection con = null;
Statement st = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");

    con = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:xe","hr","hr123");

    st = con.createStatement();
    rs = st.executeQuery("SELECT * FROM HOSTEL");

    while(rs.next()){
        int hid = rs.getInt(1);
%>

<tr>
<td><%=hid%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getString(3)%></td>

<td>
    <a href="viewRooms.jsp?hostelId=<%=hid%>" class="btn">View Rooms</a>
</td>
</tr>

<%
    }
} catch(Exception e){
%>
<tr>
<td colspan="4" style="color:red;"><%= e.getMessage() %></td>
</tr>
<%
} finally {
    try { if(rs!=null) rs.close(); } catch(Exception e){}
    try { if(st!=null) st.close(); } catch(Exception e){}
    try { if(con!=null) con.close(); } catch(Exception e){}
}
%>

</table>

</div>
</div>