function deleteBy(id,status){
	if (confirm("确定要删除吗?")) {
		$.ajax({
			type : "post",
			url : "/managermall/systemmall/finance/deleteById.do",
			data : {
				"id" : id,
				"status" : status,
			},
			success : function(byId){
				if(byId == "success"){
					window.location.reload();
					alert("删除成功")
				}else{
					alert("请确认该条记录是否还存在还款记录");
				}
			}
		});
	}
}
/**
 * 显示和隐藏预约时间框
 */
$(document).ready(function(){
	  $("#type1").click(function(){
	    $("#yuyue").hide();
	  });
	  $("#type2").click(function(){
	    $("#yuyue").show();
	  });
	  $("#interest").focus(function(){
		  if ( $("#interest").val()==0) {
			  $("#interest").val("");
		 }
	  }).blur(function(){
		  if ( $("#interest").val()=="") {
			  $("#interest").val(0);
		 } 
	  })
});
function updateStatus(id){
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/updateStatus.do",
		data : {
			"id" : id,
		},
		success : function(byId){
			if(byId == "success"){
				window.location.reload();
			}
		}
	});
}
/*function insertRepayment(id){
	var interest = $("input[name='interest']").val();
	var principal = $("input[name='principal']").val();
	var repaymentType = $("input[name='repaymentType']").val();
	var reservationTime = $("input[name='reservationTime']").val();
	var id=$("#id").val();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/insertRepayment.do",
		data : {
			"interest" : interest,
			"principal" : principal,
			"repaymentType" : repaymentType,
			"reservationTime" : reservationTime,
		},
		success : function(a){
			if(a=true){
				windows.localtion.load();
			}
		}
	});
}*/
