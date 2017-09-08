$(function() {
	$("#withdrawsMoney").blur(function(){
		$("#withdrawsMoney").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入提现金额</label>');
		var val = $("#withdrawsMoney").val().trim();
		if (val == "") {
			$("#withdrawsMoney").after(html);
		}
	});
	$("#payCode").blur(function(){
		$("#payCode").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入支付密码</label>');
		var val = $("#payCode").val().trim();
		if (val == "") {
			$("#payCode").after(html);
		}
	});
	$("#form1").submit(function() {
		$("#form1 label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px;">请输入银行卡号</label>');
		var val = $("input[name='bankId']:checked").val();
		if (val == undefined) {
			$("#bank").after(html);
		}
		$("#withdrawsMoney").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入提现金额</label>');
		var val = $("#withdrawsMoney").val().trim();
		if (val == "") {
			$("#withdrawsMoney").after(html);
		}
		$("#payCode").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入支付密码</label>');
		var val = $("#payCode").val().trim();
		if (val == "") {
			$("#payCode").after(html);
		}
		if($("#form1 label[class=one1]").length > 0)
			return false;
		var payCode = $("#payCode").val();
		var withdrawsMoney = $("#withdrawsMoney").val();
		var bankId = $("input[name='bankId']:checked").val();
		var token = $("#token").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/withdraws.do',
			data : {"payCode" : md5(payCode),
					"withdrawsMoney" : withdrawsMoney,
					"bankId":bankId,
					"token" :token},
			success : function(data) {
				if(data.message == "提现成功"){
					alert("提现申请成功");
					window.location.href = '/managermall/account/rechargeList.do';
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
