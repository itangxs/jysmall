<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/withdraws.js"></script>
<div class="membercontent">
	<!--------------左侧------------->
	<jsp:include page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="8" /></jsp:include>
	<!--------------右侧------------->

	<div class="memberright">
		<div class="member_money">
			<div class="tabs">
				<a href="/managermall/account/myRecharge.do" >充值</a><a href="/managermall/account/myWithdraws.do" class="tabaction">提现</a>
				<div class="clear"></div>
			</div>
			<div class="tishi">
				<strong>温馨提示：</strong>
				<p>1、这里可以将您的账户余额提现。</p>
				<p>2、申请后需要等待管理员审核通过，数据才能生效。</p>
				<p>3、提现之前请先认证您的银行卡。</p>
				<p>4、管理员审核通过的提现将在一到三个工作日内到达您的账户（具体到账时间以当地银行实际时间为准）。</p>
				<p>5、周六周日的申请统一周一处理。</p>
			</div>
			<div class="tishiul">
				<form id="form1" name="form1">
					<ul>
						<li>账号：${sessionScope.user.getUsername()}</li>
						<li>账号余额：${sessionScope.userCashAccount.balance}</li>
						<li>可提现余额：${sessionScope.userCashAccount.balance}</li>
					</ul>
					<h5>
						<em><a href="/managermall/account/myBank.do?status=2" class="button_red">设置银行账号</a></em>提现至本人已认证的银行账户<i>（请选择一个您的提款银行账户）</i>
					</h5>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" id="bank">
						<tr>
							<th>选择</th>
							<th>银行名称</th>
							<th>开户网点名称</th>
							<th>银行账号</th>
						</tr>
						<c:forEach var="page" items="${page}">
							<tr>
								<td><input type="radio" name="bankId" value="${page.id}" /> <label for="radio"></label></td>
								<td>${page.name}</td>
								<td>${page.branch}</td>
								<td>${page.carkNum}</td>
							</tr>
						</c:forEach>
					</table>
					<p>
						<em>交易密码：</em> <input id="payCode" name="payCode" type="password" class="input1" /> <span>请输入您的交易密码</span>
					</p>
					<p>
						<em>提现金额：</em> <input id="withdrawsMoney" name="withdrawsMoney" type="text" class="input1" /> <span>请输入您的提款金额</span>
					</p>
					<p>
						<em></em> <input class="button_red anniu" type="submit" name="button" id="button" value="        提 交 申 请        " />
						 <input class="submit6" type="hidden" id="token" name="token" value="${myWithdrawsToken}" />
					</p>
					<p class="fontred">
						<em></em>温馨提示：节假日期间不提供提现功能，请见谅！
					</p>
				</form>
			</div>
			<!--充值记录-->
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>