<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/statistics/store_analyze_list.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="13" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    		<h3 class="jinrongtitle">店铺分析</h3>
           <form id="from" class="zcform1" method="post" action="/managermall/systemmall/statistics/storeAnalyzeList.do">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
            <p class="clearfix">
            <label class="one" for="con-email4" >开户时间：</label>       
        	<input id="begin" name="startTime" class="easyui-datebox con-time" value="${startTime}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="endTime" class="easyui-datebox con-time" value="${endTime}">
            <label class="one" for="con-email4" >
            <label for="select">&nbsp;</label>
        	<label class="one" for="con-email4" >公司：</label>
        	<input class="con-email4" name="branchName" value="${branchName }" >
            <label for="select">&nbsp;</label>
            <label class="one" for="con-email4" >团队长：</label>
        	<input class="con-email4" name="teamName" value="${teamName }" > 
        	 </p>
              <p class="clearfix">
              <label class="one" for="con-email4" >业务员：</label>
        	<input class="con-email4" name="clerk" value="${clerk }" >
            <label for="select">&nbsp;&nbsp;</label>
        	<label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" name="storeId" value="${storeId }" >
            <label for="select">&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName }" > 
        	 </p>
         <p class="clearfix" style="margin-top:8px;">
             <label class="one" for="con-email4" >门店类型：</label>
        	<select name="categoryid">
        		<option value="">全部</option>
        		<c:forEach items="${list }" var="c">
        			<option value="${c.id }" <c:if test="${c.id == categoryid }">selected='selected'</c:if>>${c.name }</option>
        		</c:forEach>
        		
        	</select>
        	 <label class="one" for="con-email4" >是否有效：</label>
        	<select name="">
        		<option value="">全部</option>
        		<option value="1" <c:if test="${isEffective == 1}">selected="selected"</c:if>>是</option>
        		<option value="0" <c:if test="${isEffective == 0}">selected="selected"</c:if>>否</option>
        	</select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">&nbsp;
             <input type="button" value="导出表格" class="submit9" onclick="Excel()">&nbsp;
         </p>
        </form>

        <div class="member_myorder"  style="overflow-x:scroll;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="width:1680px;">
               <tr>  
                    <th class="td120">开户<br>时间</th>
                    <th class="td160">公司<br>名称</th>
                    <th class="td80">团队长</th>
                    <th class="td60">业务员</th>
                    <th class="td120">店铺<br>ID</th> 
                    <th class="td120">店铺<br>名称</th>                    
                    <th class="td80">店铺<br>类型</th> 
                    <th class="td120">费率<br>套餐</th>       
                    <th class="td100">支付<br>订单数</th> 
                    <th class="td100">套现<br>订单数</th>
                    <th class="td100">交易<br>金额</th>
                    <th class="td100">套现<br>金额</th>
                    <th class="td80">手续<br>费用</th>
                    <th class="td120">到账<br>金额</th>
                    <th class="td120">首单<br>消费时间</th>
                    <th class="td120">最近<br>消费时间</th>
                    <th class="td40">是否<br>有效</th>
                    <th class="td120">转化<br>有效日期</th>
                </tr>
                <c:forEach items="${page }" var="p">
	                 <tr>
	                    <td class="td120"><fmt:formatDate value="${p.createTime }" pattern="yyyy-MM-dd"/></td>
	                    <td class="td160">${p.branchName }</td>
	                    <td class="td80">${p.teamName }</td>
	                    <td class="td60">${p.clerk }</td>
	                    <td class="td120">${p.storeId }</td>
	                    <td class="td120">${p.storeName }</td>
	                    <td class="td80">${p.categoryDetails }</td>
	                    <td class="td120">${p.rateName }</td>
	                    <td class="td100">${p.totalNum }</td>
	                    <td class="td100">${p.cashNum }</td>
	                    <td class="td100">${p.totalMoney }</td>
	                    <td class="td100">${p.cashMoney }</td>
	                    <td class="td80">${p.totalRate }</td>
	                    <td class="td120">${p.totalTotamt }</td>
	                    <td class="td120"><fmt:formatDate value="${p.firstTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td class="td120"><fmt:formatDate value="${p.lastTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td class="td40"><c:if test="${p.isEffective == 1}">是</c:if>
	                    				 <c:if test="${p.isEffective == 0}">否</c:if></td>
	                    <td class="td120"><fmt:formatDate value="${p.effectiveDate }" pattern="yyyy-MM-dd"/></td>
	                </tr>
                </c:forEach>    
               
                
              </table>
        </div>
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
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>
