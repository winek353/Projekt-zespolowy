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
	<p>messages:
		<table  width='350', border = \1\>
  			<c:forEach items="${messages}" var="message">
  			 <tr>
  			 	 <td bgcolor='#D6D6D6'>author ${message.getAuthorName()}</td>
  			 </tr>
  			 <tr>
    			 <td>${message.getMessageText()}</td>
  			 </tr>
  			</c:forEach>
		</table>
		<br>
	<button onclick="createMessage()">create conversation</button>
	
<script>
	function createMessage() {
		window.location.replace("http://localhost:8080/projekt-zespolowy/create_message.jsp");
	}
</script>
	
</body>
</html>