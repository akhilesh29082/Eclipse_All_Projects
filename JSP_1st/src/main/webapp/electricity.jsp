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
        background: linear-gradient(to right, #74ebd5, #ACB6E5);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background-color: white;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        width: 400px;
        text-align: center;
    }

    h2 {
        color: #2c3e50;
        margin-bottom: 20px;
    }

    input[type="number"] {
        width: 90%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 14px;
    }

    input[type="submit"] {
        background-color: #3498db;
        color: white;
        border: none;
        padding: 12px 20px;
        border-radius: 8px;
        cursor: pointer;
        font-size: 16px;
        margin-top: 15px;
    }

    input[type="submit"]:hover {
        background-color: #2980b9;
    }

    .result {
        margin-top: 20px;
        padding: 10px;
        background-color: #dff9fb;
        border-left: 5px solid #22a6b3;
        border-radius: 8px;
        color: #130f40;
        font-size: 18px;
        font-weight: bold;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Electricity Bill Calculator</h2>

    <%
    String unitsStr = request.getParameter("units");
    String costStr = request.getParameter("cost");

    if(unitsStr != null && costStr != null) {
        int units = Integer.parseInt(unitsStr);
        double cost = Double.parseDouble(costStr);
        double bill = units * cost;
    %>
        <div class="result">
            Total Bill Amount: ₹ <%= bill %>
        </div>
    <%
    }
    %>

    <form method="post">
        Enter Units Consumed:<br>
        <input type="number" name="units" required><br><br>

        Enter Cost Per Unit:<br>
        <input type="number" step="0.01" name="cost" required><br><br>

        <input type="submit" value="Calculate Bill">
    </form>
</div>
</body>
</html>