'use strict'

App.factory('userService', [ '$http', '$q', function($http, $q) {

	return {

		createUser : function(user) {
			return $http.post('/user/', user).then(function(response) {
				return $http.post('/user/', user);
			}, function(errResponse) {
				console.error('Error while creating user');
				return $q.reject(errResponse)
			}

			);
		}

	};
	
} ]);





App.factory('removeService', [ '$http', '$q', function($http, $q) {

return {

	remove : function(email) {
		return $http.post('/removeuser/', email).then(function(response) {
			return  $http.get('/fetchregisteredusers/');
		}, function(errResponse) {
			console.error('Error while Removing user');
			return $q.reject(errResponse)
		}

		);
	}

};
} ]);

