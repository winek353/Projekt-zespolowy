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
<link rel="stylesheet" type="text/css" href="styleSheets/default.css">
<title>Insert title here</title>
</head>
<body>
<div id="topNav"></div>
<div class="main">
	<h3>friends: </h3>
	<p>
			<table>
	  			<c:forEach items="${friends}" var="friend">
	  			 <tr>
	    			<p> <td>${friend}</td> </p>
	  			 </tr>
	  			</c:forEach>
			</table>
	</p>
	<a href = "http://localhost:8080/projekt-zespolowy/send_friend_request.jsp">add friend</a>
<p>friend requests:
	<table  width='350'>
  			<c:forEach items="${friendRequests}" var="friendRequest">
  			<form method="post" action="AddFriend">  
  				 <tr>
  			 		 <td>from ${friendRequest.getRequesterName()}</td>
  			 		 <input type="hidden" name="request_id" value="${friendRequest.getId()}">
  			 		 <td><input type="submit" , value="accept"></td>
  				 </tr>
  			 </form>
  			</c:forEach>
	</table>
	</div>

</body>
</html>