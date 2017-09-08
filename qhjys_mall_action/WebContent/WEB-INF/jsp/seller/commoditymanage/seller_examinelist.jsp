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
    	<div class="excelbutton"><a href="#">添加折扣</a></div>
        <!--折扣弹出窗口开始-->
        <div class="zhekoubox" style="display:;">
        	<div class="title">添加折扣</div>
        	<ul>
            	<li>
                    <div class="left">折扣名称：</div>
                    <div class="right"><input  name="name" class="con-email2" value="${name}" > <span>请输入折扣名称</span></div>
                </li>
                <li>
                    <div class="left">开始日期：</div>
                    <div class="right"><input id="tjsjs" name="tjsjs"  class="con-email3"  type="text" readonly="readonly"  value="${tjsjs}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> <span>请输入开始日期</span></div>
                </li>
                <li>
                    <div class="left">结束日期：</div>
                    <div class="right"><input id="tjsjs" name="tjsjs"  class="con-email3"  type="text" readonly="readonly"  value="${tjsjs}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'tjsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> <span>请输入结束日期</span></div>
                </li>  
                <li>
                    <div class="left">市场价格：</div>
                    <div class="right"><input  name="name" class="con-email3" value="${name}" > <span>请输入市场价格</span></div>
                </li> 
                <li>
                    <div class="left">折扣价格：</div>
                    <div class="right"><input  name="name" class="con-email3" value="${name}" > <span>请输入折扣价格</span></div>
                </li>  
                <li>
                    <div class="left"></div>
                  <div class="right"><input type="submit" value="提交审核" class="button1"><input name="重置" type="reset" class="button2" value="取消"></div>
                </li>          
            </ul>
        </div>
        <!--折扣弹出窗口结束-->
    	<h3 style="padding-left:; padding-bottom:20px;">折扣审核</h3>
         <form action="/managermall/seller/commoditymanage/examinelist.do" method="post" id="from">
			<input type="hidden" id="page" name="page">
           <p class="clearfix">
        	<label class="one" for="con-email2" >商户名称：</label>
        	<input  name="name" class="con-email2" value="${name}" >
        	
           <%--  <label class="one" for="con-email2" >截止时间：</label>
        	<input id="jzsjs" name="jzsjs"  class="con-email3"  type="text" readonly="readonly"  value="${jzsjs}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jzsjs'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
           
            <label class="one" for="con-email2" >-</label>
            <input id="jzsje"  name="jzsje" class="con-email3"  type="text" readonly="readonly"  value="${jzsje}"/>
        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jzsje'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
            --%>
            <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核</label>
            <select name="status" id="select">
                <option value="">全部</option>
              
                <option value="0"   <c:if test="${status =='0' }">selected="selected"</c:if>>审核中</option>
                <option value="1"	<c:if test="${status =='1' }">selected="selected"</c:if>>未通过</option>
                <option value="2"	<c:if test="${status =='2' }">selected="selected"</c:if>>已通过</option>
            </select>
            
           
           </p>
           
         </form>
         
         <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="">折扣名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th class="td80">折扣</th>
                    <th class="td100">开始日期</th>
                    <th class="td100">结束日期</th>
                    <th class="td100">提交日期</th>
                    <th class="td80">审核状态</th>
                    <th class="td80">操作</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
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
                        <p style="display:none;">未通过</p>
                        <p style="display:none;">已通过</p>
                    </td>
                    <td class="td80">
                    	<a href="#">查看</a>
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
