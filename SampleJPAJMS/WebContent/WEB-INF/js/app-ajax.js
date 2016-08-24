$(document).ready(function(){
	  
	$(document).on('click', '.submitform', function(event){
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		$.ajax({
			type: "POST",
			url: "register", 
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