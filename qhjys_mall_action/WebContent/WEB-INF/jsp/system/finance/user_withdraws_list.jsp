<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/system/finance/user_withdraws_list.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">提现管理——会员提现</h3>
           <form id="from" name="from"  class="zcform1">
    			<input type="hidden" id="page" name="page" value="${pageNum}">
		        <p class="clearfix">
		            <label class="one" for="con-email4" >会员名：</label>
		        	<input class="con-email4" name="userName" value="${userName}" >
		        	<label class="one" for="con-email4" >操作时间：</label>
					<input id="beginTime" name="beginTime"  class="con-email3"  type="text" readonly="readonly"  value="${beginTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly="readonly"  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		        </p>
   		        <p class="clearfix">
		            <label class="one" for="con-email4" >状态：</label>
		        	<select name="status">
            	 		<option value="">全部</option>
            	 		<option value="0"  <c:if test="${status==0 }">selected="selected"</c:if>>提现失败</option>
            	 		<option value="1"	<c:if test="${status==1 }">selected="selected"</c:if>>提现成功</option>
            			<option value="2"  <c:if test="${status==2 }">selected="selected"</c:if>>未处理</option>
            		</select>
		            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	            	<label class="one" for="con-email4" >每页显示：</label>
        			<input class="con-email4" name="pageSize" value="${pageSize}">条 
		            <input type="submit" value="查询" class="submit8">
		        </p>
        	</form>
	           	<p class="clearfix">
	            <!--   <input type="button" value="导出Excel" class="submit9" onclick="Excel()">  -->
	           	</p>
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td80"><input id="selectAll" type="checkbox" value="" onclick="selectAll()" /></th>
                    <th class="td160">会员名</th>
                    <th class="td160">操作日期</th>          
                    <th class="td160">提现金元宝</th>
                    
                    <th class="td160">状态</th>
                </tr>
                <c:forEach items="${page}" var="page">
	                <tr>
	                	<td class="td80"><input name="ids" type="checkbox" value="${page.id}" /></td>
	                    <td class="td160">${page.userName}</td>
	                    <td class="td160"><fmt:formatDate value="${page.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td class="td160">${page.money}</td>
	                   
	                    <c:if test="${page.status == 0}">
	                    	<td class="td160">提现失败</td>
	                    </c:if>
	                     <c:if test="${page.status == 1}">
	                    	<td class="td160">提现成功</td>
	                    </c:if>
	                     <c:if test="${page.status == 2}">
	                    	<td class="td160">未处理</td>
	                    </c:if>
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
               		<input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
    </div>            
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>