function topay(){
	var yongcantime = $("#yongcantime").val();
	var yongcandate = $("#hello1").val();
	var peopleNum = $("#peopleNum").val();
	var contacts = $("#contacts").val();
	var userId = $("#userId").val();
	var contactsnum = $("#contactsnum").val();
	var contactssex = $('input[name="sample-radio"]:checked ').val();
	var nums = $("#nums").val();
	var ids = $("#ids").val();
	var storeId = $("#storeId").val();
	
	var deskNo = $("#deskNo").val();
	if (deskNo=="根据您的桌号填写,在线预约无须填写") {
		deskNo="";
	}else if(deskNo.length>10){
		alert("桌号长度超出限制");
		return false;
	}
	if (peopleNum == null || peopleNum=="") {
		alert("请选择用餐人数");
		return false;
	}
	if (yongcandate == null || yongcandate=="") {
		alert("请选择用餐日期");
		return false;
	}
	if (yongcantime == null || yongcantime=="") {
		alert("请选择用餐时间");
		return false;
	}
	var date1 = new Date(yongcandate.replace(/-/gm,"/")+" "+yongcantime+":00");
	var date2 = new Date();
	if (date1.getTime()<date2.getTime()) {
		alert("用餐日期时间不能早于当前时间");
		return false;
	}
	if (contacts == null || contacts=="") {
		alert("请填写联系人");
		return false;
	}
	if (contactsnum == null || contactsnum=="") {
		alert("请填写联系电话");
		return false;
	}
	var isMobile=/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
	if (!isMobile.test(contactsnum)) {
		alert("请填写正确的手机号码");
		return false;
	}
	$.ajax({
	    	type:'post',//可选get
	    	url:"/user/fqorder/confrimOrder.do",//这里是接收数据的后台程序
	    	data:{"ids":ids,"nums":nums,"preordainDate":yongcandate,"preordainTime":yongcantime,"contacts":contacts,"contactsSex":contactssex,
	    		"phoneNum":contactsnum,"storeId":storeId,"peopleNum":peopleNum.replace("位",""),"userId":userId,"deskNo":deskNo},//传给后台的数据，多个参数用&连接
	    	success:function(data){
	    		if (data.code=='success'){
	    			$.cookie("productcookie"+storeId, null,  {path:'/'}); 
	    			$.cookie("productNum"+storeId, null, {path:'/'}); 
	    			$.cookie("producttotalprice"+storeId, null, {path:'/'}); 
	    			window.location.href="/user/fqorder/toOrderDetail.do?orderId="+data.orderId;
	    		}
				if (data.code=='error'){
					if (data.errorcode == -3) {
						alert("部分菜品库存不足请重新选择");
					}else{
						alert("提交订单失败");
					}
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    		alert("提交订单出现异常");
	    	}
	    });
}
$(function(){
	$(".weishu li").click(function(){
		$(".weishu li").removeClass("current");
		$(this).addClass("current");
		if ($(this).attr("id") !="btn") {
			$("#peopleNum").val($(this).html());
		}
	});
	$("#deskNo").focus(function(){
		var deskNo = $("#deskNo").val();
		if (deskNo=="根据您的桌号填写,在线预约无须填写") {
			$("#deskNo").val("");
		}
	}).blur(function(){
		var deskNo = $("#deskNo").val();
		if (deskNo=="") {
			$("#deskNo").val("根据您的桌号填写,在线预约无须填写");
		}
	});
	$(".date li").click(function(){
		$(".date li").removeClass("current");
		$(this).addClass("current");
		$("#hello1").val("2016-"+$(this).children("p").html());
	});
	$(".shijian li").click(function(){
		$(".shijian li").removeClass("current");
		$(this).addClass("current");
//		alert($(this).html())
		$("#yongcantime").val($(this).html());
	});
	$(".datebg").click(function(){
		$(".date li").removeClass("current");
	});
});