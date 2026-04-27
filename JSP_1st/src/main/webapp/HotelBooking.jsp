<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hotel Room Booking</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #667eea, #764ba2);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background-color: white;
        width: 450px;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.3);
    }

    h2 {
        text-align: center;
        color: #4a148c;
        margin-bottom: 20px;
    }

    label {
        font-weight: bold;
        color: #333;
    }

    input, select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 14px;
    }

    input[type="submit"] {
        background-color: #6a1b9a;
        color: white;
        border: none;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #4a148c;
    }

    .result {
        margin-top: 20px;
        padding: 15px;
        background-color: #f3e5f5;
        border-left: 5px solid #6a1b9a;
        border-radius: 8px;
        color: #4a148c;
    }
</style>
</head>
<body>

<div class="container">
    <h2>Hotel Room Booking</h2>

    <%
        String name = request.getParameter("name");
        String roomType = request.getParameter("roomType");
        String daysStr = request.getParameter("days");

        if(name != null && roomType != null && daysStr != null) {
            int days = Integer.parseInt(daysStr);
            int roomCost = 0;

            if(roomType.equals("Standard")) {
                roomCost = 1000;
            } else if(roomType.equals("Deluxe")) {
                roomCost = 2000;
            } else if(roomType.equals("Suite")) {
                roomCost = 3500;
            }

            int total = roomCost * days;
            double gst = total * 0.18;
            double finalAmount = total + gst;
    %>

    <div class="result">
        <h3>Booking Summary</h3>
        Customer Name: <%= name %><br><br>
        Room Type: <%= roomType %><br><br>
        Number of Days: <%= days %><br><br>
        Room Charges: ₹ <%= total %><br><br>
        GST (18%): ₹ <%= gst %><br><br>
        Final Amount: ₹ <%= finalAmount %>
    </div>

    <%
        }
    %>

    <form method="post">
        <label>Customer Name:</label>
        <input type="text" name="name" required>

        <label>Room Type:</label>
        <select name="roomType" required>
            <option value="">Select Room Type</option>
            <option value="Standard">Standard</option>
            <option value="Deluxe">Deluxe</option>
            <option value="Suite">Suite</option>
        </select>

        <label>Number of Days:</label>
        <input type="number" name="days" min="1" required>

        <input type="submit" value="Book Now">
    </form>
</div>

</body>
</html>
