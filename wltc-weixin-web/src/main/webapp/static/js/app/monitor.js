$.ajaxSetup({ cache :false});
$(document).ready(function() {	
	wms_map.setHeight();
	info_container.setHeight();
	dataload.load_pstData();
	dataload.load_warnscount_data();
	$("#stdrpInfo").click(function(){
		dataload.load_pstData();
		switcher_handler.warnTypeChange();
	});
	$("#stZInfo").click(function(){
		dataload.load_zstData();
		switcher_handler.warnTypeChange();
	});
	switcher_handler.init();
	timer.startAutoFresh();
});

$(window).load(function() {
	wms_map.init();
	$("#pstcheck").attr("checked", true);
	$("#rstcheck").attr("checked", false);
	$("#zstcheck").attr("checked", false);
	$("#volChange").attr("checked", true);
	dataload.load_pstMarkers();
	dataload.load_warnMarkers();
	switcher_handler.warnTypeChange();	
	switcher_handler.alarmVolChange();
});

$(window).resize(function() {
 	wms_map.setHeight();
 	info_container.setHeight();
});

var map;
var pstmarkersLayer;
var rstmarkersLayer;
var zstmarkersLayer;
var warnmarkerLayer;
var epsg4326 =  new OpenLayers.Projection("EPSG:4326");
var epsg900913 = new OpenLayers.Projection("EPSG:900913");
OpenLayers.IMAGE_RELOAD_ATTEMPTS = 3;  
OpenLayers.Util.onImageLoadErrorColor = "transparent"; 	
OpenLayers.ImgPath = "/wm/static/js/openlayers/theme/custom/green_img/";	
wms_map = {
	init: function() {
		var bounds = new OpenLayers.Bounds();
		bounds.extend(new OpenLayers.LonLat(120.489322,29.663687).transform(epsg4326, epsg900913));
		bounds.extend(new OpenLayers.LonLat(121.370093,29.160284).transform(epsg4326, epsg900913));
	    map = new OpenLayers.Map("map",{
	    	maxExtent: bounds,
	    	restrictedExtent: bounds,	    	
	    	controls: []});
	    
	    map.addControl( new OpenLayers.Control.PanZoomBar() );
        map.addControl( new OpenLayers.Control.Navigation() );
	    map.addControl( new OpenLayers.Control.Scale() );
	    map.addControl( new OpenLayers.Control.ScaleLine() );
        var vectorLayer = new OpenLayers.Layer.Vector("EditToolBar");
        map.addLayer(vectorLayer);	    	    
	    map.addControl( new OpenLayers.Control.EditingToolbar(vectorLayer) );        
	    
		var streets = new OpenLayers.Layer.Google("GoogleStreets");		    
		var topoMap = new OpenLayers.Layer.Google("GoogleTopoMap",{type: google.maps.MapTypeId.TERRAIN});
		var sateMap = new OpenLayers.Layer.Google("GoogleSateMap",{type: google.maps.MapTypeId.SATELLITE});		
		var wms_city = new OpenLayers.Layer.WMS(
			'新昌县界',
			'http://wartermonitor.wicp.net:8080/geoserver/shaoxing/wms',
			{
					layers:'xinchang',
					transparent: true
			},
			{
				isBaseLayer: false,						
                projection: 'EPSG:900913'
			}
		);
		
		pstmarkersLayer = new OpenLayers.Layer.Vector("pstmarkersLayer");
		zstmarkersLayer = new OpenLayers.Layer.Vector("zstmarkersLayer");			
		rstmarkersLayer = new OpenLayers.Layer.Vector("rstmarkersLayer");
		warnmarkerLayer = new OpenLayers.Layer.Vector("warnmarkerLayer");
		map.addLayers([streets, topoMap, sateMap, wms_city, pstmarkersLayer,zstmarkersLayer,rstmarkersLayer, warnmarkerLayer]);
		
	    var lonLat = new OpenLayers.LonLat(120.977713,29.383099).transform(epsg4326, epsg900913);
		map.setCenter(lonLat, 11);  
		
	    var selectControl = new OpenLayers.Control.SelectFeature(
	    	[pstmarkersLayer, rstmarkersLayer, zstmarkersLayer, warnmarkerLayer],
    	    {
    	        clickout: true, toggle: false,
    	        multiple: false, hover: false,
    	        callbacks: {
    	        	click:function(event){    	        		
    	        		if(event.layer.name == "pstmarkersLayer"){
    	        			wms_map.showPStInfo(event.data);
    	        		}else if(event.layer.name == "rstmarkersLayer"){
    	        			wms_map.showRStInfo(event.data);
    	        		}else if(event.layer.name == "zstmarkersLayer"){
    	        			wms_map.showZStInfo(event.data);
    	        		}
    	        	}
    	        }
    	    }
    	);	    
    	map.addControl(selectControl);
    	selectControl.activate();
	},
	showPStInfo: function(data){
		$("#pstMarkerWindow").css("display",""); 
		$("#pstNmae").html(data.stnm);
    	dataload.load_stfullInfo_bystcd(data.stcd,'pstInfoData');
    	dataload.load_drpst_dist_values(data.stcd);
    	dataload.load_drpst_min5_values(data.stcd); 
    	dataload.load_lastreceived(data.stcd);
	},
	showRStInfo: function(data){
		$("#rstMarkerWindow").css("display",""); 
		$("#rstNmae").html(data.stnm);
		dataload.load_stfullInfo_bystcd(data.stcd,'rstInfoData');
		dataload.load_rst_real_values(data.stcd,data.sttp);
	},
	showZStInfo: function(data){
		$("#zstMarkerWindow").css("display",""); 
		$("#zstNmae").html(data.stnm);
		dataload.load_stfullInfo_bystcd(data.stcd,'zstInfoData');
		dataload.load_rst_real_values(data.stcd,data.sttp);
	},
	pstmarkerInfoClose:function(){
		$("#pstMarkerWindow").css("display","none");
	},
	rstmarkerInfoClose:function(){
		$("#rstMarkerWindow").css("display","none");
	},
	zstmarkerInfoClose:function(){
		$("#zstMarkerWindow").css("display","none");
	},
	setHeight: function(){
		var mapcontainer = $('#mapcontainer');
		var window_height = $(window).height();
		var header_height = $('#header').height();
		mapcontainer.height(window_height - header_height);			
	}
};
info_container = {
	setHeight: function(){
		var window_height = $(window).height();			
		var raindata_container =  $('#rainData');
		var container_height = window_height - 150;
		raindata_container.height(container_height);
		var raintable = $('.y-fluid-table');
		raintable.attr("max-height",container_height);
	}
};

