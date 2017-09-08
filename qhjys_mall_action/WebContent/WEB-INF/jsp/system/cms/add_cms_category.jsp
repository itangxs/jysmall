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
<script type="text/javascript" src="/js/system/cms/add.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">增加CMS类别</h3>
            <form id="form1" name="form1"  class="zcform" action="/managermall/systemmall/cms/saveCmsCategory.do" method="post">
            <input type="hidden" value="${cmsCategory.id}" name="id"/>
           <p class="clearfix">
        	<label class="one" for="con-email4" style="width:120px;text-align:right;" >选择上级类别：</label>
        	 <select name="fatherId">
        	 <c:forEach items="${cmsCategoryList }" var="cmsCategory1" >
             <option value="${cmsCategory1.id }"<c:if test="${cmsCategory.fatherId==cmsCategory1.id }"> selected="selected"</c:if>>${cmsCategory1.name }</option>
             </c:forEach>
            </select>
           </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" style="width:120px;text-align:right;" >标题: </label>
        	<input class="con-email4" name="name" id="name" value="${cmsCategory.name }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4"style="width:120px;text-align:right;"  >排序号: </label>
        	<input class="con-email4" name="paixuhao" id="paixuhao" value="${cmsCategory.paixuhao }"/>0-9
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4"style="width:120px;text-align:right;"  >下面是否还有子类: </label>
        	<select name="yezi">
        	<option value="0"<c:if test="${cmsCategory.yezi==0 }"> selected="selected"</c:if>>有</option>
        	<option value="1"<c:if test="${cmsCategory.yezi==1 }"> selected="selected"</c:if>>无</option>
        	</select>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4"style="width:120px;text-align:right;"  >是否启用: </label>
        	<select name="enabled">
        	<option value="0"<c:if test="${cmsCategory.enabled==0 }"> selected="selected"</c:if>>不启用</option>
        	<option value="1"<c:if test="${cmsCategory.enabled==1 }"> selected="selected"</c:if>>启用</option>
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
