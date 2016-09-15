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
<title>Add User</title>
</head>
<style>
div.result {
	color: red;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type: "GET",
		url: "TestSessionServlet", 
		success: function(msg){
		},
		error: function(){
			$('#result').html(msg);
		}
	});
	
	$(document).on('click', '.submitform', function(event){
		$('#result').empty();
		$.ajax({
			type: "POST",
			url: "TestSessionServlet", 
			data: $("#register").serialize(),
			success: function(msg){
				$("#register").get(0).reset();
				$.ajax({
					type: "GET",
					url: "TestSessionServlet", 
					success: function(msg){
						$('#result').text(JSON.stringify(msg));
					},
					error: function(){
						$('#result').html(msg);
					}
				});
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
							<label class="col-md-2 control-lable" for="file">KEY</label>
							<div class="col-md-7">
								<input type="text" name="key" id="key"
									class="username form-control input-sm" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">VALUE</label>
							<div class="col-md-7">
								<input type="text" name="value" id="value"
									class="form-control input-sm" />
							</div>
						</div>
					</div>
					<button id="register" class="submitform">SUBMIT</button>


				</form>


			</div>
		</div>
	</div>
	
	<div id="resultlist"></div>
</body>
</html>