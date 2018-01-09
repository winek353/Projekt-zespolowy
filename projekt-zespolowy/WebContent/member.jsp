<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty loggedInUserId}">
   welcome you are logged in
<p>
			<a href="http://localhost:8080/projekt-zespolowy/profile">display
				profile</a>
		</p>
		<p>
			<a
				href="http://localhost:8080/projekt-zespolowy/DisplayFriendRequests">friends</a>
		</p>
		<p>
			<a href="http://localhost:8080/projekt-zespolowy/create_message.jsp">create
				message</a>
		</p>
		<p>
			<a href="http://localhost:8080/projekt-zespolowy/DisplayMessages">show
				inbox</a>
		</p>
		<p>
		<form method="post" , action="Logout">
			<td><input type="submit" , value="logout"></td>
		</form>
		</p>
	</c:if>
	<c:if test="${empty loggedInUserId}">
		<p>You're not logged in!</p>
	</c:if>


</body>
</html>