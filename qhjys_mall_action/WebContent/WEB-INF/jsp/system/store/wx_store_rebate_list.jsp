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
<title>飞券网平台管理中心-微信店铺</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="4"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——微信店铺折扣列表</h3>
        <form id="from"  class="zcform1" method="post" action="/managermall/systemmall/store/wx_store_rebate_list.do">
         <input id="page" name="pageNum" type="hidden" value="${pageNum}">
         <input  name="id" type="hidden" value="${id}">
        </form> 
        <input type="button" value="添加折扣" class="submit6" onclick="javascript:window.location.href='/managermall/systemmall/store/wx_store_rebate_page.do?sellerid=${sellerid}&sid=${id}'">
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
						<th class="td130">折扣</th>
						<th class="td80">是否可用</th>
						<th class="td130">开始时间</th>
						<th class="td80">结束时间</th>
						<th class="td130">操作</th>
					</tr>
					<c:forEach var="rebate" items="${page}">
					<tr>
					    <td class="td130">
					    	${rebate.rebate}
					   </td>
						
						<td class="td130">
							<c:if test="${rebate.enable==0}">不可用</c:if>
							<c:if test="${rebate.enable==1}">可用</c:if>
						</td>
						
						<td class="td130">${rebate.beginTime}
						</td>
						
						<td class="td130">${rebate.endTime}
						</td>
						
						<td class="td130">
							<a href="/managermall/systemmall/store/wx_store_rebate_page.do?id=${rebate.id}&sellerid=${sellerid}&sid=${id}">详情/修改</a><br />
							<a href="/managermall/systemmall/store/wx_store_rebate_delete.do?id=${rebate.id}&sellerid=${sellerid}&sid=${id}" onclick="return confirm('确定将此记录删除?')">删除</a><br /> 
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
