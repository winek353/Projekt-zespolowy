<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<font color="red">${systemMessage}</font>
	<form method="post" , action="Register">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" , name="uname"></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="text" , name="email"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" , name="password"></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="password" , name="confirmedPassword"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" , value="register"></td>
			</tr>
		</table>
	</form>
</body>
</html>