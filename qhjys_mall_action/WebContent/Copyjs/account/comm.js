$(function() {
	// 搜索类型切换
	$("div.topsearch_xxk>a").click(function() {
		$("div.topsearch_xxk>a").removeClass("xxkcur");
		$(this).addClass("xxkcur");
	});
	// Show购物车
	$("#myCart").hover(getCartProd, function(e) {
		$("div.indexgouwuche_tanchu").css("display", "none");
		$("div.indextop_jiesuan>a", this).css({
			"background-color" : "#e3e3e3",
			"border-color" : "#e3e3e3"
		});
	});
	// 删除购物车
	$("#cartDet").find("a[name=delProd]").on("click", delCartProd);
	$("#switchCity,#citys").hover(function() {
		if ($("#citys").css("display") == "none") {
			$("#switchCity").toggleClass("current");
			$("#citys").css("display", "block");
		}
	}, function() {
		if ($("#citys").css("display") == "block") {
			$("#citys").css("display", "none");
			$("#switchCity").toggleClass("current");
		}
	});

	$("#switchCity1,#citys1").hover(function() {
		if ($("#citys1").css("display") == "none") {
			$("#switchCity1").toggleClass("current");
			$("#citys1").css("display", "block");
		}
	}, function() {
		if ($("#citys1").css("display") == "block") {
			$("#citys1").css("display", "none");
			$("#switchCity1").toggleClass("current");
		}
	});

	$("#switchCity2,#citys2").hover(function() {
		if ($("#citys2").css("display") == "none") {
			$("#switchCity2").toggleClass("current");
			$("#citys2").css("display", "block");
		}
	}, function() {
		if ($("#citys2").css("display") == "block") {
			$("#citys2").css("display", "none");
			$("#switchCity2").toggleClass("current");
		}
	});
	
	$("#switchCity3,#citys3").hover(function() {
		if ($("#citys3").css("display") == "none") {
			$("#switchCity3").toggleClass("current");
			$("#citys3").css("display", "block");
		}
	}, function() {
		if ($("#citys3").css("display") == "block") {
			$("#citys3").css("display", "none");
			$("#switchCity3").toggleClass("current");
		}
	});

	$("#myOrderForm,#orderForm").hover(function() {
		if ($("#orderForm").css("display") == "none") {
			$("#myOrderForm").toggleClass("current");
			$("#orderForm").css("display", "block");
		}
	}, function() {
		if ($("#orderForm").css("display") == "block") {
			$("#orderForm").css("display", "none");
			$("#myOrderForm").toggleClass("current");
		}
	});
});

function search() {
	var keywork = encodeURI(encodeURI($("#keywork").val()));
	if (keywork == "")
		return;
	var tx = $('div.topsearch_xxk').find("a.xxkcur").text();
	if (tx == '搜商品')
		location.href = getRootPath() + "/searchProduct.do?type=pord&keywork=" + keywork;
	else
		location.href = getRootPath() + "/searchStore.do?keywork=" + keywork;
}

function getRootPath() {
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	var localhost = curPath.substring(0, pos);
	return localhost;
}

var getCartProd = function(e) {
	$("div.indextop_jiesuan>a", this).css({
		"background-color" : "#fff",
		"border-color" : "#e3e3e3 #e3e3e3 #fff"
	});
	var $gouwuche = $("div.indexgouwuche_tanchu");
	if ($gouwuche.css('display') != 'none')
		return false;
	$gouwuche.html('<ul id="cartDet"></ul>').css("display", "block");
	$.ajax({
		async : true,
		type : "POST",
		url : getRootPath() + "/managermall/account/getCartProd.do",
		success : function(data) {
			if (data == null || data == "") {
				$("#cartDet").html('<div class="nopro">购物车中还没有商品，赶紧选购吧！</div>');
				return;
			}
			var $html, $delA, num = 0, sum = 0;
			$(data).each(function() {
				var pid = this.prodId;
				var url = "/getProdDetail.do?prodId=" + pid;
				var url1 = "/getStoreDetail.do?storeId=" + this.storeId;
				$html = $("<li></li>");
				if (this.prodName == null || this.prodName == "") {
					var image = this.storeImg.split(",");
					$html.append('<img src="' + image[0] + '"/>');
					$html.append('<i><a href="' + url1 + '">' + this.storeName + '</a></i>');
				}else{
					$html.append('<img src="' + this.prodImg + '"/>');
					$html.append('<i><a href="' + url + '">' + this.prodName + '</a></i>');
				}
				$html.append('<div class="shanchu"><p><strong>' + this.prodPrice + '×' + this.prodNum
						+ '</strong></p></div>');
				$delA = $('<p><a name="delProd" href="javascript:;">删除</a></p>');
				$delA.find("a").data("prodId", this.prodId);
				$delA.find("a").data("createTime", this.createTime);
				$delA.find("a").data("storeId", this.storeId);
				$delA.on("click", delCartProd);
				$html.find("div.shanchu").append($delA);
				num += 1;
				sum += this.prodPrice * this.prodNum;
				$("#cartDet").append($html);
			});
			$("div.indextop_jiesuan_cishu").text(data.length);
			$("#cartDet").after('<h2><p>共<em>' + num + '</em>件商品，共计<span>' + sum
					+ '</span></p><p><a href="/managermall/account/toCartConfirm.do" class="qujiesuan">去购物车兑换</a></p></h2>');
			$("#div.indextop_jiesuan_cishu").text(data.length);
		}
	});
};

var delCartProd = function(e) {
	var prodId = $(e.target).data("prodId");
	var createTime = $(e.target).data("createTime");
	var storeId = $(e.target).data("storeId");
	$.ajax({
		async : true,
		type : "POST",
		url : "/managermall/account/delCartProd.do",
		data : {
			'prodId' : prodId,
			'createTime':createTime,
			'storeId':storeId
		},
		success : function(data) {
			location.href = location.href;
			if (data == null || data == "")
				location.href = "/index.do";
			$(e.target).closest("li").remove();
		}
	});
	return false;
};

function gwc() {
	$.ajax({
		async : true,
		type : "POST",
		url : "/managermall/account/getCartProd.do",
		success : function(data) {
			$("#jesuan_cisuan").text(data.length);
		}
	});
}