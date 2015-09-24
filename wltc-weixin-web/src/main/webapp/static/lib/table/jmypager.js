(function ($) {
    $.fn.extend({
        pagination: function (options,handler) {
            var settings = $.extend({
				pagerid : 'kkpager',//ul id
				pno : 1,//当前页码
				total : 1,//总页码
				pagesize:10,//每页大小
				totalRecords : 0,//总数据条数
				isShowTotalPage : true,//是否显示总页数
				isShowTotalRecords : true,//是否显示总记录数
				isGoPage : true,//是否显示页码跳转输入框
				hrefFormer : '',//链接前部
				hrefLatter : '',//链接尾部
				mode:1,//展示模式
				lang : {
					prePageText : '上一页',
					nextPageText : '下一页',
					totalPageBeforeText : '共',
					totalPageAfterText : '页',
					totalRecordsAfterText : '条数据',
					gopageBeforeText : '转到',
					gopageButtonOkText : '确定',
					gopageAfterText : '页',
					buttonTipBeforeText : '第',
					buttonTipAfterText : '页'
				},
				css:{
					active:'active',
					disabled:'disabled',
					normal:''
				},
				paging:'',//导航前
				paged:''//导航
			}, options);
			
			return this.each(function () {
				var ul=$(this);
				build(ul);
				function build(list){
					
					this.prv = (settings.pno<=2) ? 1 : (settings.pno-1);
					this.next = (settings.pno >= settings.total-1) ? settings.total : (settings.pno + 1);
					this.hasPrv = (settings.pno > 1);
					this.hasNext = (settings.pno < settings.total);
					list.empty();
					//var list = $('<ul/>');
					//上一页
					list.append(buildNavigation(this.prv,settings.lang.prePageText,'normal',settings.css.normal));
					
					//分页
					if(settings.total <= 8){
						for(var i=1;i<=settings.total;i++){
							if(settings.pno == i){
								list.append(buildNavigation(i,i,'active',settings.css.active));
							}else{
								list.append(buildNavigation(i,i,'normal',settings.css.normal));
							}
						}
					}else{
						if(settings.pno <= 5){
							for(var i=1;i<=7;i++){
								if(settings.pno == i){
									list.append(buildNavigation(i,i,'active',settings.css.active));
								}else{
									list.append(buildNavigation(i,i,'normal',settings.css.normal));
								}
							}
							list.append($('<li/>').addClass(settings.css.disabled).append($("<span/>").text("...")));
						}else{
							list.append(buildNavigation(1,1,'normal',settings.css.normal));
							list.append(buildNavigation(2,2,'normal',settings.css.normal));
							list.append($('<li/>').addClass(settings.css.disabled).append($("<span/>").text("...")));
							
							var begin = settings.pno - 2;
							var end = settings.pno + 2;
							if(end > settings.total){
								end = settings.total;
								begin = end - 4;
								if(settings.pno - begin < 2){
									begin = begin-1;
								}
							}else if(end + 1 == settings.total){
								end = settings.total;
							}
							for(var i=begin;i<=end;i++){
								if(settings.pno == i){
									list.append(buildNavigation(i,i,'active',settings.css.active));
								}else{
									list.append(buildNavigation(i,i,'normal',settings.css.normal));
								}
							}
							if(end != settings.total){
								list.append($('<li/>').addClass(settings.css.disabled).append($("<span/>").text("...")));
							}
						}
					}
					
					//下一页
					list.append(buildNavigation(next,settings.lang.nextPageText,'normal',settings.css.normal));
					//listTo.empty();
					//list.children().appendTo(listTo);
					//listTo.append(list.html());
				}
				function buildNavigation(pno,title,status,css) {
					var item=$('<li/>').addClass(css);
					var itemLink=$('<a/>').text(title).attr("title",title).attr("href",'#');
					if(status=='normal'){
						itemLink.click(function () {navigate(pno); return false;})
					}else{
						itemLink.click(function () {return false;})
					}
					item.append(itemLink);
					return item;
                }
				function buildLabels(startPage){
					
                }
				function navigate(pno){
					if(handler.paging) handler.paging(pno);
					//alert(settings.pno+'||'+pno);
					settings.pno=pno;
					if(handler.paged) handler.paged(pno);
				}
			});
        },
		pager:function(){
		},
        pagerSize:function(options,handler){
        	 var settings = $.extend({
        		 pageSize:10 ,
        		 pageSizeItems:[10,20,30,50,100],
        		 pageSizeChanged:''
 			}, options);
        	var select = $(this);
        	select.empty();
        	for(i=0;i<settings.pageSizeItems.length;i++){
        		var item=settings.pageSizeItems[i];
        		select.append($('<option/>').text(item).val(item));
        	}
        	select.val(settings.pageSize);
        	select.change(function(){
        		if(handler.pageSizeChanged) handler.pageSizeChanged($(this).val());
        	});
        	
		}
    });
})(jQuery);
