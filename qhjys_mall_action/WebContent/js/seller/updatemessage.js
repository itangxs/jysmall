$(function() {
	uploadFile($('#update1'), $("#img1"), $("#img1u"));
	UM.getEditor('content').setContent($("#content1").val());
});

function submshuju(){
		var val = $("#title").val().trim();
		if (val == "") {
			alert("请输入标题");
			return false;
		}
		var val1 = $("#img1u").val().trim();
		if (val1 == "") {
			alert("请上传图片");
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/wxmessage/updateMessage.do',
			data : $("#form1").serialize(),
		    success : function(data) {
				if(data == "success"){
					alert("修改成功");
					window.location.href = '/managermall/seller/wxmessage/list.do';
				}else
					alert("修改失败");
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
}
function yulanshuju(){
	var val = $("#title").val().trim();
	if (val == "") {
		alert("请输入标题");
		return false;
	}
	var val1 = $("#img1u").val().trim();
	if (val1 == "") {
		alert("请上传图片");
		return false;
	}
	window.open("/managermall/seller/wxmessage/preview.do?title="+val+"&content="+UM.getEditor('content').getContent()+"&fileName="+val1);
}
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