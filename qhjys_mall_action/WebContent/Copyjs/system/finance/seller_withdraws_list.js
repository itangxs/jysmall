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
		if(confirm("是否确认给该商家提现?")){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/finance/auditSellerCashSaveWithd.do',
				data : {"id" : id},
				success : function(data) {
					if (data == "审核成功") {
						alert(data);
						window.location.href = '/managermall/systemmall/finance/sellerWithdrawsRecordList.do';
					} else {
						alert(data);
					}
				}
			});
		}
	}else{
		alert("请先选择要审核的商家");
	}
}
function unwithdraws(){
	var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
		if(input[i].checked==true){
			ids[i]=input[i].value;
		}
	}
	if(ids.length>0){
		id = ids.join(",");
		if(confirm("是否确认拒绝该商家提现请求?")){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/finance/unauditSellerCashSaveWithd.do',
				data : {"id" : id},
				success : function(data) {
					if (data == "审核成功") {
						alert(data);
						window.location.href = '/managermall/systemmall/finance/sellerWithdrawsRecordList.do';
					} else {
						alert(data);
					}
				}
			});
		}
	}else{
		alert("请先选择要审核的商家");
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
	var urlval = '/managermall/systemmall/finance/sellerWithdrawsRecordListExcel.do?'+ser;
	window.open(urlval);
}