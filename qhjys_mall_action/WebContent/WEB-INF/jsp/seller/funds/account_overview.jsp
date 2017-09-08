<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/funds/account_overview.js"></script>
<title>飞券网商家后台中心</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
	
    	<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
        <h3 style="padding-left:10px; padding-bottom:20px;">商家账户总览</h3>
        <input type="hidden" id="token" name="token" value="${withdrawsToken}">
        <p class="clearfix w-bg" <c:if test="${sessionScope.store.channelValidation eq 1 || sessionScope.store.txShow eq 1}">style="display:none;"</c:if>>
        	
	        <label class="one" for="con-email2" style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家账户总额：</label>            
	        <label class="one4" for="con-email2">
			<c:choose>
	        	<c:when test="${empty cash }"><span  style="font-size:16px; color:#F60">￥0.00</span></c:when>
	        	<c:otherwise><span  style="font-size:16px; color:#F60">￥${cash.balance }</span></c:otherwise>
	        </c:choose>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	        <a href="/managermall/seller/funds/cashLog.do" class="submitblue110">查看流水详情</a>
      	<!--<span style="margin-left:200px;">免费提现额度：￥200,000.00元</span> -->
		</p>
		
        <form action="/managermall/seller/funds/account/overview.do"  method="post" id="from">
        		<input type="hidden" id="page" name="page" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
        </form>
         <p class="clearfix"  style="position:relative; height:60px;<c:if test="${sessionScope.store.channelValidation eq 1 || sessionScope.store.txShow eq 1}">display:none;</c:if>">
	           	<label class="one" for="con-email2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提现：</label>
	           	<input id="tiMoney" class="con-email4" value="" placeholder="输入提现金额" > 元	
                <!--当输入提现金额时出现--><span style="position:absolute; top:30px;left:80px; color:#999;"></span>         
    			<label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	  	        <input type="button" value="确定" class="submit6" onclick="goTiXian()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a href="javascript:showRecords()" class="submitblue110">查看提现记录</a>
              
         </p>
        <div class="member_myorder">
   	    <table  border="0" cellspacing="0" cellpadding="0">
        		<tr>
                	<th colspan="4" style="font-size:15px;">在线支付结算表</th>
                </tr>
                <tr>
                   <th class="td300">日期</th>
                    <th>金额</th> 
                    <th class="td300">结算情况</th>
                </tr>
                <c:forEach items="${page }" var="p" varStatus="status">
                	<tr>
                  		<td style="text-align:center; line-height:18px;">
                  			<p><fmt:formatDate value="${p.statementDate}" pattern="yyyy-MM-dd"/></p>
                  		</td>
						<td style="text-align: center;">￥${p.totalMoney }</td>
				  		<td style="text-align: center;">
				  		<c:if test="${p.state == 0 || p.state == 2}"><span class="t-fontred">未结算</span></c:if>
				  		<c:if test="${p.state == 1 || p.state == 4}"><span class="t-fontblue">已结算</span></c:if>
				  		<c:if test="${p.state == 3 }"><span class="t-fontblue">已出账</span></c:if>
				  		</td>
                	</tr>
                </c:forEach>
            </table>
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
               <input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        	</div>
        </div>
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
<!--查看提现记录弹出层2-->
<div id="cktxjl" class="white_content">
	<p class="close">提现记录<!--<a href="javascript:void(0)" onclick="document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">
×</a> --></p>
    <div class="nr">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <th>提现日期</th>
                    <th>金额</th>
                    <th>提现手续费</th>
                    <th>状态</th>
                </tr>
                <c:forEach items="${withdraws }" var="wd" varStatus="status">
	                <tr>
	                  <td><p><fmt:formatDate value="${wd.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
						<td>￥${wd.money }</td>
	                    <td>￥${wd.feeMoney }</td>
					  <td>
					  <c:if test="${wd.status==0}"><span class="t-fontgreen">审核不通过</span></c:if>
					  <c:if test="${wd.status==1}"><span class="t-fontblue">已审核</span></c:if>
					  <c:if test="${wd.status==2}"><span class="t-fontred">未审核</span></c:if>
					  <c:if test="${wd.status==3}"><span>已出账</span></c:if>
					  </td>
	                </tr>
                </c:forEach>
            </table>
    </div>
    <div class="hranniu"><a href="javascript:void(0)" onclick="document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">关闭</a></div>
</div>
<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>
