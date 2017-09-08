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
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="52" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">红包活动详情</h3>
          
          <div class="hongbaodata">        
          	 <div class="data_left">
             	 <p class="clearfix">
            		<label class="con-email130" for="con-email4">活动名称： <span class="data_show">${fqRedpack.activityName } </span></label>
        	     </p>
                 <p class="clearfix">
             		<label class="con-email130" for="con-email4" >每日活动时段：
             		 <span class="data_show">
             		 <c:forEach items="${redpackTimes }" var="time" varStatus="sta">
                	${sta.count }.
                	<fmt:parseNumber integerOnly="true" value="${time.beginTime/60}" />点${time.beginTime%60} 分  
                	 至  <fmt:parseNumber integerOnly="true" value="${time.endTime/60 }" />点${time.endTime%60}分
                	</c:forEach>
             		</span> </label>
                 </p>
                 <p class="clearfix">
            		<label class="con-email130" for="con-email4" >红包最大金额： <span class="data_show">${fqRedpack.maxAmount }元</span></label> 
        	     </p>
             </div>
         
             <div class="data_right">
             	 <p class="clearfix">
        			<label class="con-email130" for="con-email4" >活动周期：<span class="data_show"><fmt:formatDate value="${fqRedpack.beginDate}" pattern="yyyy-MM-dd"/>     至    
        	<fmt:formatDate value="${fqRedpack.endDate}" pattern="yyyy-MM-dd"/></span></label>       
        	     </p>
                 <p class="clearfix">
            		<label class="con-email130" for="con-email4" >红包总金额：<span class="data_show">${fqRedpack.totalMoney }元</span></label> 
        		 </p>
             	 <p class="clearfix">
           			 <label class="con-email30" for="con-email4" >红包最小金额：<span class="data_show">${fqRedpack.minAmount }元</span></label> 
        	     </p>
             </div>
             	<c:forEach items="${details}" var="detail" varStatus="sta"> 
          		 	<p>
                	<label class="con-email30" for="con-email4" ><span class="data_show">&nbsp;&nbsp;${sta.count }. </span>
                	<c:if test="${detail.type==1}">{获得红包概率${detail.probability} %}</c:if>
                	<c:if test="${detail.type==2}">顾客支付金额：<span class="data_show">${detail.minMoney}元——${detail.maxMoney}元</span></c:if>&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;获得红包金额：<span class="data_show">${detail.minAmount}元——${detail.maxAmount}元（精确到0.01元）</span></label> 
                	</p>
           </c:forEach>
             
             
             <p class="clearfix">
            <label class="con-email130" for="con-email4" >单一微信号每日可领红宝数：<span class="data_show">${fqRedpack.daliyNum }个</span></label>    
        	 </p>
         </div>    
             
             
             
             
             
        <div class="hongbaodetail">
        	<div class="hbtop"><span class="title">红包发放报表</span>发放总金额：${recordVo.countMoney } 元  &nbsp;&nbsp; 发放总次数：${recordVo.countNum } 次&nbsp;&nbsp;&nbsp;&nbsp;
        	<!-- <input name="button" type="button" class="submitred120 rightanniu" value="导出至EXCEL" onclick="excel()">--></div> 
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
             <center>
             <c:if test="${fqRedpack.hasChange == 1 }"><input type="button" value="修改" class="submitred120" onclick="window.location.href='/managermall/seller/redpack/hongbao_modify.do?redpackId=${fqRedpack.id}'">
             </c:if> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input name="取消" type="button" class="submitskyblue120" value="取消" onclick="window.location.href='/managermall/seller/redpack/redpacklist.do'"> </center> 
         </p>     
	</div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>