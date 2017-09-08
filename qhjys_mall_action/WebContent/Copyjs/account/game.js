function refresh(userId,project,taskId){

	$.ajax({
		async : false,
		type : "POST",
		url : '/account/task/refresh.do',
		data : {"userId":userId,"project":project,"taskId":taskId},
		success : function(data) {
			if(data== "success"){
				window.location.href = window.location.href;
			}else if(data== "noinfo"){
				alert("您还没进行游戏体验");
			}else{
				alert("刷新游戏信息失败");
			}
		},	
		error : function(e) {
			alert("刷新游戏信息失败");
		}
	});
}
function changec(utid){
	$.ajax({
		async : false,
		type : "POST",
		url : '/account/task/changec.do',
		data : {"utid":utid},
		success : function(data) {
			if(data== "success"){
				window.location.href = window.location.href;
			}else if(data== "alread"){
				alert("已领取");
				window.location.href = window.location.href;
			}else{
				alert("领取失败");
			}
		},	
		error : function(e) {
			alert("领取失败");
		}
	});
}