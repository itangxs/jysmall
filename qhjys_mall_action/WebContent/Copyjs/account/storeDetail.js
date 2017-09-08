Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

var getProdReviews = function(storeId, level, pageNum) {
	$.ajax({
		async : true,
		type : "POST",
		url : 'queryStoreReviews.do',
		data : {
			"storeId" : storeId,
			"level" : level,
			"pageNum" : pageNum,
			"pageSize" : 10
		},
		success : function(data) {
			var reviews = $("#reviews");
			var content, pingjia, title, wuxing;
			var rateTime, ratName, imgs, revImgs, replyContent;
			$(data.page).each(function() {
				rateTime = new Date(this.revDate);
				content = $('<li></li>');
				pingjia = $('<div class="pingjianr_nr"></div>');
				title = $('<h1></h1>');
				wuxing = $('<div class="wuxing"><span><em style="width:' + this.score * 20 + '%;"></em></span></div>');
				title.append(wuxing);
				ratName = this.nickname;
				if (this.anonymous == 1)
					ratName = ratName.substr(0, 2) + '***' + ratName.substr(ratName.length-2, 2);
				title.append(rateTime.format("yyyy-MM-dd hh:mm:ss") + ' ' + ratName + ' 说：');
				pingjia.append(title);
				content.append('<div class="pingjianr_people"><img src="' + (this.userAvatar ? this.userAvatar : 'images/login_img.jpg') + '" /></div>');
				pingjia.append('<p>' + this.reviews + '</p>')
				imgs = $('<div class="pingjiapic"></div>');
				if (this.reviewImg) {
					revImgs = this.reviewImg.split("|");
					for (var i = 0; i < revImgs.length; i++)
						imgs.append('<img src="' + revImgs[i] + '"/>');
				}
				pingjia.append(imgs);
				replyContent = $('<div class="pingjianr_reply"></div>');
				if (this.replyContent) {
					replyContent.append('<p>[回复]</p>');
					replyContent.append('<p>' + this.replyContent + '</p>');
				}
				pingjia.append(replyContent);
				content.append(pingjia);
				content.append('<div class="clear"></div> ');
				reviews.append(content);
			})
			var pages = '<div class="page"><span>共' + data.pages + '页</span>';
			if (data.num > 1)
				pages += '<a href="javascript:;" class="prev">上一页</a>';
			for (var i = 1; i <= data.pages; i++) {
				if (i == data.num)
					pages += '<em class="current">' + i + '</em>';
				else if (data.pages < 8 || i < 4 || i > data.pages - 4)
					pages += '<a href="javascript:;">' + i + '</a>';
				else
					pages += '<em>...</em>';
			}
			if (data.num < data.pages)
				pages += '<a href="javascript:;" class="next">下一页</a>';
			pages += '<span>到第</span>';
			pages += '<input type="text" name="jumpPage" id="jumPage" class="input1"><span>页</span></div>';
			reviews.parent().append(pages);
		}
	});
};

$(function() {
	var pageNum = 2;
	var storeId = $('#storeId').val();
	$('input[name=level]').on('click', function() {
		getProdReviews(storeId, this.value, pageNum);
		pageNum++;
	});
	getProdReviews(storeId, null, 1);
	var imgStore = $('img.prodImg');
	$('div.smallpic img').on('click', function() {
		$('div.smallpic img').removeClass("a-current");
		$(this).addClass("a-current");
		imgStore.attr('src', $(this).attr('src'));
	});
	$('#collect').on('click', function() {
		var url = '/managermall/account/order/addMyStoreCollet.do?storeId=' + storeId;
		$.ajax({
			async : true,
			type : "POST",
			url : url,
			success : function(data) {
				if (data == 'userNull') {
					alert("请先登录！");
					window.location.href = "/account/login.do";
				} else if (data == 'storeNull') {
					alert("店铺编号错误！");
				} else if (data == 'insert') {
					$('#collect').removeClass('shoucang').addClass('yishoucang');
				} else if (data == 'delete') {
					$('#collect').removeClass('yishoucang').addClass('shoucang');
				} else {
					alert("请先登录！");
					window.location.href = "/account/login.do";
				}
			}
		});
	});
	
	$('#add').on('click', function() {
		var $val = parseInt($('#prodNum').val()) + 1;
		if ($val > 1000 ){
			alert("购买数量不能超过1000");
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
		if ($val >1000){
			alert("购买数量不能超过1000");
			$('#prodNum').val(1000);
		}
		if ($val < 1){
			alert("数量不能少于1");
			return false;
		}
	});
	$('a[name=qiang],a[name=jiaru]').on('click', function() {

		var doType = (this.name == 'qiang' ? 1 : 0);
		var prodNum = $('#prodNum').val();
	
		addCartProd(storeId, prodNum, doType);
	});
	$("#rtanchu4").hover(function(){
		$("#tanchu4").show();
	},function(){
		$("#tanchu4").hide();
	});
});
var addCartProd = function(storeId, prodNum, doType) {
	$.ajax({
		async : true,
		type : "POST",
		url : '/managermall/account/addCartProd.do',
		data : {
			storeId : storeId,
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
function diag1(address) {
	var str = prompt("请输入出发地:比如", "前海金钥匙电子商务有限公司");
	if (str !== "" && str != "前海金钥匙电子商务有限公司" && null != str) {
		var url = "http://api.map.baidu.com/direction?origin=" + str + "|name:" + str + "&destination=" + address
				+ "&mode=driving&region=深圳&output=html&src=yourCompanyName|yourAppName"
		window.open(url);
	}
}