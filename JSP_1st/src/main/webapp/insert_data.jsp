<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Data Entry</title>
</head>
<body>
<h2>Enter Student Details</h2>

<%
String name = request.getParameter("name");
String email = request.getParameter("email");

if(name != null && email != null){
    Connection con = null;
    PreparedStatement ps = null;

    try {
    	 //SELECT * FROM students;USE College;
        // Make sure MySQL Connector/J JAR is in Tomcat lib
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/College", "root", "root");
        
        String query = "INSERT INTO students (name, email) VALUES (?, ?)";
        ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, email);

        int i = ps.executeUpdate();

        if(i > 0){
            out.println("<p style='color:green;'>Data inserted successfully!</p>");
        } else {
            out.println("<p style='color:red;'>Error inserting data.</p>");
        }
    } catch(Exception e){
        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
    } finally {
        try{ if(ps != null) ps.close(); } catch(Exception e){ }
        try{ if(con != null) con.close(); } catch(Exception e){ }
    }
}
%>

<form method="post">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    <input type="submit" value="Save">
</form>

</body>
</html>