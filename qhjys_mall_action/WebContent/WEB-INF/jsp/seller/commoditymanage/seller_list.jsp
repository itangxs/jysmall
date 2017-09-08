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
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="9" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    	<h3 style="padding-left:; padding-bottom:20px;">折扣管理</h3>
         <br>
         <p class="clearfix">
        	<input id="quan" type="checkbox" onclick="xuan('qxx')"/>
            <label class="one" for="con-email2" >全选</label>
            <input type="button" value="下架" onclick="shelves()" class="submit6">
          <!--   <input type="button" value="删除" onclick="del()" class="submit6"> -->
         </p>
         <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td40"></th>
                    <th class="">折扣名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th class="td80">折扣</th>
                    <th class="td100">开始日期</th>
                    <th class="td100">结束日期</th>
                    <th class="td100">提交日期</th>
                    <th class="td80">状态</th>
                </tr>
            </table>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td40">
                    <input name="ids" type="checkbox" value="${p.id}" onclick="xuan('dx')" /></td>
                    <td class="">
                    <%--  ${fn:split(p.images, ',')[1]} --%>
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
                    <td class="td80">8.50折</td>
                    <td class="td100">2016-03-06</td>
                     <td class="td100">2016-03-06</td>
                    <td class="td100">2016-03-06</td>
                    <td class="td80">
                    	<p class="fontred">审核中</p>
                        <p style="display:none;">启售</p>
                        <p style="display:none;">下架</p>
                    	<%-- <a href="/managermall/seller/commoditymanage/update_page.do?id=${p.id}">编辑</a>
                    <c:if test="${p.status==2 }">
                    	|<a href="/getProdDetail.do?prodId=${p.id}">预览</a>
                    </c:if> --%>
                    </td>  
                </tr>
            </table>
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