dataload = {
	load_pstData: function(){
		 var hour = $("#pstHourSelect").val();
		 $.ajax({
	           type:"get",
	           url:"data/rsthourdata.htm",
	           dataType:"json",
	           async:false,
	           success:function(data){
					$("#pstDataTable").empty();
					switch(hour){
					case "1":
					  $( "#pst1HourDataTemp" ).tmpl( data ).appendTo( "#pstDataTable" );
					  break;
					case "3":
					  $( "#pst3HourDataTemp" ).tmpl( data ).appendTo( "#pstDataTable" );
					  break;
					case "6":
					  $( "#pst6HourDataTemp" ).tmpl( data ).appendTo( "#pstDataTable" );
					  break;
					case "12":
					  $( "#pst12HourDataTemp" ).tmpl( data ).appendTo( "#pstDataTable" );
					  break;
					case "24":
					  $( "#pst24HourDataTemp" ).tmpl( data ).appendTo( "#pstDataTable" );
					  break;						  
					}					
	           }
	       });		
	},
	load_zstData: function(){
		$.getJSON("data/zrsthourdata.htm",
			function(data){
				$("#zstDataTable").empty();
				$( "#zstDataTemp" ).tmpl( data ).appendTo( "#zstDataTable" );
			}); 
	},
	load_pstMarkers:function(){
		dataload.load_stMarkers("data/pstmarker.htm",pstmarkersLayer);
	},
	load_rstMarkers:function(){
		dataload.load_stMarkers("data/rstmarker.htm",rstmarkersLayer);
	},
	
	load_zstMarkers:function(){
		dataload.load_stMarkers("data/zstmarker.htm",zstmarkersLayer);
	},
	load_warnMarkers:function(){		
		$.getJSON("data/newwarns.htm",
				function(datas){		    
					for (var i=0;i<datas.length;i++){
						var lat = datas[i].station.location.lttd;
						var lng = datas[i].station.location.lgtd;
						var height = 30;
						var width = 30;
					    var stPoint = new OpenLayers.Geometry.Point(lng, lat).transform(epsg4326, epsg900913);				    
						var marker = new OpenLayers.Feature.Vector(stPoint, 
							{stcd:datas[i].station.stcd},
							{externalGraphic : "/wm/static/img/map/warning.gif",
							 graphicWidth : width,
							 graphicHeight : height,
							 graphicXOffset : -Math.round(width),
							 graphicYOffset : -Math.round(height),
							 fillOpacity : 1});
						warnmarkerLayer.addFeatures(marker);				
					}
				}); 		
	},
	load_stMarkers:function(url, markerLayer){
		$.getJSON(url,
			function(datas){		    
				for (var i=0;i<datas.length;i++){
					var lat = datas[i].lttd;
					var lng = datas[i].lgtd;
					var img = datas[i].imgpath; 
					var sttp = datas[i].sttp;
					var height = 24;
					var width = 24;
					if("ZZ" == sttp){
						width = 16;
						height = 32;
					}
				    var stPoint = new OpenLayers.Geometry.Point(lng, lat).transform(epsg4326, epsg900913);				    
					var marker = new OpenLayers.Feature.Vector(stPoint, 
						{stcd:datas[i].stcd, stnm:datas[i].stnm, sttp:sttp, lng:lng, lat:lat},
						{externalGraphic : $("#baseURL").attr("value") + "/img/map/" + img,
						 graphicWidth : width,
						 graphicHeight : height,
						 graphicXOffset : -Math.round(width),
						 graphicYOffset : -Math.round(height),
						 fillOpacity : 1});
					markerLayer.addFeatures(marker);				
				}
			}); 	
	},	
	load_stfullInfo_bystcd:function(stcd, targetDiv){
		 $.ajax({
	           type:"get",
	           url:"data/stfullinfo.htm",
	           dataType:"json",
	           async:false,
	           data:{stcd:stcd},
	           success:function(data){
					$("#"+targetDiv).empty();
					$( "#stInfoDataTemp" ).tmpl( data ).appendTo( "#"+targetDiv );
	           }
	       });
	},
	load_drpst_dist_values:function(stcd){
		 $.ajax({
	           type:"get",
	           url:"data/drpstvalues.htm",
	           dataType:"json",
	           async:false,
	           data:{stcd:stcd},
	           success:function(data){
	        	   var drpValues = new Array();
	        	   var warnValues = new Array();
	        	   var dangerValues = new Array();
	        	   var warnValue = 0, dangerValue = 0;
	        	   var i = 0;
	        	   for (var hour in data){
	        		   if(hour == -1){
	        			   warnValue = data[hour];
	        		   }else if(hour == -2){
	        			   dangerValue = data[hour];
	        		   }else{
		        		   drpValues[i] = new Array();
		        		   drpValues[i][0] = hour;
		        		   drpValues[i][1] = data[hour];
		        		   i++; 
	        		   }
	        	   }
	        	   for(var i = 0; i < 24; i++){
	        		   warnValues[i] = new Array();
	        		   dangerValues[i] = new Array();
	        		   warnValues[i][0] = i;
	        		   warnValues[i][1] = warnValue;
	        		   dangerValues[i][0] = i;
	        		   dangerValues[i][1] = dangerValue;
	        	   }
	        	   chart_handler.drpDistReport(drpValues, warnValues, dangerValues);
	           }
	       });
	},
	load_drpst_min5_values:function(stcd){
		 $.ajax({
	           type:"get",
	           url:"data/drpstrealValues.htm",
	           dataType:"json",
	           async:false,
	           data:{stcd:stcd},
	           success:function(data){
	        	   var drpValues = new Array();
	        	   var i = 0;
	        	   for (var value in data){
	        		   drpValues[i] = new Array();
	        		   drpValues[i][0] = value;
	        		   drpValues[i][1] = data[value];
	        		   i++; 
	        	   }
	        	   chart_handler.drpRealReport(drpValues);
	           }
	       });		
	},
	load_lastreceived:function(stcd){
		 $.ajax({
	           type:"get",
	           url:"data/lastReceivedTime.htm",
	           dataType:"json",
	           async:false,
	           data:{stcd:stcd},
	           success:function(data){
	        	   var lastDate = new Date(data).Format("yyyy-MM-dd hh:mm:ss");  
					$("#pstlastTime").html(lastDate);
	           }
	       });
	},
	load_newwarn_data:function(){
		 $.ajax({
	           type:"get",
	           url:"data/newwarns.htm",
	           dataType:"json",
	           async:false,
	           success:function(data){
					$("#newWarnDataTable").empty();
					$( "#newwarnDataTemp" ).tmpl( data ).appendTo( "#newWarnDataTable" );
	           }
	       });
	},
	load_confirmedwarn_data:function(){
		 $.ajax({
	           type:"get",
	           url:"data/confirmedwarns.htm",
	           dataType:"json",
	           async:false,
	           success:function(data){
					$("#confirmedWarnDataTable").empty();
					$( "#confirmedwarnDataTemp" ).tmpl( data ).appendTo( "#confirmedWarnDataTable" );
	           }
	       });
	},
	load_warnscount_data : function(){
		$.getJSON("data/warnscount.htm",
				function(data){
		     	   for (var value in data){
		    		   if(value == 1){
		    			   $("#newWarnsCount").html(data[value]);
		    		   }
		    		   
		    		   if(value == 2){
		    			   $("#confirmedWarnsCount").html(data[value]);
		    		   }
		    	   }
				}); 		
	},
	load_rst_real_values : function(stcd,sttp){
		 $.ajax({
	           type:"get",
	           url:"data/rstrealdata.htm",
	           dataType:"json",
	           async:false,
	           data:{stcd:stcd},
	           success:function(data){
	        	   var rstValues = new Array();
	        	   var warnValues = new Array();
	        	   var dangerValues = new Array();
	        	   var warnValue = 0, dangerValue = 0;
	        	   var i = 0;
	        	   for (var hour in data){
	        		   if(hour == -1){
	        			   warnValue = data[hour];
	        		   }else if(hour == -2){
	        			   dangerValue = data[hour];
	        		   }else{
	        			   rstValues[i] = new Array();
	        			   rstValues[i][0] = hour;
	        			   rstValues[i][1] = data[hour];
		        		   i++; 
	        		   }
	        	   }
	        	   for(var i = 0; i < 24; i++){
	        		   warnValues[i] = new Array();
	        		   dangerValues[i] = new Array();
	        		   warnValues[i][0] = i;
	        		   warnValues[i][1] = warnValue;
	        		   dangerValues[i][0] = i;
	        		   dangerValues[i][1] = dangerValue;
	        	   }
	        	   if("RR" == sttp){
	        		   chart_handler.rstRealReport(rstValues, warnValues, dangerValues);   
	        	   }else if("ZZ" == sttp){
	        		   chart_handler.zstRealReport(rstValues, warnValues, dangerValues);
	        	   }	        	  
	           }
	       });				
	}
};
st_handler = {
	showPStDetail:function(stcd,stnm){
		$("#rstMarkerWindow").css("display","none"); 
		$("#zstMarkerWindow").css("display","none"); 
		$("#pstMarkerWindow").css("display",""); 
		$("#pstNmae").html(stnm);
    	dataload.load_stfullInfo_bystcd(stcd,'pstInfoData');
    	dataload.load_drpst_dist_values(stcd);
    	dataload.load_drpst_min5_values(stcd); 
    	dataload.load_lastreceived(stcd);
	},
	showZStDetail:function(stcd,stnm,sttp){
		$("#rstMarkerWindow").css("display","none"); 
		$("#zstMarkerWindow").css("display","none"); 
		$("#pstMarkerWindow").css("display","none"); 
		if("RR" == sttp){
			$("#rstMarkerWindow").css("display",""); 
			$("#rstNmae").html(stnm);
			dataload.load_stfullInfo_bystcd(stcd,'rstInfoData');
			dataload.load_rst_real_values(stcd,sttp);
		}else if("ZZ" == sttp){
			$("#zstMarkerWindow").css("display",""); 
			$("#zstNmae").html(stnm);
			dataload.load_stfullInfo_bystcd(stcd,'zstInfoData');
			dataload.load_rst_real_values(stcd,sttp);		
		}
	},
};

