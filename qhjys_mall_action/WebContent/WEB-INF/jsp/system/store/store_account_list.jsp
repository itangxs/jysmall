<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_account_list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="2"/></jsp:include>
	<!-- end -->
	
		<div class="memberright">
				      <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——商家账号</h3>
					<form id="from" method="post" action="storeAccountList.do" >
					<input name="pageNum" id="page" type="hidden"/>
				           <p class="clearfix">
				        	<label class="one" for="con-email4" >注册名：</label>
				        	<input class="con-email4" name="account" value="${account }" >
				            <label class="one" for="con-email4" >手机号码：</label>
				        	<input class="con-email4" name="phone"  value="${phone}">
					</p>
				           <p class="clearfix">
				        	<label class="one" for="con-email4" >创建时间：</label>
					        <input id="begin" name="startDate" class="easyui-datebox con-time" value="${startDate}">
							<label class="one" for="con-email2">-</label>
							<input id="ending" name="endDate" class="easyui-datebox con-time" value="${endDate}">
				            
				             <label for="select">&nbsp;</label>
				             <input type="submit" value="查询" class="submit8">
				         </p>
				           <p class="clearfix">
				            <!--  <input type="button" value="批量删除" class="submit9">
				             <label for="select">&nbsp;&nbsp;&nbsp;</label> -->
				             <input type="button" value="启用" class="submit8" onclick="updateStatus(1)">
				             <label for="select">&nbsp;&nbsp;&nbsp;</label>
				             <input type="button" value="禁用" class="submit8" onclick="updateStatus(0)"> 
				           </p>
				        </form>
				        
				          <div class="member_myorder">
				         <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                <tr>
				                    <th class="td80">
				                   <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/></th>
				                    <th class="td160">商家ID</th>
				                    <th class="td160">注册名</th>
				                    <th class="td160">手机号码</th>
				                    <th class="td160">启用状态</th>
				                    <th class="td160">操作</th>
				                </tr>
				                
				               <c:forEach items="${page}" var="s"> 
				                <tr>
				                    <td class="td80">
				                     <input name="ids" type="checkbox" value="${s.id}" onclick="xuan('dx')" />
				                    <td class="td160">${s.id}</td>
				                    <td class="td160">${s.username }</td>
				                    <td class="td160">${s.phone}</td>
				                    <td class="td160">
				                    	<c:if test="${s.enabled ==0 }">关闭</c:if>
				                    	<c:if test="${s.enabled ==1 }">开启</c:if>
				                    </td>   
				                    <td class="td160">
				                     <input type="button" value="重置密码" onclick="updateResetSellerPassWord(${s.id})" class="submit9"> </td>
				                </tr>
				                </c:forEach> 
				                
				              </table>
				</div>
				            <!--上一页下一页-->

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
<!--底部-e-->
</body>
</html>
