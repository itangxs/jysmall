function closeadddiv(){
	$("#adddiv").hide();
	$("#addform")[0].reset();
	$("#storename").html("");
}

function paixuclose(){
	$("#rebid").val("");
	$("#rebaname").html("");
	$("#stoname").html("");
	$("#reblavel").val("");
	$("#paixu").hide();
}
function openadddiv(){
	$("#storeId").removeAttr("readonly");
	$("#adddiv").show();
}
function openupdatediv(id,storeId,rebateName,rebate,ortherRebate,beginDate,endDate,storeName,zexplain){
	$("#reid1").val(id);
	$("#storeId").val(storeId);
	$("#storeId").attr("readonly","readonly");
	$("#storename").html(storeName);
	$("#rebateName1").val(rebateName);

	$("#beginDate").val(beginDate);
	$("#endDate").val(endDate);
	$("#ortherRebate1").val(ortherRebate);
	$("#rebate1").val(rebate);
	$("#zexplain").val(zexplain);
	$("#adddiv").show();
}
function paixuopen(rebid,rebaname,stoname,reblavel){
	$("#rebid").val(rebid);
	$("#rebaname").html(rebaname);
	$("#stoname").html(stoname);
	$("#reblavel").val(reblavel);
	$("#paixu").show();
}
function changelevel(){
	var rebateId = $("#rebid").val();
	var level = $("#reblavel").val();
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/systemmall/rebate/changeLevel.do',
		data : {"rebateId" : rebateId,level:level},
		success : function(data) {
			if (data=="success") {
				alert("修改成功");
				window.location.reload();
			}else{
				paixuclose();
				alert("修改失败");
			}
			
		}
	});
}
function getstorename(){
 var storeid = $("#storeId").val();
 $.ajax({
		async : false,
		type : "POST",
		url : '/managermall/systemmall/rebate/getStoreName.do',
		data : {"storeId" : storeid},
		success : function(data) {
			$("#storename").html(data);
			
		}
	});
}

function addrebate(){
	var storename =  $("#storename").html();
	if (storename.length >0) {
		addform.submit();
	}else{
		alert("请先确定商家ID是否存在");
	}
}
function updatestatus(status){
    var input=document.getElementsByName("ids");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
       if(input[i].checked==true){
    	   ids.push(input[i].value);
       }
    }
	if(ids.length>0){
		id = ids.join(",");
		var mess= "是否下架这些折扣";
		if (status == 1) {
		mess= "是否上架这些折扣";
		}
		if(confirm(mess)){
			$.ajax({
				async : false,
				type : "POST",
				url : '/managermall/systemmall/rebate/changestatus.do',
				data : {"ids" : id,"status":status},
				success : function(data) {
					alert(data);
					if (data == "操作成功") {
						window.location.reload();
					}
				}
			});
		}
	}else{
		alert("请先选择要更改状态的折扣");
	}
}