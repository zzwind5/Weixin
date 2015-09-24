$.ajaxSetup({ cache :false});
var pager_config={pno:  1,
		total: 1,
		totalRecords:0 ,
		pageSize:10,pageSizeItems:[10,20,30,50,100]
};	
var pager_handler={
	  paginationWrap:'#form_table_pagination',
	  paging:function(page){
		 	pager_config.pno=page;
		 	this.initPager();
		 	this.showLabel();
			TABLE.refresh();
		},
		pageSizeChanged:function(pageSize){
			//alert(pageSize+'||'+this.pageSize);
			pager_config.pageSize=pageSize;
			pager_config.pno=1;
			pager_config.total=Math.ceil(pager_config.totalRecords*1.0/pager_config.pageSize);
			this.showLabel();
			this.initPager();
			TABLE.refresh();
		},
		showLabel:function(){
			$(this.paginationWrap+' .pagination-label').text('第 '+pager_config.pno+' 页 共 '+pager_config.total+'页');
		},
		initPager:function(){
			$(this.paginationWrap+" .pagination-items").pagination(pager_config,this);//分页
		},
		initPageSize:function(){
			$(this.paginationWrap+" .pagination-pagesize").pagerSize(pager_config,this);
		},
		init:function(){
			this.showLabel();
			this.initPager();
			this.initPageSize();
		},
		addRecord:function(i){
			pager_config.totalRecords =pager_config.totalRecords+i;
			pager_config.total=Math.ceil(pager_config.totalRecords*1.0/pager_config.pageSize);
			if(pager_config.pno>pager_config.total && pager_config.total > 1) {
				pager_config.pno =pager_config.total;
				TABLE.refresh();
			}
			this.showLabel();
			this.initPager();
		},
		next:function() {
			if(pager_config.pno>=pager_config.total) return;
			this.paging(pager_config.pno+1);
		},
		prev:function(){
			if(pager_config.pno<=1) return;
			this.paging(pager_config.pno-1);
		}
};

