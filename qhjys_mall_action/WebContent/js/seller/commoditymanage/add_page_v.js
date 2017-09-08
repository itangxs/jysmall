$(document).ready(function() {
	UM.getEditor("gmxz").setContent($("#gmxz1").val());
	UM.getEditor("cpxq").setContent($("#cpxq1").val());
	$.formValidator.initConfig({
		formID : "signupForm",
		autoTip : true,
		onError : function(a) {
			alert(a)
		},
		inIframe : true
	});
	$("#contactname").formValidator({
		onShow : "请输入商品名称",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 100,
		onError : "名称过长或者过短"
	});
	$("#splb").formValidator({
		onShow : "请选择你的类别",
		onFocus : "选择中",
		onCorrect : "谢谢你的配合",
		defaultValue : ""
	}).inputValidator({
		min : 1,
		onError : "你是不是忘记选择类别了!"
	}).defaultPassed();
	$("#spsx").formValidator({
		onShow : "请选择你的属性",
		onFocus : "选择中",
		onCorrect : "谢谢你的配合",
		defaultValue : ""
	}).inputValidator({
		min : 1,
		onError : "你是不是忘记选择属性了!"
	}).defaultPassed();
	$("#spms").formValidator({
		onShow : "请输入商品描述",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 100,
		onError : "描述过长或者过短"
	});
	$("#kssj").formValidator({
		onShow : "请输入的开始日期",
		onFocus : "请输入的开始日期，格式1900-01-01",
		onCorrect : "你输入的日期合法"
	}).inputValidator({
		min : "1900-01-01",
		max : "2500-01-01",
		type : "string",
		onError : '日期必须在"1900-01-01"和"2500-01-01"之间'
	});
	$("#jssj").formValidator({
		onShow : "请输入的结束日期",
		onFocus : "请输入的结束日期，格式1900-01-01",
		onCorrect : "你输入的日期合法"
	}).inputValidator({
		min : "1900-01-01",
		max : "2500-01-01",
		type : "string",
		onError : '日期必须在"1900-01-01"和"2500-01-01"之间'
	}).functionValidator({
		fun : jssj
	});
	$("#xjsj").formValidator({
		onShow : "请输入的下架日期",
		onFocus : "请输入的下架日期，格式1900-01-01",
		onCorrect : "你输入的日期合法"
	}).inputValidator({
		min : "1900-01-01",
		max : "2500-01-01",
		type : "string",
		onError : '日期必须在"1900-01-01"和"2500-01-01"之间'
	}).functionValidator({
		fun : xjsj
	});
	$("#scj").formValidator({
		onShow : "请输入市场价",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xs
	});
	$("#unit").formValidator({
		onShow : "请输入折扣价",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : unit
	}).functionValidator({
		fun : xs
	});
	$("#kssl").formValidator({
		onShow : "请输入可售数量",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : "格式不正确,请输入正整数"
	});
	$("#mrxg").formValidator({
		onShow : "请输入限购数",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : "格式不正确,请输入正整数"
	})
});
function jssj() {
	var c = $("#kssj").val();
	var d = new Date(c.replace("-", "/").replace("-", "/"));
	var b = $("#jssj").val();
	var a = new Date(b.replace("-", "/").replace("-", "/"));
	if (a < d) {
		return "不能小于开始日期"
	}
	return true
}
function xjsj() {
	var a = $("#kssj").val();
	var e = new Date(a.replace("-", "/").replace("-", "/"));
	var b = $("#jssj").val();
	var d = new Date(b.replace("-", "/").replace("-", "/"));
	var f = $("#xjsj").val();
	var c = new Date(f.replace("-", "/").replace("-", "/"));
	if (c < e) {
		return "不能小于开始日期"
	}
	if (c > d) {
		return "不能大于结束日期"
	}
	if (c < new Date()) {
		return "不能小于当前日期"
	}
	return true
}
function unit() {
	var scj = $("#scj").val();
	var unit = $("#unit").val();
	if (eval(unit) > eval(scj)) {
		return "折扣价必须小于市场价！"
	}
	return true
}
function xs(code) {
	if (eval(code) < eval(1)) {
		return "必须大于0"
	}
	return true
};