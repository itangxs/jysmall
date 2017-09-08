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
<script type="text/javascript" src="/js/system/product/review_list.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
		<div class="memberright">
	     <h3 style="padding-left:10px; margin-bottom:25px;">商品管理——商品评论</h3>
	    	 <form id="from" name="from"  class="zcform1">
    			<input type="hidden" id="page" name="page">
	            <p class="clearfix">
		        	<label class="one" for="con-email4" >评论商品：</label>
		        	<input class="con-email4" name="productName" value="${productName}" >
		            <label class="one" for="con-email4" >评论人：</label>
                    <input class="con-email4" name="userName"  value="${userName}">
                </p>
                 <p class="clearfix">    
		            <label class="one" for="con-email4" >评论时间：</label>
		        	<input id="startTime" name="startTime"  class="con-email3"  type="text" readonly  value="${startTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'startTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endTime" name="endTime"  class="con-email3"  type="text" readonly  value="${endTime}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		        	<label class="one" for="con-email4" >评论店铺：</label>
		        	<input class="con-email4" name="storeName" value="${storeName}" >   
		            <input type="submit" value="查询" class="submit8">
	            </p>
	        </form>
	        <form id="form1" name="form1"  class="zcform1">

	        </form>
	         <form id="form1" name="form1"  class="zcform1">
	           <p class="clearfix">
	             <input type="submit" value="删除" class="submit9" onclick="delAll()">          
	           </p>
	        </form>
	          <div class="member_myorder">
	         <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="td80"><input id="selectAll" type="checkbox" value="" onclick="selectAll()" /></th>
	                    <th class="td160">评论商铺</th>
                    	<th class="td160">评论商品                    </th>
                    	<th class="td160">评论人</th>          
                    	<th class="td160">评论时间</th>
                    	<th class="td160">操作</th>
	                </tr>
				<c:forEach items="${page}" var="page">
					<tr>
						<td class="td80"><input name="ids" type="checkbox" value="${page.id}" /></td>
                    	<td class="td160">${page.storeName}</td>
                    	<td class="td160">${page.productName}</td>
                    	<td class="td160">${page.userName}</td>  
                    	<td class="td160"><fmt:formatDate value="${page.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    	<td class="td160"><a href="/getProdDetail.do?prodId=${page.productId}#xiaofeipingjia" target="_blank">查看</a> </td>
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