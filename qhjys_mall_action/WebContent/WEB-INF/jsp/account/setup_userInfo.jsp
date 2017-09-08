<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>飞券网-个人资料</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/account/setup_userInfo.js"></script>
<style type="text/css">
.uploadify {
	margin-left: 20px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="membercontent">
		<!--------------左侧------------->
		<jsp:include page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="9" /></jsp:include>
		<!--------------右侧------------->
		<div class="memberright">
			<div class="memberright_title">个人资料</div>
			<div class="member_userinfo">
				<form id="userInfoForm" action="" method="post">
					<div class="left">
						<p></p>
						<img id="img1" src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}" />
						<input type="hidden" id="sptp" name="sptp" />
						<div style="position:relative;display:inline-block;">
							<input type="hidden" id="img1u" name="imgs" value="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}" />
							<input class="uploadify" type="file" value="上传" id="update1" style="margin-left: 50px;" />
						</div>
						<p>
							<a href="#" class=""></a>
						</p>
						<i>支持JPG，JPEG，GIF，PNG，BMP，且小于5M</i>
					</div>
					<div class="right">
						<ul>
							<li>
								<span>昵称：</span>
								<input id="sex" name="nickname" type="nickname" value="${sessionScope.user.nickname }" />
							</li>
							<li>
								<span>性别：</span>
								<input id="sex" name="sex" type="radio" value="1" /><em>男</em>
								<input id="sex" name="sex" type="radio" value="2" /><em>女</em>
							</li>
							<li>
								<span>生日：</span>
								<input id="birthday" name="birthday" class="easyui-datebox" style="height: 30px; width: 100px;">
							</li>
							<li>
								<span>身份：</span>
								<input id="identity" name="identity" type="radio" value="1" /><em>学生</em>
								<input id="identity" name="identity" type="radio" value="2" /><em>在职</em>
								<input id="identity" name="identity" type="radio" value="3" /><em>自由职业</em>
								<input id="identity" name="identity" type="radio" value="4" /><em>其它</em>
							</li>
							<li>
								<span>个人情况：</span>
								<input id="maritalStatus" name="maritalStatus" type="radio" value="1" /><em>单身</em>
								<input id="maritalStatus" name="maritalStatus" type="radio" value="2" /><em>恋爱中</em>
								<input id="maritalStatus" name="maritalStatus" type="radio" value="3" /><em>已婚</em>
								<input id="maritalStatus" name="maritalStatus" type="radio" value="4" /><em>保密</em>
							</li>
							<li>
								<span>兴趣：</span>
								<input type="checkbox" name="interest" id="interest" value="1" /><em>美食</em>
								<input type="checkbox" name="interest" id="interest" value="2" /><em>电影</em>
								<input type="checkbox" name="interest" id="interest" value="3" /><em>购物</em>
								<input type="checkbox" name="interest" id="interest" value="4" /><em>旅游</em>
								<input type="checkbox" name="interest" id="interest" value="5" /><em>休闲娱乐</em>
								<input type="checkbox" name="interest" id="interest" value="6" /><em>丽人</em>
								<input type="checkbox" name="interest" id="interest" value="7" /><em>生活服务</em>
							</li>
							<li><span></span>选择你的兴趣使你获得个性化的团购推荐哦</li>
							<li><span></span><input class="button_red" type="submit" value="保存"></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>