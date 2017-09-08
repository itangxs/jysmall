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
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/system/finance/store_loan.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
	 <form id="from2" name="from2" action="/managermall/systemmall/finance/financelistRepayment.do" method="post">
	 	<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
	 	<input id="ids" name="id" value="${loanList.id }" type="hidden" />
	 </form>
     	<h3 class="jinrongtitle">项目列表-还款</h3>
        <h3 class="jinrongtitleline">${loanList.storeName }</h3>
        <div class="jinrongbk">
        	<ul>
            	<li>放款金额：<span class="fontred" >${loanList.loanTotal }</span> 元</li>
                <li>剩余本金：<span class="fontred" >${loanList.norepayment }</span> 元</li>
                <li>服务费收入：<span class="fontred" >${loanList.interestTotal }</span> 元</li>
                <div class="clear"></div>
            </ul>
        </div>
        <h3 class="jinrongtitleline">新增还款</h3>
        <div>
        	
         <form id="from" name="form1" action="/managermall/systemmall/finance/insertRepayment.do" method="post">
          
          <input id="id" name="id" value="${loanList.id }" type="hidden" />
          <p class="jinrongformform">
          	<span class="jinrongformleft">本&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金</span>
          	<input class="con-email4" name="principal" >&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            服务费&nbsp;&nbsp;<input class="con-email4" id="interest" name="interest" value="0">&nbsp;&nbsp;元
          </p>
          <p class="jinrongformform">
          	<span class="jinrongformleft">还款方式</span>
            <input name="repaymentType" type="radio" value="1" id="type1" checked>&nbsp;&nbsp;即时还款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="repaymentType" type="radio" value="2" id="type2">&nbsp;&nbsp;预约还款 
          </p>
          <!--当选择预约付款是显示开始-->
          <p class="jinrongformform" style="display:none;" id="yuyue">
             <span class="jinrongformleft"></span>
			 预约时间&nbsp;&nbsp;<input id="beginTime" name="reservationTime"  class="con-email3"  type="text" readonly="readonly"   style="width: 160px"/>
        	 <img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,el:'beginTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/>
          </p>
          <!--当选择预约付款是显示结束-->
          <p class="jinrongformform"> 
             <span class="jinrongformleft"></span>
             <input type="submit" value="确认" class="submit8">&nbsp;&nbsp;&nbsp;&nbsp;
             <input name="重置" type="reset" class="submit8" value="重置">
          </p>
          <p>&nbsp;</p>
        </form>
        </div>
        <h3 class="jinrongtitleline">还款列表</h3>
          <div class="member_myorder">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">还款时间</th>
                    <th class="td160">还款方式</th>
                    <th class="td160">还款金额</th>
                    <th class="td160">服务费用</th>
                    <th class="td160">状态</th>
                    <th class="td160">操作</th>
                </tr>
                <c:forEach var="p" items="${page}">             
	                <tr>
	                    <td class="td160">
	                    	<c:if test="${p.repaymentType == 1 }"><fmt:formatDate value="${p.repaymentTime }" pattern="yyyy-MM-dd HH:mm:ss "/></c:if>
	                    	<c:if test="${p.repaymentType == 2 }"><fmt:formatDate value="${p.reservationTime }" pattern="yyyy-MM-dd HH:mm:ss "/></c:if>
	                    </td>
	                    <td class="td160">
	                    	<c:if test="${p.repaymentType == 1 }">及时还款</c:if>
	                    	<c:if test="${p.repaymentType == 2 }">预约还款</c:if>
	                    </td>
	                    <td class="td160">￥${p.principal }</td>
	                    <td class="td160">￥${p.interest }</td>
	                    <td class="td160">
							<c:if test="${p.status == 0 }">等待还款</c:if>
							<c:if test="${p.status == 1 }">已还款</c:if>
	                    	<c:if test="${p.status == 2 }">还款失败</c:if>
	                    	<c:if test="${p.status == 3 }">已取消</c:if>
						<!--等待还款/还款失败/已取消/已还款-->
						</td>
	                    <td class="td160">
	                    	<c:if test="${p.status == 1|| p.status == 2 || p.status == 3 }">-</c:if>
		                    <c:if test="${p.status == 0  }">
		                   		<a href="javascript:updateStatus('${p.id}')" class="fontred">取消</a>
		                    </c:if>
	                  	</td>
	                </tr>
                </c:forEach>            
              </table>
            </div>
            <!--上一页下一页-->
        <div class="page">
            	<c:if test="${page.getPageNum()>1}">
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from2','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from2','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from2','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from2','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from2','page')" class="next">下一页</a></c:if>
               <span>共${page.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from2','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
