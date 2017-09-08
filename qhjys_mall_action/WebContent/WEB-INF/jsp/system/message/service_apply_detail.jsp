<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
	<!-------------------top---------------------------------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param
			name="val" value="11" /></jsp:include>
	<!--------------------------我的账户-------------------------------------------------------->
	<div class="membercontent">
		<!----------------左侧----------------------->
		<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true"><jsp:param
				name="val" value="31" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="margin-bottom: 25px;">商家服务申请详情</h3>
			<div
				style="background: #f9f9f9; border: 1px solid #eee; padding: 15px; margin-bottom: 20px;">
				<p class="clearfix">
					<label class="one" for="con-email4">申请类型：</label>
					<c:if test="${apply.applyType == 1}">品牌包装申请</c:if>
					<c:if test="${apply.applyType==2}">营销活动申请</c:if>
					<c:if test="${apply.applyType==3}">设计服务申请</c:if>
					<c:if test="${apply.applyType==4}">金融支持申请</c:if>
					<c:if test="${apply.applyType==5}">广告投放申请</c:if>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">方案类型：</label>${apply.applyStyle }
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">商家名称：</label>${apply.storeName }
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">店铺ID：</label> ${apply.storeId }
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">申请时间：</label>
					<fmt:formatDate value="${apply.createTime}"
						pattern="yyyy-MM-dd HH:mm" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">状态：</label>
					<c:if test="${apply.status==1}"> 待处理</c:if>
					<c:if test="${apply.status==2}"> 处理中</c:if>
					<c:if test="${apply.status==3}"> 已完成</c:if>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">详情：</label> ${apply.applyInfo }
				</p>
				<c:if test="${apply.applyType==4}">
					<p class="clearfix">
						<label class="one" for="con-email4">商户工商登记信息：</label><br>
						<c:forEach items="${fn:split(support.businessImages, ',') }" var="bi">
							<img src="${bi }" width="500">
						</c:forEach>
						
					</p>
					<p class="clearfix">
						<label class="one" for="con-email4">借款人身份证正反面：</label> <br>
						<c:forEach items="${fn:split(support.idcardImages, ',') }" var="ii">
							<img src="${ii }" width="500">
						</c:forEach>
					</p>
					<p class="clearfix">
						<label class="one" for="con-email4">店铺门面照片：</label> <br>
						<c:forEach items="${fn:split(support.storeImages, ',') }" var="si">
							<img src="${si }" width="500">
						</c:forEach>
					</p>
					<p class="clearfix">
						<label class="one" for="con-email4">家庭地址：</label> ${support.address }
					</p>
					<p class="clearfix">
						<label class="one" for="con-email4">联系电话：</label> ${support.phoneNum }
					</p>
					<p class="clearfix">
						<label class="one" for="con-email4">借款用途：</label> ${support.useInfo }
					</p>
				</c:if>
			</div>
			<div></div>
		</div>
		<div class="clear"></div>
	</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
	<!--底部-e-->
</body>
</html>
