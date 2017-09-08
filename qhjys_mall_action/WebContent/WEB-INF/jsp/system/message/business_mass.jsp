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
<script type="text/javascript" src="/js/system/message/applylist.js"></script>
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
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="32" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
     <h3 style="padding-left:10px; margin-bottom:25px;">商家群发消息管理</h3>
    	  <form id="from" name="form" action="/managermall/systemmall/apply/wxMessageList.do" method="post">
          <input id="page" name="pageNum" type="hidden">
           <p class="clearfix">
        	<label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" id="storeId" name="storeId" value="${storeId }">
            <label class="one" for="con-email4" >&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >商家名称：</label>
        	<input class="con-email4" id="storeName" name="storeName" value="${storeName }">
			<label class="one" for="con-email4" >&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >审核状态：</label>
        	 <select name="status" style="border:1px #aaaaaa solid; width:120px; height:30px;">
        	 <option value="">全部</option>
             <option value="0" <c:if test="${status==0}"> selected="selected"</c:if>>审核中</option>
             <option value="1" <c:if test="${status==1}"> selected="selected"</c:if>>审核通过</option>
             <option value="2" <c:if test="${status==2}"> selected="selected"</c:if>>审核不通过</option>
             <option value="3" <c:if test="${status==3}"> selected="selected"</c:if>>等待发送</option>
             <option value="4" <c:if test="${status==4}"> selected="selected"</c:if>>已发送</option>
             </select> 
             <label class="one" for="con-email4" >&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">
             <label class="one" for="con-email4" >&nbsp;</label>
             <input type="button" value="重置群发次数" class="submit110" onclick="document.getElementById('reset-times').style.display='block';">
           </p>
        </form>       

     <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
               	  	<th class="td80">ID</th>
                    <th class="td160">商家名称</th>
                    <th class="td160">消息标题</th>
                    <th class="td160">状态</th>
                    <th class="td160">操作</th>
                </tr>
                <c:forEach var="wx" items="${page }">
					<tr>
						<td class="td80">${wx.id }</td>
	                    <td class="td160">${wx.storeName }</td>
	                    <td class="td160"><c:if test="${wx.type == 1 }">${wx.title }</c:if>
	                    <c:if test="${wx.type == 2 }">文本消息</c:if>
	                    <c:if test="${wx.type == 3 }">图片消息</c:if></td>
						<td class="td160">
							<c:if test="${wx.status==0 }">审核中</c:if>
							<c:if test="${wx.status==1 }">审核通过</c:if>
							<c:if test="${wx.status==2 }">审核失败</c:if>
							<c:if test="${wx.status==3 }">等待发送</c:if>
							<c:if test="${wx.status==4 }"><fmt:formatDate value="${wx.sendTime }" pattern="yyyy-MM-dd HH:mm" /> 已发送</c:if>
						</td>
	                       <td class="td160">
	                       	<c:if test="${wx.status==0 }"><a class="textyellow" onclick="gopreview('${wx.id}')">去审核</a></c:if>
							<c:if test="${wx.status==1 }"><a class="textyellow" target="_blank" href="/managermall/systemmall/apply/previewBySystem.do?id=${wx.id }">预览</a></c:if>
							<c:if test="${wx.status==2 }">-</c:if>
							<c:if test="${wx.status==3 }">-</c:if>
							<c:if test="${wx.status==4 }"><a class="textyellow" target="_blank" href="/managermall/systemmall/apply/previewBySystem.do?id=${wx.id }">预览</a></c:if>
	                       </td>
					</tr>
				</c:forEach>
				 <!--插入超链接弹窗-->
				<div id="reset-times" class="wx_white_content02">
					<p class="close">重置群发次数
        				<a href="javascript:void(0)" class="wx-close-btn" onclick="document.getElementById('reset-times').style.display='none';"><img src="/images/seller/wx-close-btn.png"></a>
        			</p>
					<div class="nr06">
						<p>
                        	商家ID：<input class="con-email4" id="sID" name="sID" value="${sID }">
                        	<label class="one" for="con-email4" >&nbsp;&nbsp;</label>
             				<input type="submit" value="查询" class="submit8" onclick="getStore()">
                        </p>
            			<p><span id="sName"></span></p>
            			<input onclick="resetMessageNum()" class="submitblue106" type="button" value="重置"/>                    
					</div>
				</div>
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
        
        <!--预览-->
        <div class="preview" id="sjyl">
    		<!-- <iframe src="/managermall/seller/wxmessage/preview.do" width="1200" height="920" marginwidth="0" marginheight="0" frameborder="0" scrolling="no"></iframe> -->
    	</div>
        
        <!--去审核-->
       
        
	</div>
	<div class="clear"></div>
    
 
    
    
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
