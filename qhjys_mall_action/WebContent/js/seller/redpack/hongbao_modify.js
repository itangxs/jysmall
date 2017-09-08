function quxiao(){
	window.location.href = "/managermall/seller/redpack/redpacklist.do";
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
	$("#jinee").before('<p class="clearfix"  id="tt'+sd+'"> '+
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


var b = 100;
function jiazhifu() {
	$("#fendgl").append('<p class="clearfix" id="ss'+b+'"> '+
			'<label for="select">&nbsp;</label>'+
			'<input class="con-email7" name="zhifujineNewMin" >&nbsp;元 '+
			'—'+
			'<label for="select">&nbsp;</label>'+
			'<input class="con-email7" name="zhifujineNewMax">&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;获得红包金额&nbsp;&nbsp;'+
			'<input class="con-email7" name="subMoneyNewMin" >&nbsp;元 '+
			'—'+
			'<label for="select">&nbsp;</label>'+
			'<input class="con-email7" name="subMoneyNewMax">&nbsp;元 (精确到0.01元)'+
			' <label for="select">&nbsp;&nbsp;</label>'+
				'<input type="button" value="-删除概率" class="submitblue" onclick="jianzhifu(\'ss'+b+'\')">'+
				' <input type="hidden" name="detailNewIds" value="'+b+'" class=""/></p>');
b+=1;
}

function jianzhifu(zfid){
	 $("#"+zfid).remove();
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
