<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/system/store/store_check_list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>





<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="3"/></jsp:include>
	<!-- end -->
	
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——店铺列表</h3>
  
        <form id="from"  class="zcform1" method="post" action="storeCheckLsit.do">
         <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}">
        	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input type="submit" value="查询" class="submit8">
           </p>
           <p class="clearfix" style="margin-top:8px;">
           <!--   <input type="submit" value="管理店铺" class="submit9">
             <label for="select">&nbsp;&nbsp;&nbsp;</label>        -->
             <input type="button" value="审核通过" class="submit9" onclick="updateStauts(2)">
              <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="button" value="审核不通过" class="submit9" onclick="updateStauts(1)">
         <!--     <label for="select">&nbsp;&nbsp;&nbsp;</label>       
             <input type="submit" value="修改密码" class="submit9"> -->
           </p>
           
        </form>
        
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td80">
                    <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
                    <th class="td160">ID</th>
                    <th class="td160">店铺ID</th>
                    <th class="td160">店铺名称</th>
                    <th class="td160">审核状态</th>
                </tr>
              <c:forEach items="${page}" var="s">
                <tr>
                    <td class="td80">
                   	 <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
                   	 </td>
                    <td class="td160">${s.id}</td>
                    <td class="td160">${s.storeId}</td>
                    <td class="td160">${s.name }</td>
                    <td class="td160">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核失败</c:if>
                    	<c:if test="${s.status == 2  }">审核通过</c:if>
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
