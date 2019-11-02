(function() {
	$.gvapi = {};
	
	//Initialization
	$(function() {
	    $.gvapi.router.start();
	    
	    //Pages
	    $.gvapi.upload.init();
	    $.gvapi.statistics.init();
	});
	
})();