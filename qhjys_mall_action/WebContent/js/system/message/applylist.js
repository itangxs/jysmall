
function gopreview(id){
	$(".memberright").append('<div class="preview" id="sjsh"></div>');
	$("#sjsh").append('<iframe src="/managermall/systemmall/apply/preview.do?id='+id+'" width="1200" height="920" marginwidth="0" marginheight="0" frameborder="0" scrolling="no"></iframe>');
	
	document.getElementById('sjsh').style.display='block';
}
function updateApplyStatus(status){//开启 ,关闭
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
    		if(confirm("确定要修改申请状态吗?")){
    			var urlval ="/managermall/systemmall/apply/updateStatus.do";
    			
	       		   $.ajax({
	   		    	type:'post',//可选get
	   		    	url:urlval,//这里是接收数据的后台程序
	   		    	data:{"ids":ids.join(","),"status":status},//传给后台的数据，多个参数用&连接
	   		    	success:function(msg){
	   		    		if (msg>0){
	   		    			alert("修改成功");
	   		    			window.location.href=window.location.href;
	   		    		}else{
	   		    			alert("修改失败");
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

function getStore(){
	var id = document.getElementById('sID').value;
	var urlval ="/managermall/systemmall/apply/getStoreName.do";
	if(null == id || "" == id){
		alert("请输入要查询的商家ID");
	}else{
		$.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:{"storeId":id},//传给后台的数据，多个参数用&连接
	    	success:function(msg){
	    		if(null != msg && '' !=  msg){
		    		$("#sName").html(msg.name);
	    		}else{
	    			alert("没有该店铺,请重新输入商家ID");
	    		}
	    	},
	    	error:function(){
	    		alert("服务器繁忙");
	    	}
	    });
	}
}

function resetMessageNum(){
	var id = document.getElementById('sID').value;
	if(id==''){
		alert("请输入要重置的商家ID");
	}else{
		var urlval ="/managermall/systemmall/apply/resetMessageNum.do";
		$.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:{"storeId":id,"messageNum":1},//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if(msg > 0){
		    			alert("重置成功");
		    			window.location.href=window.location.href;
		    		}else if(msg == -1){
		    			alert("没有该店铺,请重新输入商家ID");
		    		}else{
		    			alert("重置失败");
		    		}
		    	},
		    	error:function(){
		    		alert("服务器繁忙");
		    	}
		  });
	}
}
