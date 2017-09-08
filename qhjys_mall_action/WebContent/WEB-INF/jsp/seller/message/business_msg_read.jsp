<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/seller/message/business_msg_center.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="3" /></jsp:include>
	<div class="membercontent">
		<div class="memberleft">
			<!--左侧菜单-->
			<div class="membermenu">
				<ul>
					<li>
						<h1>消息中心</h1>
						<p>
							<a href="/managermall/seller/message/messageCenterList.do" class="menucurrent"><span>·</span>我的站内信</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<div class="memberright">	
	    	<form action="/managermall/seller/message/messageCenterList.do" method="post" id="from">
    			<input type="hidden" id="page" name="page">
    		</form>
        <div id="member_myorder">  
<!-- 			<div class="tabs1">
				<a href="/managermall/seller/message/messageCenterList.do?seen=0" class="tabaction">未读消息</a><a href="/managermall/seller/message/messageCenterList.do?seen=1">已读消息</a>
				<div class="clear"></div>
			</div> -->
			<ul class="menu0" id="menu0">    
            <li onclick="setTab(0)" class="hover">未读消息</li>  
            <li onclick="setTab(1)">已读消息</li>  
           </ul> 
           <div class="main" id="main0">  
             <ul class="block"><li>
                 <div class="member_myorder" style="height:500px;">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">标题</th>
                    <th class="td160">时间</th>
                    <th class="td160">操作</th>
                </tr>
                <c:forEach items="${page}" var="m">
               		<tr>
               			 <th class="td160">${m.title}</th>
               			 <th class="td160"><fmt:formatDate value="${m.createDate}" pattern="yyyy-MM-dd "/></th>
               			 <th class="td160"><input type="button" onclick="deleteMessage(${m.id})" value="删除"/></th>
               		</tr>
               </c:forEach>
            </table>
                  </div>
            </li></ul> 
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
	<div class="clear"></div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>