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


<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/order/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/order/advance_list.js"></script>

<!--日期下拉框JS-->
<script src="/js/jquery-ui.min.js"></script>
<script src="/js/select-widget-min.js"></script>
<script>
	
	$(document).ready(function(){		
		$(".ui-select").selectWidget({
			change       : function (changes) {
				return changes;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
	});	
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="22" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	
            <h3 style="padding-left:; padding-bottom:20px;">支付订单</h3>
			<form id="from" name="query" method="post" class="zcform1" action="/managermall/seller/funds/advancelist.do">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<p class="clearfix">
					<label class="one"><h1>今日收款：<span>20,000元</span>&nbsp;&nbsp;&nbsp;<span>共5笔</span></h1></label>
				</p>
				<p class="clearfix">              
					<label class="one" id="current_time">
                     	(
                    	<script language="JavaScript" type="text/javascript">
							<!--
							var enabled = 0; today = new Date();

							var day; var date;

							if(today.getDay()==0) day = " 星期日"

							if(today.getDay()==1) day = " 星期一"

							if(today.getDay()==2) day = " 星期二"

							if(today.getDay()==3) day = " 星期三"

							if(today.getDay()==4) day = " 星期四"

							if(today.getDay()==5) day = " 星期五"

							if(today.getDay()==6) day = " 星期六"

							date = (today.getFullYear()) + "-" + (today.getMonth() + 1 ) + "-" + today.getDate() + day +"";

							document.write(date);

							// -->

							</script>
                       		)									
                    </label>			
				</p>
                	<select name="drop3" class="ui-select">
						<option value="">2014年</option>
						<option value="1">2015年</option>
						<option selected  value="2">2016年</option>
						<option value="3">2017年</option>
                        <option value="3">2018年</option>
					</select>
                    <select name="drop1" class="ui-select">
						<option value="">全部</option>
						<option value="1">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4">4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
                        <option value="11">11月</option>
           		        <option value="12">12月</option>
					</select>
                    <select name="drop2" class="ui-select">
                    	<option value="">全部</option>
						<option value="1">1日</option>
						<option value="2">2日</option>
						<option value="3">3日</option>
						<option value="4">4日</option>
                        <option value="5">5日</option>
                        <option value="6">6日</option>
                        <option value="7">7日</option>
                        <option value="8">8日</option>
                        <option value="9">9日</option>
                        <option value="10">10日</option>
                        <option value="11">11日</option>
                        <option value="12">12日</option>
                        <option value="13">13日</option>
                        <option value="14">14日</option>
                        <option value="15">15日</option>
                        <option value="16">16日</option>
                        <option value="17">17日</option>
                        <option value="18">18日</option>
                        <option value="19">19日</option>
                        <option value="20">20日</option>
                        <option value="21">21日</option>
                        <option value="22">22日</option>
                        <option value="23">23日</option>
                        <option value="24">24日</option>
                        <option value="25">25日</option>
                        <option value="26">26日</option>
                        <option value="27">27日</option>
                        <option value="28">28日</option>
                        <option value="29">29日</option>
                        <option value="30">30日</option>
                        <option value="31">31日</option>
					</select>
                    <input type="submit" value="查询" class="submit78">
                    <p class="clearfix">
					<label class="one">
                    <h1 class="query_result">查询结果&nbsp;&nbsp;&nbsp;&nbsp;<span>2016-10-7 星期五</span>&nbsp;&nbsp;&nbsp;<span>共5笔</span></h1></label>
                
					</p>
			</form>

			<div class="member_myorder add_margin">
            	<div class="excelbutton new_excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td100">订单编号</th>
                        <th class="td100">支付金额</th>
                        <th class="td100">买家</th>
                        <th class="td100">支付时间</th>
						<th class="td100">支付方式</th>					
				</table>
			</div>
  	   	    <c:if test="${page.getPages()==0}">
		    	<div align="center">
		  			<font  color="red">暂时没有数据!</font>
		    	</div>
	    	</c:if>
			<!--上一页下一页-->
	       <div class="page">
            	<c:if test="${page.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
                <span>共${page.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>