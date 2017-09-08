$(document).ready(function() {
	$.formValidator.initConfig({formID : "signupForm",autoTip : true,onError : function(msg) {alert(msg);},inIframe : true
	});
	$("#typeName").formValidator({onShow : "请输入菜系名称",onFocus : "输入中...",onCorrect : "正确"}).inputValidator({min : 2,max : 100,onError : "名称过长或者过短"});
	$("#xsyxj").formValidator({}).inputValidator({min : 1,max : 10,onError : ""}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"必须在1-10之间，请确认"});
	/*$("#enable").formValidator({}).inputValidator({min : 1,max : 1,onError : "必须选择是否启用"});*/
});