chart_handler = {		
		drpDistReport : function(d1, d2, d3) {
			var elem = $('#drpdist');            
            var ds = new Array();
         
            ds.push({
                label: "降雨量",
                data:d1,
                bars: {
                    show: true,
                    barWidth:.5,
                    order: 1
                }
            });
            ds.push({
                label: "警戒值",
                data:d2,
                lines: { show: true }
            });
            ds.push({
                label: "危险值",
                data:d3,
                lines: { show: true }
            });                        
            
            var options = {
                grid:{
                    hoverable:true
                },
                xaxis: { ticks: [[0, "00"],[1, "01"],[2, "02"], [3, "03"], [4, "04"], [5, "05"], [6, "06"], [7, "07"], [8, "08"],[9, "09"],[10, "10"],[11, "11"],
                                 [12, "12"],[13, "13"],[14, "14"], [15, "15"], [16, "16"], [17, "17"], [18, "18"], [19, "19"], [20, "20"],[21, "21"],[22, "22"],[23, "23"]], 
                                 min: 0, max: 23},
                colors: [ "#b4dbeb", "#8cc7e0"]              
            };
            
            fl_c_plot = $.plot(elem, ds, options); 
            
            // Create a tooltip on our chart
            elem.qtip({
                prerender: true,
                content: 'Loading...', // Use a loading message primarily
                position: {
                    viewport: $(window), // Keep it visible within the window if possible
                    target: 'mouse', // Position it in relation to the mouse
                    adjust: { x: 7 } // ...but adjust it a bit so it doesn't overlap it.
                },
                show: false, // We'll show it programatically, so no show event is needed
                style: {
                    classes: 'ui-tooltip-shadow ui-tooltip-tipsy',
                    tip: false // Remove the default tip.
                }
            });     
            
            // Bind the plot hover
            elem.on('plothover', function(event, coords, item) {
                // Grab the API reference
                var self = $(this),
                    api = $(this).qtip(),
                    previousPoint, content,
         
                // Setup a visually pleasing rounding function
                round = function(x) { return Math.round(x * 1000) / 1000; };
         
                // If we weren't passed the item object, hide the tooltip and remove cached point data
                if(!item) {
                    api.cache.point = false;
                    return api.hide(event);
                }
				
                // Proceed only if the data point has changed
                previousPoint = api.cache.point;
                if(previousPoint !== item.seriesIndex)
                {
                    // Update the cached point data
                    api.cache.point = item.seriesIndex;
					
                    // Setup new content
                    content = item.series.label +': '+ round(item.datapoint[1]);
         
                    // Update the tooltip content
                    api.set('content.text', content);
         
                    // Make sure we don't get problems with animations
                    api.elements.tooltip.stop(1, 1);
         
                    // Show the tooltip, passing the coordinates
                    api.show(coords);
                }
            });                  
        },
        drpRealReport : function(min5Values) {
           var elem = $('#drpreal5min');			
           var ds = new Array();
        
           ds.push({
               label: "5分钟实时雨量",
               data:min5Values,
               points: {show: true },
               lines: { show: true }
           });                  
           
           var options = {
               grid:{
                   hoverable:true
               },
               xaxis: { ticks: [[1, "05"],[2, "10"],[3, "15"], [4, "20"], [5, "25"], [6, "30"], [7, "35"], [8, "40"], [9, "45"],[10, "50"],[11, "55"],[12, "60"]], 
                min: 0, max: 12},                 
               colors: [ "#b4dbeb", "#8cc7e0"]              
           };
           
           fl_d_plot = $.plot(elem, ds, options); 
           
           // Create a tooltip on our chart
           elem.qtip({
               prerender: true,
               content: 'Loading...', // Use a loading message primarily
               position: {
                   viewport: $(window), // Keep it visible within the window if possible
                   target: 'mouse', // Position it in relation to the mouse
                   adjust: { x: 7 } // ...but adjust it a bit so it doesn't overlap it.
               },
               show: false, // We'll show it programatically, so no show event is needed
               style: {
                   classes: 'ui-tooltip-shadow ui-tooltip-tipsy',
                   tip: false // Remove the default tip.
               }
           });     
           
           // Bind the plot hover
           elem.on('plothover', function(event, coords, item) {
               // Grab the API reference
               var self = $(this),
                   api = $(this).qtip(),
                   previousPoint, content,
        
               // Setup a visually pleasing rounding function
               round = function(x) { return Math.round(x * 1000) / 1000; };
        
               // If we weren't passed the item object, hide the tooltip and remove cached point data
               if(!item) {
                   api.cache.point = false;
                   return api.hide(event);
               }
				
               // Proceed only if the data point has changed
               previousPoint = api.cache.point;
               if(previousPoint !== item.seriesIndex)
               {
                   // Update the cached point data
                   api.cache.point = item.seriesIndex;
					
                   // Setup new content
                   content = item.series.label +': '+ round(item.datapoint[1]);
        
                   // Update the tooltip content
                   api.set('content.text', content);
        
                   // Make sure we don't get problems with animations
                   api.elements.tooltip.stop(1, 1);
        
                   // Show the tooltip, passing the coordinates
                   api.show(coords);
               }
           });                  
       },
       zstRealReport : function(zstValues, warnValues, dangerValues) {
           var elem = $('#zstRealReport');			
           var ds = new Array();
        
           ds.push({
               label: "实时水位",
               data:zstValues,
               points: {show: true },
               lines: { show: true }
           });  

           ds.push({
               label: "警戒水位",
               data:warnValues,
               lines: { show: true }
           });
           ds.push({
               label: "危险水位",
               data:dangerValues,
               lines: { show: true }
           });              
           
           var options = {
               grid:{
                   hoverable:true
               },
               xaxis: { ticks: [[0, "00"],[1, "01"],[2, "02"], [3, "03"], [4, "04"], [5, "05"], [6, "06"], [7, "07"], [8, "08"],[9, "09"],[10, "10"],[11, "11"],
                                [12, "12"],[13, "13"],[14, "14"], [15, "15"], [16, "16"], [17, "17"], [18, "18"], [19, "19"], [20, "20"],[21, "21"],[22, "22"],[23, "23"]], 
                                min: 0, max: 23},
               colors: [ "#b4dbeb", "#8cc7e0"]                
           };
           
           fl_zst_plot = $.plot(elem, ds, options); 
           
           // Create a tooltip on our chart
           elem.qtip({
               prerender: true,
               content: 'Loading...', // Use a loading message primarily
               position: {
                   viewport: $(window), // Keep it visible within the window if possible
                   target: 'mouse', // Position it in relation to the mouse
                   adjust: { x: 7 } // ...but adjust it a bit so it doesn't overlap it.
               },
               show: false, // We'll show it programatically, so no show event is needed
               style: {
                   classes: 'ui-tooltip-shadow ui-tooltip-tipsy',
                   tip: false // Remove the default tip.
               }
           });     
           
           // Bind the plot hover
           elem.on('plothover', function(event, coords, item) {
               // Grab the API reference
               var self = $(this),
                   api = $(this).qtip(),
                   previousPoint, content,
        
               // Setup a visually pleasing rounding function
               round = function(x) { return Math.round(x * 1000) / 1000; };
        
               // If we weren't passed the item object, hide the tooltip and remove cached point data
               if(!item) {
                   api.cache.point = false;
                   return api.hide(event);
               }
				
               // Proceed only if the data point has changed
               previousPoint = api.cache.point;
               if(previousPoint !== item.seriesIndex)
               {
                   // Update the cached point data
                   api.cache.point = item.seriesIndex;
					
                   // Setup new content
                   content = item.series.label +': '+ round(item.datapoint[1]);
        
                   // Update the tooltip content
                   api.set('content.text', content);
        
                   // Make sure we don't get problems with animations
                   api.elements.tooltip.stop(1, 1);
        
                   // Show the tooltip, passing the coordinates
                   api.show(coords);
               }
           });                  
       },  
       rstRealReport : function(rstValues, warnValues, dangerValues) {
           var elem = $('#rstRealReport');			
           var ds = new Array();
        
           ds.push({
               label: "实时水位",
               data:rstValues,
               points: {show: true },
               lines: { show: true }
           });  

           ds.push({
               label: "警戒水位",
               data:warnValues,
               lines: { show: true }
           });
           ds.push({
               label: "危险水位",
               data:dangerValues,
               lines: { show: true }
           });              
           
           var options = {
               grid:{
                   hoverable:true
               },
               xaxis: { ticks: [[0, "00"],[1, "01"],[2, "02"], [3, "03"], [4, "04"], [5, "05"], [6, "06"], [7, "07"], [8, "08"],[9, "09"],[10, "10"],[11, "11"],
                                [12, "12"],[13, "13"],[14, "14"], [15, "15"], [16, "16"], [17, "17"], [18, "18"], [19, "19"], [20, "20"],[21, "21"],[22, "22"],[23, "23"]], 
                                min: 0, max: 23},
               colors: [ "#b4dbeb", "#8cc7e0"]                
           };
           
           fl_rst_plot = $.plot(elem, ds, options); 
           
           // Create a tooltip on our chart
           elem.qtip({
               prerender: true,
               content: 'Loading...', // Use a loading message primarily
               position: {
                   viewport: $(window), // Keep it visible within the window if possible
                   target: 'mouse', // Position it in relation to the mouse
                   adjust: { x: 7 } // ...but adjust it a bit so it doesn't overlap it.
               },
               show: false, // We'll show it programatically, so no show event is needed
               style: {
                   classes: 'ui-tooltip-shadow ui-tooltip-tipsy',
                   tip: false // Remove the default tip.
               }
           });     
           
           // Bind the plot hover
           elem.on('plothover', function(event, coords, item) {
               // Grab the API reference
               var self = $(this),
                   api = $(this).qtip(),
                   previousPoint, content,
        
               // Setup a visually pleasing rounding function
               round = function(x) { return Math.round(x * 1000) / 1000; };
        
               // If we weren't passed the item object, hide the tooltip and remove cached point data
               if(!item) {
                   api.cache.point = false;
                   return api.hide(event);
               }
				
               // Proceed only if the data point has changed
               previousPoint = api.cache.point;
               if(previousPoint !== item.seriesIndex)
               {
                   // Update the cached point data
                   api.cache.point = item.seriesIndex;
					
                   // Setup new content
                   content = item.series.label +': '+ round(item.datapoint[1]);
        
                   // Update the tooltip content
                   api.set('content.text', content);
        
                   // Make sure we don't get problems with animations
                   api.elements.tooltip.stop(1, 1);
        
                   // Show the tooltip, passing the coordinates
                   api.show(coords);
               }
           });                  
       }            
};

