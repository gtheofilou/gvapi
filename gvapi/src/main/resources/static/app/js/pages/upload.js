(function() {
	
	var init = function() {
		
		var content = $("#upload");
    	
    	$("<div>").attr("id", "drop-area").addClass("dm-uploader m-5 p-5 text-center")
    	.append($("<h3>").addClass("mb-5 mt-5 text-muted").text("Drag and Drop"))
    	.append($("<div>").addClass("btn btn-primary btn-block mb-5").append($("<span>").text("Open the file Browser")).append($("<input>").attr("type", "file").attr("title", "Click to add Files")))
    	.appendTo(content);
		
	}
   
	var start = function() {
    	console.log("starting upload page");
    	
    	$("#drop-area").dmUploader({
    		  url: '/file/upload',
    		  //... More settings here...
    		  
    		  onInit: function(){
    		    console.log('Callback: Plugin initialized');
    		  }
    		  
    		  // ... More callbacks
    		});
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.upload = {
			init : init,
			start : start,
	};
	
})();