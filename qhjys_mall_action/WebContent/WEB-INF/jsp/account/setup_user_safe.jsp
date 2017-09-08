<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/setup_user_safe.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="9"/></jsp:include>
    <!--------------右侧------------->
	<div class="memberright">
			<c:set var="percent" value="5" />
			<c:if test="${empty user.password}"><c:set var="percent" value="${percent-1}" /></c:if>
			<%-- <c:if test="${empty userCashAccount.payCode}"><c:set var="percent" value="${percent-1}" /></c:if> --%>
			<c:if test="${empty user.phoneNum}"><c:set var="percent" value="${percent-1}" /></c:if>
			<c:if test="${empty user.email}"><c:set var="percent" value="${percent-1}" /></c:if>
			<c:if test="${empty user.question}"><c:set var="percent" value="${percent-1}" /></c:if>
			<c:if test="${empty user.realname}"><c:set var="percent" value="${percent-1}" /></c:if>
		<div class="memberright_title">安全中心</div>
		<div class="member_safe">
			<ul>
				<li>
					<div class="jindu">
						<span>您的安全等级：</span>
						<div class="jinduconp2p" style="width:${percent*100/5}%;"><i><fm:formatNumber value="${percent*100/5}" pattern="###"/>%</i></div>
					</div>
				</li>
				<li>
					<span class="member_safe_item1"> <i class="yishezhibg"></i>登录密码<input id="loginPassword" type="hidden" value="${user.password}"/></span>
					<span class="member_safe_item2"> <em class="">已设置</em></span>
					<span class="member_safe_item3">登录商城时需要使用的密码</span>
					<span class="member_safe_item4"><a href="javascript:;" onclick="setupPassword()">修改</a></span>
					<div id="setPassword" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:closeWindow();" ><span class="close">×</span></a>设置密码
						</h5>
						<div class="tanchuinput">
							<form id="setupPasswordForm" action="" method="post">
								<p>
									<span>当前密码：</span>
									<input id="nowPassword" name="nowPassword" class="input" type="password" />
								</p>
								<p>
									<span>密码：</span>
									<input class="input" id="password" name="password" type="password" />
								</p>
								<p>
									<span>确认密码：</span>
									<input class="input" id="confirm_password" name="confirm_password" type="password" />
								</p>
								<p>
									<span></span><input class="button_red" name="saveValue" type="submit" value="修改" />
									<a href="javascript:closeWindow();" ><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				<li>
					<c:if test="${empty userInfo.phoneNum}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>手机号</span>
					</c:if>
					<c:if test="${not empty userInfo.phoneNum}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>手机号</span>
					</c:if>
					<c:if test="${empty userInfo.phoneNum}">
					<span class="member_safe_item2"> <em class="fontred">未设置</em></span>
					<span class="member_safe_item3"> 绑定手机，可直接使用手机号登录 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setTel()">立即绑定</a></span>
					</c:if>
					<c:if test="${not empty userInfo.phoneNum}">
					<span class="member_safe_item2"> <em class="fontred">已设置</em></span>
					<span class="member_safe_item3"> 绑定手机，可直接使用手机号登录 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="updateTel()">换绑</a></span>
					</c:if>
					<div id="setTel" class="member_tanchubangding" style="width: 450px; display: none">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>绑定手机号码
						</h5>
						<div class="tanchuinput">
							<form action="" id="setTelFrom" method="post">
								<p>
									<span>手机号：</span><input id="phoneNum" name="phoneNum" class="input" type="text" />
								</p>
								<p>
									<span></span><input id="sendSMS1" type="button" value="获取动态码" class="button1" />
								</p>
								<p>
									<span>动态码：</span><input class="input" id="validateCode" name="validateCode" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="" type="submit" value="绑定" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
					<div id="updateTel" class="member_tanchubangding" style="width: 500px; display: none">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>绑定手机号码
						</h5>
						<div class="tanchuinput">
							<form action="" id="updateTelFrom" method="post">
								<p>
									<span>手机号：</span><input id="nowPhoneNum" name="nowPhoneNum" value="${userInfo.phoneNum}" readonly="readonly" class="input" type="text" />
								</p>
								<p>
									<span></span><input id="sendSMS2" type="button" value="获取动态码" class="button1" />
								</p>
								<p>
									<span>动态码：</span><input class="input" id="validateCode1" name="validateCode" type="text" />
								</p>
								<p>
									<span>新手机号：</span><input id="phoneNum1" name="phoneNum" class="input" type="text" />
								</p>
								<p>
									<span></span><input id="sendSMS3" type="button" value="获取动态码" class="button1" />
								</p>
								<p>
									<span>新动态码：</span><input class="input" id="newValidateCode" name="newValidateCode" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="" type="submit" value="绑定" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				<li>
					<c:if test="${empty userInfo.email}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>邮箱</span>
					</c:if>
					<c:if test="${not empty userInfo.email}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>邮箱</span>
					</c:if>
					<c:if test="${empty userInfo.email}">
					<span class="member_safe_item2"> <em class="">未设置</em></span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setEmail()">绑定邮箱</a></span>
					</c:if>
					<c:if test="${not empty userInfo.email}">
					<span class="member_safe_item2"> <em class="">已设置</em></span>
					<span class="member_safe_item3"> 您验证的邮箱：${userInfo.email} </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="updateEmail()">换绑</a></span>
					</c:if>
					<div id="setEmail" class="member_tanchubangding" style="width: 450px; display: none">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>绑定邮箱
						</h5>
						<div class="tanchuinput">
							<form action="" id="setEmailFrom" method="post">
								<p>
									<span>邮箱：</span><input id="email" name="email" class="input" type="text" />
								</p>
								<p>
									<span></span><input class="button_hui" type="submit" name="button" id="sendEmail" value="发送验证码到邮箱" />
								</p>
								<p>
									<span>验证码：</span><input class="input" id="emailValidateCode" name="emailValidateCode" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="" type="submit" value="绑定" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
					<div id="updateEmail" class="member_tanchubangding" style="width: 500px; display: none">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>绑定邮箱
						</h5>
						<div class="tanchuinput">
							<form action="" id="updateEmailFrom" method="post">
								<p>
									<span>邮箱：</span><input id="nowEmail" name="nowEmail" readonly="readonly" value="${userInfo.email}" class="input" type="text" />
								</p>
								<p>
									<span></span><input class="button_hui" type="submit" name="button" id="sendEmail1" value="发送验证码到旧邮箱" />
								</p>
								<p>
									<span>验证码：</span><input class="input" id="emailValidateCode1" name="emailValidateCode" type="text" />
								</p>
								<p>
									<span>邮箱：</span><input id="email1" name="email" class="input" type="text" />
								</p>
								<p>
									<span></span><input class="button_hui" type="submit" name="button" id="sendEmail2" value="发送验证码到邮箱" />
								</p>
								<p>
									<span>验证码：</span><input class="input" id="nowEmailValidateCode" name="nowEmailValidateCode" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="" type="submit" value="绑定" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				<%-- 
				<li>
					<c:if test="${empty userCashAccount.payCode}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>支付密码</span>
					</c:if>
					<c:if test="${not empty userCashAccount.payCode}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>支付密码</span>
					</c:if>
					<c:if test="${empty userCashAccount.payCode}">
					<span class="member_safe_item2"> <em class="fontred">未设置</em></span>
					<span class="member_safe_item3"> 保护账号安全，在提现时使用 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setPayCode()">立即设置</a></span>
					</c:if>
					<c:if test="${not empty userCashAccount.payCode}">
					<span class="member_safe_item2"> <em class="fontred">已设置</em></span>
					<span class="member_safe_item3"> 保护账号安全，在提现时使用 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="updatePayCode()">更换</a></span>
					</c:if>
					<div id="setPayCode" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>设置支付密码
						</h5>
						<div class="tanchuinput">
							<form id="setupPayCodeForm" action="" method="post">
								<p>
									<span>支付密码：</span><input id="payCode" name="payCode" class="input" type="password" />
								</p>
								<p>
									<span>确认密码：</span><input class="input" id="confirm_payCode" name="confirm_payCode" type="password" />
								</p>
								<p>
									<span></span><input class="button_red" name="saveValue" type="submit" value="添加" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
					<div id="updatePayCode" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>设置支付密码
						</h5>
						<div class="tanchuinput">
							<form id="updatePayCodeForm" action="" method="post">
								<p>
									<span>旧支付密码：</span><input id="nowPayCode" name="nowPayCode" class="input" type="password" />
								</p>
								<p>
									<span>新支付密码：</span><input id="payCode1" name="payCode" class="input" type="password" />
								</p>
								<p>
									<span>确认新密码：</span><input class="input" id="confirm_payCode1" name="confirm_payCode" type="password" />
								</p>
								<p>
									<span></span><input class="button_red" name="saveValue" type="submit" value="修改" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				 --%>
				<li>
					<c:if test="${empty userInfo.question}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>安全问题</span>
					</c:if>
					<c:if test="${not empty userInfo.question}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>安全问题</span>
					</c:if>
					<c:if test="${empty userInfo.question}">
					<span class="member_safe_item2"> <em class="fontred">未设置</em></span>
					<span class="member_safe_item3"> 保护账号安全 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setQuestion()">立即设置</a></span>
					</c:if>
					<c:if test="${not empty userInfo.question}">
					<span class="member_safe_item2"> <em class="fontred">已设置</em></span>
					<span class="member_safe_item3">保护账号安全 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setQuestion()">更换</a></span>
					</c:if>
					<div id="setQuestion" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span class="close">×</span></a>设置安全保护问题
						</h5>
						<div class="tanchuinput">
							<form id="setupQuestionForm" action="" method="post">
								<p>
									<span>密保问题：</span><input id="question" name="question" value="${userInfo.question}" class="input" type="text"/>
								</p>
								<p>
									<span>密保答案：</span><input class="input" id="answer" name="answer" value="${userInfo.answer}" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="saveValue" type="submit" value="确认提交" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				<li>
					<c:if test="${empty userInfo.realname}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>实名认证</span>
					</c:if>
					<c:if test="${not empty userInfo.realname}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>实名认证</span>
					</c:if>
					<c:if test="${empty userInfo.realname}">
					<span class="member_safe_item2"> <em class="fontred">未设置</em></span>
					<span class="member_safe_item3"> 保护账户安全，添加银行卡时须与之一致 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setCertified()">立即设置</a></span>
					</c:if>
					<c:if test="${not empty userInfo.realname}">
					<span class="member_safe_item2"> <em class="fontred">已设置</em></span>
					<span class="member_safe_item3">保护账户安全，添加银行卡时须与之一致 </span>
					<span class="member_safe_item4"> <a href="javascript:;" onclick="setCertified()">更换</a></span>
					</c:if>
					<div id="setCertified" class="member_tanchubangding" style="width: 470px; display: none">
						<h5>
							<a href="javascript:;" onclick="closeWindow()"><span>×</span></a>实名认证
						</h5>
						<div class="tanchuinput">
							<form id="setCertifiedForm" action="" method="post">
								<p>
									<span>真实姓名：</span><input class="input" id="realname" name="realname" value="${userInfo.realname}" type="text" />
								</p>
								<p>
									<span>身份证号码：</span><input class="input" id="cardId" name="cardId" value="${userInfo.cardId}" type="text" />
								</p>
								<p>
									<span></span><input class="button_red" name="" type="submit" value="确认" />
									<a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
								</p>
							</form>
						</div>
					</div>
				</li>
				<li>
				<c:if test="${userInfo.qqOpenId == null || userInfo.qqOpenId==''}">
					<span class="member_safe_item1"> <i class="weishezhibg"></i>QQ互联</span>
					</c:if>
					<c:if test="${userInfo.qqOpenId != null && userInfo.qqOpenId!=''}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>QQ互联</span>
					</c:if>
					<c:if test="${userInfo.qqOpenId == null || userInfo.qqOpenId=='' || empty userInfo.qqOpenId}">
					<span class="member_safe_item2"> <em class="fontred">未设置</em></span>
					<span class="member_safe_item3"> 绑定QQ,可以使用QQ快捷登录</span>
					<span class="member_safe_item4"> <a href="/qqLogin.do">立即设置</a></span>
					</c:if>
					<c:if test="${userInfo.qqOpenId != null && userInfo.qqOpenId!=''&& not empty userInfo.qqOpenId}">
					<span class="member_safe_item2"> <em class="fontred">已设置</em></span>
					<span class="member_safe_item3"> 绑定QQ,可以使用QQ快捷登录</span>
					<span class="member_safe_item4"> <a href="/qqLogin.do">更换</a></span>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>