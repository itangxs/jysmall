$(function() {
	uploadFile($('#update1'), $("#img1"), $("#img1u"));
	uploadFile($('#update2'),$("#img2"), $("#img2u"));
	$("#title").focus(function(){
		if ($("#title").val().trim()=="标题") {
			$("#title").val('');
		}
	}).blur(function(){
		if ($("#title").val().trim()=="") {
			$("#title").val('标题');
		}
	})
	
});

function addhref(){
	var hreftext = $("#hreftext").val().trim();
	if(hreftext == ''){
		alert("请填写超链接文本");
		return false;
	}
	var hrefurl = $("#hrefurl").val().trim();
	if(hrefurl == ''){
		alert("请填写超链接地址");
		return false;
	}else{
		if (hrefurl.indexOf("http://")<1 && hrefurl.indexOf("https://")<1) {
			hrefurl = "http://"+hrefurl;
		}
	}
	
	var content = $("#contenttext").val();
	$("#contenttext").val(content+'<a href="'+hrefurl+'">'+hreftext+'</a>');
	document.getElementById('super-link').style.display='none';
}

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
		var content = UM.getEditor('content').getContent();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/wxmessage/addMessage.do',
			data : {'title':val,'content':content,'fileName':val1,'type':1},
		    success : function(data) {
				if(data == "success"){
					alert("保存成功");
					window.location.href = '/managermall/seller/wxmessage/list.do';
				}else
					alert("保存失败");
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
}
function submshuju1(){
	var content = $("#contenttext").val();
	if(content.trim() == ''){
		alert("请输入文本");
		return false;
	}
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/seller/wxmessage/addMessage.do',
		data : {'content':content,'type':2},
		success : function(data) {
			if(data == "success"){
				alert("保存成功");
				window.location.href = '/managermall/seller/wxmessage/list.do';
			}else
				alert("保存失败");
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
}
function submshuju2(){
	var val1 = $("#img2u").val().trim();
	if (val1 == "") {
		alert("请上传图片");
		return false;
	}
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/seller/wxmessage/addMessage.do',
		data : {'fileName':val1,'type':3},
		success : function(data) {
			if(data == "success"){
				alert("保存成功");
				window.location.href = '/managermall/seller/wxmessage/list.do';
			}else
				alert("保存失败");
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
//	window.open("/managermall/seller/wxmessage/preview.do?title="+val+"&content='"+encodeURIComponent(UM.getEditor('content').getContent())+"'&fileName="+val1);
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/seller/wxmessage/previewByRedis.do',
		data : {'title':val,'content':UM.getEditor('content').getContent(),'fileName':val1},
		success : function(data) {
			window.open("/managermall/seller/wxmessage/preview.do?id="+data);
		},
		error : function(e) {
			alert("预览失败");
			return false;
		}
	});

}
/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 251,
		width : 452,
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