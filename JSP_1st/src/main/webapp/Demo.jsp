<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor='blue'>
Welcome to MITAOE 
<br/><br/>
The multiplaction of two number is <%= 25*4 %>
<br/><br/>
Time on Server is <%= new java.util.Date() %>
<br/><br/>
Is 75 less than 69 is <%= 75<69 %>
<br/><br/>
<%
for (int i=0;i<5;i++){
	out.println("<br/> I love Codind");
}

%>
<br/><br/>
<%
String Name = "MIT";
out.println("Welcome to " + Name);
%>
<br/><br/>
<%!
String makeItLower(String data){
	return data.toLowerCase();
}
 %>
 Lower case "Hello World":<%= makeItLower("Hello World") %>
 <br/><br/>
<%!
int addTwoNumbers(int a, int b){
    return a + b;
}
%>

The sum of 10 and 20 is: <%= addTwoNumbers(10, 20) %>
</body>

</html>