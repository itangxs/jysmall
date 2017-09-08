$(function() {
	$.formValidator.initConfig({
		formID : "addform",
		autoTip : true,
		onError : function(msg) {
			alert(msg);
		},
		inIframe : true
	});
	
	$("#jkje").formValidator({
		onShow : "请输入借款金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xs
	});
	
	$("#sqwhje").formValidator({
		onShow : "请输入上期未还金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xss
	});
	
	$("#fzr").formValidator({
		onShow : "请输入负责人",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 100,
		onError : "名称过长或者过短"
	});
	
	$("#fzrdh").formValidator({
		onShow : "请输入电话",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 11,
		max : 11,
		onError : "号码异常"
	}).regexValidator({
		regExp : "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$",
		onError : "不要瞎填"
	});
	
	$("#zyjje").formValidator({
		onShow : "请输入上期未还金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xs
	});
	
	$("#ksrq").formValidator({
		onShow : "请选择开始日期",
		onFocus : "请选择开始日期，格式2000-01-01",
		onCorrect : "正确"
	}).inputValidator({
		min : "2000-01-01",
		max : "2100-01-01",
		type : "string",
		onError : '日期必须在"2000-01-01"和"2100-01-01"之间'
	});
	$("#jsrq").formValidator({
		onShow : "请选择结束日期",
		onFocus : "请选择结束日期，格式2000-01-01",
		onCorrect : "正确"
	}).inputValidator({
		min : "2000-01-01",
		max : "2100-01-01",
		type : "string",
		onError : '日期必须在"2000-01-01"和"2100-01-01"之间'
	}).functionValidator({
		fun : jsrq
	});
});

function jsrq() {
	var c = $("#ksrq").val();
	var d = new Date(c.replace("-", "/").replace("-", "/"));
	var b = $("#jsrq").val();
	var a = new Date(b.replace("-", "/").replace("-", "/"));
	if (a < d) {
		return "不能小于开始日期"
	}
	return true
}

function xs(code) {
	if (eval(code) < eval(1))
		return "必须大于0";
	return true;
}

function xss(code) {
	if (eval(code) < eval(0))
		return "必须大于等于0";
	return true;
}

function addcredit(){
	if(window.confirm('你确定要提交吗？')){
		addform.submit();
        return true;
     }else{
        return false;
    }
}
