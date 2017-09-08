
;(function(exports){
	var KeyBoard = function(input, options){
		var article = document.getElementsByTagName('article')[0];
		var DIV_ID = options && options.divId || '__w_l_h_v_c_z_e_r_o_divid';
		
		if(document.getElementById(DIV_ID)){
			article.removeChild(document.getElementById(DIV_ID));
		}
		
		this.input = input;
		this.el = document.createElement('div');
		
		var self = this;
		var zIndex = options && options.zIndex || 1000;
		var width = options && options.width || '360px';
		var height = options && options.height || '312px';
		var fontSize = options && options.fontSize || '15px';
		var backgroundColor = options && options.backgroundColor || '#fff';
		var TABLE_ID = options && options.table_id || 'table_0909099';
		var mobile = typeof orientation !== 'undefined';
		
		this.el.id = DIV_ID;
		this.el.style.position = 'absolute';
		this.el.style.left = '-5px';
		this.el.style.top = '14px';
		this.el.style.zIndex = zIndex;
		this.el.style.width = width;
		this.el.style.height = height;
		this.el.style.backgroundColor = backgroundColor;
		
		//样式
		var cssStr = '<style type="text/css">';
		cssStr += '#' + TABLE_ID + '{text-align:center;width:360px;height:312px;background-color:#FFF;}';
		cssStr += '#' + TABLE_ID + ' td{ width:90px; height:78px;font-size:25px; background:url(/images/seller/KeyBoardbg1.png) no-repeat center center; color:#55788e; cursor:pointer;}';
		if(!mobile){
			cssStr += '#' + TABLE_ID + ' td:hover{background:url(/images/seller/KeyBoardbg11.png) no-repeat center center;color:#55788e;}';
		}
		cssStr += '</style>';
		
		//Button
		var btnStr = '<div style="width:60px;height:0px;background-color:#1FB9FF;';
		btnStr += 'float:right;margin-right:5px;text-align:center;color:#fff;';
		btnStr += 'line-height:0px;border-radius:3px;margin-bottom:0px;cursor:pointer;"></div>';
		
		//table
		var tableStr = '<table id="' + TABLE_ID + '" border="0" cellspacing="0" cellpadding="0">';
			tableStr += '<tr><td id="txt1">1</td><td id="txt2">2</td><td id="txt3">3</td><td id="delete"style="text-align:center"><img src="/images/seller/cashier_delete.png"style="display:inline-block; vertical-align:middle;" width="37" id="delete"></td></tr>';
			tableStr += '<tr><td>4</td><td>5</td><td>6</td><td id="empty" style="font-size:18px;">清空</td></tr>';
			tableStr += '<tr><td>7</td><td>8</td><td>9</td><td rowspan="2"  style="background:url(/images/seller/KeyBoardbg2.png) no-repeat center center;font-size:18px; height:156px; color:;">确定</td></tr>';
			tableStr += '<tr><td >+</td><td>0</td><td>.</td>';
			tableStr += '</table>';
		this.el.innerHTML = cssStr + btnStr + tableStr;
		
		function addEvent(e){
			var ev = e || window.event;
			var clickEl = ev.element || ev.target;
			var o =clickEl.getAttribute("id");
			var value = clickEl.textContent || clickEl.innerText;
			if(clickEl.tagName.toLocaleLowerCase() === 'td' && o!=="delete"){
				/*document.onkeydown=function(event){
					var ee = event || window.event || arguments.callee.caller.arguments[0];
					if(ee && (ee.keyCode==13||ee.keyCode==108)){ //扫码枪扫完自动提交  不能用，容易和监听input 输入框事件冲突 导致 重复支付
						doPay();
						//article.removeChild(self.el);
					}
				};*/
				if(value === "清空"){
				   document.getElementById("shoukuan").value="";
				   self.input = document.getElementById("shoukuan");//解决切换问题
					$('#shoukuan').focus();
				   document.getElementById("shoukuanma").value="";
				} else 
				if(value === "确定"){
					//alert("触发确定按钮,键盘会隐藏")
					//article.removeChild(self.el);
					var payMoney = document.getElementById("shoukuan").value;
					var authCode =  document.getElementById("shoukuanma").value;
					var money = 0;
					var reg = /^[\d_\+\.]*$/;
					if(reg.test(payMoney)){
						pay_money = [];
						pay_money = payMoney.split("+");
						for (var i=0;i<pay_money.length;i++){
							if(pay_money[i]==""){
								pay_money[i]=0;
							}
							money += parseFloat(pay_money[i]);
						}
					}else{
						alert("只能输入数字、.、+");
						$('#shoukuan').focus();
						return;
					}
					money = parseFloat(money).toFixed(2);
					document.getElementById("shoukuan").value = money;
					
					if(authCode==""||authCode.length!=18){
						if(self.input.id=="shoukuanma"){
							alert("付款码错误,只能为18位数字,请检查!");
						}
						$('#shoukuanma').focus();
						self.input = document.getElementById("shoukuanma");//解决切换问题
						return;
					}
					//发起支付
					doPay();
					//article.removeChild(self.el);
				} else 
				if(self.input){
					self.input.value += value;
				}
			}else if(clickEl.tagName.toLocaleLowerCase() === 'div' && value === "完成"){
				article.removeChild(self.el);
			}else if(null!=o&&o==="delete"){
				var num = self.input.value;
				if(num){
					var newNum = num.substr(0, num.length - 1);
					self.input.value = newNum;
				}
			}
		}
		
		if(mobile){
			this.el.ontouchstart = addEvent;
		}else{
			this.el.onclick = addEvent;
		}
		article.appendChild(this.el);
	}
	
	exports.KeyBoard = KeyBoard;
	
	$('#shoukuanma').bind('input propertychange', function() {
		if($(this).val().length==18){
			doPay();
		}
	});
	
	//金额输入框 回车事件触发
	$('#shoukuan').bind('keypress',function(event){ 
        if(event.keyCode == 13 || event.keyCode == 108)      
        {  
        	//将用户输入的金额进行+运算
        	var payMoney = document.getElementById("shoukuan").value;
        	var money = 0;
			var reg = /^[\d_\+\.]*$/;
			if(reg.test(payMoney)){
				pay_money = [];
				pay_money = payMoney.split("+");
				for (var i=0;i<pay_money.length;i++){
					if(pay_money[i]==""){
						pay_money[i]=0;
					}
					money += parseFloat(pay_money[i]);
				}
				
			}else{
				alert("只能输入数字、.、+");
				$('#shoukuan').focus();
				return;
			}
			money = parseFloat(money).toFixed(2);
			document.getElementById("shoukuan").value = money;
			$('#shoukuanma').click();//解决切换问题
        	$('#shoukuanma').focus();
        }
    });
	$('#zhifuchenggong').bind('keypress',function(event){ 
		if(event.keyCode == 13 || event.keyCode == 108)      
		{  
			$("#zhifuchenggong").hide();
			$(".zfcgcontent1").hide();
			$(".zfcgcontent").hide();
			$('#shoukuan').focus();
			
		}
	});
	$('#zhifushibai').bind('keypress',function(event){ 
		if(event.keyCode == 13 || event.keyCode == 108)      
		{  
			$("#zhifushibai").hide();
			$(".zfcgcontent1").hide();
			$(".zfcgcontent").hide();
			$('#shoukuanma').focus();
			
		}
	});
	
})(window);

