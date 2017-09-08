function gehang(){
    //获取tr节点
    var tr=document.getElementsByTagName("tr");
    //为第一行添加背景颜色
       tr[0].style.background="#00FF66";
       //为最后一行添加背景颜色
       tr[tr.length-1].style.background="#00FF66";
                }
    //创建全选反选函数
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
    
    
    
  function updateStauts(status){//修改密码
	       var msg = "确认审核通过该店铺";
	  	   if(status == 1)
	  		   msg = "确认审核不通过该店铺";
    	   var input=document.getElementsByName("ids");
	       	var ids = new Array();
	       	for(var i=input.length-1; i>=0;i--){
	              if(input[i].checked==true){
	            	  ids.push(input[i].value);
	              }
	           }
	       	
	       	if(ids.length>0){
	       		if(confirm(msg)){
	       			var urlval ="updateStoreCheckStauts.do";
	       			var parma ="ids="+ids+"&status="+status;
		       		   $.ajax({
		       			async: false, 
		   		    	type:'post',//可选get
		   		    	url:urlval,//这里是接收数据的后台程序
		   		    	data:parma,//传给后台的数据，多个参数用&连接
		   		    	success:function(msg){
		   		    		if (msg=='success'){
		   		    			alert("审核成功");
		   		    			window.location.href=window.location.href;
		   		    		}else{
		   		    			alert("审核失败");
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
	       		alert("请先选择要审核的店铺");
	       	}
       }
    
  
  
  
  
  