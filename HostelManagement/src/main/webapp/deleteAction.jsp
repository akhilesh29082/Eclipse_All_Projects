<%@ page import="com.hostel.util.DBConnection, java.sql.*" %>
<%
    String id = request.getParameter("id");
    
    if (id != null && !id.isEmpty()) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnection.getConnection();
            // Use the Student_ID attribute you defined in your schema
            String sql = "DELETE FROM STUDENT WHERE Student_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            
            int result = ps.executeUpdate();
            
            if (result > 0) {
                // Success - go back to the list
                response.sendRedirect("viewStudents.jsp?msg=Deleted");
            } else {
                out.println("Error: Student ID not found.");
            }
        } catch (SQLException e) {
            // Handle Foreign Key violations (e.g., if student is in ROOM_ALLOCATION)
            out.println("<h3>Error: Cannot delete student. They might have an active room allocation or unpaid fees.</h3>");
            out.println("<a href='viewStudents.jsp'>Back to List</a>");
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    } else {
        response.sendRedirect("viewStudents.jsp");
    }
%>