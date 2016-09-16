<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<link src="css/app.css"  rel="stylesheet"></link>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Data</title>
</head>
<style>
div.result {
  color: red;
}


</style>

<script type="text/javascript">
$(document).ready(function(){
	  
	$(document).on('click', '.submitform', function(event){
		$.ajax({
			type: "POST",
			url: "TestServlet", 
			data: $("#register").serialize(),
			success: function(msg){
				console.log(msg);
				$('#result').html('<font color="red">'+msg+'</font>');
				$("#register").get(0).reset();
			},
			error: function(){
				$('#result').html(msg);
			}
		});
		 event.preventDefault();
	});
});
</script>
	

<body class="ng-cloak">

	<div id="result">
	</div>

	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">User Registration Form </span>
			</div>
			<div class="formcontainer">


				<form id="register" action="register" method="post">


							<button id="register" class="submitform">TEST PERSIST</button>


				</form>


			</div>
		</div>
	</div>
</body>
</html>