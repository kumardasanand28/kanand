<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
	<h1>${message}</h1>


	${student.studentName} ${student.studentHobby} ${student.studentMobile}
	${student.studentDOB} ${student.studentSkills}

	<br />
	<h2>List of Users Registered</h2>

	<table border='2' class='table'>
		<tr>
			<td>NAME</td>
			<td>HOBBY</td>
			<td>MOBILE NUMBER</td>
			<td>DATE OF BIRTH</td>
			<td>SKILL SET</td>
		</tr>
		<c:forEach items="${resultList}" var="result">
			<tr>
				<td>${result.studentName }</td>
				<td>${result.studentHobby }</td>
				<td>${result.studentMobileNo }</td>
				<td>${result.studentDOB }</td>
				<td>${result.studentSkillSet }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>