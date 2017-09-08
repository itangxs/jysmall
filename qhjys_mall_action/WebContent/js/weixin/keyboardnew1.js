
;(function(exports){
	var KeyBoard = function(input, options){
		var body = document.getElementsByTagName('body')[0];
		var DIV_ID = options && options.divId || '__w_l_h_v_c_z_e_r_o_divid';
		
		if(document.getElementById(DIV_ID)){
			body.removeChild(document.getElementById(DIV_ID));
		}
		
		this.input = input;
		this.el = document.createElement('div');
		
		var self = this;
		var zIndex = options && options.zIndex || 1000;
		var width = options && options.width || '100%';
		var height = options && options.height || '240px';
		var fontSize = options && options.fontSize || '15px';
		var backgroundColor = options && options.backgroundColor || '#fff';
		var TABLE_ID = options && options.table_id || 'table_0909099';
		var mobile = typeof orientation !== 'undefined';
		
		this.el.id = DIV_ID;
		this.el.style.position = 'absolute';
		this.el.style.left = 0;
		this.el.style.right = 0;
		this.el.style.bottom = 0;
		this.el.style.zIndex = zIndex;
		this.el.style.width = width;
		this.el.style.height = height;
		this.el.style.backgroundColor = backgroundColor;
		
		//样式
		var cssStr = '<style type="text/css">';
		cssStr += '#' + TABLE_ID + '{text-align:center;width:100%;height:240px;border-top:1px solid #CECDCE;background-color:#FFF;}';
		cssStr += '#' + TABLE_ID + ' td{width:25%;border:1px solid #ddd;border-right:0;border-top:0;font-size:1.5em;}';
		if(!mobile){
			cssStr += '#' + TABLE_ID + ' td:hover{background-color:#eaeaea;color:#FFF;}';
		}
		cssStr += '</style>';
		
		//Button
		var btnStr = '<div style="width:60px;height:0px;background-color:#1FB9FF;';
		btnStr += 'float:right;margin-right:5px;text-align:center;color:#fff;';
		btnStr += 'line-height:0px;border-radius:3px;margin-bottom:0px;cursor:pointer;"></div>';
		
		//table
		var tableStr = '<table id="' + TABLE_ID + '" border="0" cellspacing="0" cellpadding="0">';
			tableStr += '<tr><td>1</td><td>2</td><td>3</td><td id="delete"style="text-align:center"><img src="/images/weixin/payshanchu.png"style="display:inline-block; vertical-align:middle;" width="35" id="delete"></td></tr>';
			tableStr += '<tr><td>4</td><td>5</td><td>6</td><td id="readypay" rowspan="3" style="background-color:#bcbcbc;font-size:1.1em; line-height:25px; color:#fff;">确认<br>支付</td></tr>';
			tableStr += '<tr><td>7</td><td>8</td><td>9</td><td></td></tr>';
			tableStr += '<tr><td ></td><td>0</td><td >.</td>';
			tableStr += '<td></td></tr>';
			tableStr += '</table>';
		this.el.innerHTML = cssStr + btnStr + tableStr;
		
		function addEvent(e){
			var ev = e || window.event;
			var clickEl = ev.element || ev.target;
			var o =clickEl.getAttribute("id");
			var value = clickEl.textContent || clickEl.innerText;
			if(clickEl.tagName.toLocaleLowerCase() === 'td' && o!=="delete"){
				if(value === "确认支付"){
					if (self.input.value.replace("￥","")>0) {
					payOrder();
					}
						//body.removeChild(self.el);
				} else 
				if(self.input){
					if (value=="."&&self.input.value.indexOf(".")>0) {
						value="";
					}
					
					if (self.input.value.indexOf(".")>0&&self.input.value.substring(self.input.value.indexOf("."),self.input.value.length).length>2) {
						value="";
					}
					self.input.value+= value;
					
					if($("#payType").val()=="1"||$("#payType").val()==1){//每次输入金额后， 查询优惠券 ！！微信！！
						calcPayCoupon();
					}
					if (self.input.value.replace("￥","")>0) {
						$("#readypay").css("background-color","#1db259");
					}
				}
			}else if(clickEl.tagName.toLocaleLowerCase() === 'div' && value === "完成"){
				body.removeChild(self.el);
			}else if(null!=o&&o==="delete"){
				var num = self.input.value;
				if(num){
					var newNum = num.substr(0, num.length - 1);
					self.input.value = newNum;
					
					if($("#payType").val()=="1"||$("#payType").val()==1){//每次删除输入金额后， 查询优惠券 ！！微信！！
						calcPayCoupon();
					}
					if (self.input.value.replace("￥","")==""||self.input.value.replace("￥","")<=0) {
						  if($(".payquan")){
					    	  $(".payquan").remove();
					      }
						$("#readypay").css("background-color","#bcbcbc");
					}
				}
			}
		}
		
		if(mobile){
			this.el.ontouchstart = addEvent;
		}else{
			this.el.onclick = addEvent;
		}
		body.appendChild(this.el);
	}
	
	exports.KeyBoard = KeyBoard;

})(window);

function calcPayCoupon(){
	$.ajax({
		url: "/wxMall/getUserAvailableCardCoupon.do",
		type: "POST",
		async:false,
		data: {totalPay:($("#totamt").val()).replace("￥",""),storeId:$("input[name='storeId']").val()},
		success: function(result){
			if(result.data!=null){ //查询到优惠券了
				var li = "";
				var rePay = 0;
				var pay = parseFloat(($("#totamt").val()).replace("￥","")).toFixed(2);
				if(result.data.couponCfg==1){ //代金
					li = "<li><span class='jian'>减</span>卡劵："+result.data.couponName+"<span class='shiyong'>";
					rePay = pay - parseFloat(result.data.couponAmount).toFixed(2);
					rePay = parseFloat(rePay).toFixed(2);
				}else if(result.data.couponCfg==2){ //折扣
					li = "<li><span class='jian'>折</span>卡劵："+result.data.couponName+"<span class='shiyong'>";
					var amount = parseFloat("0."+result.data.couponAmount).toFixed(2);
					rePay = pay*amount;
					rePay = parseFloat(rePay).toFixed(2);
				}
				if(rePay<0){
					rePay = 0;
				}
				 li+=" 使用 <input name='realPayCheck' id='check_"+result.data.id+"' type='checkbox' value='"+rePay+"' checked onclick='shifuCheckboxOnClick(this)'></span></li>";
			     var html = "<div class='payquan'>"+
								"<ul>"+li+"</ul>"+
								"<p class='shifu'>实际支付：<span class='red'>￥"+rePay+"</span></p>"+
							"</div>";
			      if($(".payquan")){
			    	  $(".payquan").remove();
			      }
				  $(".payinput").after(html);
			}else{
				if($(".payquan")){
			    	  $(".payquan").remove();
			      }
			}
		},
		error: function(){
			//alert("系统繁忙请稍后再试！");
		}
	});
	
}

function shifuCheckboxOnClick(checkbox){
	if ( checkbox.checked == true){
		 calcPayCoupon();
	}else{
		$(".payquan .shifu .red").html("￥"+$("#totamt").val());
	}

}