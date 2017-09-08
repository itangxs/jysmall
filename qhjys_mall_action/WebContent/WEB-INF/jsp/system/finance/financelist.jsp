<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/finance/store_loan.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     	<h3 class="jinrongtitle">项目数据</h3>
     	<form id="from" name="form" action="/managermall/systemmall/finance/financelist.do" method="post">
	 	<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
	 </form>
        <div class="jinrongbk">
        	<ul>
            	<li>当前项目数：<span class="fontred">${current.currentNum }</span> 家</li>
                <li>当前放款金额：<span class="fontred">${current.currentMoney }</span> 元</li>
                <li>当前未回收本金：<span class="fontred">${current.currentNorepayment }</span> 元</li>
                <li>历史项目数：${history.historyNum } 家</li>
                <li>历史放款金额：${history.historyMoney } 元</li>
                <li>历史服务费收入：${history.historyInterestTotal } 元</li>
                <div class="clear"></div>
            </ul>
        </div>
        <h3 class="jinrongtitle">项目列表</h3>
          <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">商家名称</th>
                    <th class="td160">放款金额</th>
                    <th class="td160">剩余本金</th>
                    <th class="td160">放款时间</th>
                    <th class="td160">到期时间</th>
                    <th class="">操作</th>
                </tr>
              
              <c:forEach items="${page}" var="u">
                <tr>
                    <td class="td160">${u.storeName }</td>
                    <td class="td160">￥${u.loanTotal }</td>
                    <td class="td160">￥${u.norepayment }</td>
                    <td class="td160"><fmt:formatDate value="${u.loanDate }" pattern="yyyy-MM-dd "/></td>
                    <td class="td160"><fmt:formatDate value="${u.endDate }" pattern="yyyy-MM-dd "/></td>
                    <td class=""><a href="/managermall/systemmall/finance/financedetail.do?id=${u.id }">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/managermall/systemmall/finance/financelistRepayment.do?id=${u.id }">还款</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:deleteBy('${u.id }','${u.status }')">删除</a></td>
                </tr>
              </c:forEach>
              </table>
            </div>
            <!--上一页下一页-->
        <div class="page">
            	<c:if test="${page.getPageNum()>1}">
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${page.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
