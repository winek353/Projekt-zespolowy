<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<div class="main"></div>
	<font color="red">${systemMessage}</font>
	<form method="post" , action="Login">
		<table>
			<tr>
				<td style="color:gold">User Name</td>
				<td><input type="text" , name="uname"></td>
			</tr>
			<tr>
				<td style="color:gold">Password</td>
				<td><input type="password" , name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" , value="login"></td>
			</tr>
		</table>
	</form>
	<a href = "http://localhost:8080/projekt-zespolowy/register.jsp"> Register</a>
</body>
</html>