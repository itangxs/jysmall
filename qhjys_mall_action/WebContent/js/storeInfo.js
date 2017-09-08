window.UMEDITOR_HOME_URL = "/umeditor/";
$(function() {
	$.ajax({
		async : true,
		type : "POST",
		url : "/queryProv.do",
		success : function(e) {
			$("#prov").combobox({
				data : e,
				valueField : "id",
				textField : "name",
				editable : false,
				onChange : getCity
			}).combobox("setValue", $("#prov").attr("value"))
		}
	});
	getCity(null);
	getArea(null);
	uploadFile($("#logoButn"), $("#logoImage"), $("#logo"));
	var b = UM.getEditor("detail", {
		initialFrameHeight : 300,
		initialFrameWidth : 560,
		autoHeightEnabled : false,
		textarea : "storeDetail"
	});
	var c = $("#longitude"), a = $("#latitude");
	if (!(c && a)) {
		var d = "http://api.map.baidu.com/location/ip?ak=xmQt81Tg4P6Wnu3uqXjDI0lm&coor=bd09ll&callback=?";
		$.ajax({
			async : true,
			url : d,
			timeout : 3000,
			dataType : "jsonp",
			jsonp : "callback",
			success : function(e) {
				c.val(e.content.point.x);
				a.val(e.content.point.y)
			}
		})
	}
	uploadFile($("#storeButn1"), $("#storeImage1"), $("#images1"));
	uploadFile($("#storeButn2"), $("#storeImage2"), $("#images2"));
	uploadFile($("#storeButn3"), $("#storeImage3"), $("#images3"));
	uploadFile($("#storeButn4"), $("#storeImage4"), $("#images4"));
	$("form").submit(updateStore)
});
var getCity = function(a) {
	$.ajax({
		async : true,
		type : "POST",
		url : "/queryCity.do",
		data : {
			provId : a
		},
		success : function(b) {
			$("#city").combobox({
				data : b,
				valueField : "id",
				textField : "name",
				editable : false,
				onChange : getArea
			}).combobox("setValue", $("#city").attr("value"))
		}
	})
};
var getArea = function(a) {
	$.ajax({
		async : true,
		type : "POST",
		url : "/queryArea.do",
		data : {
			cityId : a
		},
		success : function(b) {
			$("#area").combobox({
				data : b,
				valueField : "id",
				textField : "name",
				editable : false
			}).combobox("setValue", $("#area").attr("value"))
		}
	})
};
function uploadFile(butn, image, hidd) {
	butn.uploadify({
		height : 35,
		width : 145,
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
function setTab(c) {
	var b = $("#menuShow>li")[c];
	var a = $("#mainShow>div")[c];
	$("#menuShow>li").removeClass("hover");
	$(b).addClass("hover");
	$("#mainShow>div").css("display", "none");
	$(a).css("display", "block")
}
function updateStore(a) {
	var c = UM.getEditor("detail").getContent();
	$("textarea").val(c);
	var b = $(this).serialize();
	$.ajax({
		async : false,
		type : "POST",
		url : "updateStore.do",
		data : b,
		success : function(d) {
			if (d.message == "添加成功") {
				alert("店铺资料修改成功,等待后台审核!");
				window.location.href = "/managermall/seller/center/page.do"
			} else {
				alert("店铺资料修改失败")
			}
		},
		error : function(d) {
			alert("error");
			return false
		}
	});
	return false
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