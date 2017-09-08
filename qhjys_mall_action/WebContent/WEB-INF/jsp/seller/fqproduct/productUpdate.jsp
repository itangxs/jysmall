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
<title>飞券网商家后台中心-菜品编辑</title>
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet"
	href="/common/formValidator4.0.1/style/validator.css" />
<link type="text/css" rel="stylesheet"
	href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript"
	src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/DateTimeMask.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript"
		src="/js/seller/fqproduct/add_page.js"></script>
<script type="text/javascript"
	src="/js/seller/fqproduct/add_page_v.js"></script>
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
				name="position" value="25" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px;">菜品管理——修改菜品</h3>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="text-align: right;"><a
							href="/managermall/seller/fqproducts/manager.do" class="fontred">返回菜品列表</a></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th style="text-align: left; background: #fffef5;">
							<form id="signupForm" method="post"
								action="/managermall/seller/fqproducts/update.do"
								class="zcform" style="margin-left: 150px;">
								<p class="clearfix">
									<label class="one" for="contactname">菜品名称：</label> <input
										id="contactname" class="con-email" name="cpmc" value="${productInfo.productName}"><span
										id="contactnameTip"></span>
								</p>
								<p class="clearfix">
									<label class="one" for="con-email3">市场价：</label> <input
										id="scj" name="scj" class="con-email3" value="${productInfo.price}">
									&nbsp;元 <span id="scjTip"></span>
								</p>

								<p class="clearfix">
									<label class="one" for="kc">库存：</label> <input id="kc"
										name="kc" class="con-email3" value="${productInfo.stock}">&nbsp;个 <span id="kcTip"></span>
								</p>

								<p class="clearfix">
									<label class="one" for="xsyxj">显示优先级：</label> <input id="xsyxj"
										name="xsyxj" class="con-email3" value="${productInfo.level}">&nbsp;1~99 级<span
										id="xsyxjTip"></span>
								</p>
								
								<p class="clearfix">
									<label class="one" for="cplb">菜系类别：</label>
									<select name="cplb" id="cplb" style="margin-top: 8px;">
										<option value="">---请选择类别---</option>
										<c:forEach items="${productTypes}" var="pt">
											<option value="${pt.id}" <c:if test="${pt.id == productInfo.productType }">selected="selected"</c:if>>${pt.typeName}</option>
										</c:forEach>
									</select>
									<span id="cplbTip"></span>
								</p>

								<p class="clearfix">
									<label class="one" for="con-email">菜品图片：</label> <input
										type="hidden" id="sptp" name="sptp" />
								<div style="position: relative; display: inline-block;">
									<img id="img1" src="${productInfo.productImages}" width="150" height="120" alt="" /> 
									<input type="hidden" id="img1u" name="imgs" value="${productInfo.productImages}" /><input
										class="uploadify" type="file" value="上传" id="update1" />
								</div>&nbsp;&nbsp;(请上传一张440px*300px的图片)
								<p class="clearfix"></p>
								<!-- <label class="one" for="con-email"></label> <span id="sptpTip"></span> -->
								<p class="clearfix">
									<label class="one" for="cpxx">菜品信息：</label>
								</p>
								<p class="clearfix">
									<textarea id="cpxx1" style="display: none;">${productInfo.productInfo}</textarea>
									<script type="text/plain" id="cpxx" name="cpxx"></script>
									<script type="text/javascript">
										//实例化编辑器
										var um = UM.getEditor('cpxx', {
											initialFrameHeight : 329,
											initialFrameWidth : 533,
											autoHeightEnabled : false
										});
									</script>
								</p>
								<p class="clearfix">
									<label class="one" for="con-email"></label> <input
										class="submit10" type="submit" value="提交" />
								</p>
								<input type="hidden" name="token" value="${token}" />
								<input type="hidden" name="prodId" value="${productInfo.id}" />
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