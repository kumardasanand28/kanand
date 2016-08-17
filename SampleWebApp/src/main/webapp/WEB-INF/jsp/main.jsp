<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script	src="<c:url value='/resources/js/angular.js' />"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/controller/user-controller.js' />"></script>
<script src="<c:url value='/resources/js/service/user-service.js' />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<br/>
<title>Register</title>

</head>
<style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/webapp/resources/css/app.css' />" rel="stylesheet"></link>
<body ng-app="User">


	<div class="container">
		<div class="row">
			<div>
				<div ng-controller="userController as ctrl">

					<form ng-submit="ctrl.submit()" name="register">
						<div>
							<label for="email">Email</label>
							<div>
								<input type="email" ng-model="ctrl.user.email" id="email"  required />
							</div>
						</div>
						<div>
							<label for="password" >Password</label>
							<div>
								<input type="password" ng-model="ctrl.user.password" id="password" required />
							</div>
						</div>
						<div>
							<label for="nickname">Nick Name</label>
							<div>
								<input type="text" ng-model="ctrl.user.nickname" id="nickname"  required />
							</div>
						</div>
						<div>
							<div>
								<input type="submit" value="Register">

							</div>
						</div>
					</form>
					
				</div>
			</div>
		</div>

	</div>
	

</body>

<br/>
<br/>


 <script type="text/javascript">
            function Redirect() {
            			document.location.href = '/fetchregisteredusers';
            }
      </script>
      
      <form>
         <input type="button" value="Go to admin Page" onclick="Redirect();" />
      </form>
</html>