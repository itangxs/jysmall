<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="8"/></jsp:include>
	<!--------------右侧------------->
	<div class="memberright">
		<div class="member_money">
			<div class="tabs">
				<a href="/managermall/account/myRecharge.do" class="tabaction">充值</a><a href="/managermall/account/myWithdraws.do">提现</a>
				<div class="clear"></div>
			</div>
			<div class="tishi">
				<strong>温馨提示：</strong>
				<p>
					1、充值是指从银行卡提取到您飞券网电子账户中；<br />
					2、充值手续费为第三方支付平台收取的服务费，目前免费，由飞券网为您垫付；<br />
					3、充值金额没有限制；<br />
					4、充值完成后为保证帐户正常使用及资金安全，建议尽快完善帐户资料；<br />
					5、充值为工商银行的单笔不能超过￥200000（二十万）
				</p>
			</div>
			<div class="tishiul">
				<form id="form1" name="form1" method="post" action="/managermall/account/recharge.do">
					<ul>
						<li>账号：${sessionScope.user.getUsername()}</li>
						<li>账号余额：${sessionScope.userCashAccount.balance}</li>
						<li>可提现余额：${sessionScope.userCashAccount.balance}</li>
					</ul>
					<p>
						<em>支付平台：</em>
						<input name="" type="radio" value="" checked="checked"  />
						<img src="/images/lianlianzhifu.png" />
					</p>
					<p>
						<em>银行卡号：</em><input id="cardNo" name="cardNo" type="text" class="input1" />
					</p>
					<p>
						<em>充值元宝：</em><input name="rechargeMoney" type="text" class="input1" /> (${rechargeScale }金元宝需要支付1元)
					</p>
					<p>
						<em></em>
						<input class="button_red anniu" type="submit" name="button" id="button" value="登录到支付平台充值" />
						<input class="submit6" type="hidden" name="token" value="${myRechargeToken}" />
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