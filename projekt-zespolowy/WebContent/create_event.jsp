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
<div class=main></div>
	<form method="post" , action="CreateEvent">
		<table>
			<tr>
				<td>Event name</td>
				<td><input type="text" , name="eventName"></td>
			</tr>
			<tr>
				<td></td>
				<td><br><input style="margin-left:121px;" type="submit"  value="create"></td>
			</tr>
		</table>
	</form>
</body>
</html>