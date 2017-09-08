<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心-菜系</title>


<link type="text/css" rel="stylesheet" href="/common/css/validator.css"></link>
<script src="/common/js/formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript"src="/js/seller/fqproduct/product_type.js"></script>


<script>
	window.UMEDITOR_HOME_URL = "/umeditor/"
</script>
<style type="text/css">
.uploadify {
	margin-left: 50px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param
			name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param
				name="position" value="26" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px;">菜系管理——
			<c:if test="${empty productType}">添加菜系</c:if>
			<c:if test="${!empty productType}">修改菜系</c:if>
			
			
			</h3>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="text-align: right;"><a
							href="javascript:history.go(-1)" class="fontred">返回菜系列表</a></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th style="text-align: left; background: #fffef5;">
							<form id="signupForm" method="post"
								action="/managermall/seller/fqproducts/product_type.do"
								class="zcform" style="margin-left: 150px;">
								<p class="clearfix">
									<label class="one" >菜系名称：</label> <input
										id="typeName" name="typeName" class="con-email" name="cpmc" value="${productType.typeName}"> <span
										id="typeNameTip"></span>
								</p>
								<p class="clearfix">
									<label class="one">显示优先级：</label> <input id="xsyxj"
										name="level"  value="${productType.level}" class="con-email3">&nbsp;1~10 级<span
										id="xsyxjTip"></span>
								</p>
								<p class="clearfix">
									<label class="one" >是否启用：</label>
									<select name="enable" id="enable">
										<option <c:if test="${productType.enable==0}">selected="selected" </c:if> value="0">否</option>
										<option <c:if test="${productType.enable==1}">selected="selected" </c:if> value="1">是</option>
									</select>
									
								</p>
								<input type="hidden" name="id" value="${productType.id}" />
								<input type="hidden" name="token" value="${typePageTokne}" />
								<p class="clearfix">
									<label class="one" for="con-email"></label> <input
										class="submit10" type="submit" value="提交" />
								</p>
							</form>
						</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
</body>
</html>