$(function() {

	$("#exchangeIntegral").blur(function(){
		$("#exchangeIntegral").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入兑换积分</label>');
		var val = $("#exchangeIntegral").val().trim();
		if (val == "") {
			$("#withdrawsMoney").after(html);
		}
	});

	$("#form1").submit(function() {
		
		$("#exchangeIntegral").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入兑换积分</label>');
		var val = $("#exchangeIntegral").val().trim();
		if (val == "") {
			$("#exchangeIntegral").after(html);
		}
		if($("#form1 label[class=one1]").length > 0)
			return false;
		
		var exchangeIntegral = $("#exchangeIntegral").val();
		var token = $("#token").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/funds/account/exchange.do',
			data : {"token" :token,
				"exchangeIntegral" : exchangeIntegral},
		    success : function(data) {
				if(data.message == "兑换成功!"){
					alert(data.message);
					window.location.href = '/managermall/seller/funds/toExchange.do';
				}else
					alert(data.message);
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
});

function setTab(m, n) {
	var tli = document.getElementById("menu" + m).getElementsByTagName("li");
	var mli = document.getElementById("main" + m).getElementsByTagName("ul");
	for (i = 0; i < tli.length; i++) {
		tli[i].className = i == n ? "hover" : "";
		mli[i].style.display = i == n ? "block" : "none";
	}
}
