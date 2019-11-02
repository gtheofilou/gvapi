(function() {
   
	var start = function() {
		console.log("router started");
		window.addEventListener("hashchange", function() {
			route();
	    });
	}
	
	//Routing
	var route = function() {
		var hash = window.location.hash;
		console.log(hash);
		
		$(".page").hide();
		
		switch (hash) 
		{
		    case "#upload":
		    	$("#upload").show();
		    	$.gvapi.upload.start();
		        break;
		    case "#statistics":
		    	$("#statistics").show();
		    	$.gvapi.statistics.start();
		        break;
		    default:
		    	console.warn("Unknown page");
		        break;
		}
	} 
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.router = {
			start : start,
	};
	
})();