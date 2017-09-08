$(function(){
	uploadFile($('#update1'), $("#info1"), $("#img2u"));
	$("#questionType").change(function(){
		if ($("#questionType").val()==1) {
			$("#timuleixing").show();
			$("#imageUrl").hide();
			$("#upimgdiv").hide();
		}else if($("#questionType").val()==2||$("#questionType").val()==3){
			$("#timuleixing").hide();
			$("#imageUrl").show();
			$("#upimgdiv").show();
			$("#answernum").hide();
		}else{
			$("#timuleixing").hide();
			$("#imageUrl").hide();
			$("#upimgdiv").hide();
			$("#answernum").hide();
		}
	});
	$("#answerType").change(function(){
		if ($("#answerType").val()==0) {
			$("#answernum").show();
		}else{
			$("#answernum").hide();
		}
	});
});
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
		fileTypeExts : '*.*',
		uploader : '/fileUpload.do;jsessionid=' + getCookie(),
		onUploadStart : function(file) {
			var data = {};
			data.delPath = image.val();
			butn.uploadify('settings', 'formData', data);
		},
		onUploadSuccess : function(file, data, response) {
			data = eval('(' + data + ')');
			if (data.flag == 0) {
				image.val(data.src);
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

function deleteAnswer(id){
	$.ajax({
		async : true,
		type : "POST",
		data:{'id':id},
		url : '/managermall/systemmall/taskAnswer/deleteAnswer.do',
		success : function(data) {
			if (data == "success") {
				window.location.href=window.location.href;
			}
		}
	});
}
function changeCorrect(id){
	$.ajax({
		async : true,
		type : "POST",
		data:{'id':id},
		url : '/managermall/systemmall/taskAnswer/changeCorrect.do',
		success : function(data) {
			if (data == "success") {
				window.location.href=window.location.href;
			}
		}
	});
}
