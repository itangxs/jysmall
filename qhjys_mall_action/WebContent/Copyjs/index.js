$(function() {
	var id = $("#city1").val();
	var urlval = '/getCityInfo.do?cityId=' + id;
	$.ajax({
		async : true,
		type : "POST",
		url : urlval,
		success : function(data) {
			if (data) {
				$("#city1").val(id);
				$("#city").text(data.name);
			}
		}
	});
	$.ajax({
		async : true,
		type : "POST",
		url : "/getNotRead.do",
		success : function(data) {
			if (data>0) {
				$("#xiaoxi").html("<a href='/managermall/account/message/zlist.do'><img src='/images/message.gif' style='margin-right:3px;'>["+data+"]</a>");
			}
		}
	});
});

function select1() {
	$("#switchCity").attr("class", "current");
}