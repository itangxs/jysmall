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
<script type="text/javascript" src="/js/system/message/addmessage.js"></script>

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
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
         <h3 style="padding-left:10px; margin-bottom:25px;">站内消息——发送消息</h3>
            <form id="form1" name="form1" action="/managermall/systemmall/message/saveMessage.do" class="zcform1">
           <p class="clearfix">
        	<label class="one" for="con-email4" >消息标题：</label>
        	<input class="con-email4" name="title" id="title">
          </p>
          <br>
           <p class="clearfix">
        <label class="one" for="con-email4" >消息类型：</label>
        	 <select name="sendType" id="sendType">
             <option value="0">请选择</option>
             <option value="1">用户消息</option>
             <option value="2">商家消息</option>
            </select>
           </p>
           <br>
           <p class="clearfix">
        <label class="one" for="con-email4" >会员级别：</label>
        	 <select name="vipRank" id="vipRank">
             <option value="0">全部</option>
             <option class="accountv" value="1">VIP1</option>
             <option class="accountv" value="2">VIP2</option>
             <option class="accountv" value="3">VIP3</option>
             <option class="accountv" value="4">VIP4</option>
             <option class="accountv" value="5">VIP5</option>
             <option class="accountv" value="6">VIP6</option>
            </select>
           </p>
           <br>
          <p class="clearfix">
        	<label class="one" for="con-email4" >消息内容：</label>
            <textarea name="content" id="content" class="con-email4" style="height:80px; width:400px;"></textarea>
          </p>
           <p class="clearfix">
             <input type="submit" value="发送" class="submit10">          
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
