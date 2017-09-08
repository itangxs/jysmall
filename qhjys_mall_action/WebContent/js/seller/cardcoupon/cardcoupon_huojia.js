function detail(ids){
	$.ajax({             
	    type : "POST",
	    url : "/managermall/seller/kaquan/detail.do",
	    data : {id : ids},
	    success : function(card){
	    	/*$("#name").html(card.name);
	    	$("#countLimit").html(card.countLimit);
	    	if(card.couponCfg == 1){
	    		$("#couponCfg").html(card.amount);
	    	}else if(card.couponCfg == 2){
	    		$("#couponCfg").html(card.discount);
	    	}
	    	alert(card.validityCfg);
	    	if(card.validityCfg == 0){
	    		$("#validityStarttime").html(card.validityStarttime);
	    		$("#validityEndtime").html(card.validityEndtime);
	    	}else if(card.validityCfg == 1){
	    		$("#validityDay").html(card.validityDay);
	    	}
	    	$("#useExplain").html(card.useExplain);*/
	    	var data = $(card);
	    	$(".kaquan_detail").html(data.find(".kaquan_detail"));
	    	document.getElementById('huojia02').style.display='block';
	    	document.getElementById('fade').style.display='block';
	    },
	    error : function(e){
			console.info(e);
		}
	});
}

function clickDelete(id,name){
	$("#hj_id").css("display","none");
	$("#hj_id").html(id);
	$("#hj_name").html(name);
	document.getElementById('huojia01').style.display='block';
	document.getElementById('fade').style.display='block';
}

function deleteCard(){
	var url = "/managermall/seller/kaquan/deleteCardCoupon.do";
	var data = "id=" + $("#hj_id").html();
	$.ajax({
		async : false,
		type : "post",
		url : url,
		data : data,
		success : function(result) {
			if (result == "success") {	
				window.location.reload();
//				window.location.href = window.location.href;
				alert("删除成功");
			} else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}