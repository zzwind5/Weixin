$(document).ready(function() {
	profiler.initTopNavs();
});
var profiler = {
	init : function() {
		this.intChangepwdValidate();
	},
	initTopNavs:function(){
		$('#header_top_nav li a').click(function(){
			var cv=$(this).parent().data('module')+'_'+''+'_'+'';
			$.cookie('_nav_',cv,{path: '/',  secure: false});
		});
		
		$('#siderbar_nav li a').click(function(){
			var li = $(this).parent();
			var top,menu;
			top =li.data('module');
			menu =li.data('menu');
			var cv=top+'_'+''+'_'+menu;
			$.cookie('_nav_',cv,{path: '/',  secure: false});
		});
		
		var url = document.location.href;
		var current = $('#siderbar_nav li a').filter('[href="'+url+'"]');
		if(current.length){
			var li = current.parent();
			var module,menu;
			module =li.data('module');
			menu =li.data('menu');
			var cv=module+'_'+''+'_'+menu;
			$.cookie('_nav_',cv,{path: '/',  secure: false});
			$(current).parent().addClass('active');
			$('#header_top_nav li').filter("[data-module="+module+"]").addClass('active');
		}else{
			var current = $('#header_top_nav li a').filter('[href="'+url+'"]');
			if(current.length){
				var cv=current.parent().data('module')+'_'+''+'_'+'';
				$.cookie('_nav_',cv,{path: '/',  secure: false});
				$(current).parent().addClass('active');
			}else{
				var nav =$.cookie('_nav_');
				if(nav){
					var navtokens = nav.split('_');
					var module= navtokens[0];
					var menu= navtokens[2];
					if(module)
					$('#header_top_nav li').filter("[data-module="+module+"]").addClass('active');
					if(menu)
					$('#siderbar_nav li').filter("[data-menu="+menu+"]").addClass('active');
					
				}
			}
		}
	},
	intChangepwdValidate:function(){
		$('#form_changepwd').validate({
			errorClass : 'error',
			validClass : 'valid',
			rules : {
				oldPassword : {
					required : true,
					minlength : 6
				},
				newPassword : {
					required : true,
					minlength : 6
				},
				newPasswordConfirm : {
					required : true,
					minlength : 6,
					equalTo : '#newPassword'
				}
			},
			messages : {
				newPasswordConfirm : {
					equalTo : "密码不一致"
				}
			},
			highlight : function(element) {
				$(element).closest('div').addClass("f_error");
				setTimeout(function() {
					boxHeight()
				}, 200)
			},
			unhighlight : function(element) {
				$(element).closest('div').removeClass("f_error");
				setTimeout(function() {
					boxHeight()
				}, 200)
			},
			errorPlacement : function(error, element) {
				$(element).closest('div').append(error);
			}
		});
	},
	changepwd : function(btn) {
		var form = $('#form_changepwd');
		if (!form.valid())
			return;
		var modal = form.closest('.modal');
		var formData = form.serializeArray();
		var url = form.attr("action");
		jQuery.post(url, formData, function(resp) {
			if (resp.result) {
				TABLE.tooltips(btn, "密码修改成功");
				setTimeout(function() {
					modal.modal('hide');
				}, 2000);
			} else
				TABLE.tooltips(btn, "出错了");
			setTimeout(function() {
				$(btn).tooltip('hide');
				$(btn).tooltip('destroy');
			}, 2000);
		}).fail(function() {
			TABLE.tooltips(btn, "网络异常");
			setTimeout(function() {
				$(btn).tooltip('hide');
				$(btn).tooltip('destroy');
			}, 2000);
		});
	}
};