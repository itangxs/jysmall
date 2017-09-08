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
<script type="text/javascript" src="/js/system/channel/channel.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">有效店铺策略</h3>
           <form id="channelForm" name="from"  class="zcform1">
		        <div class="cel_detail">
          			<div class="cel_left">
          	 			<p class="clearfix">
                    		<label class="one" for="con-email4" >生效时间：</label>        
        	 			</p>
             			<p class="clearfix">
                    		<label class="one" for="con-email4" >有效条件：</label>        
        	 			</p>
          			</div>
         			 <div class="cel_right">
						<p class="clearfix">
                			<input id="effectiveTime" name="effectiveDate"  class="con-email4"  type="text" readonly  value="${beginTime}"/>
        					<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'effectiveTime'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/>
                		</p>
                		<p class="clearfix">
                			订单交易金额≥ <input value="" id="totalMoney" name="totalMoney" class="w180"> 元
                		</p>
                		<p class="clearfix">
                			支付订单数量≥ <input value="" id="totalNum" name="totalNum" class="w180"> 笔
                			<input value="1" id="type" name="type" type="hidden">
                		</p>
                		<p class="clearfix">
                			<input type="submit" value="保存" class="submityellow60">
                		</p>
         			 </div>
        		</div>		        
        	</form> 
        	     <div class="now-info">
        			<h3>当前策略信息</h3>
	        		<div class="effective-time">
	        			<label>当前生效时间：</label><span><fmt:formatDate value="${fqChannelRole. effectiveTime}" pattern="yyyy-MM-dd HH:mm"/></span>
	        		</div>
					<div class="effective-condition"><label>当前生效时间：</label><span>订单交易额&ge;${fqChannelRole.totalMoney }元</span><span>支付次数&ge;${fqChannelRole.totalNum }笔</span><br>
					
					</div>
        		</div>          
</div>            
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>