function clickDelete(id){
	$("#tdz_id").css("display","none");
	$("#tdz_id").html(id);
	document.getElementById('qud-sc01').style.display='block';
	document.getElementById('fade').style.display='block';
}

function deleteFqTeam(){
	var id = $("#tdz_id").html();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/deleteFqTeam.do",
		data : {'id':id},
		success : function(result) {
			document.getElementById('qud-sc01').style.display='none';
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
	$("#tdz_id").html(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/fqTeamDetail.do",
		data : {'id':id},
		success : function(fqTeam) {
			if(null != fqTeam.branchId) {
				$("#branchId1").val(fqTeam.branchId);
			}else {
				$("#branchId1").val("");
			}
	    	$("#principal1").val(fqTeam.principal);
	    	$("#phoneNum1").val(fqTeam.phoneNum);
	    	document.getElementById('qud-xg').style.display='block';
	    	document.getElementById('fade').style.display='block';
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function update(){
	var branchId = $("#branchId1").val();
	var principal = $("#principal1").val();
	var phoneNum = $("#phoneNum1").val();
	if(phoneNum == "" || branchId == "") {
		alert("请输入完整信息");
	}else {
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if(!reg.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else {
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/updateFqTeam.do",
				data : {'id':$("#tdz_id").html(),'branchId':branchId,'principal':principal,'phoneNum':phoneNum},
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
	var principal = $("#principal").val();
	var phoneNum = $("#phoneNum").val();
	var branchId = $("#branchId").val();
	if(principal == "" || phoneNum == "" || branchId == "") {
		alert("请输入完整信息");
	}else {
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if(!reg.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else if(stripscript(principal)) {
			alert('请勿输入特殊字符或者空格'); 
		}else if(!/^[\u4e00-\u9fa5a-zA-Z0-9]{1,4}$/.test(principal)) {
			alert('姓名输入超过4个字符');
		}else {
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/addFqTeam.do",
				data : {'branchId':branchId,'principal':principal,'phoneNum':phoneNum},
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
	var a="/managermall/systemmall/qdmanage/excelFqTeamList.do?"+b;
	window.open(a);
}

function stripscript(obj) { 
	var specialKey = "[`~!#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘'";
	for (var i=0;i<obj.length;i++) {
		var flg = specialKey.indexOf(obj[i]) >= 0; 
		if(flg) {
			return true;
		}
	}
	var block = /\s/g.test(obj);
	return block;
} 

function keyword() { 
	var teamId = $('#tId').val();
	if (teamId != "") {
		if (!/[0-9]{1,}/.test(teamId)) {
			alert('团队长ID输入类型错误');
			return false;
		}
	}
	return true;
} 