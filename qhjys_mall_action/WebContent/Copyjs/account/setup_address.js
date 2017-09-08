var optype;
$(function() {
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
						success : function(record) {
							$('#cityId').combobox({
								data : record.cityInfoList,
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
	$("#address").blur(function(){
		validate(this, "请输入街道地址",50);
	});
	$("#zip").blur(function(){
		$("#zip").next().remove();
		var validate = /^[0-9]{6}$/;
		var zip = $("#zip").val().trim();
		if(zip == ""){
			var html = $('<label class="one1" style="width:180px;">请输入邮政编码</label>');
			$("#zip").after(html);
		}else if(!(validate.test(zip))){
			var html = $('<label class="one1" style="width:180px;">请输入正确的邮政编码</label>');
			$("#zip").after(html);
		}
	});
	$("#recipient").blur(function(){
		validate(this, "请输入收件人",50);
	});
	$("#phone").blur(function(){
		$("#phone").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
		var val = $("#phone").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phone").after(html);
		}
	});
	$("#setupAddressForm").submit(function() {
		$("#setupAddressForm label[class=one1]").remove();
		if($('#area').combobox('getValue').trim() == ""){
			var html = $('<label class="one1" style="width:180px;">请选择所在地区</label>');
			$("#area").after(html);
		}
		validate("#address", "请输入街道地址",50);
		validate("#recipient", "请输入收货人",50);
		var zipValidate = /^[0-9]{6}$/;
		var zip = $("#zip").val().trim();
		if(zip == ""){
			var html = $('<label class="one1" style="width:180px;">请输入邮政编码</label>');
			$("#zip").after(html);
		}else if(!(zipValidate.test(zip))){
			var html = $('<label class="one1" style="width:180px;">请输入正确的邮政编码</label>');
			$("#zip").after(html);
		}
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#phone").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phone").after(html);
		}
		if($("#setupAddressForm label[class=one1]").length > 0){
			return false;
		}
		if($("#id").val() != ""){
			optype = '1';
		}else{
			optype = '2';
		}
		$.ajax({
			async : false,
			type : "POST",
			url : optype == '2' ? '/managermall/account/insertAddress.do' : '/managermall/account/updateAddress.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data.message == "添加成功"){
					alert("添加收货地址成功");
					$("input[id=id]").val("");
					window.location.href = '/managermall/account/queryAddressList.do';
				}else if(data.message == "修改成功"){
					alert("修改收货地址成功");
					$("input[id=id]").val("");
					window.location.href = '/managermall/account/queryAddressList.do';
				}else{
					alert("编辑收货地址失败");
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
function update(id){
	$("div[id=setAddress]").show();
	$("#id").val(id);
	var id = $("#id").val();
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/account/searchAddressById.do',
		data : {"id":id},
		success : function(data) {
			$('#province').combobox('setValue',data.address.province);
			$('#cityId').combobox('setValue',data.address.city);
			$('#area').combobox('setValue',data.address.area);
			$("#zip").val(data.address.zip);
			$("#address").val(data.address.address);
			$("#recipient").val(data.address.recipient);
			$("#phone").val(data.address.phone);
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
}
function closeWindow(){
	$("input[id=id]").val("");
	$("label[class=one1]").remove();
	$("#setupAddressForm")[0].reset();
	$("div[id=setAddress]").hide();
}
function del(id){
	$("#id").val(id);
	var id = $("#id").val();
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/account/deleteAddress.do',
		data : {"id":id},
		success : function(data) {
			if(data.message == "删除成功"){
				alert("删除收货地址成功");
				window.location.href = '/managermall/account/queryAddressList.do';
			}else{
				alert("删除收货地址失败");
			}
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
}
function setupAddress(){
	$("div[id=setAddress]").show();
}
function validate(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:180px;">' + str + '</label>');
		$(el).after(html);
	}
}