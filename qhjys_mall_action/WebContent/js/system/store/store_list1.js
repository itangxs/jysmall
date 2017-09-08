$(function() {
	
	$.ajax({
		async : false,
		type : "POST",
		url : '/queryCategoryInfo.do',
		success : function(province) {
			province.categoryInfoList.unshift({"id":"","name":"请选择"});
			$('#categoryId2,#hangyeType2').combobox({
				data : province.categoryInfoList,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange:function(record){
					var parentid = record;
					$.ajax({
						async : false,
						type : "POST",
						data: {"parentid": parentid},
						url : '/queryCategoryInfo1.do',
						success : function(city) {
							city.categoryInfoList.unshift({"id":"","name":"请选择"});
							$('#categoryId,#hangyeType').combobox({
								data : city.categoryInfoList,
								valueField : 'id',
								textField : 'name',
								editable : false,
								 onSelect:function(){
									 $("#signupForm label[class=one1]").remove();
								    }
							});
						}
					});
				}
			});
		}
	});
	
	$.ajax({
		async : false,
		type : "POST",
		url : "/queryProvinceInfo.do",
		success : function(a) {
			$("#licenseProvince").combobox({
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
							$("#licenseCity").combobox({
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
											$("#licenseArea").combobox({
												data : g.districtInfoList,
												valueField : "id",
												textField : "name",
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
	
	$.ajax({
		async : false,
		type : "POST",
		url : "/managermall/systemmall/qdmanage/queryFqBranch.do",
		success : function(a) {
			$("#branchId,#branchId1").combobox({
				data : a.fqBranchList,
				valueField : "id",
				textField : "branchName",
				editable : false,
				onChange : function(b) {
					var c = b;
					$.ajax({
						async : false,
						type : "POST",
						data : {
							branchId : c
						},
						url : "/managermall/systemmall/qdmanage/queryFqTeam.do",
						success : function(d) {
							$("#teamId,#teamId1").combobox({
								data : d.fqTeamList,
								valueField : "id",
								textField : "principal",
								editable : false,
								onChange : function(b) {
									var c = b;
									$.ajax({
										async : false,
										type : "POST",
										data : {
											teamId : c
										},
										url : "/managermall/systemmall/qdmanage/queryFqClerk.do",
										success : function(d) {
											$("#clerkId,#clerkId1").combobox({
												data : d.fqClerkList,
												valueField : "id",
												textField : "clerk",
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
	/*$("#licenseProvince").combobox("setValue", $("#province1").val());
	$("#licenseCity").combobox("setValue", $("#city1").val());
	$("#licenseArea").combobox("setValue", $("#area1").val());*/
});
$.fn.datebox.defaults.formatter = function(b) {
	var e = b.getFullYear();
	var a = b.getMonth() + 1;
	var c = b.getDate();
	return e + "-" + (a < 10 ? ("0" + a) : a) + "-" + (c < 10 ? ("0" + c) : c);
};
$.fn.datebox.defaults.parser = function(c) {
	if (!c) {
		return new Date();
	}
	var b = c.split("-");
	var f = parseInt(b[0], 10);
	var a = parseInt(b[1], 10);
	var e = parseInt(b[2], 10);
	if (!isNaN(f) && !isNaN(a) && !isNaN(e)) {
		return new Date(f, a - 1, e);
	} else {
		return new Date();
	}
};
function gehang() {
	var a = document.getElementsByTagName("tr");
	a[0].style.background = "#00FF66";
	a[a.length - 1].style.background = "#00FF66";
}
function xuan(d) {
	var a = document.getElementsByName("ids");
	if (d == "qx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = true;
		}
	}
	if (d == "fx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = false;
		}
	}
	if (d == "qxx") {
		var b = document.getElementById("quan");
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = b.checked;
		}
	}
	if (d == "dx") {
		var a = document.getElementsByName("ids");
		var f = true;
		var b = document.getElementsByName("quan");
		for (var c = 0; c <= a.length; c++) {
			if (a[c].checked) {
				f = false;
			}
		}
	}
}
function updateStauts(c) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改审核状态?")) {
			var b = "updateStoreStauts.do";
			var a = "ids=" + f + "&status=" + c;
			$.ajax({
				async : false,
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
						alert("修改成功");
						window.location.href = window.location.href;
					} else {
						alert("修改失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		} else {
		}
	} else {
		alert("请先选择要审核的用户");
	}
}

function updateScope(mark){
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改授权状态?")) {
			var b = "updateScope.do";
			var a = "ids=" + f + "&scope=" + mark;
			$.ajax({
				async : false,
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
						alert("修改成功");
						window.location.href = window.location.href;
					} else {
						alert("修改失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		} else {
		}
	} else {
		alert("请先选择要更改的店铺");
	}
}

function updateCashier(c) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改收银台状态?")) {
			var b = "/managermall/systemmall/store/updateCashierStatus.do";
			var a = "ids=" + f + "&cashierStatus=" + c;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
						alert("修改成功");
						window.location.href = window.location.href;
					} else {
						alert("修改失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		} else {
		}
	} else {
		alert("请先选择要修改的店铺");
	}
}

function updateOrder(c,judge) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if(null == judge){
		judge = 1;
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改点餐状态?")) {
			var b = "/managermall/systemmall/store/updateOrderStatus.do";
			var a = "ids=" + f + "&orderStatus=" + c+ "&judge=" + judge;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
						alert("修改成功");
						window.location.href = window.location.href;
					} else {
						alert("修改失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		} else {
		}
	} else {
		alert("请先选择要修改的店铺");
	}
}

function del(c) {
	if (confirm("确定要删除吗?")) {
		var b = "deleteStore.do";
		var a = "id=" + c;
		$.ajax({
			type : "post",
			url : b,
			data : a,
			success : function(d) {
				if (d == "success") {
					alert("删除成功");
					window.location.href = window.location.href;
				} else {
					alert("删除失败");
					window.location.href = window.location.href;
				}
			},
			error : function() {
				alert("服务器忙");
			}
		});
	} else {
	}
};

function setTongDao() {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if(f.length<=0){
		alert("请先选择要编辑的店铺");
		return;
	}
	$("#tongdaoIds").val(f);
	document.getElementById('tongDaoPopUp').style.display='block';
	document.getElementById('fade').style.display='block';
}

function setTongDaoSubmit() {
	var tongdaoIds = $("#tongdaoIds").val();
	var tongdaoVal = $("#tongdaoVal").val();
	if (confirm("确认修改id=" + tongdaoIds + "的支付通道？")) {
		var b = "/managermall/systemmall/store/setPayTongDao.do";
		var a = "ids=" + tongdaoIds + "&tongdaoVal=" + tongdaoVal;
		$.ajax({
			type : "post",
			url : b,
			data : a,
			success : function(data) {
				var head = "更改支付渠道失败";
				var html = "系统处理异常，请联系技术人员！";
				
				if (data.errorCount == 0) {
					head = "更改支付渠道成功";
					html = "<p>您一共更改了"+data.sumCount+"笔支付渠道，系统已接收！</p>";
				} else if (data.successCount == 0) {
					head = "更改支付渠道失败";
					html = "<p>您一共更改了"+data.sumCount+"笔支付渠道，系统未能正常接收，异常原因如下：</p>";
					$.each(data.textInfo,function(i,item){
						html += "<p>"+Number(i+1)+"、店铺ID："+item.storeId+"，"+item.msError+".</p>";
		            });
				} else if (data.successCount !=0 && data.errorCount !=0) {
					head = "更改支付渠道部分成功";
					html = "<p>您一共更改了"+data.sumCount+"笔支付渠道，系统成功接收"+data.successCount+"笔，"+data.errorCount+"笔未能正常接收，异常原因如下：</p>";
					$.each(data.textInfo,function(i,item){
						html += "<p>"+Number(i+1)+"、店铺ID："+item.storeId+"，"+item.msError+".</p>";
		            });
				}
				
				document.getElementById('tongDaoPopUp').style.display='none';
				document.getElementById('fade').style.display='none';
				
				$("#cash-head").text(head);
				$("#cash-content").html(html);
				$("#success-cash").show();
				
				
				/*if (g == "success") {
					alert("修改成功");
					window.location.href = window.location.href;
				} else if (g == "fail") {
					alert("修改失败");
					window.location.href = window.location.href;
				} else if (g == "failError") {
					alert("系统异常，请联系技术人员");
					window.location.href = window.location.href;
				}*/
			},
			error : function() {
				alert("服务器忙");
			}
		});
	}
}

function getId(storeId,rateId,statementPeriod){
	$("#storeId").val(storeId);
	$("#period").val(statementPeriod);
	 $("#rate").val(rateId);
	document.getElementById('feilvset').style.display='block';
	document.getElementById('fade').style.display='block';
}
function getcategoryId(storeId,categoryId){
	$("#categorystoreId").val(storeId);
	if(categoryId>2 && categoryId <30){
		$("#categoryId2").combobox("setValue", 1);
	}
	if(categoryId>29 ){
		$("#categoryId2").combobox("setValue", 2);
	}
		$("#categoryId").combobox("setValue", categoryId);
	document.getElementById('hangyelx').style.display='block';
	document.getElementById('fade').style.display='block';
}

function setcategoryId(){
	var url = "/managermall/systemmall/store/setStoreCategory.do";
	var storeId = $("#categorystoreId").val();
	var Avalue =   $("#categoryId").combobox('getValue');
	var Bvalue =   $("#categoryId").combobox('getText');
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':storeId,'categoryId':Avalue,'categoryDetail':Bvalue},
		success : function(result) {
			document.getElementById('feilvset').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("设置分类成功");
			}else if(result=="nochange"){}else{
				alert("设置分类失败");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}


function getPayChnnel(storeId) {
	$("#channelValidation").empty();
	$("#payChnnelStoreId").val(storeId);
	var url = "/managermall/systemmall/store/getStoreIdPayChnnel.do";
	var options;
	$.ajax({
	   		url :url,
	        type : 'POST',
	        dataType : 'json',
	        cache: false,
	        data:{'storeId' : storeId},
	        success : function(data) {
	        	$.each(data, function(i, item) {
	        		// console.log(item + "............" + i);
	        		if (item.target == "true") {
	        			options += "<option value=\"" + item.channelId + "\" selected>" + item.channelName + "</option>";
	        		} else {
	        			options += "<option value=\"" + item.channelId + "\">" + item.channelName + "</option>";
	        		}
				});
				$("#channelValidation").append(options);
	      }
	 });
	document.getElementById('payChnnel').style.display='block';
	document.getElementById('fade').style.display='block';
}

function setPayChnnel(){
	var url = "/managermall/systemmall/store/setStoreIdPayChnnel.do";
	var storeId = $("#payChnnelStoreId").val();
	var channelValidation = $("#channelValidation").val();
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':storeId, 'channelValidation':channelValidation},
		success : function(result) {
			document.getElementById('payChnnel').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("设置支付渠道成功");
			}else if(result=="fail"){}else{
				alert("设置失败");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}

function editXyAuthcationInfo() {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if(f.length<=0){
		alert("请先选择要编辑的店铺");
		return;
	}
	if(f.length>1){
		alert("一次只能编辑一个店铺");
		return;
	}
	var url = "/managermall/systemmall/store/getStoreIdXyAuthcation.do";
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':parseInt(f)},
		success : function(data){
			var g = eval('(' + data + ')');
			if (g != null && g != "") {
				$("#xyStoreId").val(g.storeId);
				$("#xyNumber").val(g.xyMerchantNum);
				$("#xyKey").val(g.xyBankKey);
			}
			document.getElementById('xyEditInfo').style.display='block';
			document.getElementById('fade').style.display='block';
		},
		error : function(){
			alert("服务器忙");
		}
	})
}

function setXyEditAucation(){
	var url = "/managermall/systemmall/store/setXyEditAucation.do";
	var xyStoreId = $("#xyStoreId").val();
	var xyNumber = $("#xyNumber").val();
	var xyKey = $("#xyKey").val();
	if (xyNumber == null || xyNumber == "") {
		alert("商户ID不能为空");
		return;
	}
	if (xyKey == null || xyKey == "") {
		alert("密钥不能为空");
		return;
	}
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':xyStoreId, 'xyNumber':xyNumber, 'xyKey':xyKey},
		success : function(result) {
			document.getElementById('xyEditInfo').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("保存成功");
			}else if(result=="fail"){}else{
				alert("保存失败");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}

function authcation(storeId,status){
	if (status != '2') {
		alert("审核未通过不能进件！");
		return;
	}
	$("#authcationId").val(storeId);
	document.getElementById('msAuthcation').style.display='block';
	document.getElementById('fade').style.display='block';
}

function setAuthcation(){
	var url = "/managermall/systemmall/store/msBankAnthentication.do";
	var bankId = $("#authBank").val();
	var storeId = $("#authcationId").val();
	var anthenticationType = $("#anthenticationType").val();
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':storeId,'bankId':bankId,'anthenticationType':anthenticationType},
		success : function(result) {
			document.getElementById('msAuthcation').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success") {
				window.location.reload();
				alert("进件资料提交成功，审核通过时间待定");
			} else if(result == "fail") {
				alert("进件资料提交失败，请联系技术人员");
			} else if (result == "repeat") {
				alert("该渠道已进件或者正在审核中");
			} else if (result == "wxOrzfbRepeat") {
				alert("微信或支付宝有渠道正在进件中，请单独选择渠道进件");
			} else if (result == "anthFail") {
				alert("没有找到该店铺进件信息，请核对资料是否齐全后重新审核");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}

function setRate(){
	var url = "/managermall/systemmall/store/setStoreRate.do";
	var rateId = $("#rate").val();
	var period = $("#period").val();
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':$("#storeId").val(),'rateId':rateId,'period':period},
		success : function(result) {
			document.getElementById('feilvset').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("设置费率成功");
			}else{
				alert("设置费率失败");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}

function setBinding(storeId,clerkId){
	$('#store_id').val(storeId);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/store/getClerkAndStore.do",
		data : {'storeId':storeId},
		success : function(data) {
	    	if(null == clerkId || '' == clerkId){
	    		$("#licenseProvince").combobox("setValue",data.provinceId);
		    	$("#licenseProvince").combobox("setText",data.provinceName);
		    	$("#licenseCiyt").combobox("setValue",data.cityId);
		    	$("#licenseCity").combobox("setText",data.cityName);
	    		document.getElementById('qdbd').style.display='block';
	    		document.getElementById('fade').style.display='block';
	    	}else{
	    		$("#licenseProvince1").combobox("setValue",data.provinceId);
		    	$("#licenseProvince1").combobox("setText",data.provinceName);
		    	$("#licenseCiyt1").combobox("setValue",data.cityId);
		    	$("#licenseCity1").combobox("setText",data.cityName);
		    	$("#branchId1").combobox("setValue",data.branchId);
		    	$("#branchId1").combobox("setText",data.branchName);
		    	$("#teamId1").combobox("setValue",data.teamId);
		    	$("#teamId1").combobox("setText",data.teamName);
		    	$("#clerkId1").combobox("setValue",data.clerkId);
		    	$("#clerkId1").combobox("setText",data.clerkName);
	    		document.getElementById('qdgb').style.display='block';
				document.getElementById('fade').style.display='block';
	    	}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function binding(){
	var provinceId = $("#licenseProvince").combobox("getValue");
	var cityId = $("#licenseCity").combobox("getValue");
	var branchId = $("#branchId").combobox("getValue");
	var teamId = $("#teamId").combobox("getValue");
	var clerkId = $("#clerkId").combobox("getValue");
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/store/storeInfoBinding.do",
		data : {'storeId':$("#store_id").val(),'provinceId':provinceId,'cityId':cityId,'branchId':branchId,'teamId':teamId,'clerkId':clerkId},
		success : function(result) {
			document.getElementById('qdbd').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("绑定成功");
			}else{
				alert("绑定失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function toBind(){
	var provinceId = $("#licenseProvince1").combobox("getValue");
	var cityId = $("#licenseCity1").combobox("getValue");
	var branchId = $("#branchId1").combobox("getValue");
	var teamId = $("#teamId1").combobox("getValue");
	var clerkId = $("#clerkId1").combobox("getValue");
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/store/storeInfoBinding.do",
		data : {'storeId':$("#store_id").val(),'provinceId':provinceId,'cityId':cityId,'branchId':branchId,'teamId':teamId,'clerkId':clerkId},
		success : function(result) {
			document.getElementById('qdgb').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("改绑成功");
			}else{
				alert("改绑失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}