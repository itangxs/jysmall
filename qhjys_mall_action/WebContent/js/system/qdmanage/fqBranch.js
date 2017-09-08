$(function() {
	$.ajax({
		async : false,
		type : "POST",
		url : "/queryProvinceInfo.do",
		success : function(a) {
			$("#provinceId,#provinceId1,#provinceId2").combobox({
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
							$("#cityId,#cityId1,#cityId2").combobox({
								data : d.cityInfoList,
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
	$("#provinceId").combobox("setValue", $("#provinceId").combobox("getText"));
	$("#cityId").combobox("setValue", $("#cityId").combobox("getText"));
});

function clickDelete(id){
	$("#fws_id").css("display","none");
	$("#fws_id").html(id);
	document.getElementById('qd-sc01').style.display='block';
	document.getElementById('fade').style.display='block';
}

function deleteFqBranch(){
	var id = $("#fws_id").html();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/deleteFqBranch.do",
		data : {'id':id},
		success : function(result) {
			document.getElementById('qd-sc01').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success") {
				window.location.reload();
				alert("删除成功");
			}else if(result=="cannot") {
				document.getElementById('qud-sc02').style.display='block';
				document.getElementById('fade').style.display='block';
			}else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function detail(id){
	$("#fws_id").html(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/fqBranchDetail.do",
		data : {'id':id},
		success : function(fqBranch) {
	    	$("#branchName").val(fqBranch.branchName);
	    	$("#principal").val(fqBranch.principal);
	    	$("#phoneNum").val(fqBranch.phoneNum);
	    	$("#provinceId2").combobox("setValue",fqBranch.provinceId);
	    	$("#provinceId2").combobox("setText",fqBranch.provinceName);
	    	$("#cityId2").combobox("setValue",fqBranch.cityId);
	    	$("#cityId2").combobox("setText",fqBranch.cityName);
	    	$("#address").val(fqBranch.address);
	    	document.getElementById('qud-xg').style.display='block';
	    	document.getElementById('fade').style.display='block';
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function update(){
	var principal = $("#principal").val();
	var phoneNum = $("#phoneNum").val();
	var provinceId = $("#provinceId2").combobox("getValue");
	var cityId = $("#cityId2").combobox("getValue");
	var address = $("#address").val();
	if(principal == "" || phoneNum == "" || provinceId == "" || cityId == "" || address == "") {
		alert("请输入完整信息");
	}else {
		if(stripscript(principal) || stripscript(address)) {
			alert('请勿输入特殊字符或者空格'); 
		}else if(!/^[\u4e00-\u9fa5a-zA-Z0-9]{1,4}$/.test(principal)) {
			alert('联系人超过输入框限制字符');
		}else if(!/^1[3|4|5|7|8][0-9]{9}$/.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else if(/^[\u4e00-\u9fa5a-zA-Z0-9]{1,4}$/.test(address)) {
			alert('详细地址输入框输入少于4个字符');
		}else{
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/updateFqBranch.do",
				data : {'id':$("#fws_id").html(), 'principal':principal,'phoneNum':phoneNum,'provinceId':provinceId,'cityId':cityId,'address':address},
				success : function(result) {
					document.getElementById('qud-xg').style.display='none';
			    	document.getElementById('fade').style.display='none';
					if(result=="success") {
						window.location.reload();
						alert("修改成功");
					}else {
						alert("修改失败");
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	}
}

function add(){
	var branchName = $("#branchName1").val();
	var principal = $("#principal1").val();
	var phoneNum = $("#phoneNum1").val();
	var provinceId = $("#provinceId1").combobox("getValue");
	var cityId = $("#cityId1").combobox("getValue");
	var address = $("#address1").val();
	if(branchName == "" || principal == "" || phoneNum == "" || provinceId == "" || cityId == "" || address == "") {
		alert("请输入完整信息");
	}else {
		if(stripscript(branchName) || stripscript(principal) || stripscript(address)) {
			alert('请勿输入特殊字符或者空格'); 
		}else if(!/^[\u4e00-\u9fa5a-zA-Z0-9]{1,14}$/.test(branchName)) {
			alert('公司名称超过输入框限制字符');
		}else if(!/^[\u4e00-\u9fa5a-zA-Z0-9]{1,4}$/.test(principal)) {
			alert('联系人超过输入框限制字符');
		}else if(!/^1[3|4|5|7|8][0-9]{9}$/.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else if(/^[\u4e00-\u9fa5a-zA-Z0-9]{1,4}$/.test(address)) {
			alert('详细地址输入框输入少于4个字符');
		}else{
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/addFqBranch.do",
				data : {'branchName':branchName,'principal':principal,'phoneNum':phoneNum,'provinceId':provinceId,'cityId':cityId,'address':address},
				success : function(result) {
					document.getElementById('qud-xz').style.display='none';
					document.getElementById('fade').style.display='none';
					if(result=="success") {
						window.location.reload();
						alert("新增成功");
					}else {
						alert("新增失败");
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	}
}

function Excel(){
	var b=$(".submit9").serialize();;
	var a="/managermall/systemmall/qdmanage/excelFqBranchList.do?"+b;
	window.open(a);
}

function stripscript(obj) { 
	var specialKey = "[`~!#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘'";
	for(var i=0;i<obj.length;i++) {
		var flg = specialKey.indexOf(obj[i]) >= 0; 
		if(flg) {
			return true;
		}
	}
	var block = /\s/g.test(obj);
	return block;
} 

function keyword() { 
	var branchId = $('#bId').val();
//	var keywords = "select,distinct,where,from,or,in,between,and,like,order by,group by,having,"
//			+"count,sum,alias,subquery,union all,union,intersect,minus,concatenate,substring"
//			+",trim,create table,create view,create index,create,update,delete,alter table,drop,truncate"
//			+",drop table,truncate table,insert into,left join,right join,on";
//	var keyword = keywords.split(",");
//	for (var i = 0; i < keyword.length; i++) {
//		if(branchId==keyword[i]) {
//			alert('服务商ID输入类型错误');
//			return false;
//		}
//	}
	if(branchId != "") {
		if(!/[0-9]{1,}/.test(branchId)) {
			alert('服务商ID输入类型错误');
			return false;
		}
	}
	return true;
} 
