<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/activity/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">活动管理——添加活动</h3> 
          <div class="member_myorder">
          	<form id="addform" action="/managermall/systemmall/activity/add.do" method="post">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="td100">店铺ID：</td>
                        <td><input id="reid1" name="id" type="hidden">
                        <input class="con-email4" id="storeId" name="storeId" value="" >&nbsp;<input id="idbut" type="button" value="确定" class="submit8" onclick="getstorename()"></td>
                      </tr>
                      <tr>
                        <td class="td100">店铺名称：</td>
                        <td id="storename"></td>
                      </tr>
                      <tr>
                        <td class="td100">活动名称：</td>
                        <td><input class="con-email5" id="rebateName1" name="lotteryName" value="" ></td>
                      </tr>
                      <tr>
                        <td class="td100">活动说明：</td>
                        <td><input class="con-email5" id="rebateName1" name="lotteryContent" value="" ></td>
                      </tr>
                      <tr>
                        <td class="td100">开始日期：</td>
                        <td>
                        	<input id="begin" name="startDate" class="easyui-datebox con-time" value="">
		            <label class="one" for="con-email4" > 请输入开始日期</label>
		            </td>
                      </tr>
                      <tr>
                        <td class="td100">结束日期：</td>
                        <td>
                        	<input id="begin" name="endDate" class="easyui-datebox con-time" value="">
                 		  	<label class="one" for="con-email4" > 请输入结束日期</label>
                   	</td>
                      </tr>
                      <!-- <tr>
                        <td class="td100">状态：</td>
                        <td>
                        <select name="status">
                        	<option value="0">不可用</option>
                        	<option value="1">可用</option>
                        </select>
                        &nbsp;&nbsp;请输入状态</td>
                      </tr> -->
                      <tr>
                        <td class="td100"></td>
                        <input  name="status" type="hidden" value="0"/>
                        <input  name="token" type="hidden" value="${activityAddToken}"/>
                        <td><input id="toaddreb" type="button" value="确定" class="submit9" onclick="addrebate()">&nbsp;&nbsp;</td>
                      </tr>
                    </table></form>
            </div>
            <!--上一页下一页-->
       
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
