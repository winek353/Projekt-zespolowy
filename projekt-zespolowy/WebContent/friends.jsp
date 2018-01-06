<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
	<a href = "http://localhost:8080/projekt-zespolowy/send_friend_request.jsp">add friend</a>
</p>
<p>
	friend requests:
	<table  width='350', border = \1\>
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
</p>

</body>
</html>