
$(document).ready(function() {
	uploadFile($('#pcBannerButn'), $("#pcBannerImage"), $("#pcBanner"));
	uploadFile($('#pcMenuMeisButn'), $("#pcMenuMeisImage"), $("#pcMenuMeis"));
	uploadFile($('#pcMenuDianyButn'), $("#pcMenuDianyImage"), $("#pcMenuDiany"));
	uploadFile($('#pcMenuGouwButn'), $("#pcMenuGouwImage"), $("#pcMenuGouw"));
	uploadFile($('#pcMenuLvyouButn'), $("#pcMenuLvyouImage"), $("#pcMenuLvyou"));
	uploadFile($('#pcMenuXiuxButn'), $("#pcMenuXiuxImage"), $("#pcMenuXiux"));
	uploadFile($('#pcMenuLirenButn'), $("#pcMenuLirenImage"), $("#pcMenuLiren"));
	uploadFile($('#pcMenuShenghButn'), $("#pcMenuShenghImage"), $("#pcMenuShengh"));
	uploadFile($('#pcMenuLicaiButn'), $("#pcMenuLicaiImage"), $("#pcMenuLicai"));
	uploadFile($('#appBanner1Butn'), $("#appBanner1Image"), $("#appBanner1"));
	uploadFile($('#appBanner2Butn'), $("#appBanner2Image"), $("#appBanner2"));
	uploadFile($('#appBanner3Butn'), $("#appBanner3Image"), $("#appBanner3"));
	uploadFile($('#appBanner4Butn'), $("#appBanner4Image"), $("#appBanner4"));
	uploadFile($('#appBanner5Butn'), $("#appBanner5Image"), $("#appBanner5"));
	
	
});
;

/**
 * @param butn 上传按钮
 * @param image 图片显示控件
 * @param hidd 图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 160,
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