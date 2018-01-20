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
      $("#topNav").load("navBars/nav.html"); 
    });
    </script>
<script src="jquery-3.2.1.min.js"></script> 
<script> 
    $(function(){
      $("#topNav").load("navBars/nav.jsp"); 
    });
    </script>
<body>
<div id="topNav"></div>
<div class=main></div>
<button onclick="displayEvents()">display events</button><br><br>

<a href = "http://localhost:8080/projekt-zespolowy/create_event.jsp">create event</a><br>
<p>
	your events:
	<table  width='350', border = \1\>
  			<c:forEach items="${events}" var="event">
  				 <tr>
  			 		 <td>name: ${event.getEventName()} </td>
  			 		 <td>host: ${event.getHostName()}</td>
  				 </tr>
  			</c:forEach>
	</table>
</p>

<script>
function displayEvents(){
	window.location.replace("http://localhost:8080/projekt-zespolowy/DisplayEventsInArea")
}
</script>


<script>
function createEvent(){
	window.location.replace("http://localhost:8080/projekt-zespolowy/create_event.jsp")
}
</script>

</body>
</html>