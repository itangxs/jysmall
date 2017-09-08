$(function() {
	$("#bankId").blur(function(){
		$("#bankId").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入银行卡号</label>');
		var val = $("#bankId").val().trim();
		if (val == "") {
			$("#bankId").after(html);
		}
	});
	$("#withdrawsMoney").blur(function(){
		$("#withdrawsMoney").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入提现金额</label>');
		var val = $("#withdrawsMoney").val().trim();
		if (val == "") {
			$("#withdrawsMoney").after(html);
		}
	});
//	$("#payCode").blur(function(){
//		$("#payCode").next().remove();
//		var html = $('<label class="one1" style="width:180px;">请输入支付密码</label>');
//		var val = $("#payCode").val().trim();
//		if (val == "") {
//			$("#payCode").after(html);
//		}
//	});
	$("#form1").submit(function() {
		$("#form1 label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px;">请输入银行卡号</label>');
		var val = $("#bankId").val().trim();
		if (val == "") {
			$("#bankId").after(html);
		}
		$("#withdrawsMoney").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入提现金额</label>');
		var val = $("#withdrawsMoney").val().trim();
		if (val == "") {
			$("#withdrawsMoney").after(html);
		}
//		$("#payCode").next().remove();
//		var html = $('<label class="one1" style="width:180px;">请输入支付密码</label>');
//		var val = $("#payCode").val().trim();
//		if (val == "") {
//			$("#payCode").after(html);
//		}
		if($("#form1 label[class=one1]").length > 0)
			return false;
		//var payCode = $("#payCode").val();
		var withdrawsMoney = $("#withdrawsMoney").val();
		var bankId = $("#bankId").val();
		var token = $("#token").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/funds/account/withdraws.do',
			data : {
				"withdrawsMoney" : withdrawsMoney,
				"bankId":bankId,
				"token" :token},
		    success : function(data) {
				if(data.message == "提现成功"){
					alert(data.message);
					window.location.href = '/managermall/seller/funds/toWithdraws.do';
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
	$("#bankId").on("change",function(){
		$("#sname").html($("#bankId option:selected").attr("lang"));
	});
	$("#withdrawsMoney").on("change",function(){
		var withdrawsMoney = $("#withdrawsMoney").val();;
		var scale= $("#scale").val();
		$("#realmoney").html("¥"+(parseFloat(withdrawsMoney)/scale).toFixed(2));
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
