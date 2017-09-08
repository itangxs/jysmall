function woyaokanjia(){
	alert("您已经发起砍价活动,找朋友帮忙砍价!");
}

function zhaorenbangm(){
	$("#fenxiangpyqdiv").show();
}
function gbzhaorenbangm(){
	$("#fenxiangpyqdiv").hide();
}

function mashangduihuan(dangqianjia){
	if (dangqianjia == 0) {
		$("#duihuanyzmdiv").show();
	}else{
		alert("价格砍到0元才能兑换,继续加油!");
	}
}
function guanbimsdh(){
	$("#duihuanyzmdiv").hide();
}

function woyaocanyu(openid){
	$.ajax({
		url: "http://www.jysmall.com/bargain/woyaocanyu.do",
		type: "POST",
		data: {'openid':openid},
		success: function(data){
			if (data=="guanzhu") {
				window.location.href="/bargain/getBargain.do";
			}else{
				$("#woyaocydiv").show();
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) 
    	{ 
    		if (4==XMLHttpRequest.readyState){
    			var data  = xmlHttpRequest.responseText; 
    			if (data=="guanzhu") {
    				window.location.href="/bargain/getBargain.do";
    			}else{
    				$("#woyaocydiv").show();
    			}
    		}else{
    			alert("服务器忙");
    		}
    	}
	});
}
function guanbiwoyaocy(){
	$("#woyaocydiv").hide();
}
function guanbibangtkj(){
	$("#bangtkjdiv").hide();
}
function bangtakanjia(userBargainId1){
	//alert('userBargainId'+userBargainId1);
	var request = $.ajax({
		  url: "http://www.jysmall.com/bargain/bangtakanjia.do",
		  method: "POST",
		  data: {userBargainId : userBargainId1},
		  dataType: "html"
		});
		 
		request.done(function(data) {
			if (data=="noguanzhu") {
				$("#bangtkjdiv").show();
			}else if(data == "already"){
				alert("你已帮他砍价,分享帮助他砍价!");
			}else if(data == "already1"){
				alert("你已经砍过价了,找朋友帮忙砍价!");
			}else{
				alert(data);
				window.location.reload();
			}
		});
		 
		request.fail(function(jqXHR, textStatus) {
			if (4==jqXHR.readyState && jqXHR.status==200){
				
			}else {
				alert("readyState"+jqXHR.readyState);
				alert("status"+jqXHR.status);
			}
		  alert( "Request failed: " + textStatus );
		});
	
	/*$.ajax({
		url: "/bargain/bangtakanjia.do",
		type: "POST",
		data: {'userBargainId':userBargainId},
		success: function(data){
			if (data=="noguanzhu") {
				$("#bangtkjdiv").show();
			}else if(data == "already"){
				alert("你已帮他砍价,分享帮助他砍价!");
			}else if(data == "already1"){
				alert("你已经砍过价了,找朋友帮忙砍价!");
			}else{
				alert(data);
				window.location.reload();
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) 
    	{ 
			alert("error");
			alert("XMLHttpRequest.status"+XMLHttpRequest.status);
    		if (4==XMLHttpRequest.readyState && XMLHttpRequest.status==200){
    			alert("error1");
    			var data = XMLHttpRequest.responseText; 
    			alert(data);
    			if (data=="noguanzhu") {
    				$("#bangtkjdiv").show();
    			}else if(data == "already"){
    				alert("你已帮他砍价,分享帮助他砍价!");
    			}else if(data == "already1"){
    				alert("你已经砍过价了,找朋友帮忙砍价!");
    			}else{
    				alert(data);
    				window.location.reload();
    			}
    		}else{
    			alert("服务器忙");
    		}
    	}
	});*/
}