switcher_handler = {
		init:function(){
			$("#alarm_player").jPlayer({
				ready: function () {
					$(this).jPlayer("setMedia", {
						wav: "/wm/static/sound/ALARM1.WAV"
					});
				},
				ended: function (event) {
					$(this).jPlayer("play");
				},
				swfPath: "js",
				supplied: "wav"
			});	
		},
		chkPStLayer: function(checked){
			if(checked){
				if(pstmarkersLayer.features.length == 0){
					dataload.load_pstMarkers();
				}
				if(!pstmarkersLayer.getVisibility()){
					pstmarkersLayer.setVisibility(true);
				} 				
			}else{
				if(pstmarkersLayer.getVisibility()){
					pstmarkersLayer.setVisibility(false);
				}				
			}
		},
		chkZStLayer: function(checked){
			if(checked){
				if(zstmarkersLayer.features.length == 0){
					dataload.load_zstMarkers();
				}
				
				if(!zstmarkersLayer.getVisibility()){
					zstmarkersLayer.setVisibility(true);
				} 				
			}else{
				if(zstmarkersLayer.getVisibility()){
					zstmarkersLayer.setVisibility(false);
				}				
			}			
		},
		chkRStLayer: function(checked){
			if(checked){	
				if(rstmarkersLayer.features.length == 0){
					dataload.load_rstMarkers();
				}
				
				if(!rstmarkersLayer.getVisibility()){
					rstmarkersLayer.setVisibility(true);
				} 				
			}else{
				if(rstmarkersLayer.getVisibility()){
					rstmarkersLayer.setVisibility(false);
				}				
			}					
		},
		alarmVolChange: function(){	
			var volStatus = $("#volChange").attr("checked");
			if("checked" == volStatus){
				switcher_handler.playVol();
			}else{				
				switcher_handler.pauseVol();
			}
		},
		playVol: function(){
			$.getJSON("data/warnscount.htm",
					function(data){
		    		   if(data[1] > 0){
		    			   $("#alarm_player").jPlayer( "play" );  
		    		   }else{
		    			   switcher_handler.pauseVol();
		    		   }
					}); 							
		},
		pauseVol: function(){
			$("#alarm_player").jPlayer( "pause" );  
		},
		warnTypeChange: function(){
			 $.ajax({
		           type:"get",
		           url:"data/stwarnstatus.htm",
		           dataType:"json",
		           async:false,
		           success:function(datas){
		        	   warnmarkerLayer.removeAllFeatures();
		        	   dataload.load_warnMarkers();
		        	   for (var i=0;i<datas.length;i++){
		        		   if(datas[i].warning){
		        			   $("#tr-" + datas[i].stcd).attr("class","warn-table-tr");   		        			  
		        		   }else{
		        			   $("#tr-" + datas[i].stcd).attr("class","normal-table-tr");		        			   
		        		   }		        		   
		        	   }
		           }
		       });			
		}	
};

