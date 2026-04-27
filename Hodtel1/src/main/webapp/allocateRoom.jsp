<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">
    <h2>Allocate Student to Room</h2>

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

    <form action="AllocateRoomServlet" method="post" class="form-box">
        <label>Student ID:</label>
        <input type="number" name="student_id" required>

        <label>Room ID:</label>
        <input type="number" name="room_id" required>

        <button type="submit" class="btn update">Allocate Student</button>
    </form>
</div>
</div>