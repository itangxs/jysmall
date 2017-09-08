function quxiao(){
	window.location.href = "/managermall/systemmall/ad/hongbao_list.do";
}

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
			'<label class="con-email120" for="con-email130" ></label>'+
			'<input class="con-email9" name="startHourNew" >&nbsp;点 '+
			'<input class="con-email9" name="startMinuteNew" value="00">&nbsp;分&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;'+
			'<input class="con-email9" name="endHourNew" >&nbsp;点 '+
			'<input class="con-email9" name="endMinuteNew" value="00">&nbsp;分'+
			' <label for="select">&nbsp;&nbsp;&nbsp;</label><input type="button" value="-删除时段" class="submitblue" onclick="jianshiduan(\'tt'+sd+'\')">'+
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
		$("#fendgl").append('<div class="nr" id="nr'+a+'"> <p>'+'<input class="con-email3" name="subMoneyNewMin" value="">元 ——  '+
    			'<input class="con-email3" name="subMoneyNewMax" value=""> 元（精确到0.01元）'+
    			' <input type="button" value="-删除概率" class="submitblue" onclick="jiangailv(\'nr'+a+'\')">'+
    			'<p><input lang="gailv" name="radiogroup'+a+'" type="radio" checked="checked" onclick="ongailvListener()"> 分段概率&nbsp;'+
        '<input class="con-email3" name="fenduanGaiLvNew" value=""> %&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<input lang="fenduan" name="radiogroup'+a+'" type="radio" onclick="onfenduanListener()"> 顾客支付金额&nbsp;'+
        '<input class="con-email3" name="zhifujineNewMin" >  元 —— <input class="con-email3" name="zhifujineNewMax" > 元 （精确到0.01元）</p>'+
        '<input type="hidden" name="detailNewIds" value="'+a+'" class=""/>'
		+ '</p></div>');
	}else if (type == 2) {
		$("#fendgl").append('<div class="nr" id="nr'+a+'"> <p>'+'<input class="con-email3" name="subMoneyNewMin" value="">元 ——  '+
				'<input class="con-email3" name="subMoneyNewMax" value=""> 元（精确到0.01元）'+
				' <input type="button" value="-删除概率" class="submitblue" onclick="jiangailv(\'nr'+a+'\')">'+
		'<p><input lang="gailv" name="radiogroup'+a+'" type="radio" onclick="ongailvListener()"> 分段概率&nbsp;'+
        '<input class="con-email3" name="fenduanGaiLvNew" > %&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<input lang="fenduan" name="radiogroup'+a+'" type="radio" checked="checked" onclick="onfenduanListener()"> 顾客支付金额&nbsp;'+
        '<input class="con-email3" name="zhifujineNewMin" value="">  元 —— '+
        '<input class="con-email3" name="zhifujineNewMax" value=""> 元 （精确到0.01元）</p>'+
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
