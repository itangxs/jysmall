function delAll(){
    var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
       if(input[i].checked==true){
    	   ids.push(input[i].value);
       }
    }
	if(ids.length>0){
		id = ids.join(",");
		if(confirm("是否删除该评价")){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/product/review/delete.do',
				data : {"id" : id},
				success : function(data) {
					if (data == "删除成功") {
						window.location.href = '/managermall/systemmall/product/review/list.do';
					} else {
						alert(data);
					}
				}
			});
		}
	}else{
		alert("请先选择要删除的商品评论");
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