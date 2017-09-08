<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/storeInfo_center.js"></script>
<style type="text/css">
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param
				name="position" value="2" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div id="member_myorder">
				<div class="membershop">
					<div class="membershop_top">
						<div class="membershop_topl"></div>
						<div class="membershop_topr">
							<p>亲爱的用户：您的店铺</p>
							<h2>尚未完成认证！</h2>
							<h3></h3>
							<h4>金钥匙商家身份认证金钥匙控股集团提供的一项关于互联网企业商家身份有效性、真实性的认证服务</h4>
						</div>
					</div>
					<div class="membershop_main">
						<p>
							<input type="image" src="/images/renz.png" alt="Submit" style="margin-top: 15px;" onclick="serviceAgreement('${status}')" />
						</p>
					</div>
					<div class="membershop_bottom"></div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>