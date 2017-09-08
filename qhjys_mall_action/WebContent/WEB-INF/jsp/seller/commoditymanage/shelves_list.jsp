<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心</title>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/seller/commoditymanage/list.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="18" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
	
		<form action="/managermall/seller/commoditymanage/shelvesList.do" method="post" id="from">
			<input type="hidden" id="page" name="page">
           <p class="clearfix">
        	<label class="one" for="con-email2" >商品名称：</label>
        	<input  name="name" class="con-email2" value="${name}" >
        	
        	
        	<label class="one" for="con-email2" >添加时间：</label>
        	<input id="tjsjs" name="tjsjs"  class="con-email3"  type="text" readonly="readonly"  value="${tjsjs}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
           
            <label class="one" for="con-email2" >-</label>
            <input id="tjsje" name="tjsje"  class="con-email3"   type="text" readonly="readonly"  value="${tjsje}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsje'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
            
             <input type="submit" value="查询" class="submit6">
           <%--  <label class="one" for="con-email2" >截止时间：</label>
        	<input id="jzsjs" name="jzsjs"  class="con-email3"  type="text" readonly="readonly"  value="${jzsjs}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jzsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
           
            <label class="one" for="con-email2" >-</label>
            <input id="jzsje"  name="jzsje" class="con-email3"  type="text" readonly="readonly"  value="${jzsje}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jzsje'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
            --%>
           <%--  <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核</label>
            <select name="status" id="select">
                <option value="">全部</option>
              
                <option value="0"   <c:if test="${status =='0' }">selected="selected"</c:if>>审核中</option>
                <option value="1"	<c:if test="${status =='1' }">selected="selected"</c:if>>未通过</option>
            </select> --%>
            
           
           </p>
         </form>
         <div><br></div>
         <!-- <p class="clearfix">
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <a href="/managermall/seller/commoditymanage/add_page.do"><img src="/images/seller/add.jpg" width="160" height="30"  align="right"/></a>
         </p> -->
         <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td80"></th>
                    <th class="td508">商品名称</th>
                    <th class="td130">添加时间</th>  
                    <th class="td130">审核状态</th>    
                    <th class="td80">操作</th>
                </tr>
            </table>
            
            
            <c:forEach items="${productInfo}" var="p">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td80">
                    <input name="ids" type="checkbox" value="${p.id}" onclick="xuan('dx')" /></td>
                    <td class="td508">
                    <%-- <img src="/${fn:substringBefore(p.images, '|')}" /> --%>
                    <c:choose>
                 		<c:when test="${fn:contains(p.images,',') }">
                 		  <img src="${fn:split(p.images, ',')[0]}" />
                 		</c:when>
                 		<c:otherwise>
                 			<img src="${fn:split(p.images, '|')[0]}" />
                 		</c:otherwise>
                 	</c:choose>
                      <p>${p.name }</p>
                      <p></p>                  
                    </td>
                   <%--  <td class="td200">
                    	<fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd "/>到<fmt:formatDate value="${p.endDate}" pattern="yyyy-MM-dd "/>
                    </td>
                    <td class="td130">${p.unitPrice}</td>
                     <td class="td130">${p.prodStock}</td>
                    <td class="td130">${p.salesNum}</td>
                    <td class="td130">
                      <p class="fontred">
                      <c:choose>
                      	<c:when test="${!empty p.scoreAvg}">${p.scoreAvg}</c:when>
                      	<c:otherwise>0</c:otherwise>
                      </c:choose>
                      	星</p>
                    </td> --%>
                    <td class="td130"><fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd "/></td>
                     <td class="td130">
                      <p>
                      	<%-- <c:if test="${p.status ==0 }">
                     		 <span class="fontred">未通过</span>
                     	 </c:if>
                   	  	<c:if test="${p.status ==1}">
                   	  		审核中
                   	  	</c:if> --%>
                   	  	<c:if test="${p.status ==3}">
                   	  		已下架
                   	  	</c:if>
                      </p>
                    </td>
                    <td class="td80"><a href="/getProdDetail.do?prodId=${p.id}">查看</a>
                    |<a href="/managermall/seller/commoditymanage/update_page.do?id=${p.id}">编辑</a></td>
                </tr>
            </table>
             </c:forEach>
           
        </div>
   	    <c:if test="${productInfo.getPages()==0}">
	    	<div align="center">
	  			<font  color="red">暂时没有数据!</font>
	    	</div>
    	</c:if>
        <!--上一页下一页-->
          <div class="page">
            	<c:if test="${productInfo.getPageNum()>1}">
            	<a href="javascript:previousPage(${productInfo.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${productInfo.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${productInfo.getPages()}">
            			<c:choose><c:when test="${i==productInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${productInfo.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==productInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${productInfo.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${productInfo.getPages()-3}">
            			<c:if test="${i==productInfo.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${productInfo.getPageNum()<productInfo.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${productInfo.getPages()-2}" end="${productInfo.getPages()}">
            			<c:choose><c:when test="${i==productInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${productInfo.getPages()>productInfo.getPageNum()}"><a href="javascript:previousPage(${productInfo.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${productInfo.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="page" type="text" value="${page}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
    </div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
