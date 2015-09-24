(function( $ ) {
	$.mobile = {};
	$.mobile.loading=function(arg1){
		if (arg1 == "show"){
			$("#loading_area").show();
		} else {
			$("#loading_area").hide();
		}
	};
}( jQuery ));

function dopagelink(pageNumber) {
	
	
	if (typeof pageNumber != 'undefined'){
		$("#pageNumber").val(pageNumber);
	}
	
	var $form = $("#pageNumber").closest("form");
	var formData=$form.serializeArray();
	
	$("#pageNumber").closest("form").submit();
	
//	jQuery.post($("#tableAjaxAction").val(),formData,function(repo){
//		
//		$("#ajaxSubmitTable").html(repo);
//		
//	});
}


function getCoupon(activeId,obj){
	$.mobile.loading('show', {theme:"b", text:"请稍后", textVisible: true});
	$.post(ROOT_PATH + "/activity/getCoupon.htm",{id:activeId},function(data){
		$.mobile.loading('hide');
		if (data.result){
			//alert("领取成功");
			$(document).simpledialog2({
			    mode: 'blank',
			    headerText: '参加成功',
			    headerClose: true,
			    showModal:false,
			    blankContent : 
			      //"<ul data-role='listview' style='text-align:center'><li>领取成功</li></ul>"+
			      // NOTE: the use of rel="close" causes this button to close the dialog.
			      "<a rel='close' data-role='button' href='#'>关闭</a>"
			  });
			//$(obj).parent().html('<p style="text-align:center">已经参与</P>');
		} else if(data.error == '9') {
			$(document).simpledialog2({
			    mode: 'blank',
			    headerText: '已经参加过',
			    headerClose: true,
			    showModal:false,
			    blankContent : 
			      "<a rel='close' data-role='button' href='#'>关闭</a>"
			  });
		} else {
			alert("领取失败，请稍后领取");
		}
		 
	},"json");
	
}



////////////////////////////////////////////////////////分页开始
var myScroll,
pullUpEl, pullUpOffset,
pageNumber = 1;
/**
 * 初始化iScroll控件
 */
function loaded() {
	pullUpEl = document.getElementById('pullUp');	
	if (!pullUpEl){
		return;
	}
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll('wrapper', {
		scrollbarClass: 'myScrollbar', /* 重要样式 */
		useTransition: false, /* 此属性不知用意，本人从true改为false */
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			 if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}
//document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
//document.addEventListener('DOMContentLoaded', loaded, false);
////////////////////////////////////////////////////////分页结束




	jQuery().ready(function(){
		
		//loaded();


		
	});