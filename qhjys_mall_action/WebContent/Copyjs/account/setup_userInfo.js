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
		url : '/managermall/account/searchExpandByUserId.do',
		success : function(data) {
			if(data.userExpand != undefined){
				$(':radio[name="sex"][value='+data.userExpand.sex+']').attr("checked",data.userExpand.sex);
				$("#birthday").datebox("setValue", data.userExpand.birthday);
				$(':radio[name="identity"][value='+data.userExpand.identity+']').attr("checked",data.userExpand.identity);
				$(':radio[name="maritalStatus"][value='+data.userExpand.maritalStatus+']').attr("checked",data.userExpand.maritalStatus);
				if(data.userExpand.interest.length > 0){
					var result = data.userExpand.interest.split(",");
					for(var i=0;i<result.length;i++){
						$("[type='checkbox'][value="+result[i]+"]").attr("checked",'true');
					}
				}
			}
		},	
		error : function(e) {
			alert("error");
			return false;
		}
	});
	$("#userInfoForm").submit(function() {
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/updateUserExpand.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data.message == "修改成功"){
					alert("个人资料设置成功! ");
					window.location.href = '/managermall/account/setupUserInfo.do';
				}else{
					alert("个人设置失败!");
				}
			},	
			error : function(e) {
				alert("请完善资料再保存");
				return false;
			}
		});
		return false;
	});
	uploadFile($('#update1'), $("#img1"), $("#img1u"));
});

/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 80,
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