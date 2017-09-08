
$(function() {
	if ($("#seltype").val() == 5) {
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryProvinceInfo.do',
		success : function(province) {
			$('#licenseProvince').combobox({
				data : province.provinceList,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange:function(record){
					var provinceId = record;
					$.ajax({
						async : false,
						type : "POST",
						data: {"provinceId": provinceId},
						url : '/queryCityInfo.do',
						success : function(city) {
							$('#licenseCity').combobox({
								data : city.cityInfoList,
								valueField : 'id',
								textField : 'name',
								editable : false,
								onChange:function(record){
									var cityId = record;
									$.ajax({
										async : false,
										type : "POST",
										data: {"cityId": cityId},
										url : '/queryDistrictInfo.do',
										success : function(district) {
											$('#licenseArea').combobox({
												data : district.districtInfoList,
												valueField : 'id',
												textField : 'name',
												editable : false,
											    onSelect:function(){
											    	var va = $("#licenseArea").val(); 
											    	$("#licenseAreaTip").css('display','none');
											    }
											
											});
										}
									});
								}
							});
						}
					});
				}
			});
		}
	});
	}
});
function submit1(){
	if ($("#realname").val() == "") {
		alert("姓名不能为空");
//	}else if ($('#birthday').datebox('getValue') == "") {
//		alert("生日不能为空");
	}else if ($("#nickname").val() == "") {
		alert("站内昵称不能为空");
	}else{
		$("#selleruserform1").submit();
	}
}
function submit2(){
	var isEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
	if (!isMobile.test($("#phoneNum").val())) {
		alert("请输入正确的手机号");
	}else if (!isEmail.test($("#email").val())) {
		alert("请输入正确的邮箱地址");
	}else{
		$("#selleruserform2").submit();
	}
}
function submit3(){
	var identity = $("input[name='identity']:checked").val();
	var marital = $("input[name='marital']:checked").val();
	if (identity == "") {
		alert("身份不能为空");
	}else if (marital == "") {
		alert("个人情况不能为空");
	}else{
		$("#selleruserform3").submit();
	}
}

function submit4(){
	var num = $("input[type='checkbox']:checked").length;
	if (num < 1) {
		alert("爱好不能不能为空");
	}else{
		$("#selleruserform4").submit();
	}
}
function submit5(){
	if ($("#licenseArea").val() == "") {
		alert("爱好不能不能为空");
	}else{
		$("#selleruserform5").submit();
	}
}
function submit6(){
	if ($("#isokqq").val() == 0) {
		alert("请绑定QQ账号");
	}else{
		$("#selleruserform6").submit();
	}
}

