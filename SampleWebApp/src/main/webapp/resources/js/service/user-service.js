/**
 * @author Macesarr
 */

'use strict'

App.factory('userService', [ '$http', '$q', function($http, $q) {

	return {

		createUser : function(user) {
			return $http.post('/user/', user).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while creating user');
				return $q.reject(errResponse)
			}

			);
		}

	};
} ]);
