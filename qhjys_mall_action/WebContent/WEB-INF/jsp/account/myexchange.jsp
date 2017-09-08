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
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="15"/></jsp:include>
    <!--------------右侧------------------>
	
	<div class="memberright">    
    	<div class="accountindex_left">
       	  <div class="accounttop">
           	  <div class="acctop1">
              	<div class="zhanghaoimg"><img src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}"/><p><a href="#">修改头像</a></p></div>
                <div class="zhanghaoinfo">
                	<p class="font18">${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}<span class="vipfont"><c:if test="${sessionScope.user.level==0}">初级会员</c:if>
					<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if></span></p>
                    <p>你目前的资料完善率为：<fmt:formatNumber  value="${jindu*100/6 }" pattern="###"/>%&nbsp;&nbsp;&nbsp; 手机：
			<c:if test="${sessionScope.user.phoneNum!= null && sessionScope.user.phoneNum!= ''}">${sessionScope.user.phoneNum}</c:if>
			<c:if test="${sessionScope.user.phoneNum == null || sessionScope.user.phoneNum == ''}">未绑定</c:if>
</p>
                    <div class="zhjindu"><div class="zhanghaojindu" style="width:${jindu*100/6 }%;"></div></div>
                </div>
                <div class="zhanghaobiji jybjl"><a href="/managermall/account/setupAccount.do" class="fontred">编辑个人资料</a></div>
              </div>
                <div class="acctop2"><span class="ybs">现有元宝：<b class="fontred"><fmt:formatNumber value="${sessionScope.userCashAccount.balance}"/></b>个</span><span class="ybs">冻结元宝：<b class="fontred"><fmt:formatNumber value="${sessionScope.userCashAccount.freezeMoney}"/>  </b>个</span><span class="jybjl"><a href="/managermall/account/myrecharge.do" class="fontred">金元宝记录</a></span></div>
          </div>
       	  <div class="adv"><a href="${ad.link == null?'#':ad.link }"><img src="${ad.image == null?'/images/madv.jpg':ad.image }" /></a></div>
   	    <div class="tabs">
    <a href="/managermall/account/myrecharge.do" >金元宝获取记录</a><a href="javascript:void(0)" class="tabaction">金元宝兑换记录</a>
                <div class="clear"></div>
            </div>
            <div class="centerrenwu" id="jybhqjl" style="display:;">
            	<dl>
                	<dt>
                    	<span class="width100">参与时间</span>
                        <span class="width280">名称</span>     
                        <span class="width100">支出金元宝</span>
                    </dt>
                    <c:forEach items="${page }" var="log">
                    <dd>
                    	<span class="width100"><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd"/></span>
                        <span class="width280"><em>${log.businessName }</em></span>  
                        <span class="width100"><i class="yuanbaoanniu">-<fmt:formatNumber  value="${log.amount }" pattern="#,###"/></i></span>
                    </dd></c:forEach>
                    
                </dl> 
            </div>
           
            <!--上一页下一页-->
            <div class="page">
                <form action="/managermall/account/myexchange.do" method="get">
                    <a href="/managermall/account/myexchange.do?page=${page.getPageNum()-1 }" class="prev">上一页</a>
                    <a href="/managermall/account/myexchange.do?page=${page.getPageNum()+1 }" class="next">下一页</a>
                   <span>共${page.getPages()}页</span><span>到第</span>
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
			if(pageNum != ""){
				window.location.href="/managermall/account/myexchange.do?page="+pageNum;
			}
		})
	})
</script>
</html>
