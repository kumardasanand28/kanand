<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<link src="css/app.css" rel="stylesheet"></link>
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
		$('#resultlist').empty();
		$('#result').empty();
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		$.ajax({
			type: "POST",
			url: "UserDataServlet", 
			data: $("#register").serialize(),
			success: function(msg){
				console.log(msg);
				$('#result').html('<font color="red">'+msg+'</font>');
				$.ajax({
					type: "GET",
					url: "UserDataServlet", 
					success: function(msg){
						var $table = $("<table border='2' class='table'>").appendTo($("#resultlist")); 
						$("<tr>").appendTo($table)
						         .append($("<td>").text("FULL NAME"))
						         .append($("<td>").text("EMP ID"))
						     	$("</tr>").appendTo($table);
						$.each(msg, function(index, user) {   
						    $("<tr>").appendTo($table)
		            		.append($("<td>").text(user.name))
		                	.append($("<td>").text(user.empId))
		                	  $("</tr>").appendTo($table);
						});
					},
					error: function(){
						$('#result').html(msg);
					}
				});
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

	<div id="result"></div>
	
	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">User Registration Form </span>
			</div>
			<div class="formcontainer">


				<form id="register" action="register" method="post">


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Full
								Name</label>
							<div class="col-md-7">
								<input type="text" name="fullname" id="name"
									class="username form-control input-sm" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Emp ID</label>
							<div class="col-md-7">
								<input type="text" name="empid" id="empid"
									class="form-control input-sm" />
							</div>
						</div>
					</div>
					<button id="register" class="submitform">Register</button>


				</form>


			</div>
		</div>
	</div>
	
	<div id="resultlist"></div>
</body>
</html>