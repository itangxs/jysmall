<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
         <th class="td136" >
           2.重设密码
         </th>
         <th class="td135">
           3.新密码设定成功
         </th></tr>
          </table>
          <br />
           <br /> 
           <p class="clearfix">
        	<label class="one" for="con-email">
        	
        	${msg}
        	<c:if test="${msg =='恭喜你已成功设定密码！'}"> 3秒后自动调回登录！<script>window.setTimeout("location.href='/seller/login.do'",3000);</script>
        	</c:if>
        	</label>
          
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
