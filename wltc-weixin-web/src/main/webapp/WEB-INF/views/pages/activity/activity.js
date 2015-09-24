jQuery().ready(function(){
	pullUpAction(true);
});

pageNumber = 0;
function pullUpAction (isinit) {
	
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	if (isinit){
		pageNumber = 1;
	} else {
		pageNumber = pageNumber + 1;
	}
	jQuery.get('page.htm?pageNumber=' +  pageNumber + "&pageType=" + pageType + "&t=" + new Date().getTime(),function(data){
    	
		if (jQuery.trim(data)==''){
    		if (isinit){
    			jQuery("#thelist").html("<p style='text-align:center'>暂无数据<p>");
    		} else {
    			jQuery(".pullUpLabel").html("<p style='text-align:center'>已经是最新<p>");
    		}
    	} else {
    		if (isinit){
    			jQuery("#thelist").html(data);
    		} else {
    			jQuery("#thelist").append(data);
    		}
    		$("#pullUp").show();
    	}
		jQuery.mobile.loading('hide');
		
	});
	
}