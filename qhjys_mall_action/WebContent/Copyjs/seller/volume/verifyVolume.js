$(function() {
	$("#verify").on("click", function() {
		var vCode = $("#vCode").val();
		if (vCode == "") {
			alert("请输入飞券串码 ！");
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : 'verifyVolume.do',
			data : {
				vCode : vCode
			},
			success : function(data) {
				if(data.message == "success"){
					$("#xiaofei1").html("飞券串码 :"+data.volume.volumeCode+"<br>商品:"+data.volume.prodName+"<br>金元宝:"+data.order.payPrice+"<br>抵用金额:"+(parseFloat(data.order.payPrice)/20).toFixed(2)+"<br>过期时间:<fm:formatDate value='"+data.volume.expiration+"' pattern='yyyy-MM-dd'/>");
					$("#xiaofei").show();
				}else{
					alert(data.message);
				}
			}
		});
	});
	$("#verify1").on("click", function() {
		var vCode = $("#vCode").val();
		if (vCode == "") {
			alert("请输入飞券串码 ！");
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : 'verifyVolume1.do',
			data : {
				vCode : vCode
			},
			success : function(data) {
				if(data == "success"){
					 $("#xiaofei1").html("");
					$("#xiaofei").hide();
					alert("飞券串码 验证成功");
				}else{
					alert(data);
				}
			}
		});
	});
	$("#vCode").change(function(){
		 $("#xiaofei1").html("");
		 $("#xiaofei").hide();
	 });
});

function formatDate(date){
	var JsonDateValue = new Date();
	JsonDateValue.setTime(date);
	var text = JsonDateValue.getYear()+"-"+JsonDateValue.getMonth()+"-"+JsonDateValue.getDay()+" "+JsonDateValue.getHours()+":"+JsonDateValue.getMinutes()+":"+JsonDateValue.getSeconds();
	return text;
}