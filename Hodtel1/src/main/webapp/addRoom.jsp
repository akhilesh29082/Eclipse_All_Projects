<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">
    <h2>Add New Room</h2>

    <%
    String msg = request.getParameter("msg");
    String err = request.getParameter("err");
    if(msg != null) {
    %>
        <p style="color:green;"><%= msg %></p>
    <%
    }
    if(err != null) {
    %>
        <p style="color:red;"><%= err %></p>
    <%
    }
    %>

    <form action="AddRoomServlet" method="post" class="form-box">
        <label>Room ID:</label>
        <input type="number" name="room_id" required>

        <label>Hostel ID:</label>
        <input type="number" name="hostel_id" required>

        <label>Room No:</label>
        <input type="text" name="room_no" required>

        <label>Room Type:</label>
        <select name="room_type" required>
            <option value="Single">Single</option>
            <option value="Double">Double</option>
            <option value="Triple">Triple</option>
        </select>

        <label>Total Beds:</label>
        <input type="number" name="total_beds" min="1" required>

        <label>Floor No:</label>
        <input type="number" name="floor_no" min="1" required>

        <button type="submit" class="btn update">Add Room</button>
    </form>
</div>
</div>