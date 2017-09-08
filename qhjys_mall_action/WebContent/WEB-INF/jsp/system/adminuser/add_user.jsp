<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/adminuser/add.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="9" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/adminuser/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">增加后台用户</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/user/saveUser.do"><input type="hidden" value="${adminUser.id}" name="id"/>
           <p class="clearfix">
        	<label class="one" for="con-email4" >用户名:</label>
        	<input class="con-email4" name="username" id="username" value="${adminUser.username }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >密码:</label>
        	<input class="con-email4" name="password" id="password"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >确认密码:</label>
        	<input class="con-email4" name="password1" id="password1"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >姓名:</label>
        	<input class="con-email4" name="realname" id="realname" value="${adminUser.realname }"/>
          </p>
           <p class="clearfix">
        <label class="one" for="con-email4" >选择会员组：</label>
        	 <select name="groupId">
        	 <c:forEach items="${adminGroupList }" var="adminGroup" >
             <option value="${adminGroup.id }"<c:if test="${adminGroup.id==adminUser.groupId }"> selected="selected"</c:if>>${adminGroup.groupName }</option>
             </c:forEach>
            </select>
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
