$(function() {
	uploadFile($("#update1"), $("#img1"), $("#img1u"));
	uploadFile($("#update2"), $("#img2"), $("#img2u"));
	uploadFile($("#update3"), $("#img3"), $("#img3u"));
	uploadFile($("#update4"), $("#img4"), $("#img4u"))
});
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 80,
		multi : false,
		method : "post",
		buttonText : "上传",
		fileObjName : "file",
		fileSizeLimit : "1MB",
		removeCompleted : true,
		swf : "/css/uploadify.swf",
		cancelImg : "/images/cancel.png",
		fileTypeExts : "*.jpg;*.png;*.gif",
		uploader : "/fileUpload.do;jsessionid=" + getCookie(),
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.attr("src");
			butn.uploadify("settings", "formData", data)
		},
		onUploadSuccess : function(file, data, response) {
			data = eval("(" + data + ")");
			if (data.flag == 0) {
				image.attr("src", data.src);
				hidd.val(data.src)
			} else {
				alert(data.state)
			}
		}
	})
}
function getCookie() {
	var a = "JSESSIONID";
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(a + "=");
		if (c_start != -1) {
			c_start = c_start + a.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = document.cookie.length
			}
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
};