$(function() {
	$.formValidator.initConfig({
		formID : "from",
		autoTip : true,
		onError : function(msg) {
			alert(msg);
		},
		inIframe : true
	});
	
	$("#hdmc").formValidator({
		onShow : "请输入活动名称",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).inputValidator({
		min : 2,
		max : 100,
		onError : "名称过长或者过短"
	});
	
	$("#beginsj").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : "正确"
	}).inputValidator({
		min : "2000-01-01",
		max : "2100-01-01",
		type : "string",
		onError : '日期错误'
	});
	$("#endingsj").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : "正确"
	}).inputValidator({
		min : "2000-01-01",
		max : "2100-01-01",
		type : "string",
		onError : '日期错误'
	});
	
	$("#zdjee").formValidator({
		onShow : "请输入最大金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xss
	});
	
	$("#zxjee").formValidator({
		onShow : "请输入最小金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xss
	});
	
	$("#zjee").formValidator({
		onShow : "请输入总金额",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "num",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xss
	});
	
	$("#drhbs").formValidator({
		onShow : "请输入个数",
		onFocus : "输入中...",
		onCorrect : "正确"
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : "格式不正确,"
	}).functionValidator({
		fun : xs
	});
	
});

function xs(code) {
	if (eval(code) < eval(1))
		return "必须大于0";
	return true;
}

function xss(code) {
	if (eval(code) < eval(1))
		return "必须不小于1";
	return true;
}


function Excel() {
	var b = $("#from").serialize();
	var a = "/managermall/systemmall/statistics/salesDiscountListExcel.do?" + b;
	window.open(a)
};

function ongailvListener(){
	var gailv = $("input:radio[lang='gailv']");
	for (var i = 0; i < gailv.length; i++) {
		gailv[i].checked = true;
	}
	$("#randType").val(1);
}

function onfenduanListener(){
	var fenduan = $("input:radio[lang='fenduan']");
	for (var i = 0; i < fenduan.length; i++) {
		fenduan[i].checked = true;
	}
	$("#randType").val(2);
}

var sd = 100;
function jiashiduan() {
	$("#jinee").before('<p class="clearfix" id="tt'+sd+'"> '+
			'<label for="select">&nbsp;</label>'+
			'<label class="con-email120" for="con-email130" ></label>'+
			'<input class="con-email9" name="startHour" >&nbsp;点 '+
			'<input class="con-email9" name="startMinute" value="00">&nbsp;分&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;'+
			'<input class="con-email9" name="endHour" >&nbsp;点 '+
			'<input class="con-email9" name="endMinute" value="00">&nbsp;分'+
			' <label for="select">&nbsp;&nbsp;</label><input type="button" value="-删除时段" class="submitblue" onclick="jianshiduan(\'tt'+sd+'\')">'+
			'<input type="hidden" name="timeNewIds" value="'+sd+'" class=""/>'+
			'</p>');
	sd+=1;
}

function jianshiduan(pid){
	 $("#"+pid).remove();
	}




var a=1000;
function jiagailv() {
	
	var type = $("#randType").val();
	if (type == 1) {
		$("#fendgl").append('<div id="nr'+a+'" class="nr"> <p>'+'<input class="con-email3" name="subMoneyMin" value="">元 ——  '+
    			'<input class="con-email3" name="subMoneyMax" value=""> 元（精确到0.01元）'+
    			' <label for="select">&nbsp;</label><input type="button" value="-删除" class="submitblue" onclick="jiangailv(\'nr'+a+'\')">'+
		'<p><input lang="gailv" name="radiogroup'+a+'" type="radio" checked="checked" onclick="ongailvListener()"> 获得红包概率&nbsp;'+
        '<input class="con-email3" name="fenduanGaiLv" value=""> %&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<input lang="fenduan" name="radiogroup'+a+'" type="radio" onclick="onfenduanListener()"> 顾客支付金额&nbsp;'+
        '<input class="con-email3" name="zhifujineMin" >  元 —— <input class="con-email3" name="zhifujineMax" > 元</p>'+
        
        '<input type="hidden" name="detailNewIds" value="'+a+'" class=""/>'
		+ '</p></div>');
	}else if (type == 2) {
		$("#fendgl").append('<div  id="nr'+a+'" class="nr"> <p>'+'<input class="con-email3" name="subMoneyMin" value="">元 ——  '+
				'<input class="con-email3" name="subMoneyMax" value=""> 元（精确到0.01元）'+
				 ' <label for="select">&nbsp;</label><input type="button" value="-删除" class="submitblue" onclick="jiangailv(\'nr'+a+'\')">'+
		'<p><input lang="gailv" name="radiogroup'+a+'" type="radio" onclick="ongailvListener()"> 获得红包概率&nbsp;'+
        '<input class="con-email3" name="fenduanGaiLv" > %&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<input lang="fenduan" name="radiogroup'+a+'" type="radio" checked="checked" onclick="onfenduanListener()"> 顾客支付金额&nbsp;'+
        '<input class="con-email3" name="zhifujineMin" value="">  元 —— '+
        '<input class="con-email3" name="zhifujineMax" value=""> 元 </p>'+
       
        '<input type="hidden" name="detailNewIds" value="'+a+'" class=""/>'
		+ '</p></div>');
	}
	a+=1;
	
}

function jiangailv(pid){
	$("#"+pid).remove();
}


function quanxuan(){
	var a = document.getElementsByName("sid");
	var b = document.getElementById("quanx");
	for (var c = 0; c < a.length; c++) {
		a[c].checked = b.checked;
	}
}

function tcquxiao(){
	var a = document.getElementsByName("sid");
	var b = document.getElementById("quanx");
	b.checked = false;
	for (var c = 0; c < a.length; c++) {
		a[c].checked = false;
	}
}

function tcqueding(){
	var a = document.getElementsByName("sid");
	var storeIds = '';
	var storeNames = '';
	for (var c = 0; c < a.length; c++) {
		if (a[c].checked) {
			storeIds = storeIds + a[c].id + ",";
			storeNames = storeNames + a[c].value + ",";
		}
	}
	if (storeIds.length > 0) {
		var ids = storeIds.substring(0, storeIds.length-1);
		var names = storeNames.substring(0,storeNames.length-1);
		
		$("#storeIds").val(ids);
		$("#storeNames").val(names);
	}else{
		$("#storeIds").val('');
		$("#storeNames").val('');
	}
	document.getElementById('shangjielist').style.display='none';
	
}

function getstorename(){
	 var storeid = $("#ssid").val();
	 $.ajax({
			async : false,
			type : "POST",
			url : '/managermall/systemmall/rebate/getStoreName.do',
			data : {"storeId" : storeid},
			success : function(data) {
				$("#storename").html(data);
				if (data.length >0) {
					$("#ssid1").val(storeid);
					$("#storeName").val(data);
				}
			}
		});
	}
function addredpack(){
	var storename =  $("#storename").html();
	var storename1 =  $("#storename").text();

	if (storename.length >0) {
		redpackfrom.submit();
	}else{
		alert("请先确定商家ID是否存在");
	}
}
