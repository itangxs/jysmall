window.UMEDITOR_HOME_URL = "/umeditor/";
$(function() {
	$.ajax({
		async : true,
		type : "POST",
		url : '/queryProv.do',
		success : function(data) {
			$('#prov').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange : getCity
			}).combobox('setValue', $('#prov').attr("value"));
		}
	});
	getCity(null);
	getArea(null);
	uploadFile($("#logoButn"), $("#logoImage"), $("#logo"));
	// 实例化编辑器
	var um = UM.getEditor('detail', {
		initialFrameHeight : 300,
		initialFrameWidth : 560,
		autoHeightEnabled : false,
		textarea : 'storeDetail'
	});
	var lngtd = $('#longitude'), lattd = $('#latitude');
	if (!(lngtd && lattd)) {
		var baiduUrl = 'http://api.map.baidu.com/location/ip?ak=xmQt81Tg4P6Wnu3uqXjDI0lm&coor=bd09ll&callback=?';
		$.ajax({
			async : true,
			url : baiduUrl,
			timeout : 3000,
			dataType : 'jsonp',
			jsonp : 'callback',
			success : function(data) {
				lngtd.val(data.content.point.x);
				lattd.val(data.content.point.y);
			}
		});
	}
	uploadFile($("#storeButn1"), $("#storeImage1"), $("#images1"));
	uploadFile($("#storeButn2"), $("#storeImage2"), $("#images2"));
	uploadFile($("#storeButn3"), $("#storeImage3"), $("#images3"));
	uploadFile($("#storeButn4"), $("#storeImage4"), $("#images4"));
	$('form').submit(updateStore);
});
// 获取城市
var getCity = function(e) {
	$.ajax({
		async : true,
		type : "POST",
		url : '/queryCity.do',
		data : {
			provId : e
		},
		success : function(data) {
			$('#city').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false,
				onChange : getArea
			}).combobox('setValue', $('#city').attr("value"));
		}
	});
};
// 获取区域
var getArea = function(e) {
	$.ajax({
		async : true,
		type : "POST",
		url : '/queryArea.do',
		data : {
			cityId : e
		},
		success : function(data) {
			$('#area').combobox({
				data : data,
				valueField : 'id',
				textField : 'name',
				editable : false
			}).combobox('setValue', $('#area').attr("value"));
		}
	});
};
/**
 * @param butn
 *            上传按钮
 * @param image
 *            图片显示控件
 * @param hidd
 *            图片地址保存控件
 */
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 145,
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

function setTab(no) {
	var menu = $('#menuShow>li')[no];
	var main = $('#mainShow>div')[no];
	$('#menuShow>li').removeClass('hover');
	$(menu).addClass('hover');
	$('#mainShow>div').css('display', 'none');
	$(main).css('display', 'block');
}

/**
 * 更新店铺信息
 * 
 * @param e
 * @returns {Boolean}
 */
function updateStore(e) {
	var text = UM.getEditor('detail').getContent();
	$('textarea').val(text);
	var param = $(this).serialize();
	$.ajax({
		async : false,
		type : "POST",
		url : 'updateStore.do',
		data : param,
		success : function(data) {
			if (data.message == "添加成功") {
				alert("店铺资料修改成功,等待后台审核!");
				window.location.href="/managermall/seller/center/page.do";
			} else {
				alert("店铺资料修改失败");
			}
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
	return false;
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