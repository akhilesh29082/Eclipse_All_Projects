<%@ page import="com.hostel.util.DBConnection, java.sql.*" %>
<html>
<head><title>Hostel Management - Add Student</title></head>
<body>
    <h2>Register New Student</h2>
    <form method="post">
        ID: <input type="text" name="sid" required><br>
        Name: <input type="text" name="sname" required><br>
        Course: <input type="text" name="course" required><br>
        <input type="submit" value="Register">
    </form>

    <%
        String id = request.getParameter("sid");
        // Check if ID is present to avoid running the code on page load
        if (id != null && !id.trim().isEmpty()) {
            // Using try-with-resources for automatic closure
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement("INSERT INTO STUDENT(Student_ID, Name, Course) VALUES(?,?,?)")) {
                
                ps.setString(1, id);
                ps.setString(2, request.getParameter("sname"));
                ps.setString(3, request.getParameter("course"));
                
                int row = ps.executeUpdate();
                if(row > 0) {
                    out.println("<p style='color:green;'>Student Registered Successfully!</p>");
                }
            } catch (Exception e) { 
                out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>"); 
            }
        }
    %>
    <br><a href="viewStudents.jsp">View All Students</a>
</body>
</html>