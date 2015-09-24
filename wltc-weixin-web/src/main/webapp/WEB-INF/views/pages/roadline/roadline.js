function changeCity(){

    var startCitytemp = jQuery("#start").val();
    var startCityCodetemp = jQuery("#startCode").val();
    
    jQuery("#start").val(jQuery("#end").val());
    jQuery("#startCode").val(jQuery("#endCode").val());
    
    jQuery("#end").val(startCitytemp);
    jQuery("#endCode").val(startCityCodetemp);

}
function dofav(lineId,obj){
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	jQuery.post(ROOT_PATH + '/roadline/dofav.htm',{lineId:lineId},function(data){
		jQuery.mobile.loading('hide');
    	if (data.result){
			$(obj).parent().html('<a href="#?" onclick="doRemovefav(' + lineId + ',this)" data-role="button" data-icon="minus" class="ui-link ui-btn ui-icon-minus ui-btn-icon-left ui-shadow ui-corner-all  ui-btn_red"><div class="button_4 mar10">取消收藏</div></a>');
		} else {
			alert('已收藏');
		}
		
	});
}

function doRemovefav(lineId,obj){
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	jQuery.post(ROOT_PATH + '/roadline/doRemovefav.htm',{lineId:lineId},function(data){
		jQuery.mobile.loading('hide');
    	if (data.result){
			$(obj).parent().html('<a href="#?" onclick="dofav(' + lineId + ',this)" data-role="button" data-icon="minus" class="ui-link ui-btn ui-icon-plus ui-btn-icon-left ui-shadow  ui-corner-all"><div class="button_3">收藏专线</div></a>');
		} else {
			alert('已取消收藏');
		}
		
	});
}
function dofavmin(lineId,obj){
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	jQuery.post(ROOT_PATH + '/roadline/dofav.htm',{lineId:lineId},function(data){
		jQuery.mobile.loading('hide');
    	if (data.result){
			$(obj).parent().html('<a href="#?" onclick="doRemovefavmin(' + lineId + ',this)" data-role="button" data-icon="minus" class="ui-link ui-btn ui-icon-minus ui-btn-icon-left ui-shadow ui-corner-all ui-mini ui-btn_red">取消收藏</a>');
		} else {
			alert('已收藏');
		}
    	jQuery("#pullUp").show();
	});
}

function doRemovefavmin(lineId,obj){
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	jQuery.post(ROOT_PATH + '/roadline/doRemovefav.htm',{lineId:lineId},function(data){
		jQuery.mobile.loading('hide');
    	if (data.result){
			$(obj).parent().html('<a href="#?" onclick="dofavmin(' + lineId + ',this)" data-role="button" data-icon="minus" class="ui-link ui-btn ui-icon-plus ui-btn-icon-left ui-shadow ui-mini ui-corner-all">收藏</a>');
		} else {
			alert('已取消收藏');
		}
		
	});
}
pageNumber = 0;
function pullUpAction (isinit) {
	
	jQuery.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	if (isinit){
		pageNumber = 1;
	} else {
		pageNumber = pageNumber + 1;
	}
	jQuery.post(ROOT_PATH + '/roadline/page.htm?pageNumber=' +  pageNumber + "&t=" + new Date().getTime(),$("#roadline_form").serialize(),function(data){
		
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
		
	},'html');
	
}