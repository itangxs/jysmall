$(function() {
	$("#lotteryBtn").rotate({
		   bind: 
			 {
				click: function(){
					requestChoujiang();
				}
			 }
		});
	
	$(".rulesbox h1").toggle(function(){
		 $(this).next(".rules_text").animate({height: 'toggle', opacity: 'toggle'}, "slow");
	   },function(){
	     $(this).next(".rules_text").animate({height: 'toggle', opacity: 'toggle'}, "slow");
	   });
});

// 是否正在抽
var ischouing = false;
var ajaxing = true;
var rotateing = true;
var ajastatus = 0;
function requestChoujiang(){
	if (!ischouing) {
		//一次抽奖开始
		ischouing = true;
		var b = "/user/fqcardactivity/acard_raffle.do";
		var recordId = $("#recordId").val();
		var userId = $("#userId").val();
		var acardId = $("#acardId").val();
		var a = "recordId=" + recordId + "&userId="+userId + "&acardId="+acardId;
		$.ajax({
			async : true,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "-1001") {
					ajaxing = false;
					ajastatus = 5;
				}else if (g == "-1000") {
					// 已经抽过奖了
					ajaxing = false;
					ajastatus = 1;
				} else if (g == "fail") {
					// 抽奖异常
					ajaxing = false;
					ajastatus = 2;
				}else {
					// 抽到奖了
					ajaxing = false;
					// 抽完赋值
					var prizeName = g.prizeName;
					$("#zhongjiangName").val(prizeName);
					$("#zhongjiangImage").attr("src",g.prizeImage);
					
					$("#duihuanImage").attr("src",g.prizeImage);
					$("#duihuanName").val(prizeName);
					
					$("#kabaoImage").attr("src",g.prizeImage);
					$("#kabaoName").val(prizeName);
					
					$("#zjprizeId").val(g.id);
					$("#dhprizeId").val(g.id);
					$("#kbprizeId").val(g.id);
					ajastatus = 3;
				}
				if (!rotateing) {
					goViewStatus(ajastatus);
				}
			},
			error : function() {
				ajaxing = false;
				ajastatus = 4;
				if (!rotateing) {
					goViewStatus(ajastatus);
				}
			}
		});
		ajaxing = true;
		
		var data = [1,2,3,4]; //返回的数组
			data = data[Math.floor(Math.random()*data.length)];
		if(data==1){
			rotateFunc(1,157,'');
		}
		if(data==2){
			rotateFunc(2,247,'');
		}
		if(data==3){
			rotateFunc(3,22,'');
		}
		if(data==4){
			rotateFunc(4,300,'');
		}
	}
}

var timeOut = function(){  //超时函数，用作出问题
	$("#lotteryBtn").rotate({
		angle:0, 
		duration: 5000, 
		animateTo: 1440, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，1440是因为我要让它转4圈，就是360*6得来的
		callback:function(){
			alert('亲！没有中奖哦！！');
		}
	}); 
};

var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
	$('#lotteryBtn').stopRotate();
	$("#lotteryBtn").rotate({
		angle:0, 
		duration: 5000, 
		animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
		callback:function(){
			rotateing = false;
			if (!ajaxing) {
				goViewStatus(ajastatus);
			}
		}
	}); 
	rotateing = true;
};

function goViewStatus(resulttag) {
	if (resulttag == 1) {
		document.getElementById('yichouguo').style.display='block';
		document.getElementById('fade').style.display='block';
	}else if (resulttag == 2) {
		alert("抽奖异常，或非法请求");
	}else if (resulttag == 3) {
		document.getElementById('zhongjiang').style.display='block';
		document.getElementById('fade').style.display='block';
	}else if (resulttag == 4) {
		alert("服务器忙");
	}else if (resulttag == 5) {
		alert("该抽奖已兑换");
	}
	// 一次抽奖结束
	ajastatus = 0;
	var ajaxing = true;
	var rotateing = true;
	ischouing = false;
}

function helpchou(id) {
	if (id == 1) {
		document.getElementById('zhongjiang').style.display='none';
	}
	if (id == 2) {
		document.getElementById('yichouguo').style.display='none';
	}
	document.getElementById('fenxiang').style.display='block';
	document.getElementById('fade').style.display='block'
}

// 中奖兑换
function zjduihuan(){
	var recordId = $("#recordId").val();
	var acardId = $("#acardId").val();
	var storeId = $("#storeId").val();
	var prizeId = $("#dhprizeId").val();
	var b = "/user/fqcardactivity/acard_exchange.do";
	var a = "userId="+userId+"&recordId="+recordId+"&prizeId="+prizeId+"&storeId="+storeId;
	$.ajax({
		async : false,
		type : "post",
		url : b,
		data : a,
		success : function(g) {
			if (g == 0) {
				document.getElementById('zhongjiang').style.display='none';
				document.getElementById('fade').style.display='none';
				alert("该奖品已兑换过！");
			}else if (g == 1) {
				showguanzhu(1);
			}else if (g == 2) {
				showduihuan(1);
			}else {
				document.getElementById('zhongjiang').style.display='none';
				document.getElementById('fade').style.display='none';
				alert("兑换异常,或非法请求");
			}
		},
		error : function() {
			document.getElementById('zhongjiang').style.display='none';
			document.getElementById('fade').style.display='none';
			alert("服务器忙");
		}
	});
}

function listduihuan(mprizeId){
	var recordId = $("#recordId").val();
	var acardId = $("#acardId").val();
	var storeId = $("#storeId").val();
	var b = "/user/fqcardactivity/acard_exchange.do";
	var a = "userId="+userId+"&recordId="+recordId+"&prizeId="+mprizeId+"&storeId="+storeId;
	$.ajax({
		async : false,
		type : "post",
		url : b,
		data : a,
		success : function(g) {
			if (g == 0) {
				alert("该奖品已兑换过！");
			}else if (g == 1) {
				showguanzhu(0);
			}else if (g == 2) {
				showduihuan(0);
			}else {
				alert("兑换异常,或非法请求");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function showduihuan(showType) {
	if (showType == 1) {
		document.getElementById('zhongjiang').style.display='none';
	}
	document.getElementById('duihuan').style.display='block';
	document.getElementById('fade').style.display='block';
}

// 提示关注层
function showguanzhu(showType){
	if (showType == 1) {
		document.getElementById('zhongjiang').style.display='none';
	}
	document.getElementById('weiguanzhu').style.display='block';
	document.getElementById('fade').style.display='block';
}

function shourukabao() {
	document.getElementById('duihuan').style.display='none';
	document.getElementById('mashangduihuan').style.display='block';
}


