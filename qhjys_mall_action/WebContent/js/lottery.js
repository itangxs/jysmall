
$(function(){
	$.ajax({
		url: "/lottery/listLotteryInfo1.do",
		type: "POST",
		data: {'userLotteryId':$("#userLotteryId").val()},
		success: function(data){
			var lis = data.lis;
			var san = 0;
			var er = 0;
			var yi = 0;
			var te = 0;
			for ( var nu in lis) {
				var li = lis[nu];
				if (li.rank == 4) {
					$("#sandengjiangul").append("<li><img src="+li.portrait+"></li>");
					san++;
				}
				if (li.rank == 3) {
					$("#erdengjiangul").append("<li><img src="+li.portrait+"></li>");
					er++;
				}
				if (li.rank == 2) {
					$("#yidengjiangul").append("<li><img src="+li.portrait+"></li>");
					yi++;
				}
				if (li.rank == 1) {
					$("#tedengjiangul").append("<li><img src="+li.portrait+"></li>");
					te++;
				}
			}
			$("#sandengjiangp").append("已有"+san+"个好友<br>帮忙抽到");
			$("#erdengjiangp").append("已有"+er+"个好友<br>帮忙抽到");
			$("#yidengjiangp").append("已有"+yi+"个好友<br>帮忙抽到");
			$("#tedengjiangp").append("已有"+te+"个好友<br>帮忙抽到");
			
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) 
    	{ 
    	}
	});
})
function fenxiangpyq(){
	$("#fenxiangpyqdiv").show();
}
function gbfenxiangpyq(){
	$("#fenxiangpyqdiv").hide();
}
function choujiangguize(){
	$("#choujiangguizediv").show();
}
function gbchoujiangguize(){
	$("#choujiangguizediv").hide();
}
function woyaochanyu(){
	$("#woyaochanyudiv").show();
}
function gbwoyaochanyu(){
	$("#woyaochanyudiv").hide();
}
function caipingzhanshi(){
	$("#caipingzhanshidiv").show();
}
function gbcaipingzhanshi(){
	$("#caipingzhanshidiv").hide();
}
function guanbimsdh(){
	$("#duihuanyzmdiv").hide();
}

function woyaofaqi(){
	var isguanzhu = $("#isguanzhu").val();
	if (isguanzhu == 0) {
		woyaochanyu();
	}else{
		window.location.href="/wxActivity/wxProductlist.do";
	}
}

