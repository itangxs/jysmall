<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/seller/header.jsp" flush="true" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>飞券网招商注册页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/register.css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jysmall_regsiter.js"></script>
</head>
<body>
	<div id="n3"></div>
	<div id="regtext">
		<div id="regtextm">
			<br /> 温馨提示：<br /> 1、手机号码是您找回密码的唯一途径,请慎重填写；<br />
			2、"*"为必填项，手机号码一旦注册不能修改，为了您的账号安全，建议您使用您本人的手机号码进行注册；<br />
			3、注册成功时，您的初始交易密码默认与初始登录密码一致；<br />
			4、为了您的账户安全，在注册成功后，请您尽快在"我的账户"的"修改密码"中，尽快修改您的交易密码。
		</div>
	</div>
	<div id="zslc"></div>
	<div id="regmain">
		<div id="regmaint">
			<div class="wrapper">
				<form id="signupForm" method="post" action="" class="zcform">
					<p class="clearfix">
						<label class="one" for="username">用户名：</label>
						<input id="username" name="username" class="input">
					</p>
					<p class="clearfix">
						<label class="one" for="password">登录密码：</label>
						<input id="password" name="password" type="password" class="input">
					</p>
					<p class="clearfix">
						<label class="one" for="confirm_password">确认密码：</label>
						<input id="confirm_password" name="confirm_password" type="password" class="input">
					</p>
			        <p class="clearfix">
			        	<label class="one" for="sellerPhone">手机号码：</label>
        				<input id="sellerPhone" name="sellerPhone" class="input">
			        </p>
					<p class="clearfix">
						<label class="one">校验码：</label>
						<input id="identifying_code" type="text" class="identifying_code" />
						<input class="get_code" type="button" value="获取验证码" />
					</p>
					<p class="clearfix agreement">
						<input type="checkbox" id="isAgree" /> <b class="left">我已阅读并同意<a
							href="#">《飞券网用户协议》</a>和<a href="#">《隐私保密》</a></b>
					</p>
					<p class="clearfix">
						<input class="submit" type="submit" value="立即注册" />
					</p>
				</form>
			</div>
		</div>
	</div>
	<div id="registerwz">
		<div id="regtextm">
			<br /> 常见问题<br /> 1.与金钥匙合作需要收费吗？<br />
			具体合作费用会在合作申请通过后，有营销顾问与您洽谈（不同合作项目及地区标准会有不同）。<br />
			2.提交合作信息后多长时间会给予回复？<br /> 如果您的项目符合点评合作要求，营销顾问会在10天内与您联系；<br />
			如果超过10天未回复，表示暂时对您的项目没有合作意向，将来有意向会优先联系。<br />
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
