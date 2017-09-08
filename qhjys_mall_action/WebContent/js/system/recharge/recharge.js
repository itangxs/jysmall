$(function(){
	$("#storeId").blur(function(){
		var storeid = $("#storeId").val();
		if(isNaN(storeid)||storeid == ''||storeid<=0){
			alert("请输入正确的店铺ID")
			return false;
		}

		 $.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/rebate/getStoreName.do',
				data : {"storeId" : storeid},
				success : function(data) {
					if (data=="") {
						$("#storename").val("此店铺不存在,请确认店铺ID");
					}else{
						$("#storename").val(data);
					}
				}
			});
	});
})

function chongzhi(){
	 $.ajax({
			async : false,
			type : "POST",
			url : '/managermall/systemmall/integral/recharge.do',
			data : {"storeId" : $("#storeId").val(),"money":$("#rechargeMoney").val(),"remark":$("#remark").val()},
			success : function(data) {
				if (data=="充值成功") {
					$("#storeId").val("");
					$("#storename").val("");
					$("#rechargeMoney").val("");
					$("#remark").val("");
					document.getElementById('tanchu').style.display='none';
					document.getElementById('tanchu02').style.display='block';
				}else{
					alert(data);
				}
			}
		});
}

function querenrecharge(){
	if ($("#storename").val()=="" || $("#storename").val()=="此店铺不存在,请确认店铺ID") {
		alert("请确认店铺ID是否正确");
		return false;
		
	}
	if(isNaN($("#rechargeMoney").val())||$("#rechargeMoney").val() == ''||$("#rechargeMoney").val()<=0){
		alert("请输入正确金额");
		return false;
	}
	$("#qrstoreId").html($("#storeId").val());
	$("#qrstoreName").html($("#storename").val());
	$("#qrrechargeMoney").html("¥"+$("#rechargeMoney").val());
	$("#qrremark").html($("#remark").val());
	document.getElementById('fade').style.display='block';document.getElementById('tanchu').style.display='block';
}

function fanhuixiugai(){
	document.getElementById('fade').style.display='none';document.getElementById('tanchu').style.display='none';
}