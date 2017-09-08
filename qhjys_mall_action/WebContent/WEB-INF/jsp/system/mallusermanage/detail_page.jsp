<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>



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
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" />
    <!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">会员管理——会员详情</h3> 
          <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">会员ID</th>
                    <th class="td160">会员姓名</th>
                    <th class="td160">会员等级</th>
                    <!-- <th class="td160">所在地区</th>   -->
                    <th class="td160">电子邮件</th>
                    <th class="td160">手机号码</th>
                   <!--  <th class="td160">账户余额</th> -->
                    <th class="td160">启用状态</th>
                </tr>
              
                <tr>
                    <td class="td160">${userInfo.id}</td>
                    <td class="td160">${userInfo.username}</td>
                    <td class="td160">${userInfo.level}</td>
                    <!-- <td class="td160">深圳</td>   -->
                    <td class="td160">${userInfo.email}</td>
                    <td class="td160">${userInfo.phoneNum}</td>
                   <!--  <td class="td160">0.00</td>    -->
                    <td class="td160">
                    		<c:choose>
                    			<c:when  test="${userInfo.enabled==1}">启用</c:when>
                    			<c:when  test="${userInfo.enabled==0}">禁用</c:when>
                    			<c:otherwise>禁用</c:otherwise>
                    		</c:choose>
                    </td>   
                </tr>
              
              </table>
            </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
