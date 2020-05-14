(function() {
	
	var init = function() {
		
		var content = $("#statistics");
		
		var controls = $("<div>").addClass("col-md-12 col-sm-12 input-group");
		
		$("<div>").addClass("mt-5 col-md-2 col-sm-12")
    		.append($("<label>").attr("for", "user-dropdown").text("User"))
    		.append($("<select>").addClass("chosen-select form-control")
    		.attr("id", "st-user-dropdown").attr("data-placeholder", "Choose User"))
    		.appendTo(controls);
		
		$("<div>").addClass("mt-5 col-md-2 col-sm-12")
			.append($("<label>").attr("for", "data-dropdown").text("Data"))
			.append($("<select>").addClass("chosen-select form-control")
			.attr("id", "st-data-dropdown").attr("data-placeholder", "Choose Data source"))
			.appendTo(controls);
		
		$("<div>").addClass("mt-5 col-md-2 col-sm-12")
			.append($("<label>").attr("for", "user-dropdown").text("Plot"))
			.append($("<select>").addClass("chosen-select form-control")
			.attr("id", "st-plot-dropdown").attr("data-placeholder", "Choose Plot"))
			.appendTo(controls);
		
		$("<div>").addClass("mt-5 col-md-2 col-sm-12")
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
		
		addData();
		addPlots();
		addTypes();
		
		$("#st-user-dropdown").add("#st-data-dropdown").add("#st-plot-dropdown").add("#st-type-dropdown").on('change', function(e) {
			if(!!$("#st-user-dropdown").val() && !!$("#st-data-dropdown").val() && !!$("#st-plot-dropdown").val() && !!$("#st-type-dropdown").val()) {
				$("#show-button").attr("disabled", false);
			}
		});
		
		$("#st-data-dropdown").on('change', function(e) {
			addTypes($("#st-data-dropdown").val());
		});
		
		$("#show-button").on('click', function() {
			plot($("#st-user-dropdown").val(), $("#st-data-dropdown").val(), $("#st-plot-dropdown").val(), $("#st-type-dropdown").val());
		});
	}
	
	var plot = function(user, dataSource, plot, type) {
		$.ajax({
		      type: 'GET',
		      data:{user: user, dataSource: dataSource, type: type, limit: 50},
		      url: "/statistics/mostRecentPerUser",
		      success: function(data) {
		    	  console.log(data);
		    	  
		    	  if(plot=='cloud') plotCloud(data, dataSource, type);
		    	  else if(plot=='histogram') plotHistogram(data, dataSource, type);
		      }
		});
		
	}
	//Sum(weights) * log10(1+N)
	var calculateMetric = function(array) {
		return array[3] * Math.log10(1 + array[4]);
	}
	
	//IDF
	var calculateTFIDF = function(array) {
		return array[4] * array[9];
	}
	
	var plotCloud = function(data, dataSource, type) {
		var parsedData= [];
  		
		if(dataSource == 'labels') {
			if(type=='avg') {//use Gerasimos metric
				for(var i=0; i<data.length; i++) {
//					parsedData.push({text:data[i][1], weight:calculateMetric(data[i])})
					parsedData.push({text:data[i][1], weight:data[i][5]})
				}
			} else {
				for(var i=0; i<data.length; i++) {
					parsedData.push({text:data[i][1], weight:data[i][2]})
				}
			}
		}
		else if(dataSource == 'ocr') {
			if(type=='tfidf') {
				for(var i=0; i<data.length; i++) {
//					parsedData.push({text:data[i][6], weight:calculateTFIDF(data[i])})
					parsedData.push({text:data[i][6], weight:data[i][9]})
				}
			}
			else if(type=='gerasimos') {
				for(var i=0; i<data.length; i++) {
					parsedData.push({text:data[i][0], weight:data[i][3]})
				}
			}
			else {
				for(var i=0; i<data.length; i++) {
					parsedData.push({text:data[i][0], weight:data[i][4]})
				}
			}
		}
		
		$('#graph div').empty().removeAttr( 'style' );
	    $('#graph div').jQCloud(parsedData, {
		  width: 500,
		  height: 350
	    });
	}
	
	var plotHistogram = function(data, dataSource, type) {
		var x = [];
		var y = [];
		

		if (dataSource == 'labels') {
			if (type == 'avg') {
//				for (var i = 0; i < data.length; i++) {
//					data[i].push(calculateMetric(data[i]))
//				}

				data.sort(function(a, b) {
					return b[5] - a[5]
				});

				for (var i = 0; i < data.length; i++) {
					x.push(data[i][1]);
					y.push(data[i][5]);
				}
			} else {
				for (var i = 0; i < data.length; i++) {
					x.push(data[i][1]);
					y.push(data[i][2]);
				}
			}
		}
		else if(dataSource == 'ocr') {
			if(type=='tfidf') {
//				for (var i = 0; i < data.length; i++) {
//					data[i].push(calculateTFIDF(data[i]))
//				}

//				data.sort(function(a, b) {
//					return b[10] - a[10]
//				});
				
				for (var i = 0; i < data.length; i++) {
					x.push(data[i][6]);
					y.push(data[i][9]);
				}
			} 
			else if(type=='gerasimos') {
				for (var i = 0; i < data.length; i++) {
					x.push(data[i][0]);
					y.push(data[i][3]);
				}
			} else {
				
				data.sort(function(a, b) {
					return b[4] - a[4]
				});
				
				for (var i = 0; i < data.length; i++) {
					x.push(data[i][0]);
					y.push(data[i][4]);
				}
			}
		}
		
		var dataPlot = [
			  {
			    x: x,
			    y: y,
			    type: 'bar'
			  }
			];

		$('#graph div').empty().removeAttr( 'style' );;
		Plotly.newPlot($('#graph div')[0], dataPlot);
	}
	
	var loadUsers = function(data) {
		var userDropdown = $("#st-user-dropdown");
		userDropdown.append($("<option />"));
		userDropdown.append($("<option />").val("allusers").text("* All Users"));
		userDropdown.append($("<option />").val("allpoliticsusers").text("* All Politics Users"));
		$.each(data, function() {
			userDropdown.append($("<option />").val(this.name).text(this.name));
		});
		userDropdown.chosen();
	}
	
	var addData = function() {
		var dataDropdown = $("#st-data-dropdown");
		dataDropdown.append($("<option />"));
		dataDropdown.append($("<option />").val("labels").text("Labels"));
		dataDropdown.append($("<option />").val("ocr").text("OCR"));
		dataDropdown.chosen();
	}
	
	var addPlots = function() {
		var typeDropdown = $("#st-plot-dropdown");
		typeDropdown.append($("<option />"));
		typeDropdown.append($("<option />").val("cloud").text("Tag Cloud"));
		typeDropdown.append($("<option />").val("histogram").text("Histogram"));
		typeDropdown.chosen();
	}
	
	var addTypes = function(dataSource) {
		var typeDropdown = $("#st-type-dropdown");
		typeDropdown.empty();
		typeDropdown.append($("<option />"));
		
		if(dataSource == 'labels') {
			typeDropdown.append($("<option />").val("count").text("Count"));
			typeDropdown.append($("<option />").val("avg").text("Average"));
		} else if(dataSource == 'ocr') {
			typeDropdown.append($("<option />").val("tf").text("TF"));
			typeDropdown.append($("<option />").val("tfidf").text("TFIDF"));
			typeDropdown.append($("<option />").val("gerasimos").text("Gerasimos"));
		}
		
		typeDropdown.trigger("chosen:updated");
		typeDropdown.chosen();
	}
	
	$.gvapi ? null: $.gvapi = {};
	$.gvapi.statistics = {
			init : init,
			start : start,
	};
	
})();