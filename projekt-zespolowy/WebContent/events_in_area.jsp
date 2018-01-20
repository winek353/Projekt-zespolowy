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
<p>
	events in area:
	<table  width='350', border = \1\>
  			<c:forEach items="${events}" var="event">
  			<form method="post" action="JoinEvent">  
  				 <tr>
  			 		 <td>name: ${event.getEventName()} </td>
  			 		 <td>host: ${event.getHostName()}</td>
  			 		 <input type="hidden" name="event_id" value="${event.getId()}">
  			 		 <td><input type="submit" , value="join"></td>
  				 </tr>
  			 </form>
  			</c:forEach>
	</table>
</p>
</body>
</html>