<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心-菜品管理</title>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/seller/fqproduct/list.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="25" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<form action="/managermall/seller/fqproducts/manager.do" method="post" id="from">
				<input type="hidden" name="page" id="page">
	           <p class="clearfix">
	        	<label class="one" for="con-email2" >商品名称：</label>
	        	<input  name="name" class="con-email2" value="${name}" >
	        	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态</label>
		            <select name="status" id="select">
		                <option value="">全部</option>
		                <option value="1"	<c:if test="${status =='1' }">selected="selected"</c:if>>上架</option>
		                <option value="0"   <c:if test="${status =='0' }">selected="selected"</c:if>>下架</option>
		            </select>
	           </p>
	           <div><br></div>
	           <p class="clearfix">
	        	<label class="one" for="con-email2" >添加时间：</label>
	        	<input id="tjsjs" name="tjsjs"  class="con-email3"  type="text" readonly="readonly"  value="${tjsjs}"/>
	        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
	           
	            <label class="one" for="con-email2" >-</label>
	            <input id="tjsje" name="tjsje"  class="con-email3"   type="text" readonly="readonly"  value="${tjsje}"/>
	        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsje'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
	            
	             <input type="submit" value="查询" class="submit6">
	         </p>
	         </form>
         	<div><br></div>
	         <p class="clearfix">
	        	<input id="quan" type="checkbox" onclick="xuan('qxx')"/>
	            <label class="one" for="con-email2" >全选</label>
	            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="上架" onclick="changestatus(1)" class="submit6">
	            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="下架" onclick="changestatus(0)" class="submit6">
	            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="删除" onclick="deleteByIds()" class="submit6">
	          <!--   <input type="button" value="删除" onclick="del()" class="submit6"> -->
	             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	             <!-- 添加 -->
	             <a href="/managermall/seller/fqproducts/productModify.do"><img src="/images/seller/add.jpg" width="160" height="30"  align="right"/></a>
	         </p>
	         <div class="member_myorder">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="td80"></th>
	                    <th class="td130">商品名称</th>
	                    <th class="td130">价格</th>
	                    <th class="td130">库存</th>
	                    <th class="td130">添加时间</th>
	                    <th class="td130">状态</th>
	                    <th class="td80">操作</th>
	                </tr>
	            </table>
	            <c:forEach items="${productInfo}" var="p">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <td class="td80">
	                    <input name="ids" type="checkbox" value="${p.id}" onclick="xuan('dx')" /></td>
	                    <td class="td130">${p.productName}</td>
	                    <td class="td130">￥<fmt:formatNumber value="${p.price}" type="currency" pattern="#,#0.00#"/></td>
	                    <td class="td130">${p.stock}</td>
	                    <td class="td130"><fmt:formatDate value="${p.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <th class="td130"><c:if test="${p.status =='0' }">下架</c:if>
						<c:if test="${p.status =='1' }">上架</c:if>
	                    <td class="td80">
	                    	<a href="/managermall/seller/fqproducts/productUpdate.do?prodId=${p.id}">修改</a>
	                    </td>
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
</body>
</html>