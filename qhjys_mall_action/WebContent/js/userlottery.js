
$(function(){
	$.ajax({
		url: "/lottery/listLotteryInfo.do",
		type: "POST",
		data: {'userLotteryId':$("#userLotteryId").val()},
		success: function(data){
			var lis = data.lis;
			var cis = data.cis;
			var san = 0;
			var er = 0;
			var yi = 0;
			var te = 0;
			for ( var nu in lis) {
				var li = lis[nu];
				if (li.rank == 4) {
					$("#sandengjiangul").append("<li><img src="+li.portrait+"></li>");
					san++;
				}
				if (li.rank == 3) {
					$("#erdengjiangul").append("<li><img src="+li.portrait+"></li>");
					er++;
				}
				if (li.rank == 2) {
					$("#yidengjiangul").append("<li><img src="+li.portrait+"></li>");
					yi++;
				}
				if (li.rank == 1) {
					$("#tedengjiangul").append("<li><img src="+li.portrait+"></li>");
					te++;
				}
			}
			$("#sandengjiangp").append("已有"+san+"个好友<br>帮你抽到");
			$("#erdengjiangp").append("已有"+er+"个好友<br>帮你抽到");
			$("#yidengjiangp").append("已有"+yi+"个好友<br>帮你抽到");
			$("#tedengjiangp").append("已有"+te+"个好友<br>帮你抽到");
			for (var cu in cis) {
				var ci = cis[cu];
				$("#sandengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#sandengjiangbut").attr("onclick","");
				$("#erdengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#erdengjiangbut").attr("onclick","");
				$("#yidengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#yidengjiangbut").attr("onclick","");
				$("#tedengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#tedengjiangbut").attr("onclick","");
				if (ci.rank == 4) {
					$("#sandengjiangbut").attr("src","/images/weixin/yiduihuan.png");
					$("#sandengjiangbut").attr("onclick","showduihuanjuan('"+ci.id+"');");
				}
				if (ci.rank == 3) {
					$("#erdengjiangbut").attr("src","/images/weixin/yiduihuan.png");
					$("#erdengjiangbut").attr("onclick","showduihuanjuan('"+ci.id+"');");
				}
				if (ci.rank == 2) {
					$("#yidengjiangbut").attr("src","/images/weixin/yiduihuan.png");
					$("#yidengjiangbut").attr("onclick","showduihuanjuan('"+ci.id+"');");
				}
				if (ci.rank == 1) {
					$("#tedengjiangbut").attr("src","/images/weixin/yiduihuan.png");
					$("#tedengjiangbut").attr("onclick","showduihuanjuan('"+ci.id+"');");
				}
			}
			if (san == 0) {
				$("#sandengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#sandengjiangbut").attr("onclick","");
			}
			if (er == 0) {
				$("#erdengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#erdengjiangbut").attr("onclick","");
			}
			if (yi == 0) {
				$("#yidengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#yidengjiangbut").attr("onclick","");
			}
			if (te == 0) {
				$("#tedengjiangbut").attr("src","/images/weixin/weiduihuan.png");
				$("#tedengjiangbut").attr("onclick","");
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) 
    	{ 
    	}
	});
});
function duihuanjuan(userLotteryId,rank){
	if(confirm("确定兑换该券吗? 一个商家只能兑换一张券哦!")){
		window.location.href="/lottery/exchange.do?userLotteryId="+userLotteryId+"&rank="+rank;
	}else{
		return null;
	}
}
function showduihuanjuan(id){
	window.location.href="/lottery/showCoupons.do?id="+id;
}
function fenxiangpyq(){
	$("#fenxiangpyqdiv").show();
}
function gbfenxiangpyq(){
	$("#fenxiangpyqdiv").hide();
}
function choujiangguize(){
	$("#choujiangguizediv").show();
}
function gbchoujiangguize(){
	$("#choujiangguizediv").hide();
}
function caipingzhanshi(){
	$("#caipingzhanshidiv").show();
}
function gbcaipingzhanshi(){
	$("#caipingzhanshidiv").hide();
}
function woyaochanyu(){
	$("#woyaochanyudiv").show();
}
function gbwoyaochanyu(){
	$("#woyaochanyudiv").hide();
}
function showmessage(message,caozuo){
	$("#messagetanchup").html(message);
	$("#messagetanchu").show();
	$("#messagetanchua").attr("onclick",caozuo+"()");
}
function closemessage(){
	$("#messagetanchup").html("");
	$("#messagetanchu").hide();
}
