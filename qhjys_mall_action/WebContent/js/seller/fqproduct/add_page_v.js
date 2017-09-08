$(document).ready(function() {
	UM.getEditor('cpxx').setContent($("#cpxx1").val());
	$.formValidator.initConfig({
		formID : "signupForm",
		autoTip : true,
		onError : function(msg) {
			alert(msg);
		},
		inIframe : true
	});
	$("#contactname").formValidator({
		onShow : "请输入菜品名称",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 100,
		onError : "名称过长或者过短"
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
	$("#kc").formValidator({
		onShow : "请输入可售数量",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : "格式不正确,请输入正整数"
	}).functionValidator({
		fun : xs
	});
	$("#xsyxj").formValidator({
		onShow : "请输入显示优先级",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : "格式不正确,请输入正整数"
	}).functionValidator({
		fun : xs
	}).functionValidator({
		fun : ss
	});
	$("#cplb").formValidator({
		onShow : "请选择菜系类别",
		onFocus : "选择中",
		onCorrect : "谢谢你的配合",
		defaultValue : ""
	}).inputValidator({
		min : 1,
		onError : "你是不是忘记选择类别了!"
	});
	$("#img1u").formValidator({
		onShow : "请上传图片(大小:440x300)",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 500,
		onError : "未上传图片"
	});
	
});

function jssj() {
	var startTime = $("#kssj").val();
	var start = new Date(startTime.replace("-", "/").replace("-", "/"));
	var endTime = $("#jssj").val();
	var end = new Date(endTime.replace("-", "/").replace("-", "/"));
	if (end < start)
		return "不能小于开始日期";
	return true;
}

function xjsj() {
	var kssj = $("#kssj").val();
	var date1 = new Date(kssj.replace("-", "/").replace("-", "/"));
	var jssj = $("#jssj").val();
	var date2 = new Date(jssj.replace("-", "/").replace("-", "/"));
	var xjsj = $("#xjsj").val();
	var date3 = new Date(xjsj.replace("-", "/").replace("-", "/"));
	if (date3 < date1)
		return "不能小于开始日期";
	if (date3 > date2)
		return "不能大于结束日期";
	if (date3 < new Date())
		return "不能小于当前日期";
	return true;
}

function unit() {
	var scj = $("#scj").val();
	var unit = $("#unit").val();
	if (eval(unit) > eval(scj))
		return "折扣价必须小于市场价！";
	return true;
}

function xs(code) {
	if (eval(code) < eval(1))
		return "必须大于0";
	return true;
}
function ss(code) {
	if (eval(code) > eval(99))
		return "优先级不能大于99";
	return true;
}
