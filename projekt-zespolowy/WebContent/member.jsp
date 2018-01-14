<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="styleSheets/default.css">
<script src="jquery-3.2.1.min.js"></script> 
<script> 
    $(function(){
      $("#topNav").load("navBars/nav.jsp"); 
    });
    </script>
<title>Insert title here</title>
</head>
<body>
<div id="topNav"></div>
welcome you are logged in
<div class="main"></div>
<p>
	<a href = "http://localhost:8080/projekt-zespolowy/profile">display profile</a>
</p>
<p>
	<a href = "http://localhost:8080/projekt-zespolowy/DisplayFriendRequests">friends</a>
</p>
<p>
	<a href = "http://localhost:8080/projekt-zespolowy/create_message.jsp">create message</a>
</p>
<p>
	<a href = "http://localhost:8080/projekt-zespolowy/DisplayMessages">show inbox</a>
</p>
<p>
	<form method="post" , action="Logout">
		<td><input type="submit" , value="logout"></td>
	</form>
</p>

</body>
</html>