
$(function() {
	$("#channelForm").submit(function() {
		if ($("#totalMoney").val()=='') {
			alert("请输入订单交易金额");
			return false;
		}
		if ($("#totalNum").val()=='') {
			alert("请输入支付订单数量");
			return false;
		}
		if ($("#type").val()==2) {
			if ($("#computeCycle").val()=='') {
				alert("请输入核算周期");
				return false;
			}
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/systemmall/channel/addEffectiveStore.do',
			data : $(this).serialize(),
			success : function(data) {
				alert(data.message);
				if(data.message == "SUCCESS"){
					alert("添加成功");
					window.location.reload();
				}else{
					alert("添加失败");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (4==XMLHttpRequest.readyState){
					alert("添加成功");
					window.location.reload();
	    		}else{
	    			alert("error");
					return false;
	    		}
				
			}
		});
		return false;
	});
});