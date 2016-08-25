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
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		$.ajax({
			type: "POST",
			url: "register?action="+id+"&name="+name, 
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
	
	
<script type="text/javascript">


$(document).ready(function(){
	
	var param = "<%=request.getParameter("name")%>";
	var addAddress = "<%=request.getParameter("addAddress")%>";
		if (param != 'null') {
			$.ajax({
				type : "GET",
				data : {
					"getAllValues" : "false",
					"name" : param
				},
				url : "register",
				success : function(responseJson) {
					console.log(responseJson);
					$('#name').val(responseJson.fullName);
					$('#age').val(responseJson.age);
					$('#qual').val(responseJson.qualification);
					$('#yop').val(responseJson.yearPassed);
					if(addAddress == 'false'){
						$('#aNickName').val(responseJson.addressList[0].addressNickName);
						$('#street').val(responseJson.addressList[0].street);
						$('#city').val(responseJson.addressList[0].city);
						$('#state').val(responseJson.addressList[0].state);
						$('#zip').val(responseJson.addressList[0].zip);
					}
					if (responseJson.gender == 'male') {
						$("#male").prop("checked", true);
					} else {
						$("#female").prop("checked", true);
					}
				},
				error : function() {
					$('#result').html("Exception!!");
				}
			});
		}
	});
</script>
<c:if test="${!empty param.name && param.addAddress == 'false'}">
<c:set var="stateAddress" value="disabled"></c:set>
</c:if>

<c:if test="${!empty param.name && param.addAddress == 'true'}">
<c:set var="stateOthers" value="disabled"></c:set>
</c:if>

<body class="ng-cloak">

	<div id="result">
	</div>

	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">User Registration Form </span>
			</div>
			<div class="formcontainer">


				<form id="register" action="UserRegisterServlet" method="POST">

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Full
								Name</label>
							<div class="col-md-7">
								<input type="text" name="fullname" id="name"
									class="username form-control input-sm" ${stateOthers } />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Age</label>
							<div class="col-md-7">
								<input type="text" name="age" id="age"
									class="form-control input-sm" ${stateOthers }/>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Qualification</label>
							<div class="col-md-7">
								<input type="text" name="qual" id="qual"
									class="form-control input-sm" ${stateOthers } />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Year
								Passed</label>
							<div class="col-md-7">
								<input type="text" name="yop" id="yop"
									class="form-control input-sm" ${stateOthers } />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Address Nick Name</label>
							<div class="col-md-7">
								<input type="text" name="aNickName" id="aNickName"
									class="form-control input-sm"  autocomplete="on" ${stateAddress}/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Street</label>
							<div class="col-md-7">
								<input type="text" name="street" id="street" class="form-control input-sm" ${stateAddress} autocomplete="on"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">City</label>
							<div class="col-md-7">
								<input type="text" name="city" id="city" class="form-control input-sm"  ${stateAddress} autocomplete="on" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">State</label>
							<div class="col-md-7">
								<input type="text" name="state" id="state" class="form-control input-sm"  ${stateAddress} autocomplete="on" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Zip Code</label>
							<div class="col-md-7">
								<input type="text" name="zip" id="zip" class="form-control input-sm"   ${stateAddress}autocomplete="on"/>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Gender</label>
							<div class="col-md-7">
								<input type="radio" name="gender" value="male" id="male" ${stateOthers } /> Male
								<input type="radio" name="gender" value="female" id="female" ${stateOthers } />Female
							</div>
						</div>
					</div>

					<c:choose>
						<c:when test="${param.addAddress == 'true'}">
							<button name="${param.name}" id="addaddress" class="submitform">Add Address</button>
						</c:when>
						<c:when test="${!empty param.name}">
							<button name="${param.name}" id="update" class="submitform">Update</button>
						</c:when>
						<c:otherwise>
							<button id="register" class="submitform">Register</button>
						</c:otherwise>
					</c:choose>


				</form>


			</div>
		</div>
	</div>
	<a href="/SampleJPAJMS/listUsers.jsp">List Users</a>
</body>
</html>