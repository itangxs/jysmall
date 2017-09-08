<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<script type="text/javascript" src="/js/system/mallusermanage/user_lv_list.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/mallusermanage/uLeft.jsp" flush="true" ><jsp:param name="position" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">会员管理——会员等级</h3>
    	  
         <form id="from" name="form1"  class="zcform1" action="/managermall/systemmall/malluser/userLvList.do">
         <input type="hidden" id="page" name="pageNum" >
           <p class="clearfix">
            <!--  <input type="submit" value="批量删除" class="submit9"> -->
             <label for="select">&nbsp;&nbsp;&nbsp;</label>
             <input type="button" value="添加" class="submit8" onclick="window.location.href='/managermall/systemmall/malluser/toAddUserLv.do'" />
           </p>
        </form>
          <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">等级名称</th>
                    <th class="td160">等级图片</th>
                    <th class="td160">最小积分</th>
                    <th class="td160">最大积分</th>  
                    <th class="td160">是否默认</th>
                    <th class="td160">操作</th>
                </tr>
                
                <c:forEach items="${page}" var="u">
                <tr>
                    <td class="td160">${u.lvName}</td>
                    <td class="td160">
                    <img src="${u.lvImage}" width="16" height="16" /></td>
                    <td class="td160">${u.minIntegral }</td>
                    <td class="td160">${u.maxIntegral }</td>  
                    <td class="td160">
                    	<c:if test="${u.status==0 }">
                    		<img src="/qhjys_mall/seller-manage/admin/images/tu03.png" width="16" height="16" />
                    		
                    	</c:if>
                    	
                    	<c:if test="${u.status==1 }">
                    	<img src="/qhjys_mall/seller-manage/admin/images/tu01.png" width="16" height="16" /></td>   
                    	
                    	</c:if>
                   </td>    
                    <td class="td160">
                    <c:if test="${u.status==0 }">
                   		 <button onclick="upda(${u.id},'1')">默认</button> |
                    </c:if>
                    	<button onclick="window.location.href='/managermall/systemmall/malluser/toUpdateUserLv.do?id=${u.id}'">编辑</button> | <button onclick="del(${u.id})">删除 </button>
                    	
                    	</td>
                </tr>  
                 </c:forEach>
              </table>
            </div>
          
	</div>
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
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
