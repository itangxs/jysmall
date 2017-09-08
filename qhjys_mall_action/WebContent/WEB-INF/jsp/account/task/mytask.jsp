<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="飞券、飞券网、Free、Free Change、免费兑换、网上购物、积分购物、免费购物、金元宝、理财商城、积分兑换、积分商城、金钥匙商城、理财购物、本地生活服务、O2O商城 " />
<meta name="description" content="飞券网（www.jysmall.com）是基于互联网与移动互联网，为商户和用户提供服务信息发布与交易达成，使用户随时随地通过完成商户所发布的有趣、简单任务，累积奖励免费兑换各类商户优惠的新型互动生活服务类O2O（Online to Offline）商城平台。通过它您不仅能享受积分返利，尊享积分兑换、理财等会员权益，同时还能提供O2O模式服务，包括美食、电影、旅游、休闲娱乐、丽人、生活服务等线上购物及线下消费体验。" />
<title>飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<link href="/css/mymember.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/account/evaluate/add_page.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<c:if test="${mstatus == 11 }"><jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="11"/></jsp:include></c:if>
	<c:if test="${mstatus == 12 }"><jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="12"/></jsp:include></c:if>
	<c:if test="${mstatus == 13 }"><jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="13"/></jsp:include></c:if>
	<c:if test="${mstatus < 11 ||  mstatus>13}"><jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="11"/></jsp:include></c:if>
	
    <!--------------右侧------------------>
	
	<div class="memberright">    
    	<div class="accountindex_left">
       	  <div class="accounttop">
           	  <div class="acctop1">
              	<div class="zhanghaoimg"><img src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}"/><p><a href="/managermall/account/setupUserInfo.do">修改头像</a></p></div> 
                <div class="zhanghaoinfo">
                	<p class="font18">
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  != 'JYS' && sessionScope.user.nickname != ''}">${sessionScope.user.nickname }</c:if>
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  == 'JYS' || sessionScope.user.nickname == ''}">
							${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}
						</c:if>
					<span class="vipfont"><c:if test="${sessionScope.user.level==0}">初级会员</c:if>
					<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if></span></p>
                    <p>你目前的资料完善率为：<fmt:formatNumber  value="${jindu*100/6 }" pattern="###"/>%&nbsp;&nbsp;&nbsp; 手机：
			<c:if test="${sessionScope.user.phoneNum!= null && sessionScope.user.phoneNum!= ''}">${sessionScope.user.phoneNum}</c:if>
			<c:if test="${sessionScope.user.phoneNum == null || sessionScope.user.phoneNum == ''}">未绑定</c:if>
</p>
                    <div class="zhjindu"><div class="zhanghaojindu" style="width:${jindu*100/6 }%;"></div></div>
                </div>
                <div class="zhanghaobiji jybjl"><a href="/managermall/account/setupUserInfo.do" class="fontred">编辑个人资料</a></div>
              </div>
                <div class="acctop2"><span class="ybs">现有元宝：<b class="fontred"><fmt:formatNumber value="${sessionScope.userCashAccount.balance}"/></b>个</span><span class="ybs">冻结元宝：<b class="fontred"><fmt:formatNumber value="${sessionScope.userCashAccount.freezeMoney}"/>  </b>个</span><span class="jybjl"><a href="/managermall/account/myrecharge.do" class="fontred">金元宝记录</a></span></div>
          </div>
       	  <div class="adv"><a href="${ad.link == null?'#':ad.link }"><img src="${ad.image == null?'/images/madv.jpg':ad.image }" /></a></div>
   	    <div class="tabs">
              <a href="/account/task/mytask.do?mstatus=11" <c:if test="${mstatus == 11 }">class="tabaction"</c:if>>已完成任务</a><a href="/account/task/mytask.do?mstatus=12"  <c:if test="${mstatus == 12 }">class="tabaction"</c:if>>审核中任务</a><a href="/account/task/mytask.do?mstatus=13"  <c:if test="${mstatus == 13 }">class="tabaction"</c:if>>未通过审核任务</a>
                <div class="clear"></div>
            </div>
            <div class="centerrenwu" id="yiwancheng" style="display:;">
            	<dl>
                	<dt>
                    	<span class="width280">名称</span>
                        <span class="width100">参与时间</span>
                        <span class="width100">审核</span>
                        <span class="width100">获得元宝</span>
                    </dt>
                     <c:forEach var="it"  items="${upage}">
	                     <dd>
	                    	<span class="width280"><i class="xiaodian"></i><em>${it.taskName }</em></span>
	                        <span class="width100"><fmt:formatDate value="${it.ucreateTime}" pattern="yyyy-MM-dd"/></span>
	                        <span class="width100">
	                        <c:if test="${it.ustatus== 'c' || it.ustatus== '0'}">审核中</c:if>
	                        <c:if test="${it.ustatus== 's' || it.ustatus== '3'}">筛选</c:if>
	                        <c:if test="${it.ustatus== 'q'}">配额满</c:if>
	                        <c:if test="${it.ustatus== 'cp' || it.ustatus== '1'}">审核通过</c:if>
	                        <c:if test="${it.ustatus== 'cr'|| it.ustatus== '2'}">审核失败</c:if>
	                        </span>
	                        <span class="width100"><i class="yuanbaoanniu">+
	                         <c:if test="${it.ustatus== 'c'}">
	                         <fmt:formatNumber  value="${it.fulfilReward }" pattern="#,###"></fmt:formatNumber></c:if>
	                       <c:if test="${it.ustatus!= 'c'}"> <fmt:formatNumber  value="${it.totamt }" pattern="#,###"/></c:if></i></span>
	                    </dd>
                     </c:forEach>
                </dl> 
            </div>
            <!--上一页下一页-->
            <div class="page">
            <input type="hidden" id="mstatus" value="${mstatus}">
                <form action="/account/task/mytask.do" method="get">
                	<input type="hidden" name="mstatus" value="${mstatus }">
                    <a href="/account/task/mytask.do?page=${upage.getPageNum()-1 }&mstatus=${mstatus}" class="prev">上一页</a>
                    <a href="/account/task/mytask.do?page=${upage.getPageNum()+1 }&mstatus=${mstatus}" class="next">下一页</a>
                    <span>共${upage.getPages()}页</span><span>到第</span>
                    <input class="input1" name="page" id="changepage" type="text" /><span>页</span><input class="anniu" id="changebut" type="button" value="确定" />
                </form>
            </div>
        </div>
        <jsp:include  page="/account/task/uright.do"></jsp:include>
        <div class="clear"></div>
    	
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
<script type="text/javascript">
	$(function(){
		$("#changebut").click(function(){
			var pageNum = $("#changepage").val();
			var mstatus = $("#mstatus").val();
			if(pageNum != ""){
				window.location.href="/account/task/mytask.do?page="+pageNum+"&mstatus="+mstatus;
			}
		})
	})
</script>
</html>
