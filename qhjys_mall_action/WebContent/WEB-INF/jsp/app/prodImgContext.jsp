<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/app/public.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#prod,#stor').on('click', function() {
		$(this).addClass('tabaction').siblings().removeClass('tabaction');
		if (this.id == 'prod') {
			$('#prodContext').css('display', 'block');
			$('#storContext').css('display', 'none');
		} else {
			$('#storContext').css('display', 'block');
			$('#prodContext').css('display', 'none');
		}
	});
});
</script>
</head>
<body>
	<div class="global">
	<c:choose>
			<c:when test="${check==true}">商品编号错误！</c:when>
			<c:otherwise>
				<div class="tabs">
					<a id="prod" href="javascript:;" class="tabaction">套餐内容</a><a id="stor" href="javascript:;">商家详情</a>
					<div class="clear"></div>
				</div>
				<div id="prodContext">
					<div class="proinfo">
                        <div class="procontent">${info.prodDetail}</div>
                    </div>
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
						<div class="ratelist">
							<div class="title">热门评价（${review.personNum}条）</div>
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
								<c:if test="${!empty i.reviewImg}">
                                <div class="ratelistimg">
                                    <ul>
                                    	<c:forTokens var="i" items="${i.reviewImg}" delims="|">
                                    	<li><img src="${i}"></li>
                                    	</c:forTokens>
                                    </ul>
                                </div>
                                </c:if>
							</div>
							</c:forEach>
							<div class="more">
								<a id="getRate" href="getReviewList.do?prodId=${info.id}">查看更多评论</a>
							</div>
						</div>
					</div>
				</div>
				<div id="storContext" style="display:none;">
					<div class="proinfo">
						<div class="procontent">${store.storeDetail}</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>