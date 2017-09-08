function examine(status) {
	var isThrought = "是否启售该商品";
	if (status == 0)
		isThrought = "是否禁售该商品";
	var input = document.getElementsByName("ids");
	var ids = new Array();
	for (var i = 0; i < input.length; i++)
		if (input[i].checked == true){
			ids.push(input[i].value);
		}
	if (ids.length > 0) {
		id = ids.join(",");
		if (confirm(isThrought)) {
			$.ajax({
				async : false,
				type : "POST",
				url : 'update.do',
				data : { "id" : id, "enabled" : status },
				success : function(data) {
					alert(data);
					if (data == "审核成功")
						window.location.reload(true);
				}
			});
		}
	} else
		alert("请先选择要审核的商品");
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