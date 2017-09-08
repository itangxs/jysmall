function withdraws(){
    var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
       if(input[i].checked==true){
    	   ids[i]=input[i].value;
       }
    }
	if(ids.length>0){
		id = ids.join(",");
		if(confirm("是否确认给该用户提现?")){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/finance/auditUserCashSaveWithd.do',
				data : {"id" : id},
				success : function(data) {
					if (data == "审核成功") {
						alert(data);
						window.location.href = '/managermall/systemmall/finance/userWithdrawsRecordList.do';
					} else {
						alert(data);
					}
				}
			});
		}
	}else{
		alert("请先选择要审核的用户");
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

function Excel() {
	var ser = $("#from").serialize();
	var urlval = '/managermall/systemmall/finance/userWithdrawsRecordListExcel.do?'+ser;
	window.open(urlval);
}