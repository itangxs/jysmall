<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-商品订单</title>

<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<style>
.member_myorder{ margin-top:27px;}
.td80 img{width:30px;height:30px; margin:0 auto; }
</style>

<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/order/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="/js/pagingUtil.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="3" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
            <h3 style="padding-left:; padding-bottom:20px;">累计发放人数：150</h3>
			<form id="from" name="query" method="post" class="zcform1" action="/managermall/seller/funds/advancelist.do">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				
				<p class="clearfix">
					<label class="one" for="con-email2">时间：</label>
					<input id="startTime" name="startTime" class="easyui-datebox con-time" value="${startTime}">
					<label class="one" for="con-email2">-</label>
					<input id="endTime" name="endTime" class="easyui-datebox con-time"  value="${endTime}"><em class="space"></em>
					<input type="submit" value="查询" class="submit6">
				</p>
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td100">活动名称</th>
						<th class="td60">昵称</th>					
						<th class="td80">头像</th>
						<th class="td80">卡券名称</th>
						<th class="td100">发放时间</th>
					</tr>
					 <tr>
                        <td class="td100">小肥羊卡券1</td>
						<td class="td60">wary</td>				
						<td class="td80">
                         	<img src="">
                        </td>
						<td class="td80">三等奖</td>
						<td class="td100">2016年8月7日 15:23</td>
					</tr>
				</table>
			</div>


		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>