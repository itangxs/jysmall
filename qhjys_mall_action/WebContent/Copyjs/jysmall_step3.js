$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#name").formValidator({onShow:"请输入公司名称",onFocus:"输入中...",onCorrect:""}).inputValidator({min:2,max:33,onError:"名称过短或者过长"});
	 $("#licenseNumber").formValidator({onShow:"请输入营业执照号",onFocus:"输入中...",onCorrect:""}).inputValidator({min:4,max:40,onError:"营业执照号过短或者过长"});
	 $("#corpnName").formValidator({onShow:"请输入法人姓名",onFocus:"输入中...",onCorrect:""}).inputValidator({min:3,max:10,onError:"姓名过短或者过长"});
	 $("#corpnId").formValidator({onShow:"请输入身份证",onFocus:"输入中...",onCorrect:""}).inputValidator({min:15,max:18,onError:"身份证过短或者过长"});
	 //$("#licenseArea").formValidator({onShow:"请输入所在地",onCorrect:"",defaultValue:""}).inputValidator({min:1,onError: "请选择所在地!"}).functionValidator({fun:codeYzm()});
	 $("#licenseAddress").formValidator({onShow:"请输入详细地址",onFocus:"输入中...",onCorrect:""}).inputValidator({min:5,max:40,onError:"地址过短或者过长"});
	 $("#capital").formValidator({onShow:"请输入注册资本",onCorrect:""}).regexValidator({regExp:"intege1",dataType:"enum",onError:"格式不正确,如:1000.."});
	// $("#licenseCard").formValidator({onShow:"请上传执照电子版",onFocus:"上传中...",onCorrect:""}).inputValidator({min:3,onError:"上传执照电子版错误"});

}); 

 







$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};
$(function() {
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
	uploadFile($('#cardButn'), $("#cardImg"), $("#corpnCard"));
	uploadFile2($('#licenseButn'), $("#licenseImg"), $("#licenseCard"));
	uploadFile($('#organiButn'), $("#organiImg"), $("#organizationImage"));

	
	var Avalue =   $("#licenseCity").combobox('getValue');
	var Avalue1 =  $("#licenseCity").combobox('getValue');
	var Avalue2 =  $("#licenseProvince").combobox('getValue');
	if(Avalue.length<1){
		$("#licenseAreaTip").css('display','block'); 
	}else if(Avalue1.length<1){
		$("#licenseAreaTip").css('display','block'); 
	}else
	if(Avalue2.length<1){
		$("#licenseAreaTip").css('display','block'); 
	}else{
		$("#licenseAreaTip").css('display','none');
	}
	
	var imgtrt =   $("#licenseCard").val();
	if(imgtrt.length<1){
		$("#licenseCardTip").css('display','block'); 
	}else{
		$("#licenseCardTip").css('display','none');
	}
	/*
	$("#name").blur(function(){
		validate(this, "请输入公司名称",20);
		return false;
	});
	$("#licenseNumber").blur(function(){
		$("#licenseNumber").next().remove();
		var html = $('<label class="one1" style="width:180px; color: red;">请输入营业执照号</label>');
		var val = $("#licenseNumber").val().trim();
		if (val == "") {
			$("#licenseNumber").after(html);
			//$("#licenseNumber").focus();
			return false;
		}
	});
	$("#corpnName").blur(function(){
		validate(this, "请输入法人姓名",20);
	});
	$("#licenseAddress").blur(function(){
		validate(this, "请输入营业执照详细地址",30);
	});
	$("#capital").blur(function(){
		$("#capital").next().remove();
		var html = $('<label class="one1" style="width:180px; color: red;">请输入正确的注册资本</label>');
		var val = $("#capital").val().trim();
		var isCapital=/^(\+)?\d+$/;
		if (val != "" && !isCapital.test($("#capital").val())) {
			$("#capital").after(html);
			//this.focus();
		}
	});
	
	
	*/
	
	$("#signupForm").submit(function() {
		if(!sbmit()){
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : '/managermall/seller/saveCompany.do',
			data : $(this).serialize(),
			success : function(data) {
				alert(data.message);
				if(data.message == "成功"){
					window.location.href = '/managermall/seller/addStoreInfo.do';
				}else{
					alert(data.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (4==XMLHttpRequest.readyState){
	    			return true;
	    		}else{
	    			alert("error");
					return false;
	    		}
				
			}
		});
		return false;
	});
	
	
	function sbmit(){
		var Avalue =   $("#licenseCity").combobox('getValue');
		var Avalue1 =  $("#licenseCity").combobox('getValue');
		var Avalue2 =  $("#licenseProvince").combobox('getValue');
		var a = false;
		if(Avalue.length<1){
			alert("请输入所在地");
			$("#licenseAreaTip").css('display','block'); 
			 return false;
		}else if(Avalue1.length<1){
			alert("请输入所在地");
			$("#licenseAreaTip").css('display','block'); 
			return false;
		}else
		if(Avalue2.length<1){
			alert("请输入所在地");
			$("#licenseAreaTip").css('display','block'); 
			return false;
		}else{
			$("#licenseAreaTip").css('display','none');
			a=true;
		}
		
		var imgtrt =   $("#licenseCard").val();
		if(imgtrt.length<1){
			alert("请上传企业营业执照电子版");
			$("#licenseCardTip").css('display','block'); 
			return false
		}else{
			$("#licenseCardTip").css('display','none');
			a=true;
		}
		
		return a;
		
	}

	
});



function validate(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:180px;  color: red; ">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
		//$(el).focus();
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
		$(el).after(html);
		//$(el).focus();
	}
}
function validatesubmit(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
		$(el).focus();
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:180px; color: red;">' + str + '</label>');
		$(el).after(html);
		$(el).focus();
	}
}

/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 160,
		multi : false,
		method : 'post',
		buttonText : '上传',
		fileObjName : 'file',
		fileSizeLimit : '1MB',
		removeCompleted : true,
		swf : '/css/uploadify.swf',
		cancelImg : '/images/cancel.png',
		fileTypeExts : '*.jpg;*.png;*.gif',
		uploader : '/fileUpload.do;jsessionid=' + getCookie(),
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.attr('src');
			butn.uploadify('settings', 'formData', data);
		},
		onUploadSuccess : function(file, data, response) {
			data = eval('(' + data + ')');
			if (data.flag == 0) {
				image.attr('src', data.src);
				hidd.val(data.src);
			} else
				alert(data.state);
		}
	});
}

/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile2(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 160,
		multi : false,
		method : 'post',
		buttonText : '上传',
		fileObjName : 'file',
		fileSizeLimit : '1MB',
		removeCompleted : true,
		swf : '/css/uploadify.swf',
		cancelImg : '/images/cancel.png',
		fileTypeExts : '*.jpg;*.png;*.gif',
		uploader : '/fileUpload.do;jsessionid=' + getCookie(),
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.attr('src');
			butn.uploadify('settings', 'formData', data);
		},
		onUploadSuccess : function(file, data, response) {
			data = eval('(' + data + ')');
			if (data.flag == 0) {
				image.attr('src', data.src);
				hidd.val(data.src);
				$("#licenseCardTip").css('display','none');
			} else
				alert(data.state);
		}
	});
}

function getCookie() {
	var c_name = 'JSESSIONID';
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
}