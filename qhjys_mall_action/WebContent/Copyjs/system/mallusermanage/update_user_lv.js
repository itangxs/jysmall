$(function() {
	uploadFile($('#update1'), $("#img1"), $("#img1u"));
/*	uploadFile($('#update2'), $("#img2"), $("#img2u"));
	uploadFile($('#update3'), $("#img3"), $("#img3u"));
	uploadFile($('#update4'), $("#img4"), $("#img4u"));*/
	
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

$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#name").formValidator({onShow:"请输入商品名称",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:2,max:100,onError:"名称过长或者过短"});
	 $("#imgs").formValidator({onShow:"请上传图片",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:1,max:100,onError:"图片名称"});
	 $("#min").formValidator({onShow:"请输入最小积分",onFocus:"输入中...",onCorrect:"正确"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"格式不正确,请输入正整数"});
	 $("#max").formValidator({onShow:"请输入最大积分",onFocus:"输入中...",onCorrect:"正确"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"格式不正确,请输入正整数"});
	/* $("#status").formValidator({onShow:"请选择你的类别",onFocus:"选择中",onCorrect:"谢谢你的配合",defaultValue:""}).inputValidator({min:1,onError: "你是不是忘记选择状态了!"}).defaultPassed();*/
});