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
<script type="text/javascript" src="/js/seller/schedule/updateScheduleProduct.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="15" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
	      
		  <div class="member_myorder">
		  <form id="form1" name="form1" method="post" action="/managermall/seller/schedule/saveScheduleProduct.do"><input type="hidden" name="id" value="${sellerScheduleProductInfoVo.productId }"/>
	   	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <th width="292" class="td130"><span style="text-align:left;">编辑预约管理</span></th>
	                  <th width="292" class="td130">&nbsp;</th>
	                  <th width="292" class="td130">&nbsp;</th>
	                    <th width="636" class="td132" style="text-align:right"><a href="business_order_manage.do"  class="fontred">返回预约管理</a></th>
	                </tr>
	            </table>
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	                    <td width="80" style="text-align:right;">商品名称：</td>
	                    <td width="848" class="td508">${sellerScheduleProductInfoVo.title }</td>
	          </tr>
	                <tr>
	                  <td width="80" style="text-align:right;">是否可以预约：</td>
	                    <td width="848" class="td508"> <label><input name="scheduleType" type="radio" value="1"  <c:if test="${sellerScheduleProductInfoVo.scheduleType==1 }">checked="checked" </c:if>style="margin-top:10px;"/>是 </label>
	                    <label><input name="scheduleType" type="radio" value="0"  <c:if test="${sellerScheduleProductInfoVo.scheduleType==1 }">checked="checked" </c:if>style="margin-top:10px;"/>否 </label></td>
	                </tr>
	                <tr>
	                  <td width="80" style="text-align:right;">最大预约数：</td>
	                    <td width="848" class="td508"><input name="scheduleer" class="con-email3" value="${sellerScheduleProductInfoVo.scheduleer}" ></td>
	                </tr>
	                                
	                
	              <tr>
	                  <td style="text-align:right;">&nbsp;</td>
	                  <td class="td508">
	                    <input type="submit" name="button" id="button" value="提交"  class="submit6" />
	                  </td>
	              </tr>
	            </table>
	            </form>
	              </div>
	             
	</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>