$(function() {
	uploadFile($("#storeButn1"), $("#storeImage1"), $("#images1"));
	uploadFile($("#storeButn2"), $("#storeImage2"), $("#images2"));
	uploadFile($("#storeButn3"), $("#storeImage3"), $("#images3"));
	$.ajax({
		async : false,
		type : "POST",
		url : "/queryProvinceInfo.do",
		success : function(a) {
			$("#province").combobox({
				data : a.provinceList,
				valueField : "id",
				textField : "name",
				editable : false,
				onChange : function(b) {
					var c = b;
					$.ajax({
						async : false,
						type : "POST",
						data : {
							provinceId : c
						},
						url : "/queryCityInfo.do",
						success : function(d) {
							$("#city").combobox({
								data : d.cityInfoList,
								valueField : "id",
								textField : "name",
								editable : false,
								onChange : function(e) {
									var f = e;
									$.ajax({
										async : false,
										type : "POST",
										data : {
											cityId : f
										},
										url : "/queryDistrictInfo.do",
										success : function(g) {
											$("#area").combobox({
												data : g.districtInfoList,
												valueField : "id",
												textField : "name",
												editable : false
											})
										}
									})
								}
							})
						}
					})
				}
			})
		}
	});
	
		
		//正则验证start
//		$("#contactTel")
//		.blur(
//				function() {
//					$("#contactTel").next().remove();
//					var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">*您输入的手机号码有误!</p>');
//					var d = $("#contactTel").val().trim();
//					var a = /^1[3|4|5|8][0-9]\d{4,8}$/ ; 
//					if (d == "") {
//						$("#contactTel").after(b)
//					} else {
//						if (!a.test($("#contactTel").val())) {
//							var c = "*您输入的手机号码有误!";
//							var b = $('<p class="one1" style="width:300px; color: red;  text-align:center;">'
//									+ c + "</p>");
//							$("#contactTel").after(b)
//						}
//					}
//				});
//		$("#corpnId")
//		.blur(
//				function() {
//					$("#corpnId").next().remove();
//					var b = $('<p class="one1" style="width:300px; color: red; text-align:center;">*请输入正确的身份证号码!</p>');
//					var d = $("#contactTel").val().trim();
//					var a = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
//					if (d == "") {
//						$("#corpnId").after(b)
//					} else {
//						if (!a.test($("#corpnId").val())) {
//							var c = "*请输入正确的身份证号码!";
//							var b = $('<p class="one1" style="width:300px; color: red;  text-align:center;">'
//									+ c + "</p>");
//							$("#corpnId").after(b)
//						}
//					}
//				});
//
//		$("#contactPhone")
//				.blur(
//						function() {
//							$("#contactPhone").next().remove();
//							var b = $('<p class="one1" style="width:150px; color: red; text-align:center;">*手机号码输入有误!</p>');
//							var d = $("#contactPhone").val().trim();
//							var a = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
//							if (d == "") {
//								$("#contactPhone").after(b)
//							} else {
//								if (!a.test($("#contactPhone").val())) {
//									var c = "*手机号码输入有误!";
//									var b = $('<p class="one1" style="width:150px; color: red;text-align:center;">'
//											+ c + "</p>");
//									$("#contactPhone").after(b)
//								}
//							}
//						});
//
//		$("#carkNumber")
//				.blur(
//						function() {
//							$("#carkNumber").next().remove();
//							var b = $('<p class="one1" style="width:300px; color: red; text-align:center;">*您输入的卡号位数不对！</p>');
//							var d = $("#carkNumber").val().trim();
//							var a = /^(\d{16}|\d{19})$/;
//							if (d == "") {
//								$("#carkNumber").after(b)
//							} else {
//								if (!a.test($("#carkNumber").val())) {
//									var c = "*您输入的卡号位数不对！";
//									var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">'
//											+ c + "</p>");
//									$("#carkNumber").after(b)
//								}
//							}
//						});
//		
//		
//		$("#phone")
//				.blur(
//						function() {
//							$("#phone").next().remove();
//							var b = $('<p class="one1" style="width:300px; color: red;text-align:center; ">*手机号码输入有误!!</p>');
//							var d = $("#phone").val().trim();
//							var a = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
//							if (d == "") {
//								$("#phone").after(b)
//							} else {
//								if (!a.test($("#phone").val())) {
//									var c = "*手机号码输入有误!";
//									var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">'
//											+ c + "</p>");
//									$("#phone").after(b)
//								}
//							}
//						});
		
	//正则验证end	
});



function checkStoreName(sts){ 
	$.ajax({
		async : false,
		type : "POST",
		data : {
			"storeId":$("#storeId").val(),"storeName" : $("#name").val()
		},
		url : "/managermall/seller/checkStoreName.do",
		success : function(g) {
			if (g=="already") {
				var a = $('<label class="one1" style="width:150px; color: red;">店铺名称已存在</label>');
				$("#name").after(a);
				if (sts == 1) {
					$("#name").focus();
				}
			}
		}
	})
}

function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 160,
		multi : false,
		method : "post",
		buttonText : "上传",
		fileObjName : "file",
		fileSizeLimit : "1MB",
		removeCompleted : true,
		swf : "/css/uploadify.swf",
		cancelImg : "/images/cancel.png",
		fileTypeExts : "*.jpg;*.png;*.gif",
		uploader : "/fileUpload.do;jsessionid=" + getCookie(),
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.attr("src");
			butn.uploadify("settings", "formData", data)
		},
		onUploadSuccess : function(file, data, response) {
			data = eval("(" + data + ")");
			if (data.flag == 0) {
				image.attr("src", data.src);
				hidd.val(data.src)
			} else {
				alert(data.state)
			}
		}
	})
}

function getCookie() {
	var a = "JSESSIONID";
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(a + "=");
		if (c_start != -1) {
			c_start = c_start + a.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = document.cookie.length
			}
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
}
