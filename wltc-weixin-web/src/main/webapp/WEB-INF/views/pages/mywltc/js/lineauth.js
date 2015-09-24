$(function(){
    $('#startCityName').CityPicker({codeId : 'startCityCode'});
    $('#endCityName').CityPicker({codeId : 'endCityCode'});
		
	$('#form1').validate({
		onkeyup: false,
		errorClass: 'error',
		validClass: 'valid',
		errorElement:"p",
		rules: {
			orgName: {required:true},
			orgPhone: {required:true},
			orgAddress: {required:true},
			startCityName: {required:true},
			contract: {required:true},
			phone: {required:true},
			endCityName: {required:true},
			contract2: {required:true},
			phone2: {required:true}
		},
	    messages: {
	    }
	});	
	
	$("#orgName").blur(function() {
		var orgName = $(this).val();
		queryOrg(orgName);
	});
});

function queryOrg(orgName) {
	if (orgName != "") {
		jQuery.post(ROOT_PATH + "/mywltc/findOrgByName.htm", {
			param : orgName
		}, function(data) {
			var org = data.data;
			console.log(data);
			if(org != null){
				setOrg(org);
			}else{
				setOrg();
			}
		}, "json");
	}
}

function setOrg(org){
	if(org == null){
		$("#orgId,#orgContract,#orgPhone,#orgAddress").val("");
		$("#orgContract,#orgPhone,#orgAddress").attr("disabled",false);		
	}else{
		$("#orgId").val(org.id);
		$("#orgContract").val(org.contract);
		$("#orgPhone").val(org.phone);
		$("#orgAddress").val(org.address);
		
		$("#orgContract,#orgPhone,#orgAddress").attr("disabled",true);		
	}

}
