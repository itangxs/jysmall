<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript" src="/js/account/refund/apply_page.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="6"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
   		<div class="memberright_title">申请退款</div>
   		<form action="/managermall/account/refund/submitApply.do" method="post"  id="myForm">
			<div class="member_refundapply">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="td150">退款金额：</td>
						<td class="fontred"><strong><fmt:formatNumber value="${detailVo.payPric}" pattern="#,###" /></strong>金元宝</td>
					</tr>
					<tr>
						<td class="td150">退款方式：</td>
						<td><strong>退回平台余额</strong>（三个工作日以内到账）<!-- <p>平台余额可在下次团购时直接冲抵现金，也可随时提现</p> --></td>
					</tr>
					<tr>
						<td class="td150">退款原因：</td>
						<td class="fontred">
							<textarea name="reason" id="reason" rows="3" cols="25" maxlength="30"></textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" id="token" name="token" value="${token}">
				<input type="hidden" id="detailId" name="detailId" value="${detailVo.detailId}">
				<h2>
					<input type="button" class="button_red" value="申请退款" onclick="submit1(${id})" />
					<input type="reset" class="button_hui" onclick="javascript:history.go(-1)" value="取消申请" />
				</h2>
				<p>
					<strong>服务提示</strong><br /> 1、退款申请提交后需经过审核确认您的消费券未消费；<br /> 2、过期消费券和已消费的消费券不做退款处理；<br />
					3、审核通过后，退款会在3个工作日内退还到您的账户余额，请注意查收；<br /> 4、如果三个工作日后您未收到退款，请及时联系商家。<br />
				</p>
			</div>
		</form>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>