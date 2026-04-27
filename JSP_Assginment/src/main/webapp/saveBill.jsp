<%@ page import="java.sql.*" %>
<%@ page import="database.DBConnection" %>

<%
String customerName = request.getParameter("customer_name");
String address = request.getParameter("address");
String billMonth = request.getParameter("bill_month");

String unitStr = request.getParameter("units_consumed");
String costStr = request.getParameter("cost_per_unit");

int unitsConsumed = 0;
double costPerUnit = 0;

if(unitStr != null && !unitStr.isEmpty()) {
    unitsConsumed = Integer.parseInt(unitStr);
}

if(costStr != null && !costStr.isEmpty()) {
    costPerUnit = Double.parseDouble(costStr);
}

double totalBill = unitsConsumed * costPerUnit;
String paymentStatus = "Paid";

Connection con = null;
PreparedStatement ps = null;
int row = 0;
String errorMessage = "";

try {
    con = DBConnection.getConnection();

    String query = "INSERT INTO electricity_bill(customer_name, address, bill_month, units_consumed, cost_per_unit, total_bill, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

    ps = con.prepareStatement(query);

    ps.setString(1, customerName);
    ps.setString(2, address);
    ps.setString(3, billMonth);
    ps.setInt(4, unitsConsumed);
    ps.setDouble(5, costPerUnit);
    ps.setDouble(6, totalBill);
    ps.setString(7, paymentStatus);

    row = ps.executeUpdate();

} catch(Exception e) {
    errorMessage = e.getMessage();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Electricity Bill Status</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
}

.container {
    width: 500px;
    margin: 50px auto;
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 0px 10px gray;
}

h2 {
    text-align: center;
}

.success {
    color: green;
}

.error {
    color: red;
}

p {
    font-size: 18px;
}
</style>
</head>
<body>

<div class="container">

<%
if(row > 0) {
%>

    <h2 class="success">Electricity Bill Paid Successfully</h2>

    <p><strong>Customer Name:</strong> <%= customerName %></p>
    <p><strong>Address:</strong> <%= address %></p>
    <p><strong>Bill Month:</strong> <%= billMonth %></p>
    <p><strong>Units Consumed:</strong> <%= unitsConsumed %></p>
    <p><strong>Cost Per Unit:</strong> Rs. <%= costPerUnit %></p>
    <p><strong>Total Bill Amount:</strong> Rs. <%= totalBill %></p>
    <p><strong>Payment Status:</strong> <%= paymentStatus %></p>

<%
} else {
%>

    <h2 class="error">Something went wrong while saving the bill.</h2>
    <p><%= errorMessage %></p>

<%
}
%>

</div>

</body>
</html>