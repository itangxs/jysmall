Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

var getProdReviews = function(prodId, level, pageNum) {
	$.ajax({
		async : true,
		type : "POST",
		url : 'queryProdReviews.do',
		data : {
			"prodId" : prodId,
			"level" : level,
			"pageNum" : pageNum,
			"pageSize" : 10
		},
		success : function(data) {
			var reviews = $("#reviews");
			reviews.html("");
			$(".page").remove();
			var revDate, revImgs;
			$(data.page).each(function() {
				var lis = $('<li></li>');
				var avatar = $('<div class="pingjianr_people"><img src="/images/login_img.jpg" /></div>');
				var context = $('<div class="pingjianr_nr"></div>');
				var tag = $('<h1></h1>');
				var imgs = $('<div class="pingjiapic"></div>');
				revDate = new Date(this.revDate);
				if (this.userAvatar)
					avatar.find("img").attr('src', this.userAvatar);
				tag.append('<div class="wuxing"><span><em style="width:' + this.score * 20 + '%;"></em></span></div>');
				tag.append(revDate.format("yyyy-MM-dd hh:mm:ss") + " " + this.nickname + "说：");
				context.append(tag);
				context.append('<p>' + this.reviews + '</p>');
				if (this.reviewImg) {
					revImgs = this.reviewImg.split("|");
					for (var i = 0; i < revImgs.length; i++) {
						if(revImgs[i].length>1){
							imgs.append('<img src="' + revImgs[i] + '"/>');
						}
					}
					context.append(imgs);
				}
				if (this.replyContent)
					context.append('<div class="pingjianr_reply"><p>[回复] ' + new Date(this.replyTime).format("yyyy-MM-dd hh:mm:ss")
							+ '</p><p>' + this.replyContent + '</p></div>');
				lis.append(avatar);
				lis.append(context);
				lis.append('<div class="clear"></div>');
				reviews.append(lis);
			})
			var pages = '<div class="page"><span>共' + data.pages + '页</span>';
			if (data.num > 1)
				pages += '<a href="javascript:changepage(-1,'+data.num+');" class="prev">上一页</a>';
			for (var i = 1; i <= data.pages; i++) {
				if (i == data.num)
					pages += '<em class="current">' + i + '</em>';
				else if (data.pages < 8 || i < 4 || i > data.pages - 4)
					pages += '<a href="javascript:;changepage(-3,'+i+')">' + i + '</a>';
				else
					pages += '<em>...</em>';
			}
			if (data.num < data.pages)
				pages += '<a href="javascript:changepage(1,'+data.num+');" class="next">下一页</a>';
			pages += '<span>到第</span>';
			pages += '<input type="text" name="jumpPage" id="jumPage" class="input1" onChange="changepage(0,'+data.num+')"><span>页</span></div>';
			reviews.parent().append(pages);
		}
	});
};

function changepage(upordown,num){
	var prodId = $("#prodId"), level = $("input[name='level']:checked");
	var gonum =num;
	if(upordown == 1){
		gonum = num+1;
	}else if(upordown == -1){
		gonum = num-1;
	}else if(upordown == 0){
		gonum = $("#jumPage").val();
	}
	getProdReviews(prodId.val(), level.val(), gonum);
	
}

