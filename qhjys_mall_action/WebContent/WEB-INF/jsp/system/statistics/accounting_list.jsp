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
<script type="text/javascript" src="/js/system/statistics/accounting_list.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="14" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    		<h3 class="jinrongtitle">业绩分析</h3>
           <form id="from" class="zcform1" method="post" action="">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
            <p class="clearfix">
            <label class="one" for="con-email4" >入职时间：</label>       
        	<input id="begin" name="benginTime" class="easyui-datebox con-time" value="${benginTime}">

            <label class="one" for="con-email4" >
            <label for="select">&nbsp;&nbsp;</label>
        	<label class="one" for="con-email4" >年/月：</label>
        	<input class="con-email4" name="" value="" >
            <label for="select">&nbsp;&nbsp;</label>
             <label class="one" for="con-email4" >区域：</label> 
           <select id="provinceId" name="provinceId" prompt="请选择" class="easyui-combobox" style="width:130px;height:32px"></select>
            <label for="select">&nbsp;&nbsp;</label>
           <select id="cityId" name="cityId" prompt="请选择" class="easyui-combobox" style="width:130px;height:32px"></select>
            
        	 </p>
              <p class="clearfix">
			<label class="one" for="con-email4" >公司：</label> 
           <select prompt="请选择" id="branchId" name="branchId" class="easyui-combobox" style="width:130px;height:32px"></select>
            <label for="select">&nbsp;&nbsp;</label>
				<label class="one" for="con-email4" >团队长：</label> 
            <select prompt="请选择" id="teamId" name="teamId" class="easyui-combobox" style="width:130px;height:32px"></select>
            <label for="select">&nbsp;&nbsp;</label>
				<label class="one" for="con-email4" >业务员：</label> 
            <select prompt="请选择" id="clerkId" name="clerkId" class="easyui-combobox" style="width:130px;height:32px"></select>
             <input type="hidden" id="branchId1" value="${branchId }">
                <input type="hidden" id="teamId1" value="${teamId }">
                <input type="hidden" id="clerkId1" value="${clerkId }">
                 <input type="hidden" id="provinceId1" value="${provinceId }">
                <input type="hidden" id="cityId1" value="${cityId }">
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">&nbsp;
             <input type="button" value="导出表格" class="submit9" onclick="Excel1()">
        	 </p>
        </form>

        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="">
               <tr>  
                    <th class="td120">业务员<br>入职时间</th>
                    <th class="td80">年/月</th>
                    <th class="td80">区域</th>
                    <th class="td120">公司</th>
                    <th class="td60">团队长</th>
                    <th class="td60">业务员</th>
                    <th class="td80">当月签约<br>店铺数</th>
                    <th class="td80">当月有效<br>店铺数</th>
                    <th class="td80">交易<br>金额</th>
                    <th class="td80">套现<br>金额</th>
                    <th class="td60">业务员<br>提成</th>
                    <th class="td60">团队长<br>提成</th>

                </tr>    
               <!--  <tr>
                    <td class="td120">2017-05-18</td>
                    <td class="td80">2017/06</td>
                    <td class="td80">广东深圳</td>
                    <td class="td120">深圳分公司</td>
                    <td class="td60">李四</td>
                    <td class="td60">张三</td>
                    <td class="td80">10</td>
                    <td class="td80">8</td>
                    <td class="td80">50000.00</td>
                    <td class="td80">40000.00</td>
                    <td class="td60">99.5</td>
                    <td class="td60">99.5</td>

                </tr>
                 -->
                
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
