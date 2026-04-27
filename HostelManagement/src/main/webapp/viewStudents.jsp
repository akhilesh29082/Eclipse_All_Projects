<%@ page import="com.hostel.util.DBConnection, java.sql.*" %>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Course</th><th>Action</th>
    </tr>
    <%
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
            while(rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("Student_ID") %></td>
        <td><%= rs.getString("Name") %></td>
        <td><%= rs.getString("Course") %></td>
        <td>
            <a href="editStudent.jsp?id=<%= rs.getString("Student_ID") %>">Edit</a> |
            <a href="deleteAction.jsp?id=<%= rs.getString("Student_ID") %>">Delete</a>
        </td>
    </tr>
    <% } } catch(Exception e) { out.println(e); } %>
</table>