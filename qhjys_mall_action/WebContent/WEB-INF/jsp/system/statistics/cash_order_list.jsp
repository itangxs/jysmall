<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/statistics/cash_discount_list.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="12" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    		<h3 class="jinrongtitle">套现订单列表</h3>
           <form id="from" class="zcform1" method="post" action="/managermall/systemmall/statistics/cashDiscountList.do">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<input type="hidden" id="isSelect" name="isSelect" value="1">
            <p class="clearfix">
            <label class="one" for="con-email4" >创建时间：</label>       
        	<input id="begin" name="benginTime" class="easyui-datebox con-time" value="${benginTime}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="endTime" class="easyui-datebox con-time" value="${endTime}">
            <label class="one" for="con-email4" >
            <label for="select">&nbsp;</label>
        	<label class="one" for="con-email4" >订单号：</label>
        	<input class="con-email4" name="orderNo" value="${orderNo}" >
            <label for="select">&nbsp;</label>
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}" > 
        	 </p>
              <p class="clearfix">
              <label class="one" for="con-email4" >店铺ID：</label>
        	<input class="con-email4" name="storeId" value="${storeId}" >
            <label for="select">&nbsp;&nbsp;</label>
            <label class="one" for="con-email4" >会员ID：</label>
        	<input class="con-email4" name="openId" value="${openId }" > 
        	 </p>
         <p class="clearfix" style="margin-top:8px;">
             <label class="one" for="con-email4" >支付方式：</label>
        	<select name="payType">
        		<option value="">全部</option>
        		<option value="1" <c:if test="${payType == 1}">selected="selected"</c:if>>微信</option>
        		<option value="2" <c:if test="${payType == 2}">selected="selected"</c:if>>支付宝</option>
        		<option value="3" <c:if test="${payType == 3}">selected="selected"</c:if>>QQ钱包</option>
        	</select>
            <label for="select">&nbsp;&nbsp;</label>
        	 <label class="one" for="con-email4" >是否套现：</label>
        	<select name="bankType">
        		<option value="">全部</option>
        		<option value="1" <c:if test="${isCash == 1}">selected="selected"</c:if>>是</option>
        		<option value="0" <c:if test="${isCash == 0}">selected="selected"</c:if>>否</option>
        	</select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">&nbsp;
             <input type="button" value="导出表格" class="submit9" onclick="Excel()">&nbsp;
             <input type="button" value="变更套现设置" class="submit110" onclick = "document.getElementById('bgtx').style.display='block';document.getElementById('fade').style.display='block'"> 
         </p>
        </form>

        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th class="td120">交易<br>订单号</th>
                    <th class="td120">创建<br>时间</th>
                    <th class="td60">会员<br>ID</th>
                    <th class="td100">店铺<br>ID</th> 
                    <th class="td60">店铺<br>名称</th>
                    <th class="td60">交易<br>金额</th> 
                    <th class="td40">是否<br>套现</th>       
                    <th class="td70">支付<br>方式</th> 
                    <th class="td70">消费<br>来源</th>
                    <th class="td60">会员<br>支付笔数</th>
                    <th class="td100">套现设置</th>
                </tr>

                  <c:forEach items="${page}" var="s">
                <tr>
                    <td class="td120" style="word-break:break-all;">${s.orderNo }</td>
                    <td class="td100"><fmt:formatDate value="${s.payTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td class="td60">${s.openId }</td>
                    <td class="td70">${s.storeId }</td>
                    <td class="td100" style="word-break:break-all;">${s.storeName }</td>
                    <td class="td60">${s.realPay }</td>
                    <td class="td40"><c:if test="${s.isCash == 1}">是</c:if>
                    <c:if test="${s.isCash == 0}">否</c:if></td>
                    <td class="td70"><c:if test="${s.type==1 || s.type==3 || s.type==5}">
										微信							   
							   </c:if>
							    <c:if test="${s.type==2 || s.type==6}">
										支付宝						   
							   </c:if>
							    <c:if test="${s.type==7}">
										QQ钱包							   
							   </c:if>
							    <c:if test="${s.type==4}">
										POS							   
							   </c:if></td>
                    <td class="td70"><c:if test="${s.bankType == 1}">零钱</c:if>
					                    <c:if test="${s.bankType == 2}">信用卡</c:if>
					                    <c:if test="${s.bankType == 3}">储蓄卡</c:if>
					                    <c:if test="${empty s.bankType || s.bankType=='' }">-</c:if></td>
                    <td class="td60">${s.payNum }</td>
                    <td class="td100"><input id="iscash" name="iscash${s.id }" type="radio" value="${s.id}#1"/>是 否<input  id="iscash" name="iscash${s.id }" type="radio" value="${s.id }#0"/></td>

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
    <!--变更套现设置弹出层-->
	<div id="bgtx" class="white_content">
		<p class="close03">变更套现设置</p>
		<div class="nr nr01">
			<div class="kaquan_list">
				<p style="line-height:40px; font-size:24px">确定变更选中的订单套现属性吗？<p>
			</div>
		</div>
		<div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('bgtx').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "changeiscash()">确认</a>
                                                        
     </div> 
	</div>
 

<div id="fade" class="black_overlay"></div>
</body>
</html>
