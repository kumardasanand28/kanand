<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

	<h1>${headerMessage}</h1>

	<form:errors path="student.*" />

	<form action="/SpringApp/register/submitAdmissionForm.html" method="post">

		<table>
			<tr>
				<td>Student's Name : <input type="text" name="studentName" /></td>
			</tr>

			<tr>
				<td>Student's Hobby : <input type="text" name="studentHobby" /></td>
			</tr>

			<tr>
				<td>Student's Mobile : <input type="text" name="studentMobile" /></td>
			</tr>
			<tr>
				<td>Student's Date Of Birth : <input type="text"
					name="studentDOB" /></td>
			</tr>

			<tr>
				<td>Student's Skill Set</td>
				<td><select name="studentSkills" multiple>
						<option value="Core Java">Core Java</option>
						<option value="ATG">ATG</option>
						<option value="SQL">SQL</option>
				</select></td>
			</tr>


		</table>
		<input type="submit" value="Submit">
	</form>
</body>
</html>