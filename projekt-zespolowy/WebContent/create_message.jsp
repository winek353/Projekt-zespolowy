<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="main"></div>
<form method="post" , action="SendMessage">
	User: 
	<input type="text" , name="recipient"><br>
	<br/>
	<textarea rows="10" cols="60" name="message"></textarea>
    <br/>
 	<input type="submit" value="send" />
</form>

</body>
</html>