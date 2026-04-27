<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

<h2>Add Hostel</h2>

<% if(request.getParameter("msg") != null) { %>
    <p style="color:green;"><%= request.getParameter("msg") %></p>
<% } %>

<form action="<%=request.getContextPath()%>/AddHostelServlet" method="post">

<input type="number" name="id" placeholder="Hostel ID" required>

<input type="text" name="name" placeholder="Hostel Name" required>

<select name="type">
    <option value="Boys">Boys</option>
    <option value="Girls">Girls</option>
</select>

<input type="number" name="rooms" placeholder="Total Rooms" required>

<input type="text" name="warden" placeholder="Warden Name" required>

<input type="text" name="phone" placeholder="Phone Number" required>

<input type="text" name="address" placeholder="Address" required>

<button type="submit">Add Hostel</button>

</form>

</div>
</div>