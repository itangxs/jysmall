function deleteCard(id){
	var url = "/managermall/seller/kaquan/deleteCardCoupon.do";
	var data = "id=" + id;
	$.ajax({
		async : false,
		type : "post",
		url : url,
		data : data,
		success : function(result) {
			if (result == "success") {	
				window.location.reload();
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

function delivery(type){
	obj = document.getElementsByName("test");
    check_val = [];
    check_id = [];
    for(k in obj){
        if(obj[k].checked){
        	check_val.push(obj[k].value);
        	check_id.push(obj[k].id);
        } 
    }
    if(check_val!=null && check_val!=""){
    	if(type == 0){
        	$('#cardName').html(check_val[0]);
        	document.getElementById('kaquan03').style.display='none';
        	document.getElementById('kaquan04').style.display='block';
        }else if(type == 1){
        	$('#pCardName').html(check_val[0]);
        	document.getElementById('zytf02').style.display='none';
            document.getElementById('zytf03').style.display='block';
        }else if(type == 2){
        	$('#zCardName').html(check_val[0]);
        	document.getElementById('zbtf02').style.display='none';
        	document.getElementById('zbtf03').style.display='block';
        }
        $('#pCardId').val(check_id);
    }else{
    	alert("请选择要投放的卡券");
    }
} 

function updateStatus(pushNum,delivery){
	var sort = $("#sort").val();
	var id = $("#pCardId").val();
	if(pushNum == 0){
		if(delivery==1){
			document.getElementById('zytf03').style.display='none';
			document.getElementById('zytf04').style.display='block';
		}else if(delibery==2){
			document.getElementById('zbtf03').style.display='none';
			document.getElementById('zbtf04').style.display='block';
		}
	}else{
		var url = "/managermall/seller/kaquan/comfirmdelivery.do";
		var data = {'cardId':id,'deliveryType':delivery,'sort':sort};
		$.ajax({
			async : false,
			type : "post",
			url : url,
			data : data,
			success : function(result) {
				if (result == "start") {	
					window.location.reload();
					alert("投放启动");
				} else if(result == "success"){
					window.location.reload();
					alert("投放成功");
				} else{
					alert("投放失败");
				}
			},
			error : function() {
				alert("服务器忙");
			}
		});
		document.getElementById('fade').style.display='none';
		document.getElementById('zytf03').style.display='none';
		document.getElementById('zbtf03').style.display='none';
	}
}

function insert(sort){
	$('#sort').val(sort);
}
