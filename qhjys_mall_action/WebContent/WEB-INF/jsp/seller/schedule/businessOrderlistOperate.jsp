<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心-预约管理</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/schedule/businessOrderlistOperate.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="14" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">	
	        <div id="member_myorder">  
	           <ul class="menu0" id="menu0">    
	            <li onclick="setTab(0,0)" class="hover">未读预约</li>  
	            <li onclick="setTab(0,1)">已处理预约</li> 
	           </ul>
	           <form id="form1" name="form1"  class="zcform3">
	           <p class="clearfix">
	           <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号：</label>
	        	<input id="con-email2" name="con-email2" >
	    
	             <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	             <input type="submit" value="查询" class="submit6">
	           </p>
	        </form>
	           <div class="main" id="main0">  
	             <ul class="block"><li>
	                  <div class="member_myorder">
	             <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                   
	                    <th class="td160">联系人</th>
	                    <th class="td160">电话</th>
	                    <th class="td160">预约时间</th>  
	                    <th class="td160">到店时间</th>
	                    <th class="td160">人数</th>
	                    <th class="td160">内容</th>
	                    <th class="td160">操作</th>
	                </tr>
	           
	                <c:forEach items="${sellerScheduleVoList1 }" var="sellerScheduleVo">
		                <tr>
		                     <td class="td160">${sellerScheduleVo.name }</td>
		                    <td class="td160">${sellerScheduleVo.phone }</td>
		                    <td class="td160"><fmt:formatDate value="${sellerScheduleVo.createDate}" pattern="MM-dd"/>  </td>  
		                    <td class="td160"><fmt:formatDate value="${sellerScheduleVo.reserTime}" pattern="MM-dd HH:mm"/></td>
		                    <td class="td160">${sellerScheduleVo.reserNum }</td>
		                    <td class="td160">${sellerScheduleVo.content }</td>
		                    <td class="td160"><c:if test="${sellerScheduleVo.status==1 }"><a href="/managermall/seller/schedule/agreement.do?id=${sellerScheduleVo.id }&status=2">同意</a>|
		                    <a href="/managermall/seller/schedule/agreement.do?id=${sellerScheduleVo.id }&status=3">拒绝</a></c:if></td>
		                </tr>
		            </c:forEach>
	            </table>
	                  </div>
	            </li></ul>  
	             <ul><li>
	                <div class="member_myorder">
	             <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="td160">联系人</th>
	                    <th class="td160">电话</th>
	                    <th class="td160">预约时间</th>  
	                    <th class="td160">到店时间</th>
	                    <th class="td160">人数</th>
	                    <th class="td160">内容</th>
	                    <th class="td160">操作</th>
	                </tr>
	                <c:forEach items="${sellerScheduleVoList2 }" var="sellerScheduleVo">
	                 <tr>
	                     <td class="td160">${sellerScheduleVo.name }</td>
	                    <td class="td160">${sellerScheduleVo.phone }</td>
	                    <td class="td160"><fmt:formatDate value="${sellerScheduleVo.createDate}" pattern="MM-dd"/>  </td>  
	                    <td class="td160"><fmt:formatDate value="${sellerScheduleVo.reserTime}" pattern="MM-dd HH:mm"/></td>
	                    <td class="td160">${sellerScheduleVo.reserNum }</td>
	                    <td class="td160">${sellerScheduleVo.content }</td>
	                    <td class="td160"><c:if test="${sellerScheduleVo.status==2 }">同意</c:if><c:if test="${sellerScheduleVo.status==3 }">拒绝</c:if> </td>
	                </tr>
	                </c:forEach>
	            </table>
	                  </div>
	             </li></ul>  
	           </div>  
	         </div>  
	        
	    	</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>