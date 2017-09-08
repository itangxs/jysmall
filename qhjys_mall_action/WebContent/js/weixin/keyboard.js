
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
		var height = options && options.height || '200px';
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
		cssStr += '#' + TABLE_ID + '{text-align:center;width:100%;height:200px;border-top:1px solid #f7f7f7;background-color:#666666;}';
		cssStr += '#' + TABLE_ID + ' td{width:33%;color:#fff;font-size:1.5em;font-family:"微软雅黑";border:1px solid #f7f7f7;border-right:0;border-top:0;}';
		if(!mobile){
			cssStr += '#' + TABLE_ID + ' td:hover{background-color:#666666;color:#fff;}';
		}
		cssStr += '</style>';
		
		//Button
		var btnStr = '<div style="width:100%;height:0px;background-color:#f9f9f9; border-top:0px solid #ccc;';
		btnStr += 'float:right;text-align:right; padding-right:20px; color:#565656;';
		btnStr += 'line-height:0px;cursor:pointer;"></div>';
		
		//table
		var tableStr = '<table id="' + TABLE_ID + '" border="0" cellspacing="0" cellpadding="0">';
			tableStr += '<tr><td>1</td><td>2</td><td>3</td></tr>';
			tableStr += '<tr><td>4</td><td>5</td><td>6</td></tr>';
			tableStr += '<tr><td>7</td><td>8</td><td>9</td></tr>';
			tableStr += '<tr><td style="background-color:#666666;">.</td><td>0</td>';
			tableStr += '<td style="background-color:#666666;font-size:1em;font-family:"微软雅黑";">删除</td></tr>';
			tableStr += '</table>';
		this.el.innerHTML = cssStr + btnStr + tableStr;
		
		function addEvent(e){
			var ev = e || window.event;
			var clickEl = ev.element || ev.target;
			var value = clickEl.textContent || clickEl.innerText;
			if(clickEl.tagName.toLocaleLowerCase() === 'td' && value !== "删除"){
				if(self.input){
					self.input.value += value;
				}
			}else if(clickEl.tagName.toLocaleLowerCase() === 'div' && value === "完成"){
				body.removeChild(self.el);
			}else if(clickEl.tagName.toLocaleLowerCase() === 'td' && value === "删除"){
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
		body.appendChild(this.el);
	}
	
	exports.KeyBoard = KeyBoard;

})(window);