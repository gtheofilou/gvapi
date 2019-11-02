(function() {
	
	var init = function() {
		
	}
   
	var start = function() {
    	console.log("starting statistics page");
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.statistics = {
			init : init,
			start : start,
	};
	
})();