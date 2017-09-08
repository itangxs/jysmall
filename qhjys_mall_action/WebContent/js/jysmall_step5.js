$(function() {
	uploadFile($("#logoButn"), $("#logoImage"), $("#logo"));
	uploadFile($("#storeButn1"), $("#storeImage1"), $("#images1"));
	uploadFile($("#storeButn2"), $("#storeImage2"), $("#images2"));
	uploadFile($("#storeButn3"), $("#storeImage3"), $("#images3"));
	uploadFile($("#storeButn4"), $("#storeImage4"), $("#images4"));
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
	$("#name").blur(function() {
		validate(this, "请输入店铺名称", 20);
		 checkStoreName(0);
	});
	$("#contactName").blur(function() {
		validate(this, "请输入联系人", 20)
	});
	$("#contactTel")
			.blur(
					function() {
						$("#contactTel").next().remove();
						var b = $('<label class="one1" style="width:150px; color: red; ">请输入联系号码</label>');
						var d = $("#contactTel").val().trim();
						var a = /^\d{3,4}-?\d{7,9}$/;
						if (d == "") {
							$("#contactTel").after(b)
						} else {
							if (!a.test($("#contactTel").val())) {
								var c = "请输入030-开头的数字";
								var b = $('<label class="one1" style="width:150px; color: red; ">'
										+ c + "</label>");
								$("#contactTel").after(b)
							}
						}
					});
	$("#contactPhone")
			.blur(
					function() {
						$("#contactPhone").next().remove();
						var b = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
						var d = $("#contactPhone").val().trim();
						var a = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
						if (d == "") {
							$("#contactPhone").after(b)
						} else {
							if (!a.test($("#contactPhone").val())) {
								var c = "请输入正确的手机号";
								var b = $('<label class="one1" style="width:150px; color: red; ">'
										+ c + "</label>");
								$("#contactPhone").after(b)
							}
						}
					});
	$("#address").blur(function() {
		validate(this, "请输入店铺详细地址", 40)
	});
	$("#bankName").blur(function() {
		validate(this, "请输入所属银行", 20)
	});
	$("#branch").blur(function() {
		validate(this, "请输入银行支行名称", 20)
	});
	$("#cardholder").blur(function() {
		validate(this, "请输入真实姓名", 20)
	});
	$("#carkNumber")
			.blur(
					function() {
						$("#carkNumber").next().remove();
						var a = $('<label class="one1" style="width:150px; color: red; ">请输入银行卡号</label>');
						var b = $("#carkNumber").val().trim();
						if (b == "") {
							$("#carkNumber").after(a)
						} else {
							if (isNaN($("#carkNumber").val())) {
								var a = $('<label class="one1" style="width:150px; color: red; ">请输入正确银行卡号</label>');
								$("#carkNumber").after(a)
							}
						}
					});
	$("#phone")
			.blur(
					function() {
						$("#phone").next().remove();
						var b = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
						var d = $("#phone").val().trim();
						var a = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
						if (d == "") {
							$("#phone").after(b)
						} else {
							if (!a.test($("#phone").val())) {
								var c = "请输入正确的手机号";
								var b = $('<label class="one1" style="width:150px; color: red; ">'
										+ c + "</label>");
								$("#phone").after(b)
							}
						}
					});
	$("#longitude")
			.blur(
					function() {
						$("#longitude").next().remove();
						var a = $('<label class="one1" style="width:150px; color: red; ">请输入地图经度</label>');
						var b = $("#longitude").val().trim();
						if (b == "") {
							$("#longitude").after(a)
						} else {
							if (isNaN($("#longitude").val())) {
								var a = $('<label class="one1" style="width:150px; color: red; ">请输入正确地图经度</label>');
								$("#longitude").after(a)
							}
						}
					});
	$("#latitude")
			.blur(
					function() {
						$("#latitude").next().remove();
						var a = $('<label class="one1" style="width:150px; color: red; ">请输入地图纬度</label>');
						var b = $("#latitude").val().trim();
						if (b == "") {
							$("#latitude").after(a)
						} else {
							if (isNaN($("#latitude").val())) {
								var a = $('<label class="one1" style="width:150px; color: red; ">请输入正确地图纬度</label>');
								$("#latitude").after(a)
							}
						}
					});
	$("#storeDetail").blur(function() {
		validate("#storeDetail", "请输入商家介绍", 20)
	});
	$("#labels").blur(function() {
		validate(this, "请输入标签", 20)
	});
	$("#signupForm")
			.submit(
					function() {
						$("#signupForm label[class=one1]").remove();
						validatesubmit("#labels", "请输入标签", 20);
						var c = $('<label class="one1" style="width:150px; color: red;">请输入地图纬度</label>');
						var e = $("#latitude").val().trim();
						if (e == "") {
							$("#latitude").after(c);
							$("#latitude").focus()
						} else {
							if (isNaN($("#latitude").val())) {
								var c = $('<label class="one1" style="width:150px; color: red;">请输入正确地图纬度</label>');
								$("#latitude").after(c);
								$("#latitude").focus()
							}
						}
						var c = $('<label class="one1" style="width:150px; color: red;">请输入地图经度</label>');
						var e = $("#longitude").val().trim();
						if (e == "") {
							$("#longitude").after(c);
							$("#longitude").focus()
						} else {
							if (isNaN($("#longitude").val())) {
								var c = $('<label class="one1" style="width:150px; color: red;">请输入正确地图经度</label>');
								$("#longitude").after(c);
								$("#longitude").focus()
							}
						}
						if ($("#logo").val().trim() == "") {
							$("#logo").next().remove();
							var c = $('<font color="red">请上传店铺logo</font>');
							$("#logo").parent().append(c);
							location.hash = "corpnCardTip"
						}
						var a = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
						if ($("#phone").val().trim() == "") {
							var c = $('<label class="one1" style="width:150px; color: red;">请输入手机号码</label>');
							$("#phone").after(c);
							$("#phone").focus();
						} else {
							if (!a.test($("#phone").val())) {
								var c = $('<label class="one1" style="width:150px; color: red;">请输入正确的手机号</label>');
								$("#phone").after(c);
								$("#phone").focus();
							}
						}
						var c = $('<label class="one1" style="width:150px; color: red;">请输入银行卡号</label>');
						var e = $("#carkNumber").val().trim();
						if (e == "") {
							$("#carkNumber").after(c);
							$("#carkNumber").focus()
						} else {
							if (isNaN($("#carkNumber").val())) {
								var c = $('<label class="one1" style="width:150px; color: red;">请输入正确银行卡号</label>');
								$("#carkNumber").after(c);
								$("#carkNumber").focus()
							}
						}
						validatesubmit("#cardholder", "请输入真实姓名", 20);
						validatesubmit("#branch", "请输入银行支行名称", 20);
						validatesubmit("#bankName", "请输入所属银行", 20);
						validatesubmit("#address", "请输入商户详细地址", 40);
						if ($("input[name ='area']").val().trim() == "") {
							var c = $('<label class="one1" style="width:180px; color: red;">请输入商户地址</label>');
							$("#area").parent().append(c);
							location.hash = "corpnCardTip"
						}
						var b = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
						if ($("#contactPhone").val().trim() == "") {
							var c = $('<label class="one1" style="width:150px; color: red;">请输入手机号码</label>');
							$("#contactPhone").after(c);
							$("#contactPhone").focus()
						} else {
							if (!b.test($("#contactPhone").val())) {
								var d = "请输入正确的手机号";
								var c = $('<label class="one1" style="width:150px; color: red;">'
										+ d + "</label>");
								$("#contactPhone").after(c);
								$("#contactPhone").focus()
							}
						}
						var a = /^\d{3,4}-?\d{7,9}$/;
						if ($("#contactTel").val().trim() == "") {
							var c = $('<label class="one1" style="width:180px; color: red;">请输入电话号码</label>');
							$("#contactTel").after(c);
							$("#contactTel").focus()
						} else {
							if (!a.test($("#contactTel").val())) {
								var d = "请输入如030-开头的数字";
								var c = $('<label class="one1" style="width:180px; color: red;">'
										+ d + "</label>");
								$("#contactTel").after(c);
								$("#contactTel").focus()
							}
						}
						validatesubmit("#contactName", "请输入联系人", 20);
						validatesubmit("#name", "请输入店铺名称", 20);
						 checkStoreName(1);
						if ($("#signupForm label[class=one1]").length > 0) {
							this.focus();
							return false
						}
						$
								.ajax({
									async : false,
									type : "POST",
									url : "/managermall/seller/saveStoreInfo.do",
									data : $(this).serialize(),
									success : function(f) {
										if (f.message == "添加成功") {
											window.location.href = "/managermall/seller/addCategory.do"
										} else {
											if (f.message == "tokenEorro") {
												alert("请勿后退后重复提交,请刷新后修改");
												window.location.href = window.location.href
											} else {
												alert(f.message)
											}
										}
									},
									error : function(f) {
										alert("error");
										return false
									}
								});
						return false
					})
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
		uploader : "/fileUpload.do",
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
function validate(b, e, c) {
	$(b).next().remove();
	var a = $('<label class="one1" style="width:150px; color: red;">' + e
			+ "</label>");
	var d = $(b).val().trim();
	if (d == "") {
		$(b).after(a)
	} else {
		if ($(b).val().length > c) {
			e = "长度不能大于" + c + "位！";
			a = $('<label class="one1" style="width:150px; color: red;">' + e
					+ "</label>");
			$(b).after(a)
		}
	}
}
function validatesubmit(b, e, c) {
	$(b).next().remove();
	var a = $('<label class="one1" style="width:150px; color: red;">' + e
			+ "</label>");
	var d = $(b).val().trim();
	if (d == "") {
		$(b).after(a);
		$(b).focus()
	} else {
		if ($(b).val().length > c) {
			e = "长度不能大于" + c + "位！";
			a = $('<label class="one1" style="width:150px; color: red;">' + e
					+ "</label>");
			$(b).after(a);
			$(b).focus()
		}
	}
};