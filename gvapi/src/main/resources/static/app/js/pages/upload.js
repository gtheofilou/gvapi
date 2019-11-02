(function() {
	
	var init = function() {
		
		var content = $("#upload");
    	
    	$("<div>").attr("id", "drop-area").addClass("dm-uploader m-5 p-5 text-center")
    	.append($("<h3>").addClass("mb-5 mt-5 text-muted").text("Drag and Drop"))
    	.append($("<div>").addClass("btn btn-primary btn-block mb-5").append($("<span>").text("Open the file Browser")).append($("<input>").attr("type", "file").attr("title", "Click to add Files")))
    	.appendTo(content);
    	
    	$("<p>").addClass("mx-5 mb-2").append($("<span>").addClass("status"))
    	.appendTo(content);
    	
    	$("<div>").addClass("progress mx-5")
    	.append($("<div>").addClass("progress-bar bg-success").attr("role", "progressbar").attr("aria-valuenow", "0").attr("aria-valuemin", "0").attr("aria-valuemax", "100"))
    	.appendTo(content);
		
	}
   
	var start = function() {
    	console.log("starting upload page");
    	startUploader();
	}
	
	var startUploader = function() {
		$("#drop-area").dmUploader({
  		  url: '/file/upload',
  		  onInit: function(){
  		    console.log('Callback: Plugin initialized');
  		  },
  		  onBeforeUpload: function(id){
  			  updateProgressBar(0);
  			  updateStatus('uploading', 'Uploading...');
  		   },
  		  onUploadProgress: function(id, percent) {
  			  updateProgressBar(percent);
  		  },
  		  onUploadSuccess: function(id, data){
  			  updateStatus('success', 'Upload Complete');
  			  updateProgressBar(100, false);
  		    },
  		   onUploadError: function(id, xhr, status, message){
  			  updateStatus('danger', xhr.responseJSON.error);
  			  updateProgressBar(0, false);
  		   },
		});
	}
	
	var updateProgressBar = function(percent, active) {
		
		 active = (typeof active === 'undefined' ? true : active);
		 
		 var bar = $("#upload").find('div.progress-bar');
		 bar.width(percent + '%').attr('aria-valuenow', percent);
		 bar.toggleClass('progress-bar-striped progress-bar-animated', active);

		 if (percent === 0) bar.html('');
		 else bar.html(percent + '%');
	}
	
	var updateStatus = function(status, message)
	{
	  $("#upload").find('span.status').html(message).prop('class', 'status text-' + status);
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.upload = {
			init : init,
			start : start,
	};
	
})();