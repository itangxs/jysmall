<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jysmall_step2.js"></script>
<style type="text/css">
.zcform {width:600px;margin:30px 0 0 326px}
.zcform .submit2{margin-left:130px;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="1" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
    	<form id="form1" name="form1">
            <h3 style="padding-left:10px;">商家中心——店铺认证</h3>
        </form>
        <div class="member_myorder2">
        <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td161">&nbsp;</th>
                    <th class="td162">&nbsp;</th>
                    <th class="td167">&nbsp;</th>
                    <th class="td168">&nbsp;</th>
                 </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                     <th class="td160">入驻须知</th>
                    <th class="td160">公司信息认证</th>
                    <th class="td160">店铺信息认证</th>
                    <th class="td160">等待审核</th>
                 </tr>
            </table>
        </div>
         <div class="member_myorder">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                <th style="text-align:left; padding-left:20px; padding-top:30px; background:#fffef5;">卖家入驻联系人信息 用于入驻过程中接收飞券网反馈的入驻通知，请务必正确填写</th>
                </tr>
          </table>
         </div>
          <div class="member_myorder">   
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>    
                <th>
          <form id="signupForm" method="post" action="" class="zcform">	
        		<p class="clearfix">
        			<label class="one2" for="telphone" style="text-align:left;">卖家入驻联系人信息</label>
        		</p>
       			<p class="clearfix">
				<label class="one" for="realname">联系人姓名：</label>
				<input id="contactName" name="realname" class="con-email" value="${seller.realname }">
			</p>
			<p class="clearfix">
				<label class="one" for="phone">联系人手机：</label>
				<input id="contactPhone" name="phone" class="con-email" value="${seller.phone }">
			</p>
			<p class="clearfix">
				<label class="one" for="email">联系人邮箱：</label>
				<input id="contactEmail" name="email" class="con-email" value="${seller.email }">
			</p>
			<p class="clearfix">
				<input class="submit22" type="submit" value="下一步，完善公司信息" />
			</p>
    </form> </th></tr>
          </table>
         </div>
    	</div>
	<div class="clear"></div>
</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