var TABLE={
	model_view:'#model_show',
	model_edit:'#model_edit',
	model_edit_inner:'#model_edit_inner',
	model_new:'#model_new',
	model_new_inner:'#model_new_inner',
	query_form:"#queryForm",
	table_id:"#form_table",
	table_wrap:"#form_table_wrap",
	table_toolbar:"#form_table_operation",
	pagerUrl:'table.htm',
	validate_sel: 'form_validation_reg',
	close:function(modalId){
		$(modalId).modal('hide');
		$(modalId).find(".modal-body").empty();
	},
	tooltips:function(target,msg){
		var btn=$(target);
		btn.tooltip({title:msg});
		btn.tooltip('show');
	},
	show:function(modalId){
		$(modalId).modal('show');
	},
	save:function(btn){
		var form=$('form',this.model_edit);
		var formData=form.serializeArray();
		var url=form.attr("action");
		var validate_form = $(this.model_edit + ' form'+ '.' + this.validate_sel);
		if(validate_form.length == 0 || validate_form.valid()){
			if (validate_form.length > 0){
				if ( validate_form.data("validator").settings.submitHandler ) {
					if (! validate_form.data("validator").settings.submitHandler.call(this,{'url':url,'formData':formData,'btn':btn},TABLE.saveSubmit)){
						return false;
					}
				}
			} 
			if (form.attr('enctype') == 'multipart/form-data'){
				form.submit();
			} else {
				this.saveSubmit({'url':url,'formData':formData,'btn':btn});
			}
			
		}
	},
	saveSubmit:function(data){
		 jQuery.post(data.url,data.formData,
					function(resp){
						if(resp.result) {
							TABLE.tooltips(data.btn,"缴费成功");
							setTimeout(function(){
								TABLE.refresh();
								TABLE.close(TABLE.model_edit);
							},1000);
						}
						else TABLE.tooltips(data.btn,resp.msg||"出错了");
		            }).fail(function(){
		            	 TABLE.tooltips(data.btn,"网络异常");
					});
				
	},
	saveInner:function(btn){
		var form=$('form',this.model_edit_inner);
		var formData=form.serializeArray();
		var url=form.attr("action");
		var validate_form = $(this.model_edit_inner + ' form'+ '.' + this.validate_sel);
		if(validate_form.length == 0 || validate_form.valid()){
			if (validate_form.length > 0){
				if ( validate_form.data("validator").settings.submitHandler ) {
					if (! validate_form.data("validator").settings.submitHandler.call(this,{'url':url,'formData':formData,'btn':btn},TABLE.saveInnerSubmit)){
						return false;
					}
				}
			}
			this.saveInnerSubmit({'url':url,'formData':formData,'btn':btn});
		}
	},
	saveInnerSubmit:function(data){
		 jQuery.post(data.url,data.formData,
					function(resp){
						if(resp.result) {
							TABLE.tooltips(data.btn,"保存成功");
							setTimeout(function(){
								//TABLE.refresh();
								TABLE.close(TABLE.model_edit);
							},1000);
						}
						else TABLE.tooltips(data.btn,resp.msg||"出错了");
		            }).fail(function(){
		            	 TABLE.tooltips(data.btn,"网络异常");
					});
				
	},	
	add:function(btn){
		var form=$('form',this.model_new);
		var formData=form.serializeArray();
		var url=form.attr("action");
		var validate_form = $(this.model_new + ' form'+ '.' + this.validate_sel);
		if(validate_form.length == 0 || validate_form.valid()){
			if (validate_form.length > 0){
				if ( validate_form.data("validator").settings.submitHandler ) {
					if (! validate_form.data("validator").settings.submitHandler.call(this,{'url':url,'formData':formData,'btn':btn},TABLE.addSubmit)){
						return false;
					}
				}
			}
			
			if (form.attr('enctype') == 'multipart/form-data'){
				form.submit();
			} else {
				this.addSubmit({'url':url,'formData':formData,'btn':btn});
			}
		}
	},
	addSubmit:function(data){
		jQuery.post(data.url,data.formData,
				function(resp){
					if(resp.result) {
					//	TABLE.tooltips(data.btn,"添加成功");
					//	setTimeout(function(){
							TABLE.refresh();
					//		pager_handler.addRecord(1);
							TABLE.close(TABLE.model_new);
							var obj = resp.data;
							var id = obj.bmid;
							jQuery("#form_table tbody tr:has(input:checkbox[name='select_row'][value='"+id+"']) td:last a[name='jf']").click();
					//	},1000);
					}
					else {
						TABLE.tooltips(data.btn,resp.msg||"出错了");
					}
	            }).fail(function(){
	            	 TABLE.tooltips(data.btn,"网络异常");
			});
				
	},
	addInner:function(btn){
		var form=$('form',this.model_new_inner);
		var formData=form.serializeArray();
		var url=form.attr("action");
		var validate_form = $(this.model_new_inner + ' form'+ '.' + this.validate_sel);
		if(validate_form.length == 0 || validate_form.valid()){
			if (validate_form.length > 0){
				if ( validate_form.data("validator").settings.submitHandler ) {
					if (! validate_form.data("validator").settings.submitHandler.call(this,{'url':url,'formData':formData,'btn':btn},TABLE.addInnerSubmit)){
						return false;
					}
				}
			}			
			this.addInnerSubmit({'url':url,'formData':formData,'btn':btn});
		}
	},
	addInnerSubmit:function(data){
		jQuery.post(data.url,data.formData,
				function(resp){
					if(resp.result) {
						TABLE.tooltips(data.btn,"添加成功");
						setTimeout(function(){
							//TABLE.refresh();
							//pager_handler.addRecord(1);
							TABLE.close(TABLE.model_new);
						},1000);
					}
					else {
						TABLE.tooltips(btn,resp.msg||"出错了");
					}
	            }).fail(function(){
	            	 TABLE.tooltips(btn,"网络异常");
			});
				
	},	
	refresh:function(){
		var formData=$(this.query_form).serializeArray();
		formData.push({name:'pageSize',value:pager_config.pageSize});
		formData.push({name:'pageNumber',value:pager_config.pno});
		jQuery.post(this.pagerUrl,formData,function(result){
            $(TABLE.table_wrap).html(result);
            TABLE.initTable();
         });
	},
	resize:function(btn,modelId){
		if ($(btn).attr("modalwidth") && $(btn).attr("modalwidth") != ''){
			$(modelId).css("width",$(btn).attr("modalwidth"));
		}
		if ($(btn).attr("modalheight") && $(btn).attr("modalheight") != ''){
			$(modelId).css("height",$(btn).attr("modalheight"));
		}		
	},
	show_view:function(btn){
		   var url = $(btn).attr("href");
		   $(this.model_view+' .modal-body').load(url);
		   this.resize(btn,this.model_view);
		   $(this.model_view).modal('show');
	},	
	show_edit:function(btn){
		   var url = $(btn).attr("href");
		    $(this.model_edit+' .modal-body').load(url,function(){
		    	TABLE.initLoadedUrl();
		    });
		    this.resize(btn,this.model_edit);
			$(this.model_edit).modal('show');
			$(this.model_edit+' form').attr("action",url);
	},
	show_edit_inner:function(btn){
		    var url = $(btn).attr("href");
		    $(this.model_edit_inner+' .modal-body').load(url);
		    this.resize(btn,this.model_edit_inner);
			$(this.model_edit_inner).modal('show');
			$(this.model_edit_inner+' form').attr("action",url);
	},		
	show_new:function(btn){
		var url = $(btn).attr("href");
	    $(this.model_new+ ' .modal-body').load(url,function(){
		    	TABLE.initLoadedUrl();
		});
		this.resize(btn,this.model_new);
		$(this.model_new).modal('show');
		$(this.model_new+' form').attr("action",url);
	},
	show_new_inner:function(btn){
		var url = $(btn).attr("href");
	    $(this.model_new_inner+ ' .modal-body').load(url);
		this.resize(btn,this.model_new_inner);
		$(this.model_new_inner).modal('show');
		$(this.model_new_inner+' form').attr("action",url);
	},
	remove:function(btn){
		 jQuery.messager.confirm(btn.title+"确认", "确定要"+btn.title+"该数据吗？", function() { 
				var url = $(btn).attr("href");
				jQuery.post(url,function(resp){
						if(resp.result){
							//TABLE.tooltips(btn,btn.title+"成功");
							pager_handler.addRecord(-1);
							if (resp.msg =='' || resp.msg==null) {
								$(btn).closest('tr').remove();
							}
						}else{
							jQuery.messager.alert(resp["msg"] || "出错了！请重试");
						}
		            }).fail(function(){
		            	jQuery.messager.alert("网络异常！请重试");
				});
			 
           });
	},
	batch_remove:function(btn){
		var rows =$('input[name=select_row]:checked', this.table_id);
		if(!rows.length) return;
		if(rows.length>0){
			jQuery.messager.confirm("删除确认", "确定要删除该数据吗？", function() { 
				var vals = [];
				rows.map(function(){vals.push($(this).val());});
				var url = $(btn).attr("href");
				 jQuery.post(url,{items:vals},function(resp){
						if(resp.result){
							TABLE.tooltips(btn,"删除成功");
							pager_handler.addRecord(-1*vals.length);
							rows.closest('tr').fadeTo(300, 0, function () { 
			                     $(this).remove();
			                     $('.select_rows',TABLE.table_id).attr('checked',false);
			                 });
						}else alert("出错了！请重试");
				}).fail(function(){
					alert("网络异常！请重试");
				});
			});
		}
	},
	selectRows:function(btn){
		var tableid = $(btn).data('tableid');
        $('#'+tableid).find('input[name=select_row]').attr('checked', btn.checked);
	},
	initSearch:function(){
		$(".btnSearch",this.query_form).click(function(){
			$(this).append($('<input type="hidden" name="pageSize" />').val(pager_config.pageSize));
			return true;
		});
	},
	initTable:function(){
		$(".select_rows").click(function () {
			TABLE.selectRows(this);
		});
		$(this.table_id+' .btnRowView').click(function(){
			TABLE.show_view(this);
			return false;
    	});
		$(this.table_id+' .btnRowEdit').click(function(){
			$("#model_new,#model_edit").find(".modal-body").empty();
			TABLE.show_edit(this);
			return false;
    	});
		$(this.table_id+' .btnRowDelete').click(function(){
			//删除行
			TABLE.remove(this);
			return false;
    	});
	},
	initToolbar:function(){
		$(this.table_toolbar+' .btnRowBDelete').click(function(){
			//批量删除
			TABLE.batch_remove(this);
			return false;
    	});
		$(this.table_toolbar+' .btnRowNew').click(function(){
			$("#model_new,#model_edit").find(".modal-body").empty();
			TABLE.show_new(this);
			return false;
    	});
		$(this.table_toolbar+' .btnPrev').click(function(){
			pager_handler.prev();
			return false;
    	});
		$(this.table_toolbar+' .btnNext').click(function(){
			pager_handler.next();
			return false;
    	});
	},
	initLoadedUrl:function(){
		$(this.model_new+' .btnRowNew_inner').click(function(){
			TABLE.show_new_inner(this);
			return false;
    	});
		$(this.model_edit+' .btnRowEdit_inner').click(function(){
			TABLE.show_edit_inner(this);
			return false;
    	});
	}	
};