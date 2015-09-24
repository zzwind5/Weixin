;
jQuery.ajaxSetup({ async:false,traditional:true});
$().ready(function(){
	$( ".draggable" ).draggable();
	
	$(".moreserach-btn a").click(function(){
		$("#moreSerach").toggle();
	});
	
	if (typeof Highcharts != "undefined" && Highcharts){
	    Highcharts.setOptions({
		     global: {
		            timezoneOffset: -8 * 60
		     },
			lang: {
				months: ['1', '2', '3', '4', '5', '6',  '7', '8', '9', '10', '11', '12'],
				weekdays: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
			},
            credits: {
                enabled: false
            },
            exporting:{
            	enabled: true,
            	url:ROOT_PATH + '/highchars/dowload.htm'
            },
			colors: ['#FF0033','#fe4f00', 'black',  '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
		});
	    
	    /*
		参考之https://github.com/highslide-software/highcharts.com/issues/3095
		修复 Highcharts 下载图片导致后台报错
		by:qph
	     */
	    Highcharts.wrap(Highcharts.Chart.prototype, 'getSVG', function (proceed) {
	        return proceed.call(this).replace(
	                /(fill|stroke)="rgba\(([ 0-9]+,[ 0-9]+,[ 0-9]+),([ 0-9\.]+)\)"/g, 
	                '$1="rgb($2)" $1-opacity="$3"'
	            );
	    });
	    
	}
});