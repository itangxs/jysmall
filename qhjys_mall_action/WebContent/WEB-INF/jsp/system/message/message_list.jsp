<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/message/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">站内消息——发送消息历史</h3> 
    	  <form id="from" name="form1" action="/managermall/systemmall/message/messagePage.do" method="post">
          <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
        	<label class="one" for="con-email4" >消息标题：</label>
        	<input class="con-email4" id="title" name="title" value="${title }">
            <label class="one" for="con-email4" >接收会员：</label>
        	<input class="con-email4" id="account" name="account" value="${account }">
           <label class="one" for="con-email4" >发送时间：</label>
        	<input class="easyui-datebox con-time" id="beginTime" name="beginTime" value="${beginTime }">
            <label class="one" for="con-email4" >-</label>
            <input class="easyui-datebox con-time" id="endTime" name="endTime" value="${endTime }">
           </p>
            <p class="clearfix">
        	<label class="one" for="con-email4" >读写状态：</label>
        	 <select name="all">
             <option value="-1" <c:if test="${seen==-1 ||  send == null}"> selected="selected"</c:if>>全部</option>
             <option value="0" <c:if test="${seen==0}"> selected="selected"</c:if>>已读</option>
             <option value="1" <c:if test="${seen==1}"> selected="selected"</c:if>>未读</option>
             </select> 
             <input type="submit" value="查询" class="submit8">
         </p>
        </form>
        <p class="clearfix">
             <input type="button" value="批量删除" class="submit9" onclick="delMessageByIds();">          
           </p>
          <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
               	  	<th class="td80">  <input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/></th>
                    <th class="td160">接收人</th>
                    <th class="td160">消息标题</th>
                    <th class="td160">发送时间</th>
                    <th class="td160">状态</th>
                    <th class="td160">操作</th>
                </tr>
              
              <c:forEach items="${page}" var="mes">
              
                <tr><td class="td80">
                   	 <input name="ids" type="checkbox" value="${mes.id}" onclick="xuan('dx')" /></td>
                    </td>
                    <td class="td160">${mes.account}</td>
                    <td class="td160">${mes.title}</td>
                    <td class="td160"><fmt:formatDate value="${mes.createDate}" pattern="yyyy-MM-dd hh:mm"/></td>
                    <td class="td160"><c:if test="${mes.seen==0}"> 已读</c:if><c:if test="${mes.seen==1}"> 未读</c:if></td>
                    <td class="td160">
						<a href="/managermall/systemmall/message/getMessage.do?id=${mes.id}">查看</a>|
						<a href="javascript:delMessage('${mes.id}')">删除</a>
					</td>
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
<!--底部-e-->
</body>
</html>
