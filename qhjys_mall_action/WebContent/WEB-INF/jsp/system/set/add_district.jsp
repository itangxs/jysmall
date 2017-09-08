<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/set/add.js"></script>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">增加商圈</h3>
            <form id="form1" name="form1"  class="zcform" action="/managermall/systemmall/set/saveDistrict.do"><input type="hidden" value="${district.id}" name="id"/>
           <p class="clearfix">
        	<label class="one" for="con-email4" style="text-align:right;" >省市:</label>
        	<input id="province" name="province" value="${district.provinceId}" type="text"
							style="width: 90px; height: 30px;"><input id="city" name="city" value="${district.cityId}"
							style="width: 90px; height: 30px;"><input id="area" name="area" value="${district.areaId}"
							style="width: 90px; height: 30px;">				
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" style="text-align:right;" >商圈:</label>
        	<input class="con-email4" name="name" id="name" value="${district.name}"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" style="text-align:right;" >是否启用:</label>
        	<select name="enabled">
        	<option value="0"<c:if test="${district.enabled==0}"> selected="selected"</c:if>>不启用</option>
        	<option value="1"<c:if test="${district.enabled==1}"> selected="selected"</c:if>>启用</option>
        	</select>
          </p>    
           <p class="clearfix">
             <input type="submit" value="增加" class="submit22" style="margin-left:120px;width:75px;">          
           </p>
        </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
