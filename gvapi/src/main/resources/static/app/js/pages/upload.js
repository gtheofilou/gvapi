(function() {
	
	var init = function() {
		
		var content = $("#upload");
		
		var dropArea = $("<div>").addClass("col-md-6 col-sm-12")
		var fileList = $("<div>").addClass("col-md-6 col-sm-12")
		
		content.addClass("row").append(dropArea).append(fileList);
    	
		//dropArea
    	$("<div>").attr("id", "drop-area").addClass("dm-uploader m-5 p-5 text-center")
    	.append($("<h3>").addClass("mb-5 mt-5 text-muted").text("Drag and Drop"))
    	.append($("<div>").addClass("btn btn-primary btn-block mb-5").append($("<span>").text("Open the file Browser")).append($("<input>").attr("type", "file").attr("title", "Click to add Files")))
    	.appendTo(dropArea);
    	
    	$("<p>").addClass("mx-5 mb-2").append($("<span>").addClass("status"))
    	.appendTo(dropArea);
    	
    	$("<div>").addClass("progress mx-5")
    	.append($("<div>").addClass("progress-bar bg-success").attr("role", "progressbar").attr("aria-valuenow", "0").attr("aria-valuemin", "0").attr("aria-valuemax", "100"))
    	.appendTo(dropArea);
    	
    	//fileList
    	$("<div>").addClass("card h-100 mt-5 file-list")
    	.append($("<div>").addClass("card-header").text("File List"))
    	.append($("<ul>").addClass("list-unstyled p-2 d-flex flex-column col").attr("id", "files").append($("<li>").addClass("text-muted text-center empty").text("No files uploaded")))
    	.appendTo(fileList);
	}
   
	var start = function() {
    	console.log("starting upload page");
    	startUploader();
    	load();
	}
	
	var load = function() {
		$.ajax({
		      type: 'GET',
		      url: "/file/getFileList",
		      success: function(data) {
		    	  loadFileRows(data);
		      }
		});
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
  			  $('#cr' + id).attr('gvapi-id', 'gvapi' + data.id);
  			  $('#button' + id).attr('id', 'button' + data.id).attr('btn-orgin', data.id);
  			  updateProgressBar(100, false);
  		  },
  		  onUploadError: function(id, xhr, status, message){
  			  updateStatus('danger', xhr.responseJSON.error);
  			  updateProgressBar(0, false);
  		  },
  		  onNewFile: function(id, file){
  			 var row = createFileRow(id, file);
  			 $('#files').find('li.empty').fadeOut();
  		     $('#files').prepend(row);

  		      if (typeof FileReader !== "undefined"){
  		        var reader = new FileReader();
  		        var img = $('#cr' + id).find('img');
  		        
  		        reader.onload = function (e) {
  		          img.attr('src', e.target.result);
  		        }
  		        reader.readAsDataURL(file);
  		      }
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
	
	var createFileRow = function(id, file) {
		var row = $("<li>").addClass("media").attr("id", "cr" + id);
		var image = $("<img>").addClass("mr-3 mb-2 preview-img").attr("src", id);
		var info = $("<div>").addClass("media-body mb-1").append($("<p>").addClass("mb-2").append($("<strong>").text(file.name)));
		
		var rowData = {
				id : id,
				name : file.name,
				sent : false,
		}
		var controls = $("<div>").addClass("mb-2").append(createControls(rowData));
		
		info.append(controls);
		row.append(image).append(info);
		return row;
	}
	
	var loadFileRows = function(data) {
		if(data.length > 1) $('#files').find('li.empty').fadeOut();
		for (i = 0; i < data.length; i++) {
			var row = $("<li>").addClass("media").attr("id", "lo" + data[i].id).attr('gvapi-id', 'gvapi' + data[i].id);
			var image = $("<img>").addClass("mr-3 mb-2 preview-img").attr("src", "/file/download/" + data[i].name);
			var info = $("<div>").addClass("media-body mb-1").append($("<p>").addClass("mb-2").append($("<strong>").text(data[i].name)));
			var controls = $("<div>").addClass("mb-2").append(createControls(data[i]));
			
			info.append(controls);
			row.append(image).append(info);
			
  		    $('#files').prepend(row);
		}
	}
	
	var createControls = function(row) {
		
		var container = $("<div>");
		var btn = $("<button>").attr('id', 'button' + row.id).attr('btn-orgin', row.id).attr('type', 'button').addClass('btn btn-sm')
			.attr('data-loading-text', "<i class='fa fa-spinner fa-spin'></i> Sending...");
		container.append(btn);
		
		if(row.sent == true) btn.addClass('btn-success').attr("disabled", true).text('Sent');
		else btn.addClass('btn-primary').text('Send')
		
		var getId = function() {
			return function() {
				return btn.attr('btn-orgin');
			}
		}();
		
		btn.on('click', function() {
				$this = $(this);
				$this.html($this.attr('data-loading-text'));
				
				var imgId = getId();
				$.ajax({
				      type: 'POST',
				      url: "/googleapi/send",
				      dataType : "json",
				      contentType: "application/json; charset=utf-8",
				      data: JSON.stringify({ id: imgId }),
				      success: function(data) {
				    	  $this.text('Sent').removeClass('btn-primary').addClass('btn-success').attr("disabled", true);
				    	  showbtn.attr("disabled", false).addClass("btn-info");
				      }
				});
			});
		
		var showbtn = $("<button>").attr('id', 'button-show' + row.id).attr('btn-orgin', row.id).attr('type', 'button').attr('data-toggle', 'modal')
			.attr('data-target', '#gvModal').addClass('btn btn-sm btn-info ml-2').text('Show');
		container.append(showbtn);
		
		if(row.sent != true) showbtn.attr("disabled", true).removeClass("btn-info");
		showbtn.on('click', function() {
			var modal = $("#gvModal");
			var content = $("#gvModal .modal-body").empty();
			var title = $("#gvModal .modal-title");
			title.text('Google Response');
			var tbl = $("<table>").addClass('display').css("width", "100%");
			content.append(tbl);
			$.ajax({
			      type: 'GET',
			      url: "/googleapi/get/" + getId(),
			      dataType : "json",
			      contentType: "application/json; charset=utf-8",
			      success: function(data) {
			    	  tbl.DataTable({
			    		  	searching: false,
			    		    ordering:  false,
			    		    lengthChange: false,
			                data: data,
			                columns: [
			                    { data: 'description', title: 'Description'},
			                    { data: 'score', title: 'Score'},
			                ]
			            });
			    	  modal.modal();
			      }
			});
		});
		
		return container;
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.upload = {
			init : init,
			start : start,
	};
	
})();