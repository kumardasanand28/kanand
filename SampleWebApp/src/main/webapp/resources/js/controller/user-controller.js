'use strict'

App.controller('userController', [
		'$scope',
		'userService',function($scope, userService) {
			
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

			
			self.submit = function() {

				self.createUser(self.user);

				self.reset();
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
