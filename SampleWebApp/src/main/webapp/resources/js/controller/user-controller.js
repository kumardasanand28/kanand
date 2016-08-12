'use strict'

App.controller('userController', ['$scope','userService','removeService',function($scope, userService,removeService) {
			
			var self = this;
			
			
			self.user = {
				email : '',
				password : '',
				nickname : '',
			};
			
			
			self.users = [];

			self.createUser = function(user) {
				userService.createUser(user).then(
						console.log('Successfully Created'), function(errResponse) {
							console.error('Error while creating User');
						});
			};
			
			self.removeUser = function(email) {
				removeService.remove(email).then(
						 window.location.reload(false), function(errResponse) {
							console.error('Error while removing User');
						});
			};

			self.submit = function() {

				
				self.createUser(self.user);

				self.reset();
			};
			
			

			self.remove = function(e){
				self.removeUser(e.currentTarget.value);
				
			};
			
			
			
			self.reset = function() {
				self.user = {
					email : '',
					password : '',
					nickname : ''
				};
				$scope.register.$setPristine(); // reset Form
			};
			
		}]);
