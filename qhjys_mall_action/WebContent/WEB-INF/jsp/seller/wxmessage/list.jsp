<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-商品订单</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">


<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/order/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/wxmessage.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="34" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
        	<div class="excelbutton"><a href="/managermall/seller/wxmessage/toSendMessage.do">编辑消息</a></div>
            <h3 style="padding-left:; padding-bottom:20px;">微信图文消息</h3>
			<form id="from" name="query" method="post" class="zcform1" action="/managermall/seller/wxmessage/list.do">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td100">消息</th>
                        <th class="td70">消息类型</th>
						<th class="td100">创建时间</th>
						<th class="td100">状态</th>
						<th class="td100">操作</th>
					</tr>
					<c:forEach var="wx" items="${page }">
						<tr>
							<td class="td100">
								
								<c:if test="${wx.type==1 }">${wx.title }</c:if>
								<c:if test="${wx.type==2 }">文本消息</c:if>
								<c:if test="${wx.type==3 }">图片消息</c:if>
							</td>
							<td class="td70">
								<c:if test="${wx.type==1 }">图文</c:if>
								<c:if test="${wx.type==2 }">文本</c:if>
								<c:if test="${wx.type==3 }">图片</c:if>
							</td>
							<td class="td100">
								<fm:formatDate value="${wx.createTime }" pattern="yyyy-MM-dd HH:mm" />
							</td>
							<td class="td100">
								<c:if test="${wx.status==0 }">审核中</c:if>
								<c:if test="${wx.status==1 }">审核通过</c:if>
								<c:if test="${wx.status==2 }">审核失败
									<c:if test="${not empty wx.remark }">: ${wx.remark }</c:if>
								</c:if>
								<c:if test="${wx.status==3 }">等待发送</c:if>
								<c:if test="${wx.status==4 }">已发送</c:if>
							</td>
	                        <td class="td100">
	                       		<c:if test="${wx.status==0 }"><span class="textgray">请等待审核</span></c:if>
								<c:if test="${wx.status==1 }"><span><a target="_blank" class="textyellow" href="/managermall/seller/wxmessage/previewByseller.do?id=${wx.id }">预览</a>&nbsp;&nbsp;<a class="textyellow" href="javascript:smsg('${wx.id }')">发送</a></span></c:if>
								<!-- <c:if test="${wx.status==2 }"><span><a class="textyellow" href="">预览</a>&nbsp;&nbsp;<a class="textyellow" href="">发送</a></span></c:if>-->
								<c:if test="${wx.status==3 }">等待发送</c:if>
								<c:if test="${wx.status==4 }"><span><a class="textyellow"  target="_blank"  href="/managermall/seller/wxmessage/previewByseller.do?id=${wx.id }">预览</a>&nbsp;&nbsp;</span></c:if>
	                        </td>
						</tr>
					</c:forEach>
				</table>
			</div>
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
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>