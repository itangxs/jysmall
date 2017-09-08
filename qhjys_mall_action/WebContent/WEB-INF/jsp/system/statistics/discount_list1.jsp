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
<script type="text/javascript" src="/js/system/statistics/discount_list.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="8" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    		<h3 class="jinrongtitle">交易订单列表</h3>
           <form id="from" class="zcform1" method="post" action="/managermall/systemmall/statistics/discountList.do">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<input type="hidden" id="isSelect" name="isSelect" value="1">
            <p class="clearfix">
            <label class="one" for="con-email4" >创建时间：</label>       
        	<input id="begin" name="benginTime" class="easyui-datebox con-time" value="${benginTime}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="endTime" class="easyui-datebox con-time" value="${endTime}">
            <label class="one" for="con-email4" >
        	<label class="one" for="con-email4" >订单号：</label>
        	<input class="con-email4" name="orderNo" value="${orderNo}" >
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}" > 
        	 </p>
         <p class="clearfix" style="margin-top:8px;">
             <label class="one" for="con-email4" >支付方式：</label>
        	<select name="payType">
        		<option value="">全部</option>
        		<option value="1" <c:if test="${payType == 1}">selected="selected"</c:if>>微信</option>
        		<option value="2" <c:if test="${payType == 2}">selected="selected"</c:if>>支付宝</option>
        		<option value="3" <c:if test="${payType == 3}">selected="selected"</c:if>>QQ钱包</option>
        	</select>
        	 <label class="one" for="con-email4" >消费来源：</label>
        	<select name="bankType">
        		<option value="">全部</option>
        		<option value="1" <c:if test="${bankType == 1}">selected="selected"</c:if>>零钱</option>
        		<option value="2" <c:if test="${bankType == 2}">selected="selected"</c:if>>信用卡</option>
        		<option value="3" <c:if test="${bankType == 3}">selected="selected"</c:if>>储蓄卡</option>
        	</select>
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">&nbsp;
             <input type="button" value="重置" class="submit8"  onclick="window.location.href=window.location.href">&nbsp;
             <input type="button" value="导出表格" class="submit9" onclick="Excel()">
             <input type="button" value="变更套现设置" class="submit110" onclick = "document.getElementById('bgtx').style.display='block';document.getElementById('fade').style.display='block'"> 
         </p>
        </form>
        
     <h3 class="jinrongtitle">查询统计</h3>
        <div class="jinrongbk">
        	<ul>
            	<li><strong>店铺数量：${orderCountVo.storeNum} 个</strong></li>
                <li><strong>会员数量：${orderCountVo.userNum} 个</strong></li>
                <li><strong>订单数量：${orderCountVo.orderNum} 条</strong></li>
                <li><strong>交易金额：${orderCountVo.payTotal} 元</strong></li>
                <li><strong>手续费用：${orderCountVo.rateTotal} 元</strong></li>
                <li><strong>到账金额：${orderCountVo.totamtTotal} 元</strong></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th class="td140">订单号</th>
                    <th class="td100">创建<br>时间</th>
                    <th class="td160">会员<br>ID</th>
                    <th class="td120">店铺<br>名称</th>
                    <th class="td60">交易<br>金额</th>
                    <th class="td70">手续<br>费率</th> 
                    <th class="td60">手续费</th> 
                    <th class="td80">到账<br>金额</th>       
                    <th class="td70">支付<br>方式</th> 
                    <th class="td70">消费<br>来源</th>
                    <th class="td70">套现<br>属性</th>
                    <th class="td70">套现设置</th>
                </tr>

                
                <tr>
                    <td class="td140 tdfont" style="word-break:break-all;">${s.orderNo }</td>
                    <td class="td100 tdfont"><fmt:formatDate value="${s.payTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td class="td160" style="word-break:break-all;">${s.openId }</td> 
                    <td class="td120">${s.storeName }</td>  
                    <td class="td60">${s.realPay }</td> 
                    <td class="td70">${s.orderRate }%</td> 
                    <td class="td60">${s.rateFee }</td> 
                    <td class="td80">${s.totamt }</td>
                    <td class="td70">  <c:if test="${s.type==1 || s.type==3 || s.type==5}">
										微信							   
							   </c:if>
							    <c:if test="${s.type==2 || s.type==6}">
										支付宝						   
							   </c:if>
							    <c:if test="${s.type==7}">
										QQ钱包							   
							   </c:if></td> 
                    <td class="td70"><c:if test="${s.bankType == 1}">零钱</c:if>
					                    <c:if test="${s.bankType == 2}">信用卡</c:if>
					                    <c:if test="${s.bankType == 3}">储蓄卡</c:if>
					                    <c:if test="${empty s.bankType || s.bankType=='' }">-</c:if>
                    </td>  
                    <td class="td70">否</td>
                    <td class="td70"><input name="ids" type="radio" value="${s.id}" onclick="xuan('dx')" />是 否<input name="ids" type="radio" value="${s.id}" onclick="xuan('dx')" /></td>
                </tr>
                
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
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick = "">确认</a>
                                                        
     </div> 
	</div>
 

<div id="fade" class="black_overlay"></div>
</body>
</html>
