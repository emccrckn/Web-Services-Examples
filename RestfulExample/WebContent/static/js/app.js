$("#createRandomMessageBtn").click(function(){
	var randomText = $("#randomTextFld").val();
	$.ajax({
		url:"/RestfulExample/create",
		method:"POST",
		contentType: "text/plain",
		dataType: "json",
		success: function(data,status,xhr){
			if(data.code == "success"){
				alert("Success!");
			}else if(data.code == "error"){
				alert("Error! Message is "+data.message);
			}
		},
		error: function(data,status,xhr){
			alert(JSON.stringify(data));
		},
		data: "text:"+randomText
	});
	
});

$("#createFormBtn").click(function(){
	var randomText = $("#randomTextFld").val();
	var data = {
			text:randomText
			};
	$.ajax({
		url:"/RestfulExample/create",
		method:"POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json",
		success: function(data,status,xhr){
			if(data.code == "success"){
				alert("Success!");
			}else if(data.code == "error"){
				alert("Error! Message is "+data.message);
			}
		},
		error: function(data,status,xhr){
			alert(JSON.stringify(data));
		},
		data: data
	});
	
});
