'use strict';

var App = angular.module('User', []);

/*var App = angular.module('User', ['ngRoute', 'ngResource']);

userApp.config(function($routeProvider) {
	$routeProvider.when('/fetchregisteredusers', {
		controller : 'ViewUsersCtrl',
		templateUrl : 'userlist.jsp'
	})
});


userApp.factory( 'userservices', [ '$resource', function( $resource ){
	return new User( $resource );
	}] );

function User( resource ) {
	this.resource = resource;
	var User = resource('/fetchregisteredusers');
	User.get('',function(user){
		scope.user = user;
	})
};*/