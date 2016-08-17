<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
       .nickname.ng-valid {
          background-color: lightgreen;
      }
      .nickname.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .nickname.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>

<body ng-app="User" class="ng-cloak">
	<div class="generic-container" ng-controller="userController as ctrl">
	 <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Registration Form </span></div>
              <div class="formcontainer">
              <form ng-submit="ctrl.submit()" name="register" class="form-horizontal">
              
              
                <div class="row">
                   <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="file">Name</label>
                 <div class="col-md-7">
                    <input type="text" ng-model="ctrl.user.name" name="name" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                      <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                     </div>
                   </div>
                 </div>
              
                 <div class="row">
                   <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="file">Email</label>
                 <div class="col-md-7">
                   <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                      <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                       </div>
                     </div>
                   </div>
                 </div>
                
                 <div class="row">
                   <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="file">Nick Name</label>
                 <div class="col-md-7">
                   <input type="nickname" ng-model="ctrl.user.nickname" name="nickname" class="form-control input-sm" placeholder="Enter your Password" required/>
                     </div>
                   </div>
                 </div>
                 <div>
                    <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Register" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                          </div>
                      </div>
                   </div>   
                      
              </form>
            </div>
            
     </div>
     
     
     <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Email</th>
                              <th>Nick Name</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.users">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.name"></span></td>
                              <td><span ng-bind="u.email"></span></td>
                              <td><span ng-bind="u.nickname"></span></td>
                              <td>
                             <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
          
	</div>
</body>
<script	src="<c:url value='../resources/js/angular.js' />"></script>
<script src="<c:url value='../resources/js/app.js' />"></script>
<script src="<c:url value='../resources/js/controller/user-controller.js' />"></script>
<script src="<c:url value='../resources/js/service/user-service.js' />"></script>


</html>