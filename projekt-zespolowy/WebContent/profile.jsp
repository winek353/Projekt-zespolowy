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
		<table border = \1\>
			<tr>
				<th>user name:</th><th>${userName}</th>
			</tr>
			<tr>
				<th>email:</th><th> ${userEmail}</th>
			</tr>
		</table>
	</p>
	<p>
		friends:
		<table>
  			<c:forEach items="${friends}" var="friend">
  			 <tr>
    			 <td>${friend}</td>
  			 </tr>
  			</c:forEach>
		</table>
	</p>
</body>
</html>