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
<title>飞券网平台管理中心-微信店铺</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/mallusermanage/list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/store/store_list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
<!--------------------------我的账户-------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="4"/></jsp:include>
	<!-- end -->
		<div class="memberright">
             <h3 style="padding-left:10px; margin-bottom:25px;">商家管理——微信店铺列表</h3>
        <form id="from"  class="zcform1" method="post" action="/managermall/systemmall/store/wxlist.do">
         <input id="page" name="pageNum" type="hidden" value="${pageNum}">
           <p class="clearfix">
        	<label class="one" for="con-email4" >商家ID：</label>
        	<input class="con-email4" name="sellerid" value="${sellerid}">
            <label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" name="wxstoreid" value="${wxstoreid}">
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="wxstorename" value="${wxstorename}">
           </p>
         
           <p class="clearfix">
        	<label class="one" for="con-email4" >创建时间：</label>
        	<input id="begin" name="startdate" class="easyui-datebox con-time" value="${startDate}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="enddate" class="easyui-datebox con-time" value="${endDate}">
             
             <label class="one" for="con-email4" >审核状态：</label> 
            <select name="status">
             	 <option value="">全部</option>
	             <option value="0" <c:if test="${ status ==0 }">selected="selected"</c:if>>未审核</option>
	             <option value="1" <c:if test="${status ==1 }">selected="selected"</c:if>>审核通过</option>
            </select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">
       	  </p>
       	  
        </form> 
        
        <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">店铺ID</th>
                    <th class="td160">店铺名称</th>
                    <th class="td160">店铺联系电话</th>
                    <th class="td160">状态</th> 
                    <th class="td160">创建时间</th>        
                    <th class="td160">排序</th>        
                    <th class="td160">操作</th>
                </tr>
              <c:forEach items="${page}" var="s">
                <tr>
                    <td class="td160">${s.id}</td>
                    <td class="td160">${s.storeName }</td>
                    <td class="td160">${s.phoneNum }</td>
                    <td class="td160">
                    	<c:if test="${s.status == 0  }">未审核</c:if>
                    	<c:if test="${s.status == 1  }">审核通过</c:if>
                    </td>
                    <td class="td160"><fmt:formatDate value="${s.createTime}" pattern="yyyy-MM-dd "/></td> 
                    <td class="td160">${s.level}</td> 
                    <td class="td160">
                    <button onclick="javascript:window.location.href='/managermall/systemmall/store/wx_store.do?id=${s.id}'">查/修 </button>
                   <!--  <c:if test="${s.type==1}">
                     <button onclick="javascript:window.location.href='/managermall/systemmall/store/wx_store_rebate_list.do?id=${s.id}&sellerid=${s.sellerId}'">折扣列表 </button>
                    </c:if> -->
                    <%-- <button onclick="del(${s.id})">删除 </button></td> --%>
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
