<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<title>飞券网 - 积分明细</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="7"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
        <div class="member_money">
			<div class="tabs">
            	<a href="/managermall/account/myPoints.do">我的积分</a><a href="/managermall/account/pointsDetailList.do" class="tabaction">积分明细</a>
				<div class="clear"></div>
            </div>
            <div class="member_points">
				<form action="/managermall/account/pointsDetailList.do" method="post" id="from">
					<input type="hidden" id="page" name="page">
					 <p class="clearfix">
					 <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：</label>
					<input id="kssj" name="startTime" class="con-email3"  type="text" readonly="readonly" value="${startTime}" />
					<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'kssj'})" src="/common/My97DatePicker/skin/datePicker.gif" align="absmiddle"> 
					<label class="one" for="con-email2" >-</label>
					<input id="jssj" name="endTime"  class="con-email3"  type="text" readonly="readonly"  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
					<input type="submit" class="button_red" value="搜索" />
					</p>
				</form>
				<!--积分明细-->
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<th class="td20">变更日期</th>
						<th class="td12">变更类型</th>
						<th class="td12">当前积分</th>
						<th class="td12">变更积分</th>
						<th class="td12">变更后积分</th>
						<th class="td32">备注</th>
					</tr>
					<c:forEach var="page" items="${page}">
					<tr>
						<td class="td20"><fm:formatDate value="${page.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
						<td class="td12">${page.businessName}</td>
						<td class="td12">${page.integral}</td>
						<c:if test="${page.type==2}">
							<td class="td12">${page.sendBefor}</td>
							<td class="td12">${page.sendAfter}</td>
						</c:if>
						<c:if test="${page.type!=2}">
							<td class="td12">${page.reviewBefor}</td>
							<td class="td12">${page.reviewAfter}</td>
						</c:if>
						<td class="td32">${page.remark}</td>
					</tr>
					</c:forEach>
				</table>
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
               <input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
            </div>
      	</div>
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>