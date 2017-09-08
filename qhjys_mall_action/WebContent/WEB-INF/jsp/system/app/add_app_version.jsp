<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/app/addapp.js"></script>

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
	<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true" ><jsp:param name="val" value="10" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">增加APP版本信息</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/app/saveAppVersion.do">
           <p class="clearfix">
        	<label class="one" for="con-email4" >App版本号:</label>
        	<input class="con-email4" name="versionCode" id="versionCode" value="${appVersion.versionCode }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >App版本名称:</label>
        	<input class="con-email4" name="versionName" id="versionName" value="${appVersion.versionName }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >App文件:</label>
        	<label class="one" id="appUrl1" >请上传文件!</label>
        	<input type="hidden" name="downloadUrl" id="appUrl" value="${appVersion.downloadUrl}"/>
        	<input  type="file" value="上传" id="appButn" class="submit8">
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >App版本介绍:</label>
        	<textarea name="content" id="content" cols="70" rows="5">${appVersion.content}</textarea>
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
