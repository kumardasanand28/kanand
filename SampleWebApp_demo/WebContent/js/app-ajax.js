$(document).ready(function(){
	  
	$("#register").submit(function(event){
		console.log('called ajax');
		$.ajax({
			type: "POST",
			url: "UserRegistrationServlet", 
			data: $("#register").serialize(),
			success: function(msg){
				console.log(msg);
				$('#result').html(msg);
				$("#register").get(0).reset();
			},
			error: function(){
				$('#result').html(msg);
			}
		});
		 event.preventDefault();
	});

});