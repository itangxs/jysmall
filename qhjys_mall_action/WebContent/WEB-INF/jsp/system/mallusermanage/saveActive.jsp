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
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/message/addmessage.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" ><jsp:param name="position" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
         <h3 style="padding-left:10px; margin-bottom:25px;">站内消息——发送消息</h3>
            <form id="form1" name="form1" action="/managermall/systemmall/active/saveActive.do" class="zcform1">
           <p class="clearfix">
        	<label class="one" for="con-email4" >起始时间：</label>
        	<input id="begin" name="beginTime" class="easyui-datebox con-time">
          </p>
          <br>
           <p class="clearfix">
       <label class="one" for="con-email4" >结束时间：</label>
        	<input id="begin" name="endTime" class="easyui-datebox con-time">
           </p>
           <br>
           <p class="clearfix">
       <label class="one" for="con-email4" >通用卷元宝：</label>
        	<input class="con-email4" name="commonCoupon" id="commonCoupon">
           </p>
           <br>
           <p class="clearfix">
       <label class="one" for="con-email4" >商家卷元宝：</label>
        	<input class="con-email4" name="storeCoupon" id="storeCoupon">
           </p>
           <br>
           <p class="clearfix">
       <label class="one" for="con-email4" >有效期(天)：</label>
        	<input class="con-email4" name="couponValidity" id="couponValidity">
           </p>
           <br>
           <p class="clearfix">
        <label class="one" for="con-email4" >是否启用：</label>
        	 <select name="enabled" id="enabled">
             <option value="1" selected="selected">启用</option>
             <option value="0">禁用</option>
            </select>
           </p>
           <br>
           <p class="clearfix">
             <input type="submit" value="提交" class="submit10">          
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
