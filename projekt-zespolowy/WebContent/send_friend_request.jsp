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
      $("#topNav").load("navBars/nav.html"); 
    });
    </script>
<title>Insert title here</title>
</head>
<body>
<div id="topNav"></div>
	<form method="post" , action="SendFriendRequest">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" , name="friendName"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" , value="add"></td>
			</tr>
		</table>
	</form>
</body>
</html>