<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<nav>
	<c:if test="${not empty loggedInUserId}">
		<input type="text" placeholder="Search" action="Search">
		<a class="active" href="profile">profile</a>
		<a href="DisplayFriendRequests">friend list</a>
		<a href="DisplayMessages">messages</a>
		<a href="DisplayUserEvents">events</a>
		<a href="options.jsp">options</a>
		
		<form method="post" , action="Logout">
			<input id="logInNavi" type="submit" , value="logout">
		</form>	
	</c:if>
	
	<c:if test="${empty loggedInUserId}">
	<a class="active" href="register.jsp">register now</a>
		<form method="post" action="Login">
			<input id="logInNavi" type="submit" value="login">
			<input id="logInNavi" type="password" placeholder="Password" name="password" required>
			<input id="logInNavi" type="text" placeholder="User name" name="uname" required>
		</form>
	</c:if>
</nav>

</body>
</html>
