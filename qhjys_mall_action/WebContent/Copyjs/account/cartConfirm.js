var changCartNum = function(prodId,storeId, prodNum, $el) {
	var $td = $el.closest("td.td100");
	var unitp = $td.find("input[name=unitp]").val();
	var total = $td.next().find("label.total");
	var totalmon = $td.next().next().find("label.totalmon");
	var money = $td.find("input[name=money]");
	var cartId = $td.find("input[name=cartId]").val();
	var re = /^[0-9]*[1-9][0-9]*$/;
	if (!re.test(prodNum))
		return false;
	$.ajax({
		async : true,
		type : "POST",
		url : '/managermall/account/changCartNum.do',
		data : {
			createTime : cartId,
			prodId : prodId,
			storeId : storeId,
			prodNum : prodNum
		},
		success : function(data) {
			if (data == null || data == "")
				location.href = "/account/login.do";
			if (data) {
				total.text(parseFloat(prodNum * unitp));
				totalmon.text((parseFloat(prodNum * unitp)/20).toFixed(2));
				money.val(parseFloat(prodNum * unitp));
				var sum = 0.00;
				$("#cartTable input[name=money]").each(function() {
					sum += parseFloat(this.value);
				});
				$("#ordTotal").text(sum);
				$el.val(prodNum);
				
			} else
				alert("修改商品数量异常，请重新登录！");
		}
	});
};

$(function() {
	var sum = 0.00;
	$("#cartTable input[name=money]").each(function() {
		sum += parseFloat(this.value);
	});
	$("#ordTotal").html(sum);
	$("input[name=add],input[name=sub]").on("click", function(e) {
		var parent = $(this).parent();
		var prodId = parent.find("input[name=prodId]").val();
		var storeId = parent.find("input[name=storeId]").val();
		var unitPrice = parseInt(parent.find("input[name=unitPrice]").val());
		var stock = parseInt(parent.find("input[name=stock]").val());
		var cartNum = parseInt(parent.find("input[name=cartNum]").val());
		if (this.value == "+") {
			cartNum++;
			if (cartNum > unitPrice) {
				alert("飞券数量不能大于商品价格!");
				return false;
			}
			if (stock && 1 > stock) {
				alert("该商品库存不足!");
				return false;
			}
		} else {
			cartNum--;
			if (cartNum < 1) {
				alert("商品数量必须大于0!");
				return false;
			}
		}
		changCartNum(prodId,storeId, cartNum, parent.find("input[name=cartNum]"));
	});

	$('div.goumaishuliang input[name=cartNum]').on('change', function() {
		var parent = $(this).parent();
		var prodId = parent.find("input[name=prodId]").val();
		var unitPrice = parseInt(parent.find("input[name=unitPrice]").val());
		var stock = parseInt(parent.find("input[name=stock]").val());
		var cartNum = parseInt(parent.find("input[name=cartNum]").val());
		if ( cartNum > unitPrice) {
			alert("飞券数量不能大于商品价格!");
			return false;
		}
		if (stock && 1 > stock) {
			alert("该商品库存不足!");
			return false;
		}
		if (cartNum < 1) {
			alert("商品数量必须大于0!");
			return false;
		}
		changCartNum(prodId, cartNum, parent.find("input[name=cartNum]"));
	});

	$('li', '#delivery').on('click', function() {
		$(this).siblings().removeClass('licurrent').find('input').removeAttr('checked');
		$(this).addClass('licurrent').find('input').prop("checked", true);
	});

	$('#coupon').on('click', function() {
		if ($(this).prop("checked"))
			$('#useCoupon').css('display', 'inline');
		else
			$('#useCoupon').css('display', 'none');
	});

	$('#couponId').on('change', function() {
		var sum = $("#ordTotal").html();
		var amount = parseInt($('#couponId>option:selected').prop('lang'));
	});

	$('#getCaptcha').on('click', function() {
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		var phone = $("#phone").val();
		if (!reg.test(phone)) {
			alert('手机号码格式错误！');
			return false;
		}
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendSMSCaptcha.do',
			data : {phone : phone},
			success : function(data) {
				if (data == "0000")
					timeWait($el);
				else {
					if (data == "0001")
						alert("手机号码格式错误！");
					else
						alert("发送短信异常！");
					$el.removeAttr("disabled");
				}
			}
		});
	});
	
	$("#phone").blur(function(){
		$("#phone").next().remove();
		var html = $('<label class="one1" style="width:180px;color:red;">请输入手机号码</label>');
		var val = $("#phone").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;color:red;">' + str + '</label>');
			$("#phone").after(html);
		}
	});
	
	$("#captcha").blur(function(){
		$("#captcha").next().remove();
		if ($("#captcha").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;color:red;">请输入动态码</label>');
			$("#captcha").after(html);
		}
	});
	$("#cartForm").submit(function() {
		var addrId = $("input[name='address']:checked").val();
		var token = $("input[name='token']").val();
		var phone = $("#phone").val();
		var captcha = $("#captcha").val();
		if($("#phoneNum").val() == "" || $("#phoneNum").val() == null){
			$("#tishinr label[class=one1]").remove();
			var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
			if ($("#phone").val().trim() == "") {
				var html = $('<label class="one1" style="width:180px;color:red;">请输入手机号码</label>');
				$("#phone").after(html);
				alert("请填写手机号码");
			} else if (!isMobile.test($("#phone").val())) {
				var str = "请输入正确的手机号";
				var html = $('<label class="one1" style="width:180px;color:red;">' + str + '</label>');
				$("#phone").after(html);
			}
			if ($("#captcha").val().trim() == "") {
				var html = $('<label class="one1" style="width:180px;color:red;">请输入动态码</label>');
				$("#captcha").after(html);
			}
		}
		if($("#tishinr label[class=one1]").length > 0)
			return false;
		var couponId = null;
		if ($('#coupon').prop("checked"))
			couponId = $('#couponId').val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/order/addOrder.do',
			data : {"addrId" : addrId,
					"token" : token,
					"captcha" : captcha,
					"phone" : phone,
					"couponId" : couponId
					},
			success : function(data) {
				if(data == "tokenError"){
					alert("表单重复提交");
				}else if(data == "codeError"){
					alert("验证码错误");
				}else if(!isNaN(data)){
					alert("提交订单成功");
					window.location.href = '/managermall/account/order/toPay.do?orderId='+data;
				}else{
					alert(data);
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
});

var wait = 120;
function timeWait($el) {
	if (wait == 0) {
		$el.removeAttr("disabled");
		$el.val("获取验证码");
		wait = 120;
	} else {
		$el.attr("disabled", true);
		$el.val("重新发送(" + wait + ")");
		wait--;
		setTimeout(function() {
			timeWait($el)
		}, 1000)
	}
}

function del(id,storeId,createTime){
	$.ajax({
		async : true,
		type : "POST",
		url : '/managermall/account/delCartProd.do?prodId='+id+"&storeId="+storeId+"&createTime="+createTime,
		success : function(data) {
			if (data)
				 location.reload();
			else {
				alert("服务器忙");
			}
		}
	});
}