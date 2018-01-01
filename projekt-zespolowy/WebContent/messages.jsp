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
		messages:
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
	</p>
</body>
</html>