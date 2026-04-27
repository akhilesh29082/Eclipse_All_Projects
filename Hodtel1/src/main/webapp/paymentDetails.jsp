<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp" %>
<%@ page import="java.sql.*" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

    <h2>Fees Payment Details</h2>

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
        <input type="text" name="search" placeholder="Search by Student ID">
        <button type="submit">Search</button>
    </form>

    <table class="table">
        <tr>
            <th>Payment ID</th>
            <th>Student ID</th>
            <th>Amount Paid</th>
            <th>Payment Date</th>
            <th>Payment Mode</th>
            <th>Receipt No</th>
            <th>Due Amount</th>
            <th>Status</th>
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

    String query = "SELECT * FROM FEES_PAYMENT ";

    if(search != null && !search.trim().equals("")) {
        query += "WHERE TO_CHAR(STUDENT_ID) LIKE ? ORDER BY PAYMENT_ID";
        ps = con.prepareStatement(query);
        ps.setString(1, "%" + search.trim() + "%");
    } else {
        query += "ORDER BY PAYMENT_ID";
        ps = con.prepareStatement(query);
    }

    rs = ps.executeQuery();

    boolean hasData = false;

    while(rs.next()) {
        hasData = true;

        int due = rs.getInt("DUE_AMOUNT");
        String status = (due == 0) ? "Paid" : "Pending";
%>

        <tr>
            <td><%= rs.getInt("PAYMENT_ID") %></td>
            <td><%= rs.getInt("STUDENT_ID") %></td>
            <td>&#8377; <%= rs.getInt("AMOUNT") %></td>
            <td><%= rs.getDate("PAYMENT_DATE") %></td>
            <td><%= rs.getString("PAYMENT_MODE") %></td>
            <td><%= rs.getString("RECEIPT_NO") %></td>
            <td>&#8377; <%= due %></td>

            <td>
                <span class="status <%= (due == 0) ? "resolved" : "pending" %>">
                    <%= status %>
                </span>
            </td>
        </tr>

<%
    }

    if(!hasData) {
%>
        <tr>
            <td colspan="8" style="text-align:center; color:#666;">
                No payment records found.
            </td>
        </tr>
<%
    }

} catch(Exception e) {
%>
        <tr>
            <td colspan="8" style="color:red; text-align:center;">
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