$(function() {
	var prodId = $("#prodId"), level = $("input[name='level']:checked");
	getProdReviews(prodId.val(), level.val(), 1);
	var maxPay = parseInt($('#maxPay').val());
	var maxPrice = parseInt($('#maxPrice').val());
	var stock = parseInt($('#stock').val());
	$('#add').on('click', function() {
		var $val = parseInt($('#prodNum').val()) + 1;
		if ($val > maxPrice ){
			alert("购买数量不能超过商品价格");
			return false;
		}
		$('#prodNum').val($val);
	});
	$('#subtract').on('click', function() {
		var $val = parseInt($('#prodNum').val()) - 1;
		if ($val < 1){
			alert("数量不能少于1");
			return false;
		}
		$('#prodNum').val($val);
	});
	$('#prodNum').on('change', function() {
		var $val = parseInt($('#prodNum').val());
		if ($val >maxPrice){
			alert("购买数量不能超过商品价格");
			$('#prodNum').val(maxPrice);
		}
		if ($val < 1){
			alert("数量不能少于1");
			return false;
		}
	});
	$('a[name=qiang],a[name=jiaru]').on('click', function() {

		var doType = (this.name == 'qiang' ? 1 : 0);
		var prodId = $('#prodId').val();
		var prodNum = $('#prodNum').val();
		var reg = /^[1-9]*[1-9][0-9]*$/;
		if (!reg.test(prodNum)) {
			alert("商品数量错误！");
			return false;
		}
		if (stock<1) {
			alert("商品数量不足!");
			return false;
		}
		addCartProd(prodId, prodNum, doType);
	});
	$('#shopMsg>div.close,#close').on('click', function() {
		$('#shopMsg').css("display", "none");
	});
	$(".linkcurrent").click(function(){
		var prodId = $("#prodId"), level = $("input[name='level']:checked");
		getProdReviews(prodId.val(), level.val(), 1);
	});
	$('a[name=goTop]').on("click", function() {
		$('html, body').animate({
			scrollTop : 0
		}, 600);
	});
	$("#rtanchu1").mouseover(function (){
		$("#tanchu1").show();
	});
	$("#rtanchu1").mouseout(function (){
		$("#tanchu1").hide();
	});
	
	$("#rtanchu4").hover(function(){
		$("#tanchu4").show();
	},function(){
		$("#tanchu4").hide();
	});
	
});

/**
 * 添加到购物车
 * @param prodId
 * @param prodNum
 * @param doType
 */
var addCartProd = function(prodId, prodNum, doType) {
	$.ajax({
		async : true,
		type : "POST",
		url : '/managermall/account/addCartProd.do',
		data : {
			prodId : prodId,
			prodNum : prodNum
		},
		success : function(data) {
			if (data == 'success') {
				if (doType == 1) // 去下单
					location.href = "/managermall/account/toCartConfirm.do";
				else // 继续浏览
					$('#shopMsg').css("display", "block");
			} else if (data == 'userNull') {
				alert('请先登录！');
				if (doType == 1)
					location.href = "/account/login.do";
			} else if (data == 'prodNull') {
				alert('商品参数错误！');
			} else if (data == 'prodError') {
				alert('该商品已下架！');
			} else
				alert('加入购物车出错，请重试！');
		}
	});
};


/**
 * 收藏商品
 * @param proid
 * @param status
 */
function shoucang(proid, status) {
	//1是添加.2是删除
	if(1==status){
	/*	var param = 'proId'+proid;*/
		var urlval = '/managermall/account/order/addMyProdCollet.do?proId='+proid;
	   $.ajax({
		   async: true,
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    /*	data:param,//传给后台的数据，多个参数用&连接
*/	    	success:function(msg){
	    		if (msg=='addError'){
	    		}else 
	    		if (msg=='userNull'){
	    			alert("请先登录");
	    			window.location.href = "/account/login.do";
	    		}else 
				if (msg=='error'){
					
	    		}else 
				if (msg=='sccuess'){
					window.location.href = window.location.href;
				}else{
					alert("请先登录");
	    			window.location.href = "/account/login.do";
				}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    		alert("服务器忙");
	    	}
	    });
	}
	if(2==status){
		var urlval = '/managermall/account/order/deleteCollet.do?prodId='+proid;
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    /*	data:param,//传给后台的数据，多个参数用&连接
	*/	    	success:function(msg){
		    		if (msg=='addError'){
		    		}
		    		if (msg=='userNull'){
		    			alert("请先登录");
		    			window.location.href = "/account/login.do";
		    		}
					if (msg=='error'){
						
		    		}
					if (msg=='scuess'){
						window.location.href = window.location.href;
					}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    	//ajax提交失败的处理函数！
		    	}
		    });
			
	}
}

function diag1(address) {
	var str = prompt("请输入出发地:比如", "前海金钥匙电子商务有限公司");
	if (str !== "" && str != "前海金钥匙电子商务有限公司" && null != str) {
		var url = "http://api.map.baidu.com/direction?origin=" + str + "|name:" + str + "&destination=" + address
				+ "&mode=driving&region=深圳&output=html&src=yourCompanyName|yourAppName"
		window.open(url);
	}
}