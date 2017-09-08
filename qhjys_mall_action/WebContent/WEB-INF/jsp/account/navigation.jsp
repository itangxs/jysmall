<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/css/member.css" />
<script type="text/javascript">
$(function() {
	$('#signIn').on('click', function() {
		var $el = $(this).attr('disabled', "true");
		$.ajax({
			async : true,
			type : "POST",
			url : "/signIn.do",
			success : function(data) {
				if (data == 'logOut')
					alert("用户登录超时！");
				else if (data == false) {
					alert("用户已签到！");
					return;
				} else if (data == true)
					;
				else
					alert("用户签到异常，请稍后重试！");
				window.location.reload(true);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="memberleft">
		<div class="memberleft_user">
			<a href="/managermall/account/setupUserInfo.do">
			<img src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}" />
			</a>
			<div class="memberleft_user_state">
				<h1>
					<p>${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}</p>
					<c:if test="${sessionScope.judgeSignIn}">
		      			<a href="javascript:;">已签到</a>
		      		</c:if>
		      		<c:if test="${!sessionScope.judgeSignIn}">
		      			<a id="signIn" href="javascript:;">签到</a>
		      		</c:if>
				</h1>
				<div class="phone_yes">
					<a id="myOrderForm" href="javascript:;" ></a>
					<c:if test="${!empty sessionScope.user.phoneNum}">
						<div id="orderForm" class="phone-info_tanchu" style="display:none">
							<div class="tanchujiantou"></div>
							<div class="tanchu_nr">已经绑定手机</div>
						</div>
						</c:if>
						<c:if test="${empty sessionScope.user.phoneNum}">
						<div id="orderForm" class="phone-info_tanchu" style="display:none">
							<div class="tanchujiantou"></div>
							<div class="tanchu_nr">
								未绑定手机：<a href="/managermall/account/setupUserSafe.do" class="fontred">去绑定</a>
							</div>
						</div>
					</c:if>
				</div>
				<!--编辑资料-->
				<div class="info_no">
					<a href="/managermall/account/setupUserInfo.do"></a>
					<div class="phone-info_tanchu" style="display:none">
						<div class="tanchujiantou"></div>
						<div class="tanchu_nr">编辑资料</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="memberleft_user_jifen">
			<span><br /><a href="/mallcms/info2.do?id=12">
				<c:if test="${sessionScope.user.level==0}">初级会员</c:if>
				<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if>
			</a></span>
			<span><a href="/managermall/account/rechargeList.do"><em>${sessionScope.userCashAccount.balance}</em><br />元宝</a></span>
			<div class="clear"></div>
		</div>
		<!--左侧菜单-->
		<div class="membermenu">
			<ul>
				<li>
					<h1>我的订单</h1>
					<p>
						<a href="/managermall/account/order/myOrderForm.do" class="${param.position eq '1'?'menucurrent':''}"><span>·</span>我的订单</a>
					</p>
					<p>
						<a href="/managermall/account/preordain/list.do" class="${param.position eq '2'?'menucurrent':''}"><span>·</span>我的优惠劵</a>
					</p>
					<p>
						<a href="/managermall/account/order/myCollet.do" class="${param.position eq '3'?'menucurrent':''}"><span>·</span>我的收藏</a>
					</p>
				</li>
				<li>
					<h1>我的评价</h1>
					<p>
						<a href="/managermall/account/evaluate/list.do" class="${param.position eq '4'?'menucurrent':''}"><span>·</span>商品评价</a>
					</p>
				</li>
				<li>
					<h1>我的消息</h1>
					<p>
						<a href="/managermall/account/message/zlist.do" class="${param.position eq '5'?'menucurrent':''}"><span>·</span>站内消息</a>
					</p>
				</li>
				<%-- <li>
					<h1>退款维权</h1>
					<p>
						<a href="/managermall/account/refund/list.do" class="${param.position eq '6'?'menucurrent':''}"><span>·</span>我的退款</a>
					</p>
				</li> --%>
				<li>
					<h1>我的账户</h1>
					<%--<p>
						<a href="/managermall/account/myPoints.do" class="${param.position eq '7'?'menucurrent':''}"><span>·</span>我的元宝</a>
					</p>
					 <p>
						<a href="/managermall/account/rechargeList.do" class="${param.position eq '8'?'menucurrent':''}"><span>·</span>我的余额</a>
					</p> --%>
					<p>
						<a href="/managermall/account/setupAccount.do" class="${param.position eq '9'?'menucurrent':''}"><span>·</span>账户设置</a>
					</p>
                   <%-- <p>
						<a href="/managermall/account/myThirdAccount.do" class="${param.position eq '10'?'menucurrent':''}"><span>·</span>关联账户</a>
					</p>--%>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>