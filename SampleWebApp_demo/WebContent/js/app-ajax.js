$(document).ready(function(event){
	$("#submitButton").on("click", function(){
		$.ajax({
			type: "POST",
			url: "UserRegistrationServlet", 
			data: $("#register").serialize(),
			success: function(msg){
				console.log(msg);
				console.log(msg);
				$('#result').html(msg);
				$('#ex').show();
			},
			error: function(){
				$('#result').html(msg);
			}
		});

	});

});