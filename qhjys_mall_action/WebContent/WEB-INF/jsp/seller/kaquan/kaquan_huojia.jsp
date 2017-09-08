<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -商品订单</title>
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<style>
.member_myorder {
	margin-top: 27px;
}

.td80 img {
	width: 30px;
	height: 30px;
	margin: 0 auto;
}
</style>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/cardcoupon/cardcoupon_huojia.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param
			name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param
				name="position" value="27" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<form action="/managermall/seller/kaquan/list.do"  method="post" id="from">
	            <input id="page" name="pageNum" type="hidden"
							value="${page.getPageNum() }">
        	</form>
			<h3 style="padding-left: 10px; margin-bottom: 25px;">卡券货架</h3>
			<div class="member_myorder">
				<div class="left-hj">
					<c:forEach items="${page }" var="card">
						<c:if test="${card.couponCfg==0 }">
							<div class="lpq-hj float-hj">
		                    	<div class="hj-content clearfix">
		                    		<h1 class="lpq-zt">
										<c:if test="${card.statusCfg==0 }">未投放</c:if>
										<c:if test="${card.statusCfg==1 || card.statusCfg==2 }">投放中</c:if>
									</h1>
									<h2>${card.name }</h2>
		                        </div>
		                        <div class="hj-bottom clearfix">                     	
		                            <p class="lpq-hj">
										<a href="javascript:detail('${card.id }')"
											>详情</a>
									</p>
									<p class="lpq-hj">
										<a href="javascript:clickDelete('${card.id }','${card.name }')">删除</a>
									</p>
		                        </div>
		                   	 </div>
						</c:if>
						<c:if test="${card.couponCfg==1 }">
							<div class="djq-hj float-hj">
		                    	<div class="hj-content clearfix">
		                    		<h1 class="lpq-zt">
										<c:if test="${card.statusCfg==0 }">未投放</c:if>
										<c:if test="${card.statusCfg==1 || card.statusCfg==2 }">投放中</c:if>
									</h1>
									<h2>${card.name }</h2>
		                        </div>
		                        <div class="hj-bottom clearfix">                     	
		                            <p class="djq-hj">
										<a href="javascript:detail('${card.id }')"
											>详情</a>
									</p>
									<p class="djq-hj">
										<a href="javascript:clickDelete('${card.id }','${card.name }')">删除</a>
									</p>
		                        </div>
	                        </div>
						</c:if>
						<c:if test="${card.couponCfg==2 }">
							<div class="zkq-hj float-hj">
		                    	<div class="hj-content clearfix">
		                    		<h1 class="lpq-zt">
										<c:if test="${card.statusCfg==0 }">未投放</c:if>
										<c:if test="${card.statusCfg==1 || card.statusCfg==2 }">投放中</c:if>
									</h1>
									<h2>${card.name }</h2>
		                        </div>
		                        <div class="hj-bottom clearfix">                     	
		                            <p class="zkq-hj">
										<a href="javascript:detail('${card.id }')"
											>详情</a>
									</p>
									<p class="zkq-hj">
										<a href="javascript:clickDelete('${card.id }','${card.name }')"
											>删除</a>
									</p>
		                        </div>
	                       </div>
						</c:if>
                        
					</c:forEach>
				</div>
                <div class="clear"></div>
                
                
			</div>
            
            <c:if test="${page.getPages()==0}">
    	<div align="center">
  			<font  color="red">暂时没有数据!</font>
    	</div>
   	</c:if>
	<div class="page">
          	<c:if test="${page.getPageNum()>1}"> 
          	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
          	</c:if>
          	<c:choose>
          	<c:when test="${page.getPages()<7}">
          		<c:forEach var="i" begin="1" end="${page.getPages()}">
          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
          		</c:forEach>
          	</c:when>
          	<c:when test="${page.getPages()>6}">
          		<c:forEach var="i" begin="1" end="3">
          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
          		</c:forEach>
          		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
          		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
          			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
          		</c:forEach>
          		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
          		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
          		</c:forEach>
          	</c:when>
          	</c:choose>
          	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
              <span>共${page.getPages()}页</span><span>到第</span>
              <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
    </div>
	
		</div>
		<div class="clear"></div>
        
        
	</div>
	
	
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />





	<!--卡券货架删除-鱼香肉丝-->
	<div id="huojia01" class="white_content">
		<p class="close02">提示</p>
		<div class="nr nr03">
			<div class="toufang_list">
				<p class="zj-step02">
					是否确认删除卡券：<br>
					<span id="hj_name"></span>
					<span id="hj_id"></span>
				<p>
			</div>

		</div>
		<div class="kqanniu01">
			<a href="javascript:void(0)"
				onclick="document.getElementById('huojia01').style.display='none';document.getElementById('fade').style.display='none'"
				style="background-color: #ccc;">取消</a> <a href="javascript:deleteCard()"
				onclick="document.getElementById('huojia01').style.display='none';document.getElementById('fade').style.display='none'"
				style="background-color: #ff8b00;">确认</a>

		</div>
	</div>


	<!--卡券货架详情弹窗--->
	<div id="huojia02" class="white_content" style="">
		<p class="close02">详情</p>
		<div class="nr nr03">

			<div class="kaquan_detail" style=" margin:60px 0 0 40px;">
				<div class="detail_left">
					<p class="clearfix">
						<label class="con-email130" for="con-email4">卡券名称： <span id="name">${cardCouponTemplate.name }</span></label>
					</p>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">领券限制： <span id="countLimit">${cardCouponTemplate.countLimit }</span></label>
					</p>

				</div>
				<div class="detail_right">
					<p class="clearfix">
						<label class="con-email130" for="con-email4">面额/折扣： <span id="couponCfg">
								<c:if test="${cardCouponTemplate.couponCfg==1 }">${cardCouponTemplate.amount }</c:if>
								<c:if test="${cardCouponTemplate.couponCfg==2 }">${cardCouponTemplate.discount }</c:if>
						</span></label>
					</p>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">有效期限：
							<span>
								<span id="validityStarttime">
				            		<c:if test="${cardCouponTemplate.validityCfg==0 }">
										<fm:formatDate value="${cardCouponTemplate.validityStarttime }"
											pattern="yyyy-MM-dd" />
									</c:if>
								</span>
								<span id="validityEndtime">
									<c:if test="${cardCouponTemplate.validityCfg==0 }">
				            			至<fm:formatDate value="${cardCouponTemplate.validityEndtime }"
											pattern="yyyy-MM-dd" />
									</c:if>
								</span>
								<span id="validityDay">
									<c:if test="${cardCouponTemplate.validityCfg==1 }">
			            				${cardCouponTemplate.validityDay }
			            			</c:if> 
			            		</span>
							</span>

						</label>
					</p>
				</div>
				<div>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">使用说明： <span id="useExplain">${cardCouponTemplate.useExplain }</span></label>
					</p>
				</div>

			</div>

		</div>
		<div class="kqanniu01">
			<a href="javascript:void(0)" style="background-color: #ccc;"
				onclick="document.getElementById('huojia02').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
		</div>
	</div>
	

	<!--弹出幕布-->
	<div id="fade" class="black_overlay"></div>
</body>
</html>