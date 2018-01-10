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
      $("#topNav").load("navBars/nav.html"); 
    });
    </script>
<title>Register</title>
</head>
<body>
<div id="topNav"></div>


<h1>Sign up</h1>
	<font color="red">${systemMessage}</font>
	
	<form method="post" action="Register">
  <div class="container">
    <label><b>User name</b></label>
    <input type="text" placeholder="User name" name="uname" required>

    <label><b>Email</b></label>
    <input type="text" placeholder="Email" name="email" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Password" name="password" required>
    
    <label><b>Confirm password</b></label>
    <input type="password" placeholder="Password" name="confirmedPassword" required>
    
    <input type="checkbox" checked="checked"> Remember me
    <p>By creating an account you agree to our <a href="https://www.youtube.com/watch?v=WjrqpJQBKCQ">Terms & Privacy</a>.</p>
    

    <div class="clearfix">
    <a href="profile.jsp">
      <button type="submit" class="signupbtn">Sign Up</button>
    </a>
    
      <a href="login.jsp">
      <button type="button" class="cancelbtn">Cancel</button>
      </a>
    </div>
  </div>
</form>
	
</body>
</html>