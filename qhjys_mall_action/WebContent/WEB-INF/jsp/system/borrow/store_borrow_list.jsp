<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心-借贷管理</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/borrow/store_borrow_list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="13" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/borrow/borrowLeft.jsp"><jsp:param name="position"  value=""/></jsp:include>
	<!-- end -->
	<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">${wxstorename}--借贷信息</h3>
        <form id="from"  class="zcform1" method="post" action="/managermall/systemmall/borrow/storeborrowlist.do">
         <input id="page" name="pageNum" type="hidden" value="${pageNum}">
           <p class="clearfix">
        	<label class="one" for="con-email4" >期数：</label>
        	<input class="con-email4" name="period" value="${period}">
        	<input id="wxstoreid" name="wxstoreid" type="hidden" value="${wxstoreid}">
        	<input id="storeName" name="storeName" type="hidden" value="${wxstorename}">
        	<input type="submit" value="查询" class="submit8">
           </p>
           <p class="clearfix">
            <!--  <input type="button" value="批量删除" class="submit9">
             <label for="select">&nbsp;&nbsp;&nbsp;</label> -->
             <input type="button" value="启用" class="submit8" onclick="updateStatus(1)">
             <label for="select">&nbsp;&nbsp;&nbsp;</label>
             <input type="button" value="关闭" class="submit8" onclick="updateStatus(2)">
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="button" value="添加新借贷" class="submit9" onclick="addnewcredit()">
           </p>
        </form> 
        <div class="member_myorder">
        <input type="button" value="返回上级菜单" class="tanchubutton"  
        onclick="javascript:window.location.href='/managermall/systemmall/borrow/brstorelist.do'">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td80">
				  	<input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/></th>
                    <th class="td80">期数</th>
                    <th class="td120">借款金额</th>
                    <th class="td120">上期未还金额</th>
                    <th class="td120">本期未还金额</th>
                    <th class="td120">开始时间</th>
                    <th class="td120">结束时间</th>
                    <th class="td100">负责人</th>
                    <th class="td100">负责人电话</th>
                    <th class="td120">每周预警金额</th>
                    <th class="td100">启用状态</th>
                </tr>
              <c:forEach items="${page}" var="c">
                <tr>
                	<td class="td80">
				    <input name="ids" type="checkbox" value="${c.id}" onclick="xuan('dx')" />
				    <td class="td80">${c.period}</td>
				    <td class="td120">${c.creditMoney}</td>
				    <td class="td120">${c.beforeMoney}</td>
				    <td class="td120">${c.noRevert}</td>
				    <%-- <td class="td120"><fmt:formatNumber value="${c.creditMoney}" type="currency" pattern="### ###.00"/></td>
				    <td class="td120"><fmt:formatNumber value="${c.beforeMoney}" type="currency" pattern="### ###.00"/></td>
				    <td class="td120"><fmt:formatNumber value="${c.noRevert}" type="currency" pattern="### ###.00"/></td> --%>
				    <td class="td120"><fmt:formatDate value="${c.startTime}" pattern="yyyy-MM-dd"/></td>
				    <td class="td120"><fmt:formatDate value="${c.endTime}" pattern="yyyy-MM-dd"/></td>
				    <%-- <td class="td160">
				    	<c:choose>
   							<c:when test="${c.status==0 }">
   								未开始
		   					</c:when>
		   					<c:otherwise>
		   						<fmt:formatDate value="${c.startTime}" pattern="yyyy-MM-dd"/>
		   					</c:otherwise>
						</c:choose>
				    </td> --%>
				    <%-- <td class="td160">
				    	<c:choose>
   							<c:when test="${c.status==2 }">
   								<fmt:formatDate value="${c.endTime}" pattern="yyyy-MM-dd"/>
		   					</c:when>
		   					<c:otherwise>
		   						未结束
		   					</c:otherwise>
						</c:choose>
				    </td> --%>
                    <td class="td100">${c.principal}</td>
                    <td class="td100">${c.principalPhone}</td>
                    <td class="td120">${c.weekMoney}</td>
                    <td class="td100">
                    	<c:if test="${c.status==0}">未启用</c:if>
                    	<c:if test="${c.status==1}">进行中</c:if>
                    	<c:if test="${c.status==2}">已结束</c:if>
                    </td>
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