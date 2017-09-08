
function delet(){
	alert("你好删除");
}

function changestatus(status){
		var input=document.getElementsByName("ids");
     	var ids = new Array();
     	for(var i=input.length-1; i>=0;i--){
            if(input[i].checked==true){
         	   if(parseInt(input[i].value)>0){
         		   ids.push(input[i].value);
         	   }
            }
         }
     	if(ids.length>0){
     		var message = "";
     		if (status == 0) {
     			message = "下架";
			}else{
				message = "上架";
			}
     		if(confirm("确定"+message+"这些产品嘛?")){
     			var params = "ids="+ids+"&status="+status;
     			var urlval = '/managermall/seller/fqproducts/changeStatus.do';
     		   $.ajax({
     		    	type:'post',//可选get
     		    	url:urlval,//这里是接收数据的后台程序
     		    	data:params,//传给后台的数据，多个参数用&连接
     		    	success:function(data){
     		    		if (data=='success'){
     		    			alert(message+"成功");
     		    			window.location.reload();
     		    		}
     					if (data=='error'){
     						alert(message+"失败");
     		    		}
     		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
     		    	},
     		    	error:function(){
     		    		alert(message+"出现异常");
     		    	}
     		    });
     		}else{
     			//alert("删除失败");
     		}
     		}
     	}

function deleteByIds(){ 
	var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=input.length-1; i>=0;i--){
		if(input[i].checked==true){
			if(parseInt(input[i].value)>0){
				ids.push(input[i].value);
			}
		}
	}
	if(ids.length>0){
		if(confirm("确定删除这些产品嘛?")){
			var urlval = '/managermall/seller/fqproducts/deleteByIds.do';
			$.ajax({
				type:'post',//可选get
				url:urlval,//这里是接收数据的后台程序
				data:"ids="+ids,//传给后台的数据，多个参数用&连接
				success:function(data){
					if (data=='success'){
						alert("删除成功");
						window.location.reload();
					}
					if (data=='error'){
						alert("删除失败");
					}
					//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
				},
				error:function(){
					alert("删除出现异常");
				}
			});
		}else{
			//alert("删除失败");
		}
	}
}
		



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
            	var e =true;
            	var quan=document.getElementById("quan");
            	for(var i=0;i<=qcheck.length;i++){
            		if(qcheck[i].checked){
            			continue;
            		}else{
            			e =false;
            		}
            	}
            		quan.checked = e;
            
            }
            
        }
    
    
       /* function del(){
            var input=document.getElementsByName("ids");
            	var ids = new Array();
            	for(var i=input.length-1; i>=0;i--){
                   if(input[i].checked==true){
                	   ids[i]=input[i].value;
                   }
                }
            	if(ids.length>0){
            		if(confirm("确定删除吗?")){
            			window.location.href="/managermall/seller/commoditymanage/delete.do?ids="+ids;
            			return true;
            		}else{
            			//alert("删除失败");
            		}
            	}else{
            		alert("请先选择要删除的商品");
            	}
            }*/

        function shelves(){
            var input=document.getElementsByName("ids");
            	var ids = new Array();
            	for(var i=input.length-1; i>=0;i--){
                   if(input[i].checked==true){
                	   if(parseInt(input[i].value)>0){
                		   ids.push(input[i].value);
                	   }
                   }
                }
            	if(ids.length>0){
            		if(confirm("确定下架吗?")){
            			window.location.href="/managermall/seller/commoditymanage/shelves.do?ids="+ids;
            			return true;
            		}else{
            			//alert("删除失败");
            		}
            	}else{
            		alert("请先选择要下架的商品");
            	}
            }
        