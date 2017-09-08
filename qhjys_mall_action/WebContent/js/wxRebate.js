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
	uploadFile($("#relevantButn1"), $("#relevantImage1"), $("#relevant1"));
	uploadFile($("#relevantButn2"), $("#relevantImage2"), $("#relevant2"));
	uploadFile($("#relevantButn3"), $("#relevantImage3"), $("#relevant3"));
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

$.fn.datebox.defaults.formatter = function(b) {
	var e = b.getFullYear();
	var a = b.getMonth() + 1;
	var c = b.getDate();
	return e + "-" + (a < 10 ? ("0" + a) : a) + "-" + (c < 10 ? ("0" + c) : c)
};
$.fn.datebox.defaults.parser = function(c) {
	if (!c) {
		return new Date()
	}
	var b = c.split("-");
	var f = parseInt(b[0], 10);
	var a = parseInt(b[1], 10);
	var e = parseInt(b[2], 10);
	if (!isNaN(f) && !isNaN(a) && !isNaN(e)) {
		return new Date(f, a - 1, e)
	} else {
		return new Date()
	}
};
function gehang() {
	var a = document.getElementsByTagName("tr");
	a[0].style.background = "#00FF66";
	a[a.length - 1].style.background = "#00FF66"
}
function xuan(d) {
	var a = document.getElementsByName("ids");
	if (d == "qx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = true
		}
	}
	if (d == "fx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = false
		}
	}
	if (d == "qxx") {
		var b = document.getElementById("quan");
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = b.checked
		}
	}
	if (d == "dx") {
		var a = document.getElementsByName("ids");
		var f = true;
		var b = document.getElementsByName("quan");
		for (var c = 0; c <= a.length; c++) {
			if (a[c].checked) {
				f = false
			}
		}
	}
}


$(document).ready(function() {
	$.formValidator.initConfig({
		formID : "formss",
		alertMessage : true,
		onError : function(msg) {
			alert(msg)
		}
	});
	$("#rebate").formValidator().inputValidator({min : 1,onError : "折扣不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"折扣格式不正确"}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"折扣必须在1-10之间，请确认"});
	$("#rebateInfo").formValidator().inputValidator({min : 1,onError : "折扣说明不能为空"});
	$("#beginTime").formValidator().inputValidator({min : 1,onError : "开始时间不能为空"});
	$("#endTime").formValidator().inputValidator({min : 1,onError : "结束时间不能为空"}).functionValidator({fun:date});




	 
	

		$.formValidator.initConfig({validatorGroup:"0t1", formID:"form",alertMessage:true,onError:function(msg){alert(msg)}});
		$("#storename").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,max:100,onError:"微店铺名称不能为空或者字数过长"});
		$("#logo").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,onError:"微信店铺LOGO不能为空"});
		$("#storeinfo").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,max:300,onError:"店铺介绍不能为空,并且字数在150之内"});
	/*	$("#storeRebate").formValidator({validatorGroup:"0t1"}).inputValidator({min : 1,onError : "折扣不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"折扣格式不正确"}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"折扣必须在1-10之间，请确认"});
		$("#activityinfo").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,max:300,onError:"活动介绍不能为空,并且字数在150之内"});*/
		$("#address").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,max:100,onError:"地址不能为空或者字数过长"});
		$("#traffictime").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,onError:"营业时间不能为空"});
		$("#phonenum").formValidator({validatorGroup:"0t1"}).inputValidator({min:11,onError:"手机号码错误"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"号码不正确"});
		$("#proportion").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,max:100,onError:"定金比例不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"定金比例格式不正确"}).inputValidator({min:1,max:100,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"定金比例须在1-100之间，请确认"});
		$("#trafficBeginTime").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,onError:"开始营业时间不能为空"});
		$("#trafficEndTime").formValidator({validatorGroup:"0t1"}).inputValidator({min:1,onError:"结束营业时间不能为空"}).functionValidator({fun:date1});
		/*$("#locationid").formValidator({validatorGroup:"01"}).inputValidator({min:1,onError:"商圈不能为空"});
		$("#cuisineid").formValidator({validatorGroup:"01"}).inputValidator({min:1,onError:"菜系不能为空"});*/
	
});

function date1(){
	 var beginTime = $("#trafficBeginTime").val();
	 var endTime = $("#trafficEndTime").val();
	 beginTime = "2016-5-30 "+beginTime +":00"
	 endTime = "2016-5-30 "+endTime +":00"
	 if(beginTime>=endTime){
		 return "必须大于开始时间";
	 }else{
		 return true;
	 }
}

function date(){
	 var beginTime = $("#beginTime").val();
	 var endTime = $("#endTime").val();
	 beginTime = "2016-5-30 "+beginTime +":00"
	 endTime = "2016-5-30 "+endTime +":00"
	 if(beginTime>=endTime){
		 return "必须大于开始时间";
	 }else{
		 return true;
	 }
}

