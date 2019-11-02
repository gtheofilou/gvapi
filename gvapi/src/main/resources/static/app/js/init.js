(function() {
	$.gvapi = {};
	
	//Initialization
	$(function() {
	    //Pages
	    $.gvapi.upload.init();
	    $.gvapi.statistics.init();
	    
	    $.gvapi.router.start();
	});
	
})();