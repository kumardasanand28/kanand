'use strict'

App.controller('userController', ['$scope','userService',function($scope, userService) {

	var self = this;


	self.user = {
			email : '',
			id : null,
			name : '',
			nickname : '',
	};


	self.users = [];

	self.submit = submit;
	self.remove = remove;
	self.reset = reset;

	fetchAllUsers();

	function fetchAllUsers(){
		userService.fetchAllUsers()
		.then(
				function(d) {
					self.users = d;
				},
				function(errResponse){
					console.error('Error while fetching Users');
				}
		);
	};


	function createUser(user){
		console.log('Called Create User');
		userService.createUser(user)
		.then(
				fetchAllUsers,
				function(errResponse){
					console.error('Error while creating User');
				}
		);
	};



	function deleteUser(id){
		userService.deleteUser(id)
		.then(
				fetchAllUsers,
				function(errResponse){
					console.error('Error while deleting User');
				}
		);
	};





	function submit() {
		console.log('Saving New User', self.user);
		createUser(self.user);
		reset();
	};




	function remove(id){
		console.log('id to be deleted', id);
		deleteUser(id);
	};


	function reset(){
		self.user={id:null,name:'',address:'',email:''};
		$scope.register.$setPristine(); 
	};

}]);
