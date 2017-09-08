<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/seller/header.jsp" flush="true" />
<head>
<title>飞券网商家后台中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/seller/back_password.js"></script>
<script type="text/javascript" src="/js/changeimg.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<div class="membercontent1">
   <div class="membertop">
       <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>商家找回密码</td>
				</tr>
       </table>         
     </div>
    <div class="memberbut">
	<!----------------左侧----------------------->
	<div class="memberleft2">  
		<div class="member_myorder" style="padding-left:35px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>    
			  <th class="td135">
			   1.输入账号
			 </th>
			 <th class="td136" >
			   2.重设密码
			 </th>
			 <th class="td136">
			   3.新密码设定成功
			 </th></tr>
			  </table>
			  <br /> <br />
			<form id="signupForm" method="post" action="/seller/toBackPassword2.do" class="zcform">
				<p class="clearfix">
					<label class="one" for="con-email" style="width:360px; text-align:right;">请输入账号：</label>
					<input name="phone"   type="text" id="phone" class="con-email4" >
					
				</p>    
				<p class="clearfix">
					<label class="one" for="con-email" style="width:360px; text-align:right;">验证码：</label>
					<input type="hidden" name="syzm" id="syzm" value="syzm"/>
					<input name="yzm" type="text" id="yzm" maxlength="11" style="width:120px;" class="con-email3" >
					<!-- <img src="/qhjys_mall/seller-manage/manage/images/captcha.png" width="56" height="28" style="padding-top:2px; padding-left:2px;" />  -->
					<span id="yanzhengmaimg" style="cursor: pointer;" onclick="changeImg('imageId','','');"> <img id="imageId" src="/valiCode.do" border="0"  style="margin-top:5px; margin-left:3px;"/></span>
					<span id="yzmTip"></span> 
					<span id="yzmTip"></span> 
				</p> 
				<p> 
					<button class="submit11" type="submit" style="margin-left:365px;">下一步</button>
					<input  name="token" type="hidden" value="${seller_back_password}"/>
				</p>
			</form>
         </div>
    </div>
    <!--------------右侧------------------>
	<div class="memberright2">
    	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
