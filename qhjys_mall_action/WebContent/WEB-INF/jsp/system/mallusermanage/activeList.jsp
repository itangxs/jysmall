<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/activelist.js"></script>
</head>
<body>
<!-------------------top---------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" ><jsp:param name="position" value="5" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">活动列表——会员注册</h3>
     	<form id="from" name="form1" action="/managermall/systemmall/active/list.do" method="post">
          <input id="page" name="pageNum" type="hidden">
      
        </form>
         <p class="clearfix">
             <input type="button" value="重置密码"  class="submit9" onclick="resetPassword()" >
           </p>
          <div class="member_myorder">
         	<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                  <!--  <th class="td80">
                 	 <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/> -->
                   <th class="td160">起始时间</th>
                   <th class="td160">结束时间</th>
                   <th class="td160">通用卷元宝</th>
                   <!-- <th class="td160">所在地区</th>   -->
                   <th class="td160">商家卷元宝</th>
                   <th class="td160">有效期</th>
                  <!--  <th class="td160">账户余额</th> -->
                   <th class="td160">启用状态</th>
                   <th class="td160">操作</th>
               </tr>
              <c:forEach items="${page}" var="a">
                <tr>
                   <!--  <td class="td80">
                   	 <input name="ids" type="checkbox" value="${u.id}" onclick="xuan('dx')" /></td>
                    </td> -->
                    <td class="td160"><fmt:formatDate value="${a.beginTime}" pattern="yyyy-MM-dd"/></td>
                    <td class="td160"><fmt:formatDate value="${a.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td class="td160">${a.commonCoupon}</td>
                    <!-- <td class="td160">深圳</td>   -->
                    <td class="td160">${a.storeCoupon}</td>
                    <td class="td160">${a.couponValidity}</td>
                   <!--  <td class="td160">0.00</td>    -->
                    <td class="td160">
                  		<c:choose>
                  			<c:when test="${a.enabled==1}">启用</c:when>
                  			<c:when test="${a.enabled==0}">禁用</c:when>
                  			<c:otherwise>禁用</c:otherwise>
                  		</c:choose>
                    </td>   
                    <td class="td160"><a href="javascript:void(0);" onclick="activeEnabled('${a.id}')">
                    <c:choose>
                  			<c:when test="${a.enabled==1}">禁用</c:when>
                  			<c:when test="${a.enabled==0}">启用</c:when>
                  		</c:choose></a> | 
                    <a href="/managermall/systemmall/active/goUpdate.do?activeId=${a.id}">修改</a> </td>
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
