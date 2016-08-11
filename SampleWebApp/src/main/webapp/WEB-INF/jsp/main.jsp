<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

</head>
<body ng-app="efialtes">
	<div class="container">
		<div class="row">
			<div>
				<div ng-controller="userController as ctrl"
					class="well bs-component">

					<form ng-submit="ctrl.submit()" name="register">
						<div>
							<label for="email">Email</label>
							<div>
								<input type="email" ng-model="ctrl.user.email" id="email"
									placeholder="abcd@gmail.com" required />
							</div>
						</div>
						<div>
							<label for="password" class="col-lg-2 control-label">Password</label>
							<div>
								<input type="password" ng-model="ctrl.user.password"
									id="password" required />
							</div>
						</div>
						<div>
							<label for="nickname">Nick Name</label>
							<div>
								<input type="text" ng-model="ctrl.user.nickname" id="nickname"
									placeholder="Nick Name" required />
							</div>
						</div>

						<div>
							<div>
								<input type="submit" value="Register"
									ng-disabled="register.$invalid">

							</div>
						</div>

					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>