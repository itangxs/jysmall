function examine(status){
	var isThrought ="通过该商品";
	if(status == 1){
		isThrought = "不通过该商品";
	}
    var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
       if(input[i].checked==true){
    	   ids.push(input[i].value);
       }
    }
	if(ids.length>0){
		id = ids.join(",");
		if(confirm("是否审核"+isThrought)){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/product/audit/update.do',
				data : {
					"id" : id,
					"status" : status
					
				},
				success : function(data) {
					if (data == "审核成功") {
						alert(data);
						window.location.href = '/managermall/systemmall/product/audit/list.do';
					} else {
						alert(data);
					}
				}
			});
		}
	}else{
		alert("请先选择要审核的商品");
	}

	
}

function selectAll(){
	//获取name值
    var ids=document.getElementsByName("ids");
	//判断是否全选
	if($("#selectAll").is(':checked') == true){
        for(var i=0;i<ids.length;i++){
        	ids[i].checked=true;
        }
	}else{
		for(var i=0;i<ids.length;i++){
        	ids[i].checked=false;
        }
	}
}