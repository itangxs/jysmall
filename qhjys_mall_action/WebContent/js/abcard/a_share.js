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
var ajastatus = 0;
function requestChoujiang(){
	if (!ischouing) {
		//一次抽奖开始
		ischouing = true;
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
			//转完了请求服务器
			choujiangFunc();
		}
	}); 
};

function choujiangFunc() {
	var b = "/user/fqcardactivity/acard_share_raffle.do";
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
				ajastatus = 5;
			}else if (g == "-1000") {
				// 已经抽过奖了
				ajastatus = 1;
			} else if (g == "fail") {
				// 抽奖异常
				ajastatus = 2;
			}else {
				// 抽到奖了
				// 抽完赋值
				var prizeName = g.prizeName;
				$("#zhongjiangName").val(prizeName);
				$("#zhongjiangImage").attr("src",g.prizeImage);
				ajastatus = 3;
			}
			goViewStatus(ajastatus);
		},
		error : function() {
			ajastatus = 4;
			goViewStatus(ajastatus);
		}
	});
}

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
	ischouing = false;
}

function gojoin() {
	var acardId = $("#acardId").val();
	var storeId = $("#storeId").val();
	var orderId = $("#orderId").val();
	window.location.href = "/user/fqcardactivity/a_raffle.do?acardId="+acardId+"&storeId="+storeId+"&orderId="+orderId+"&type=1";
	
}

