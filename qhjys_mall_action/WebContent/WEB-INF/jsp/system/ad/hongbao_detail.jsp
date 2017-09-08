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
<script type="text/javascript" src="/js/system/ad/hongbao_detail.js"></script>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">红包活动详情</h3>
           <form id="from" method="post" action="/managermall/systemmall/ad/hongbao_detail.do">
    			<%-- <input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}"> --%>
    			<input type="hidden" id="redpackId" name="redpackId" value="${redpackId}">
    		</form>
                <p class="clearfix">
            <label class="con-email120" for="con-email4" >活动名称</label>
        	${fqRedpack.activityName } 
        	 </p>
              <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家ID</label>
        	${fqRedpack.storeId }
        	 </p>
             <div>
             	<div class="fdgl-left">店铺名称</div>
                <div class="fdgl-right">${fqRedpack.storeName }
                </div>
                <div class="clear"></div>
             </div>
             
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动周期</label>       
        	<fmt:formatDate value="${fqRedpack.beginDate}" pattern="yyyy-MM-dd"/>     至    
        	<fmt:formatDate value="${fqRedpack.endDate}" pattern="yyyy-MM-dd"/>
         </p>
         	<div>
             	<div class="fdgl-left" ><label class="con-email120" for="con-email4" >每日活动时段</label></div>
                <div class="fdgl-right">
                	<c:forEach items="${redpackTimes }" var="time" varStatus="sta">
                	<span class="iconmembox"><em class="iconmem">${sta.count }</em>
                	<fmt:parseNumber integerOnly="true" value="${time.beginTime/60}" />点${time.beginTime%60} 分  
                	 至  <fmt:parseNumber integerOnly="true" value="${time.endTime/60 }" />点${time.endTime%60}分</span>
                	</c:forEach>
                </div>
                <div class="clear"></div>
             </div>
              <p class="clearfix">
            <label class="con-email120" for="con-email4" >红包总金额</label> ${fqRedpack.totalMoney }  元  
        	 </p>
            <p class="clearfix">
            <label class="con-email120" for="con-email4" >红包最大金额</label> ${fqRedpack.maxAmount }  元  
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >红包最小金额</label> ${fqRedpack.minAmount }  元  
        	 </p>
             <div>
             	<div class="fdgl-left" ></div>
                <div class="fdgl-right" style="margin-left:-116px;">
                	<c:forEach items="${details}" var="detail" varStatus="sta"> 
                	<p><span class="iconmem">${sta.count}</span><label class="con-email120" for="con-email4" style="margin-left:-20px;" >获得红包金额&nbsp;</label>${detail.minAmount}元——${detail.maxAmount}元（精确到0.01元）  
                	<c:if test="${detail.type==1}">{获得红包概率${detail.probability} %}</c:if>
                	<c:if test="${detail.type==2}">&nbsp;&nbsp;<label class="con-email120" for="con-email4" style="margin-left:-20px;" >顾客支付金额</label>   ${detail.minMoney}元--${detail.maxMoney}元 </c:if>
                	</p>
                	</c:forEach>
                </div>
                <div class="clear"></div>
             </div>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" ><span style="line-height:15px;vertical-align:middle; display:inline-block;">单一微信号<br>
每日可领红包数</span></label> ${fqRedpack.daliyNum } 个   
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" ><span style="line-height:15px;vertical-align:middle; display:inline-block;">开通商家权限<br>
		</span></label><c:if test="${fqRedpack.hasChange == 1 }">已开通权限</c:if>
	                    <c:if test="${fqRedpack.hasChange == 0 }">未开通权限</c:if>
        	 </p>
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">红包发放报表</span>发放总金额：${recordVo.countMoney } 元  发放总次数：${recordVo.countNum } 次&nbsp;&nbsp;&nbsp;&nbsp;
        	<input name="button" type="button" class="submitblue120 rightanniu" value="导出至EXCEL" onclick="excel()"></div>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="hongbaotable">
              <tr>
                <th>发放时间</th>
                <th>发放商家</th>
                <th>消费金额</th>
                <th>发放金额</th>
              </tr>
              <c:forEach items="${page }" var="record">
	              <tr>
	                <td><fmt:formatDate value="${record.createTime }" pattern="yyyy-MM-dd"/></td>
	                <td>${record.storeName }</td>
	                <td>${record.consumeMoney }</td>
	                <td>${record.redpackMoney }</td>
	              </tr>
              </c:forEach>
            </table>
        </div>
   		<p class="clearfix">
        	 <label class="con-email120"></label>
             <center><input type="button" value="修改" class="submitred120" onclick="javascript:xiugai('${redpackId}')"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input name="取消" type="button" class="submitblue120" value="取消" onclick="javascript:quxiao()"> </center>      
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
