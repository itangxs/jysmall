<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script type="text/javascript" src="/js/seller/back_password2.js"></script>
<script type="text/javascript" src="/js/changeimg.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<div class="membercontent1">
   <div class="membertop">
       <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>商家找回密码</td></tr>
       </table>         
     </div>
    <div class="memberbut">
	<!----------------左侧----------------------->
	<div class="memberleft2">  
    <div class="member_myorder" style="padding-left:35px;">   
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>    
          <th class="td136">
           1.输入账号
         </th>
         <th class="td135" ><img src="/qhjys_mall/seller-manage/manage/images/dbg1.png" width="28" height="28" align="right">
           2.重设密码
         </th>
         <th class="td136">
           3.新密码设定成功
         </th></tr>
          </table>
          <br />
           <form id="signupForm" method="post" action="/seller/backPassword.do" class="zcform" style="margin-left:330px;">        
          <p class="clearfix">
        	<label class="one" for="con-email">联系人：</label>
            <label class="one1">${seller.realname}</label>   	
         </p>    
           <p class="clearfix">
        	<label class="one" for="con-email">手机号码：</label>
           <%--  <label>${fn:substring(backPasswordPhone, 0, 4)}***${fn:substring(backPasswordPhone, fn:length(backPasswordPhone)-4 , fn:length(backPasswordPhone))} --%>
            <label>
        <%--     <span id="phone">${backPasswordPhone}</span> --%>
        	<input id="phone" value="${seller.phone}" readOnly="true" />
            </label>   	
         </p>    
          <p class="clearfix">
        	<label class="one" >校验码：</label>
            <input class="identifying_code" type="text" name="jym" id="jym" />
           <input class="get_code" type="button" id="sendSms" value="获取验证码" onclick="getPhoneCode(${seller.phone})" />   
        </p>    
          <p class="clearfix">
        	<label class="one" for="contactname">新密码：</label>
        	<input name="password" type="password" id="password" class="identifying_code" ><span style="padding-left:5px; color:#F30">*</span> 
        	<span id="passwordTip"></span>    
         </p>
         <p class="clearfix">
        	<label class="one" for="contactname">确认密码：</label>
        	<input name="password2" type="password" id="password2" class="identifying_code"><span style="padding-left:5px; color:#F30">*</span>
        	<span id="password2Tip"></span>    
         </p> 
         <p class="clearfix">
        	<label class="one" for="con-email">验证码：</label>
        	<input name="ymyzm" id="ymyzm"type="text" id="con-email" style="width:120px;" maxlength="11" class="con-email3" >
        	<span id="yanzhengmaimg" style="cursor: pointer;" onclick="changeImg('imageId','','');"> <img id="imageId" src="/valiCode.do" border="0"  style="margin-top:5px; margin-left:3px;"/></span>
            <span id="ymyzmTip"></span>
                    </p>  
                <input type="hidden" value="${seller_back_password2}"  name="token" />    
       	<p> 
         <input  class="submit11" value="重设密码" type="submit"  style="margin-left:100px;" /> </a>
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
