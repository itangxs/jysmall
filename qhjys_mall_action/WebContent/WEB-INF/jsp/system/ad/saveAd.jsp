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
<script type="text/javascript" src="/js/system/ad/addAd.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">广告管理--添加广告</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/ad/saveAd.do">
           <p class="clearfix">
        	<label class="one" for="con-email4" >广告地区:</label>
			<select id="city" name="city"  style="width:130px;height:32px">
				
				<option value="200">深圳</option>
				<option value="203">佛山</option>
			</select>
			<!-- <select id="area" name="area" class="easyui-combobox" style="width:130px;height:32px"></select> -->
          </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >广告名称:</label>
        	<input class="con-email4" name="name" id="name"/>
          </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >广告位:</label>
	        	<select id="positionId" name="positionId">
	    	  		<option>请选择..</option>
	    	  		<c:forEach items="${aps }" var="ap">
	    	  			<option value="${ap.id }" >${ap.name }</option>
	    	  		</c:forEach>
	    	  	</select>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >广告图片:</label>
	        	<img id="linkimg" width="150" height="120" src="" alt="" />
	        	<input type="hidden" name="image" id="image" value=""/>
	             <input  type="file" value="上传" id="linkButn" class="submit8">
          </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >链接地址:</label>
        	<input class="con-email4" name="link" id="link"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >开始时间:</label>
        	<input id="startTime" name="startTime" class="easyui-datebox con-time">
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >结束时间:</label>
        	<input id="endTime" name="endTime" class="easyui-datebox con-time">
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
