<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jysmall_step1.js"></script>
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
                    <th class="td166">&nbsp;</th>
                    <th class="td167">&nbsp;</th>
                    <th class="td168">&nbsp;</th>
                 </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" cellpadding="0" >
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
                <th><iframe height="1000px;" width="100%;"  frameborder="0" src="/html/yhfwx.html"></iframe></th>
                </tr>
          </table>
         </div>
                      <div class="member_myorder">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td508"></td>
                 </tr>
               </table>
               </div>
          <form id="signupForm" method="post" action="" class="zcform">
          <input class="submit24" type="submit" value="同意以上协议，下一步"/> 
          </form>    
    	</div>
	<div class="clear"></div>
</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>