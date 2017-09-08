$(function() {
	uploadFile($('#logoButn'), $("#logoImage"), $("#logo"));
	uploadFile($('#storeButn1'), $("#storeImage1"), $("#images1"));
	uploadFile($('#storeButn2'), $("#storeImage2"), $("#images2"));
	uploadFile($('#storeButn3'), $("#storeImage3"), $("#images3"));
	uploadFile($('#storeButn4'), $("#storeImage4"), $("#images4"));
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryProvinceInfo.do',
		success : function(province) {
			$('#province').combobox({
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
							$('#city').combobox({
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
											$('#area').combobox({
												data : district.districtInfoList,
												valueField : 'id',
												textField : 'name',
												editable : false
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

	$("#name").blur(function(){
		validate(this, "请输入店铺名称",20);
	});
	$("#contactName").blur(function(){
		validate(this, "请输入联系人",20);
	});
	$("#contactTel").blur(function(){
		$("#contactTel").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入联系号码</label>');
		var val = $("#contactTel").val().trim();
		var isMobile=/^\d{3,4}-?\d{7,9}$/;
		if (val == "") {
			$("#contactTel").after(html);
		} else if (!isMobile.test($("#contactTel").val())) {
			var str = "请输入030-开头的数字";
			var html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
			$("#contactTel").after(html);
		}
	});
	$("#contactPhone").blur(function(){
		$("#contactPhone").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
		var val = $("#contactPhone").val().trim();
		var isTelphone=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#contactPhone").after(html);
		} else if (!isTelphone.test($("#contactPhone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
			$("#contactPhone").after(html);
		}
	});
	$("#address").blur(function(){
		validate(this, "请输入店铺详细地址",40);
	});
	$("#bankName").blur(function(){
		validate(this, "请输入所属银行",20);
	});
	$("#branch").blur(function(){
		validate(this, "请输入银行支行名称",20);
	});
	$("#cardholder").blur(function(){
		validate(this, "请输入真实姓名",20);
	});
	$("#carkNumber").blur(function(){
		$("#carkNumber").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入银行卡号</label>');
		var val = $("#carkNumber").val().trim();
		if (val == "") {
			$("#carkNumber").after(html);
		} else if (isNaN($("#carkNumber").val())) {
			var html = $('<label class="one1" style="width:150px; color: red; ">请输入正确银行卡号</label>');
			$("#carkNumber").after(html);
		}
	});
	$("#phone").blur(function(){
		$("#phone").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
		var val = $("#phone").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
			$("#phone").after(html);
		}
	});
	$("#longitude").blur(function(){
		$("#longitude").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入地图经度</label>');
		var val = $("#longitude").val().trim();
		if (val == "") {
			$("#longitude").after(html);
		} else if (isNaN($("#longitude").val())) {
			var html = $('<label class="one1" style="width:150px; color: red; ">请输入正确地图经度</label>');
			$("#longitude").after(html);
		}
	});
	$("#latitude").blur(function(){
		$("#latitude").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入地图纬度</label>');
		var val = $("#latitude").val().trim();
		if (val == "") {
			$("#latitude").after(html);
		} else if (isNaN($("#latitude").val())) {
			var html = $('<label class="one1" style="width:150px; color: red; ">请输入正确地图纬度</label>');
			$("#latitude").after(html);
		}
	});
	$("#storeDetail").blur(function(){
		validate("#storeDetail", "请输入商家介绍",20);
	});
	$("#labels").blur(function(){
		validate(this, "请输入标签",20);
	});
	
	
	$("#signupForm").submit(function() {
		$("#signupForm label[class=one1]").remove();
		validatesubmit("#labels", "请输入标签",20);
		/*if($("#images1").val().trim() == ""){
			var html = $('<font color="red">请上传商家图片</font>');
			$("#images1").parent().append(html);
			 location.hash="corpnCardTip"; 
		}*/ 
		var html = $('<label class="one1" style="width:150px; color: red;">请输入地图纬度</label>');
		var val = $("#latitude").val().trim();
		if (val == "") {
			$("#latitude").after(html);
			$("#latitude").focus();
		} else if (isNaN($("#latitude").val())) {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入正确地图纬度</label>');
			$("#latitude").after(html);
			$("#latitude").focus();
		}
		var html = $('<label class="one1" style="width:150px; color: red;">请输入地图经度</label>');
		var val = $("#longitude").val().trim();
		if (val == "") {
			$("#longitude").after(html);
			$("#longitude").focus();
		} else if (isNaN($("#longitude").val())) {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入正确地图经度</label>');
			$("#longitude").after(html);
			$("#longitude").focus();
		}
		if($("#logo").val().trim() == ""){
			$("#logo").next().remove();
			var html = $('<font color="red">请上传店铺logo</font>');
			$("#logo").parent().append(html);
			location.hash="corpnCardTip"; 
		}
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#phone").val().trim() == "") {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入手机号码</label>');
			$("#phone").after(html);
			$("#phone").focus();
		} else if (!isMobile.test($("#phone").val())) {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入正确的手机号</label>');
			$("#phone").after(html);
			$("#phone").focus();
		}
		var html = $('<label class="one1" style="width:150px; color: red;">请输入银行卡号</label>');
		var val = $("#carkNumber").val().trim();
		if (val == "") {
			$("#carkNumber").after(html);
			$("#carkNumber").focus();
		} else if (isNaN($("#carkNumber").val())) {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入正确银行卡号</label>');
			$("#carkNumber").after(html);
			$("#carkNumber").focus();
		}
		validatesubmit("#cardholder", "请输入真实姓名",20);
		validatesubmit("#branch", "请输入银行支行名称",20);
		validatesubmit("#bankName", "请输入所属银行",20);
		validatesubmit("#address", "请输入商户详细地址",40);
		if($("input[name ='area']").val().trim() == ""){
			var html = $('<label class="one1" style="width:180px; color: red;">请输入商户地址</label>');
			$("#area").parent().append(html);
			location.hash="corpnCardTip"; 
		}
		var isTelphone=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#contactPhone").val().trim() == "") {
			var html = $('<label class="one1" style="width:150px; color: red;">请输入手机号码</label>');
			$("#contactPhone").after(html);
			$("#contactPhone").focus();
		} else if (!isTelphone.test($("#contactPhone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:150px; color: red;">' + str + '</label>');
			$("#contactPhone").after(html);
			$("#contactPhone").focus();
		}
		var isMobile=/^\d{3,4}-?\d{7,9}$/;
		if ($("#contactTel").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px; color: red;">请输入电话号码</label>');
			$("#contactTel").after(html);
			$("#contactTel").focus();
		} else if (!isMobile.test($("#contactTel").val())) {
			var str = "请输入如030-开头的数字";
			var html = $('<label class="one1" style="width:180px; color: red;">' + str + '</label>');
			$("#contactTel").after(html);
			$("#contactTel").focus();
		}
		validatesubmit("#contactName", "请输入联系人",20);
		validatesubmit("#name", "请输入店铺名称",20);
		if($("#signupForm label[class=one1]").length > 0){
			this.focus();
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/saveStoreInfo.do',
			data : $(this).serialize(),
			success : function(data) {
				if (data.message == "添加成功") {
					window.location.href = '/managermall/seller/addCategory.do';
				}else if(data.message == "tokenEorro"){
					alert("请勿后退后重复提交,请刷新后修改");
					window.location.href = window.location.href;
				} else {
					alert(data.message);
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
});

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

function validate(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:150px; color: red;">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:150px; color: red;">' + str + '</label>');
		$(el).after(html);
	}
}
function validatesubmit(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:150px; color: red;">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
		$(el).focus();
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:150px; color: red;">' + str + '</label>');
		$(el).after(html);
		$(el).focus();
	}
}