sidebar_hander = {
	pstHourChange : function(value){
		dataload.load_pstData(value);
	},
	newWarnClick : function(){
		dataload.load_newwarn_data();
		$("#undealwarning").css("display",""); 
	},
	newWarnClose : function(){
		$("#undealwarning").css("display","none");	
	},
	confirmedWarnClick : function(){
		dataload.load_confirmedwarn_data();
		$("#confirmedwarning").css("display",""); 
	},
	confirmedWarnClose : function(){
		$("#confirmedwarning").css("display","none");	
	}	
};
map_handler = {
	changeMapType : function(type){
		switch(type){
			case "streetMap":
				map_handler.changeToStreetMap();
				break;	
			case "topoMap":
				map_handler.changeToTopoMap();
				break;		
			case "sateMap":
				map_handler.changeToSateMap();
				break;					
		}			
	},	
	changeToStreetMap : function(){
		$("#mapType").html("街景图");
		$("#mapTypeGroup .dropdown-menu .splashy-check").attr("class", "splashy-bullet_blue_small");
		$("#streetMapIcon").attr("class", "splashy-check");
		map_handler.switchBaseLayerMap("GoogleStreets");
	},
	changeToTopoMap : function(){
		$("#mapType").html("地形图");
		$("#mapTypeGroup .dropdown-menu .splashy-check").attr("class", "splashy-bullet_blue_small");
		$("#topoMapIcon").attr("class", "splashy-check");
		map_handler.switchBaseLayerMap("GoogleTopoMap");
	},
	changeToSateMap : function(){
		$("#mapType").html("卫星图");
		$("#mapTypeGroup .dropdown-menu .splashy-check").attr("class", "splashy-bullet_blue_small");
		$("#sateMapIcon").attr("class", "splashy-check");
		map_handler.switchBaseLayerMap("GoogleSateMap");
	},
	changeMapDist : function(adcd, adnm, lgtd, lttd){
		$("#mapDist").html(adnm);
		$("#mapDistGroup .dropdown-menu .splashy-check").attr("class", "splashy-bullet_blue_small");
		$("#dist" + adcd + "Icon").attr("class", "splashy-check");
	    var lonLat = new OpenLayers.LonLat(lgtd,lttd).transform(epsg4326, epsg900913);	
	    if(adcd == "all"){
	    	map.setCenter(lonLat, 11);	
	    }else{
		    if( map.getZoom() < 13 ){
		    	map.setCenter(lonLat, 13);	
		    }else{
		    	map.setCenter(lonLat);
		    }
	    }	       	
	},
	switchBaseLayerMap : function(mapName){
		var layers = map.layers.slice();
		var layerId;
		for(var i=0, len=layers.length; i<len; i++) {
			var layer = layers[i];
    		var baseLayer = layer.isBaseLayer;
    		var layerName = layer.name;
    		if(layerName == mapName){
    			layerId = layer.id;	
    			break;
    		}            		
		}
		map.setBaseLayer(map.getLayer(layerId));				
	}
}
timer = {
	startAutoFresh:function(){
		var timer = $.timer(function() {
			dataload.load_warnscount_data();
			dataload.load_pstData();
			dataload.load_zstData();			
			switcher_handler.alarmVolChange();
			switcher_handler.warnTypeChange();
		});
		timer.set({ time : 30000, autostart : true });
	}
};
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}