<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

    <h2>Add Complaint</h2>

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

    <form action="ComplaintServlet" method="post" class="form-box">

        <input type="hidden" name="action" value="add">

        <!-- STUDENT DETAILS -->
        <label>Student ID</label>
        <input type="number" name="studentId" placeholder="Enter Student ID" required>

        <label>Room ID</label>
        <input type="number" name="roomId" placeholder="Enter Room ID" required>

        <hr>

        <!-- COMPLAINT DETAILS -->
        <label>Complaint Type</label>
        <select name="type" required>
            <option value="">Select Type</option>
            <option>Plumbing</option>
            <option>Cleaning</option>
            <option>Furniture</option>
            <option>Electricity</option>
            <option>Other</option>
        </select>

        <label>Description</label>
        <textarea name="issue" placeholder="Describe the issue..." required></textarea>

        <br>

        <button type="submit" class="btn update">Submit Complaint</button>

    </form>

</div>
</div>