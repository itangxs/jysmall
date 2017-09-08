<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="7"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
        <div class="member_money">
			<div class="tabs">
            	<a href="/managermall/account/myPoints.do" class="tabaction">我的积分</a>
            	<a href="/managermall/account/pointsDetailList.do">积分明细</a>
				<div class="clear"></div>
            </div>        
            
            <div class="member_points">
            	<h3>
            		您当前的积分是 <span>${sessionScope.userCashAccount.integral}</span>分
            		年底将有 <span>0</span> 积分过期 <em>至2015年12月31日</em>
            	</h3>
            	<h2><span>积分可抵现金啦！</span>100积分=1元，团购全场通用&nbsp;&nbsp;<a class="fontred" href="/mallcms/info2.do?id=14" target="_blank">>>查看积分规则</a></h2>
                <p>如何"积分抵现金"</p>
                <img src="/images/jifen.jpg" />
            </div>   
      	</div>
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>