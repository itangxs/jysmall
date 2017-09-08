$(function() {
	uploadFile($('#update1'), $("#img1"), $("#imgu1"));
	uploadFile($('#update2'), $("#img2"), $("#imgu2"));
	uploadFile($('#update3'), $("#img3"), $("#imgu3"));
	uploadFile($('#update4'), $("#img4"), $("#imgu4"));
});

function deletetime(id,obj){
	var b = "/managermall/systemmall/ad/acard_delete_time.do";
	var a = "timeId=" + id;
	$.ajax({
		async : false,
		type : "post",
		url : b,
		data : a,
		success : function(g) {
			if (g == "success") {
				$(obj).parent().parent().remove();
			} else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("服务器忙")
		}
	});
}

function quxiao(){
	window.location.href = "/managermall/systemmall/ad/acard_list.do";
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
