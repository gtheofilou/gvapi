(function() {
	
	var init = function() {
		
		var content = $("#statistics");
		
		var controls = $("<div>").addClass("col-md-12 col-sm-12 input-group");
		
		$("<div>").addClass("mt-5 col-md-3 col-sm-12")
    		.append($("<label>").attr("for", "user-dropdown").text("User"))
    		.append($("<select>").addClass("chosen-select form-control")
    		.attr("id", "st-user-dropdown").attr("data-placeholder", "Choose User"))
    		.appendTo(controls);
		
		$("<div>").addClass("mt-5 col-md-3 col-sm-12")
			.append($("<label>").attr("for", "user-dropdown").text("Plot"))
			.append($("<select>").addClass("chosen-select form-control")
			.attr("id", "st-plot-dropdown").attr("data-placeholder", "Choose Plot"))
			.appendTo(controls);
		
		$("<div>").addClass("mt-5 col-md-3 col-sm-12")
			.append($("<label>").attr("for", "user-dropdown").text("Type"))
			.append($("<select>").addClass("chosen-select form-control")
			.attr("id", "st-type-dropdown").attr("data-placeholder", "Choose Type"))
			.appendTo(controls);
		
		$("<span>").addClass("mt-5").append($("<button>").attr('id', 'show-button')
			.attr('disabled', true)
			.attr('type', 'button')
			.css("margin-top","30px")
			.addClass('btn btn-sm btn-primary input-group-btn')
			.text('Show'))
			.appendTo(controls);
		
		
		var graph = $("<div>").attr("id", "graph")
			.addClass("col-md-12 col-sm-12")
			.append($("<div>").addClass("mx-auto mt-5"));

		
		content.addClass("row").append(controls).append(graph);
	}
   
	var start = function() {
    	console.log("starting statistics page");
    	load();
    	extraInit();
	}
	
	var load = function() {
		$.ajax({
		      type: 'GET',
		      url: "/users/get",
		      success: function(data) {
		    	  loadUsers(data, false);
		      }
		});
	}
	
	var extraInit = function() {
		
		addPlots();
		addTypes();
		
		$("#st-user-dropdown").add("#st-plot-dropdown").add("#st-type-dropdown").on('change', function(e) {
			if(!!$("#st-user-dropdown").val() && !!$("#st-plot-dropdown").val() && !!$("#st-type-dropdown").val()) {
				$("#show-button").attr("disabled", false);
			}
		});
		
		$("#show-button").on('click', function() {
			plot($("#st-user-dropdown").val(), $("#st-plot-dropdown").val(), $("#st-type-dropdown").val());
		});
	}
	
	var plot = function(user, plot, type) {
		$.ajax({
		      type: 'GET',
		      data:{user: user, type:type, limit: 100},
		      url: "/statistics/mostRecentPerUser",
		      success: function(data) {
		    	  console.log(data);
		    	  
		    	  if(plot=='cloud') plotCloud(data, type);
		    	  else if(plot=='histogram') plotHistogram(data, type);
		      }
		});
		
	}
	//Sum(weights) * log10(1+N)
	var calculateMetric = function(array) {
		return array[3] * Math.log10(1 + array[4]);
	}
	
	var plotCloud = function(data, type) {
		var parsedData= [];
  		
		if(type=='avg') {//use Gerasimos metric
			for(var i=0; i<data.length; i++) {
				parsedData.push({text:data[i][1], weight:calculateMetric(data[i])})
			 }
		}else {
		  for(var i=0; i<data.length; i++) {
			parsedData.push({text:data[i][1], weight:data[i][2]})
		  }
		}
		  $('#graph div').empty().removeAttr( 'style' );
	      $('#graph div').jQCloud(parsedData, {
			  width: 500,
			  height: 350
		  });
	}
	
	var plotHistogram = function(data, type) {
		var x = [];
		var y = [];
		
		
		if(type=='avg') {
			for(var i=0; i<data.length; i++) {
				data[i].push(calculateMetric(data[i]))
			}
			
			data.sort(function(a, b){return b[5] - a[5]});
			
			for(var i=0; i<data.length; i++) {
				x.push(data[i][1]);
				y.push(data[i][5]);
			}
		}else {
			for(var i=0; i<data.length; i++) {
				x.push(data[i][1]);
				y.push(data[i][2]);
			}
		}
		
		var data = [
			  {
			    x: x,
			    y: y,
			    type: 'bar'
			  }
			];

		$('#graph div').empty().removeAttr( 'style' );;
		Plotly.newPlot($('#graph div')[0], data);
	}
	
	var loadUsers = function(data) {
		var userDropdown = $("#st-user-dropdown");
		userDropdown.append($("<option />"));
		$.each(data, function() {
			userDropdown.append($("<option />").val(this.name).text(this.name));
		});
		userDropdown.chosen();
	}
	
	var addPlots = function() {
		var typeDropdown = $("#st-plot-dropdown");
		typeDropdown.append($("<option />"));
		typeDropdown.append($("<option />").val("cloud").text("Tag Cloud"));
		typeDropdown.append($("<option />").val("histogram").text("Histogram"));
		typeDropdown.chosen();
	}
	
	var addTypes = function() {
		var typeDropdown = $("#st-type-dropdown");
		typeDropdown.append($("<option />"));
		typeDropdown.append($("<option />").val("count").text("Count"));
		typeDropdown.append($("<option />").val("avg").text("Average"));
		typeDropdown.chosen();
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.statistics = {
			init : init,
			start : start,
	};
	
})();