function doPay(){
	var payMoney = document.getElementById("shoukuan").value;
	var authCode =  document.getElementById("shoukuanma").value;
	var money = 0;
	var reg = /^[\d_\+\.]*$/;
	if(reg.test(payMoney)){
		pay_money = [];
		pay_money = payMoney.split("+");
		for (var i=0;i<pay_money.length;i++){
			if(pay_money[i]==""){
				pay_money[i]=0;
			}
			money += parseFloat(pay_money[i]);
		}
	}else{
		alert("只能输入数字、.、+");
		$('#shoukuan').focus();
		return;
	}
	if(authCode==""||authCode.length!=18){
		alert("付款码错误,只能为18位数字,请检查!");
		$('#shoukuanma').focus();
		return;
	}
	money = parseFloat(money).toFixed(2);
	$(".zfcgcontent1").show();
	$(".zfcgcontent").show();
	$.ajax({
        type: 'GET',
        url: '/managermall/seller/paymoney.do',
        data: {money:money,authCode:authCode},
        dataType: 'json',
        success: function(obj){
			if(obj.flag==0||obj.flag=="0"){
				//支付成功后清空输入框
				
				document.getElementById("shoukuan").value="";
				document.getElementById("shoukuanma").value="";
				$(".textredcg").html("收款金额：￥"+obj.data.money);
				$("#zhifuchenggong").show();
				setTimeout(function(){
					$("#zhifuchenggong").hide();
					$(".zfcgcontent1").hide();
					$(".zfcgcontent").hide();
					$('#shoukuan').focus();
				},5000);
				
			}else{
				document.getElementById("shoukuanma").value="";
				$("#zhifushibaiinfo").html(obj.reason);
				$("#zhifushibai").show();
			}
        },
        error: function(xhr, type){
            alert('请求网络失败!');
        }
	 });

}

function orderList(){
	if (document.getElementById('cashierdesk').style.display!='none' && document.getElementById('cashierdesk').style.display!='') {
		var url = "/managermall/seller/orderlist.do";
		$.ajax({
			async : false,
			type : "post",
			url : url,
			success : function(result) {
				var data = $(result);
				$('.tablebox').html(data.find(".tablebox"));
			},
			error : function() {
				alert("服务器忙");
			}
		});
		
	}
	
}