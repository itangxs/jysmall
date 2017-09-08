/**
 * 删除
 */
function delet(id,sellerId) {
	console.log(id);
	console.log(sellerId);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/cardcoupon/deleteCardStatusCfg.do",
		data : {"id":id,"sellerId":sellerId},
		success : function(b) {
			if (b) {
				window.location.reload();
				return true;
			} else {
				alert("删除失败");
				return false;
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function deleteFun(divId,sellerId, numId,name) {
	document.getElementById(divId).style.display = 'block';
	document.getElementById('fade').style.display = 'block';
	$("#numId").val(numId);
	$("#sellerId").val(sellerId);
	$("#k_name").html(name);
}
function onLoad() {
	document.getElementById('cktxj2').style.display = 'none';
	document.getElementById('fade').style.display = 'none';
}


function detail(id) {
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/cardcoupon/detail.do",
		data : {
			"id" : id,
			},
		success : function(byId) {
			var startTime = byId.validityStarttime;
			var dateStart = new Date(startTime);
			if(startTime != null){
				Y1 = dateStart.getFullYear() + '-';
				M1 = (dateStart.getMonth()+1 < 10 ? '0'+(dateStart.getMonth()+1) : dateStart.getMonth()+1) + '-';
				D1 = (dateStart.getDate()<10 ? '0'+dateStart.getDate():dateStart.getDate()) +'至';
				$("#validityStarttime").html(Y1+M1+D1);
			}else{
				$("#validityStarttime").html(byId.validityStarttime);
			}
			
			var endTime = byId.validityEndtime;
			if(endTime != null){
			var dateEnd = new Date(endTime);
				Y2 = dateEnd.getFullYear() + '-';
				M2 = (dateEnd.getMonth()+1 < 10 ? '0'+(dateEnd.getMonth()+1) : dateEnd.getMonth()+1) + '-';
				D2 = (dateEnd.getDate()<10 ? '0'+dateEnd.getDate():dateEnd.getDate()) + ' ';
				$("#validityEndtime").html(Y2+M2+D2);
			}else{
				$("#validityEndtime").html(byId.validityEndtime);
			}
			
			var day = byId.validityDay;
			if(day != null){
				$("#validityday").html(day+'天');
			}else{
				$("#validityday").html(day);
			}
			
			var discount = byId.discount;
			if(discount != null){
				$("#discount").html(discount+'%');
			}else{
				$("#discount").html('暂无折扣');
			}
			var surpluscount = byId.surplusCount;
			if(surpluscount != null){
				$("#surpluscount").html(surpluscount+'张');
			}else{
				$("#surpluscount").html('无');
			}
			var explain = byId.useExplain;
			if(explain == ""){
				$("#explain").html('暂无说明');
			}else{
				$("#explain").html(explain+'');
			}
			$("#cardname").html(byId.name);
			$("#countlimit").html(byId.countLimit);
			
			document.getElementById('cktxj2').style.display = 'block';
			document.getElementById('fade').style.display = 'block';
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function updateStatus(sellerId,id){
	console.log(id,sellerId);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/cardcoupon/updateStatus.do",
		data : {"sellerId" : sellerId,"id" : id},
		success : function(u) {
			if (u) {
				window.location.reload();
				return true;
			} else {
				alert("更改失败");
				return false;
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}