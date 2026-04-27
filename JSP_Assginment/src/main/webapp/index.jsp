<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Electricity Bill Calculator</title>
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
    color: darkblue;
}

label {
    font-weight: bold;
}

input[type=text], input[type=number] {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

input[type=submit] {
    width: 100%;
    padding: 10px;
    background-color: green;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

input[type=submit]:hover {
    background-color: darkgreen;
}
</style>
</head>
<body>

<div class="container">
    <h2>Electricity Bill Payment System</h2>

    <form action="saveBill.jsp" method="post">

        <label>Customer Name</label>
        <input type="text" name="customer_name" required>

        <label>Address</label>
        <input type="text" name="address" required>

        <label>Bill Month</label>
        <input type="text" name="bill_month" placeholder="Example: April 2026" required>

        <label>Units Consumed</label>
        <input type="number" name="units_consumed" required>

        <label>Cost Per Unit</label>
        <input type="number" step="0.01" name="cost_per_unit" required>

        <input type="submit" value="Calculate and Pay Bill">

    </form>
</div>

</body>
</html>