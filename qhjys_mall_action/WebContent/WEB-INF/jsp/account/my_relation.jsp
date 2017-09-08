<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/my_relation.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="10"/></jsp:include>
    <!--------------右侧------------->
	<div class="memberright">
		<div class="memberright_title">安全中心</div>
		<div class="member_safe">
			<ul>
				<li>
				<input type="hidden" id="thirdtoken" value="">
				<input type="hidden" id="thirdtokentime" value="">
				<input type="hidden" id="username" value="">
				<input type="hidden" id="phone" value="">
				<c:if test="${!empty jysp2p}">
					<span class="member_safe_item1"> <i class="yishezhibg"></i>金钥匙网贷</span>
					<span class="member_safe_item3"> 您关联的账户名：${fn:substring(jysp2p.account, 0, fn:length(jysp2p.account)-4)}****<!--您还未关联账户--></span>
					<span class="member_safe_item4"><a href="javascript:;">已关联</a></span>
				</c:if>
				<c:if test="${empty jysp2p}">
				    	<span class="member_safe_item1"><i class="weishezhibg"></i>金钥匙网贷 </span>
                    <span class="member_safe_item3">
                    	您还未关联账户
                    </span>
                    <span class="member_safe_item4">
                   		<a href="javascript:;" onclick="setAccountShow('${sessionScope.user.thirdDay}')">关联账户</a>                        
                    </span>
				</c:if>
					
					<div id="setAccount" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:;" onclick="closeWindow1()"><span class="close">×</span></a>绑定账号
						</h5>
						<div class="tanchuinput">
								<p>
									<i>账户名：</i>
									<input id="account" name="account" class="input" type="text"/>
								</p>
								<p>
									<i>手机号：</i>
									<input class="input" id="phoneNum" name="phoneNum"  type="text" />
								</p>
								<p>
									<i></i><input class="button_red" name="saveValue" type="button" onclick="vifitAccount();" value="绑定账号" />
									<a href="javascript:;" onclick="closeWindow1()"><span>取消</span></a>
								</p>
								</div>
								</div>
								
						<div id="setCode" class="member_tanchubangding" style="width: 450px; display: none;">
						<h5>
							<a href="javascript:;" onclick="closeWindow2()"><span class="close">×</span></a>绑定账号
						</h5>		
						<div class="tanchuinput">
								<p>
									<i>验证码：</i>
									<input id="phonecode" name="phonecode" class="input" type="text" value="请输入手机收到的验证码" />
								</p>
								<p>
									<i></i><input class="button_red" name="saveValue" type="button" onclick="vifitCode();" value="确定" />
									<a href="javascript:;" onclick="closeWindow2()"><span>取消</span></a>
								</p>
						</div>
					</div>
				</li>
                <li>
                </li>	
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>