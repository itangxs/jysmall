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
<script type="text/javascript" src="/js/system/adminuser/addgroup.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="9" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/adminuser/uLeft.jsp" flush="true"  ><jsp:param name="val" value="5" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">增加用户组</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/userrole/saveGroup.do"><input type="hidden" value="${adminGroup.id}" name="id"/>
           <p class="clearfix">
        	<label class="one" for="con-email4" >组名:</label>
        	<input class="con-email4" name="groupName" id="groupName" value="${adminGroup.groupName }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >对应权限</label>
        	<c:forEach items="${roleMenuList}" var="roleMenu">
        	
        	<input name="roleId" type="checkbox" value="${roleMenu.id}"<c:forEach items="${adminRoleGroupList}" var="adminRoleGroup">
        	<c:if test="${adminRoleGroup.roleId== roleMenu.id}"> checked="checked"</c:if>
        	</c:forEach>/>${roleMenu.name}&nbsp;&nbsp;&nbsp;&nbsp;
        	</c:forEach>
          </p>     
           <p class="clearfix">
             <input type="submit" value="增加" class="submit10">          
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
