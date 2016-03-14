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

//If making a simple Get call to a server then there is a shorthand for the above ajax
$("#getButton").click(function(){
	$.get("/ServletExample/Hello", function(data){
		$("#helloHeader").html(data);
	});
	
});

//You can get pretty much anything even other html.
$("#getHtmlButton").click(function(){
	$.get("/ServletExample/ThreadSafety.html", function(data){
		$("#helloHeader").html(data);
	});
	
});

//You can even grab other javascript files using getScript
$("#getScriptButton").click(function(){
	$.getScript("/ServletExample/js/app2.js").done(function(script,textStatus){
		console.log(textStatus);
	});
	
});