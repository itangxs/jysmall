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
<script type="text/javascript" src="/js/system/product/manage_list.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
		<div class="memberright">
     	<h3 style="padding-left:10px; margin-bottom:25px;">商品管理——商品管理</h3>
    		<form id="from" name="from"  class="zcform1" method="post">
    			<input type="hidden" id="page" name="page">
		        <p class="clearfix">
		        	<label class="one" for="con-email4" >商品名称：</label>
		        	<input class="con-email4" name="productName" value="${productName}" >
		            <label class="one" for="con-email4" >店铺名称：</label>
		        	<input class="con-email4" name="storeName" value="${storeName}" >
		        </p>
   		        <p class="clearfix">
		        	<label class="one" for="con-email4" >创建时间：</label>
		        	<input id="startTime" name="startTime"  class="con-email3"  type="text" readonly="readonly"  value="${startTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'startTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly="readonly"  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >启售/禁售：</label>    
		            <select id="enabled" name="enabled">
		            <option value="-1">全部</option>
		            <option value="0" <c:if test="${enabled ==0 }">selected="selected"</c:if>>禁售</option>
		            <option value="1" <c:if test="${enabled ==1 }">selected="selected"</c:if> >启售</option>
		            </select>
		            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		            <input type="submit" value="查询" class="submit8">
		        </p>
        	</form>
         	<form id="form1" name="form1"  class="zcform1">
	           	<p class="clearfix">
	             	<input type="submit" value="启售" class="submit9" onclick="examine(1)">
	             	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	             	<input type="submit" value="禁售" class="submit9"  onclick="examine(0)">
	           	</p>
         	</form>
          	<div class="member_myorder">
         	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td80"><input id="selectAll" type="checkbox" value="" onclick="selectAll()" /></th>
                    <th class="td160">商品名称</th>
                    <th class="td160">商户名称</th>
                    <th class="td160">评分</th>
                    <th class="td160">价格</th>  
                    <th class="td160">原价</th>
                    <th class="td160">销售数量</th>
                    <th class="td160">结束日期</th>
                    <th class="td160">下架日期</th>
                    <th class="td160">是否启售</th>
                    <th class="td160">排序等级</th>
                    <th class="td160">操作</th>
                </tr>
				<c:forEach items="${page}" var="page">
					<tr>
						<td class="td80"><input name="ids" type="checkbox" value="${page.productId}" /></td>
						<td class="td160">${page.productName}</td>
                    	<td class="td160">${page.storeName}</td>
                    	<td class="td160">${page.score}</td>
                    	<td class="td160">${page.unitPrice}</td>
                    	<td class="td160">${page.origPrice}</td>  
                    	<td class="td160">${page.quantity}</td>  
                    	<td class="td160"><fmt:formatDate value="${page.endDate}" pattern="yyyy-MM-dd"/></td>
                    	<td class="td160"><fmt:formatDate value="${page.offDate}" pattern="yyyy-MM-dd"/></td>
                    	<c:if test="${page.enabled == 0}">
                    		<td class="td160">禁售</td>
                    	</c:if>
                    	<c:if test="${page.enabled == 1}">
                    		<td class="td160">启售</td>
                    	</c:if>
                    	<td class="td160">${page.level}</td>  
                    	<td class="td160">
                    		<a href="/getProdDetail.do?prodId=${page.productId}" target="_Blank">查看</a>
                    		<a href="getProdTitle.do?prodId=${page.productId}">修改</a>
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
               		<input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
</body>
</html>