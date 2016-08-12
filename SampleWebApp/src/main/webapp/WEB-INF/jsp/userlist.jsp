<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script	src="<c:url value='/resources/js/angular.js' />"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/controller/user-controller.js' />"></script>
<script src="<c:url value='/resources/js/service/user-service.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<body>

<h1>LIST OF USERS REGISTERED</h1>
<table border="1" style="width: 95%; border-collapse: collapse;">
    
  <tr>
    <td class="headercol"><b>EMAIL</b></td>
    <td class="headercol"><b>VISITS</b></td>
    <td class="headercol"><b>NICKNAME</b></td>
    <td class="headercol"><b>ACTIVE/NOTACTIVE</b></td>
    <td class="headercol"><b>REGISTRATION DATE</b></td>
  
  </tr>
<c:forEach items="${userlist}" var="user">     
    <tr>
      <td class="rowcol">${user.email}</td>
      <td class="rowcol">${user.visits}</td>
      <td class="rowcol">${user.nickname}</td>
      <td class="rowcol">${user.active}</td>
      <td class="rowcol">${user.createdDate}</td>
    </tr>
</c:forEach>
  
</table>


</body>
</html>