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
<div class="main">
<h2>Personal Data:</h2>
Email:<br>
	<input type="email" placeholder="${userEmail}" name="email"><br><br>
	
Birthday:
	<form action="">
	  <input type="date" name="birthday"><br><br>
	</form>
Gender:
	<form action="">
	  <input type="radio" name="gender" value="male" checked> Male<br>
	  <input type="radio" name="gender" value="female"> Female<br>
	</form> 
<h3>About me:</h3>
<textarea placeholder="Write something about yourself" rows="4" cols="50"></textarea>
<h3>Your hobbies:</h3>
<input type="checkbox" name="myCheck1"> Football<br>
<input type="checkbox" name="myCheck2"> Baseball<br>
<input type="checkbox" name="myCheck3"> Baseball<br>
<input type="checkbox" name="myCheck4"> Running<br>
<input type="checkbox" name="myCheck5"> Swimming<br>
<input type="checkbox" name="myCheck6"> Powerlifting<br><br>
<button style="margin-left:320px;" type="submit" class="saveHobbies">Save</button>
</div>

</body>
</html>