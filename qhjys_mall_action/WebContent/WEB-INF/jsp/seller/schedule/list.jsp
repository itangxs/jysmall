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
<script type="text/javascript" src="/js/seller/schedule/list.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="12" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">	
	        <div id="member_myorder">  
	           <ul class="menu0" id="menu0">    
	            <li onclick="setTab(0,0)" class="hover">预约管理</li>  
	            <li onclick="setTab(0,1)">预约设置</li> 
	           </ul>
	           <div class="main" id="main0">  
	           <ul class="block"><li>
	                 <div class="member_myorder">
	                   
	             <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="td160">产品</th>
	                    <th class="td160">今天</th>
	                    <th class="td160">4-2</th>
	                    <th class="td160">4-3</th>
	                    <th class="td160">4-4</th>
	                    <th class="td160">4-5</th>
	                    <th class="td160">4-6</th>
	                    <th class="td160">4-7</th>
	                    <th class="td80"><a href="#" class="fontred">></a></th>
	                </tr>
	            </table>
	            <div id="scheduleDateList">
		             <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                    <td class="td160">团购产品一</td>
		                    <td class="td160"> <input type="submit" value="预约已满" class="submit8"></td>
		                    <td class="td160"><input type="submit" value="可预约10" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约5" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约8" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约6" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约6" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约6" class="submit6"></td>
		                    <td class="td80">&nbsp;</td>
		                </tr>
		            </table>
		             <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                    <td class="td160">团购产品二</td>
		                    <td class="td160"> <input type="submit" value="可预约10" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约10" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约2" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约3" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约5" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约6" class="submit6"></td>
		                    <td class="td160"><input type="submit" value="可预约10" class="submit6"></td>
		                    <td class="td80">&nbsp;</td>
		                </tr>
		            </table> -->
	            </div>
	            </div>
	            </li></ul>  
	             <ul><li>
	                <div class="member_myorder">
	             <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="td130">商品名称</th>
	                    <th class="td160">是否可以预约</th>
	                    <th class="td80">最大预约数</th>
	                    <th class="td80">操作</th>
	                </tr>
	            </table>
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	            <c:forEach items="${sellerScheduleProductList }" var="sellerScheduleProduct">
	                <tr>
	                    <td class="td130">${sellerScheduleProduct.title }</td>
	                    <td class="td160">
	                    <label><input name="scheduleType" type="radio" value="1"  <c:if test="${sellerScheduleProduct.scheduleType==1 }">checked="checked" </c:if>style="margin-top:10px;"/>是 </label>
	                    <label><input name="scheduleType" type="radio" value="0"  <c:if test="${sellerScheduleProduct.scheduleType==0 }">checked="checked" </c:if>style="margin-top:10px;"/>否 </label></td>
	                    <td class="td80">${sellerScheduleProduct.scheduleer }</td>
	                    <td class="td80"><a href="/managermall/seller/schedule/updateScheduleProduct.do?id=${sellerScheduleProduct.productId}"  class="fontred">编辑</a> </td>
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