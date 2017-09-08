
$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};

function delMessage(id){
	var ids = new Array();
	ids[0] = id;
	if(confirm("确定要删除本条消息吗?")){
		var urlval ="/managermall/systemmall/message/delMessage.do";
			var parma ="ids="+ids;
   		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:parma,//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if (msg=='success'){
		    			alert("删除成功");
		    			window.location.href=window.location.href;
		    		}else{
		    			alert("删除失败");
		    			window.location.href=window.location.href;
		    		}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    	//ajax提交失败的处理函数！
		    		alert("服务器忙");
		    	}
		    });
	}
}

function delMessageByIds(){//开启 ,关闭
	   var input=document.getElementsByName("ids");
    	var ids = new Array();
    	for(var i=input.length-1; i>=0;i--){
           if(input[i].checked==true){
         	  if(input[i].value>=1){
         		 ids.push(input[i].value);
         	  }
           }
        }
    	if(ids.length>0){
    		if(confirm("确定要删除本条消息吗?")){
    			var urlval ="/managermall/systemmall/message/delMessage.do";
    			var parma ="ids="+ids;
	       		   $.ajax({
	   		    	type:'post',//可选get
	   		    	url:urlval,//这里是接收数据的后台程序
	   		    	data:parma,//传给后台的数据，多个参数用&连接
	   		    	success:function(msg){
	   		    		if (msg=='success'){
	   		    			alert("删除成功");
	   		    			window.location.href=window.location.href;
	   		    		}else{
	   		    			alert("删除失败");
	   		    			window.location.href=window.location.href;
	   		    		}
	   		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	   		    	},
	   		    	error:function(){
	   		    	//ajax提交失败的处理函数！
	   		    		alert("服务器忙");
	   		    	}
	   		    });
	       			/*window.location.href="/managermall/seller/commoditymanage/delete.do?ids="+ids;
	       			return true;*/
	       		}else{
	       			//alert("删除失败");
	       		}
    	}else{
    		alert("请先选择要删除的消息");
    	}
}


function xuan(type){
    //获取name值
    var qcheck=document.getElementsByName("ids");
    //获取选的按钮
    if(type=="qx"){
        for(var i=0;i<=qcheck.length;i++){
            qcheck[i].checked=true;
                                         }
                  }
        if(type=="fx"){
        for(var i=0;i<=qcheck.length;i++){
            qcheck[i].checked=false;
                                          }
                      }
       
        if(type=="qxx"){
        	 var quan=document.getElementById("quan");
        	for(var i=0;i<=qcheck.length;i++){
        			qcheck[i].checked=quan.checked;
        		/*	
        		 * 	if(qcheck[i].checked){qcheck[i].checked=false;
        		} else{
        			qcheck[i].checked=true;
    
        		}*/
            }
        }
        if(type=="dx"){//单选
        	var qcheck=document.getElementsByName("ids");
        	var e =true;
        	var quan=document.getElementsByName("quan");
        	for(var i=0;i<=qcheck.length;i++){
        		if(qcheck[i].checked){
        			e =false;
        		}
        	}
        }
        
    }
