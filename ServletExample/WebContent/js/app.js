$("#helloButton").click(function(){
	var name = $("#nameField").val();
	$.ajax({
		url:"/ServletExample/FormServlet",
		method:"POST",
		success: function(data,status,xhr){
			$("#helloHeader").html(data);
		},
		error: function(data,status,xhr){
			$("#helloHeader").html("Error");
		},
		data: name
	});
	
});