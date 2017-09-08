<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script type="text/javascript" src="/js/system/finance/recharge.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
		<div class="memberright">
        <h3 style="padding-left:10px; margin-bottom:25px;">会员管理——平台充值</h3> 
        <form id="form1" name="form1"  class="zcform1">
       	   <p class="clearfix">
        	 <label class="one" for="con-email4" >会员名：${userName}</label>
        	 <br />
        	 <input type="hidden" value="${userId}" name="userId"/>
	       </p>
	       <p class="clearfix">
		        <label class="one" for="con-email4" >充值金额：</label>
		        <input class="con-email4" name="money" >
		                元</p>
           <p class="clearfix">
           	 <input type="hidden" name="token" value="${rechargeToken}"/>
             <input type="submit" value="充值" class="submit10">          
           </p>
        </form>
  	</div>
</div>            
<div class="clear"></div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>