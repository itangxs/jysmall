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
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div style="background-color:#f9f4df">
	<div class="yingxiao-banner">
    	<img src="/images/seller/yingxiao-banner.jpg">
        <img src="/images/seller/yingxiao-go.jpg">
        <div class="yingxiao-rule">
    
                <p>1、老商户指与飞券合作的商户，新商户指尚未与飞券合作的过的商户；</p>
				<p>2、新老商户任一方联系飞券（客服热线：400-6333-088），飞券将安排与新商户的合作洽谈；</p>
				<p>3、凡新商户与飞券达成合作，老商户获得300元营销基金，新商户获得100元营销基金；</p>
				<p>4、奖励的营销基金将在新商户与飞券签署协议3天内，充值至新老商户的红包账户，同时即刻开启支付返现活动；</p>
				<p>5、不限定邀请商户数量，老商户可继续邀请新商户赚取营销基金，也可向账户进行充值持续返现活动；</p>
				<p>6、飞券保留本次活动最终解释权。</p>       
        </div>
    </div>

</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->



</body>
</html>