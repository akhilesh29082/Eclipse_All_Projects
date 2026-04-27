<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
<div class="container">

<h2>Add Student</h2>

<%
String msg = request.getParameter("msg");
String err = request.getParameter("err");
%>

<% if(msg != null) { %>
    <p style="color:green; font-weight:600;"><%= msg %></p>
<% } %>

<% if(err != null) { %>
    <p style="color:red; font-weight:600;"><%= err %></p>
<% } %>

<form action="AddStudentServlet" method="post" class="form-box">

<!-- BASIC INFO -->
<label>Student ID</label>
<input type="number" name="id" placeholder="Enter Student ID" required>

<label>Full Name</label>
<input type="text" name="name" placeholder="Enter Full Name" required>

<label>Gender</label>
<select name="gender" required>
    <option value="">Select Gender</option>
    <option>Male</option>
    <option>Female</option>
</select>

<label>Date of Birth</label>
<input type="date" name="dob" required>

<label>Blood Group</label>
<input type="text" name="blood_group" placeholder="Ex: O+, A-" required>

<hr>

<!-- CONTACT -->
<label>Mobile Number</label>
<input type="text" name="mobile" placeholder="Enter Mobile Number" required>

<label>Email</label>
<input type="email" name="email" placeholder="Enter Email" required>

<label>Address</label>
<input type="text" name="address" placeholder="Enter Address" required>

<hr>

<!-- ACADEMIC -->
<label>Course</label>
<input type="text" name="course" placeholder="Enter Course" required>

<label>Year</label>
<input type="number" name="year" placeholder="Enter Year" required>

<hr>

<!-- PARENT DETAILS -->
<label>Parent Name</label>
<input type="text" name="parent_name" placeholder="Enter Parent Name" required>

<label>Parent Contact</label>
<input type="text" name="parent_contact" placeholder="Enter Parent Contact" required>

<hr>

<!-- OPTIONAL -->
<label>Room ID (Optional)</label>
<input type="number" name="room_id" placeholder="Enter Room ID">

<label>Hostel ID (Optional)</label>
<input type="number" name="hostel_id" placeholder="Enter Hostel ID">

<br>

<button type="submit" class="btn update">Add Student</button>

</form>

</div>
</div>