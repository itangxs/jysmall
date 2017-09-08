<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${cms.title} - 飞券网</title>
<meta name="keywords" content="${cms.keywords}" />
<meta name="description" content="${cms.description}" />
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="membercontent">
		<!----------------左侧----------------------->
		<div class="memberleft">
			<!--左侧菜单-->
			<div class="membermenu">
				<ul>
					<li>
						<h1>
							<span class="fontred">网站协议</span>
						</h1>
						<p>
							<a href="/mallcms/info2.do?id=17"><span>·</span>用户服务协议</a>
						</p>
						<p>
							<a href="/mallcms/info2.do?id=16"><span>·</span>商户服务协议</a>
						</p>
						
					</li>

				</ul>
			</div>

		</div>
		<div class="memberright">
			<div class="memberright_title" id="info001">${cms.name}</div>
			<div class="member_myorder">${cms.content}</div>
		</div>

		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
	<!--底部-e-->
</body>
</html>
