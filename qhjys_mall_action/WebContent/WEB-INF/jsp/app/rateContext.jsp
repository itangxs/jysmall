<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/app/public.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
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

$(function() {
	var pageNum = 2;
	var moreRate = $('#more');
	$('#getRate').on('click', function() {
		$.ajax({
			async : true,
			type : "POST",
			url : 'getReviewJson.do?prodId=${prodId}&pageNum=' + pageNum,
			success : function(data) {
				if (data == null || data == "" || data.length == 0)
					return;
				var content, pingjia, imgs, ratetime, revImgs;
				$(data).each(function() {
					ratetime = new Date(this.revDate);
					content = $('<div class="ratelist-content"></div>');
					pingjia = $('<div class="pingjia"></div>');
					imgs = $('<div class="pingjiapic"></div>');
					pingjia.append('<span class="ratetime">' + ratetime.format("yyyy-MM-dd hh:mm:ss") + '</span>');
					pingjia.append('<div class="pingjiafen1"><div class="starbg"><span style="width:' + this.score * 20 + '%;"></span></div></div>');
					if (this.anonymous == 1) {
						var name = this.nickname;
						pingjia.append('<span>' + name.substr(0, 2) + '***' + name.substr(name.length-2, 2) + '</span>');
					} else {
						pingjia.append('<span>' + this.nickname + '</span>');
					}
					content.append(pingjia);
					content.append('<p>' + this.reviews + '</p>');
					if (this.reviewImg) {
						revImgs = this.reviewImg.split("|");
						for (var i = 0; i < revImgs.length; i++)
							imgs.append('<img src="' + revImgs[i] + '"/>');
						content.append(imgs);
					}
					moreRate.before(content);
				});
				pageNum++;
			}
		});
	});
});
</script>
</head>
<body>
	<div class="global">
		<div class="ratedetail">
			<div class="proinfotitle">
				<span>用户评价</span>
			</div>
			<div class="pingjiafenall">
				<div class="pingjia1">
					<p class="font16"><fm:formatNumber value="${review.avgScore}" type="currency" pattern="0.0"/>分</p>
					<div class="pingjiafen">
						<div class="starbg"><span style="width:${review.avgScore*20}%;"></span></div>
						<div class="clear"></div>
					</div>
					<p class="font14">共有${review.personNum}人评价<br></p>
				</div>
				<div class="pingjia2">
					<div class="pingjiafen2">
						<span>好评</span>
						<div class="jindubg">
							<div class="jindutop" style="width:${review.praiseNum*100/review.personNum};"></div>
						</div>
						<span class="renshu">${review.praiseNum}</span>
					</div>
					<div class="pingjiafen2">
						<span>中评</span>
						<div class="jindubg">
							<div class="jindutop" style="width:${review.generalNum*100/review.personNum}%;"></div>
						</div>
						<span class="renshu">${review.generalNum}</span>
					</div>
					<div class="pingjiafen2">
						<span>差评</span>
						<div class="jindubg">
							<div class="jindutop" style="width:${review.badNum*100/review.personNum}%;"></div>
						</div>
						<span class="renshu">${review.badNum}</span>
					</div>
				</div>
			</div>
			<div id="ratelist" class="ratelist">
				<div class="title">热门评价（50条）</div>
				<c:forEach var="i" items="${page}">
				<div class="ratelist-content">
					<div class="pingjia">
						<span class="ratetime"><fm:formatDate value="${i.revDate}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
						<div class="pingjiafen1">
							<div class="starbg">
								<span style="width:${i.score*20}%;"></span>
							</div>
						</div>
						<c:if test="${empty i.anonymous || i.anonymous==0}">
						<span>${i.nickname}</span></c:if>
						<c:if test="${i.anonymous==1}">
						<span>${fn:substring(i.nickname,0,2)}***${fn:substring(i.nickname,fn:length(i.nickname)-2,fn:length(i.nickname))}</span>
						</c:if>
					</div>
					<p>${i.reviews}</p>
				</div>
				</c:forEach>
				<div id="more" class="more">
					<a id="getRate" href="javascript:;">查看更多评论</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>