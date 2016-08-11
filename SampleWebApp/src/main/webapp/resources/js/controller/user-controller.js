/**
 * @author Macesarr
 */

'use strict'

App.controller('userController', [
		'$scope',
		'userService',

		function($scope, userService) {
			var self = this;
			self.user = {
				email : '',
				password : '',
				nickname : '',
			};
			self.users = [];

			self.createUser = function(user) {
				userService.createUser(user).then(
						console.log('usuario creado'), function(errResponse) {
							console.error('Error while creating User');
						});
			};

			self.submit = function() {

				console.log('Saving new user', self.user);

				self.createUser(self.user);

				/*
				 * if (self.user.id === null) { console.log('Saving New User',
				 * self.user); self.createUser(self.user); } else {
				 * self.updateUser(self.user, self.user.id); console.log('User
				 * updated with id ', self.user.id); }
				 */

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
