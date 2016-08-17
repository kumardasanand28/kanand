<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="js/app-ajax.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Data</title>
</head>
<style>
div.ex {
	text-align: right width:300px;
	padding: 10px;
	border: 5px solid grey;
	margin: 0px
}
</style>
<body>
	<h1>Registration Form</h1>
	
	<div class="result">    
	<div class="ex">
		<form action="UserRegistrationServlet" method="POST" id="register">
			<table style="with: 50%">
				<tr>
					<td>Full Name</td>
					<td><input type="text" name="fullname" /></td>
				</tr>
				<tr>
					<td>Age</td>
					<td><input type="text" name="age" /></td>
				</tr>
				<tr>
					<td>Qualification</td>
					<td><input type="text" name="qual" /></td>
				</tr>
				<tr>
					<td>Percentage</td>
					<td><input type="text" name="percent" /></td>
				</tr>
				<tr>
					<td>Year Passed</td>
					<td><input type="text" name="yop" /></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><input type="radio" name="gender" value="male" /> Male
					<input type="radio" name="gender" value="female" />Female</td>
				</tr>
				<tr>
					<td>Interests</td>
					<td>
					<input type="checkbox" name="interest" value="cricket" />Cricket 
					<input type="checkbox" name="interest" value="hockey" />Hockey
					<input type="checkbox" name="interest" value="football" />FootBall
					<input type="checkbox" name="interest" value="tt" />Table Tennis
					</td>
				</tr>
			</table>
			<input type="submit" value="Register" id="submitButton" />
		</form>
	</div>
	</div>
</body>
</html>