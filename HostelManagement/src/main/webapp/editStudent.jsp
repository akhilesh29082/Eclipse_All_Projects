<%@ page import="com.hostel.util.DBConnection, java.sql.*" %>
<%
    String id = request.getParameter("id");
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT WHERE Student_ID=?");
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
%>
<form action="updateAction.jsp" method="post">
    <input type="hidden" name="sid" value="<%= rs.getString("Student_ID") %>">
    Name: <input type="text" name="sname" value="<%= rs.getString("Name") %>"><br>
    Course: <input type="text" name="course" value="<%= rs.getString("Course") %>"><br>
    <input type="submit" value="Update">
</form>