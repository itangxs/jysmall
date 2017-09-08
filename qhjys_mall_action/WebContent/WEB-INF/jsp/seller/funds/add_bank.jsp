<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/seller/funds/add_bank_v.js"></script>

<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="6" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    <div class="addbank">
          <div class="title">添加银行卡</div>
          <h2><strong>温馨提示：</strong><br />
            1.带*标记的为必填项，必须使 用以本人为户名的银行卡进行认证。<br />
            2.同一银行卡号连续出现三次认证失败，该卡不能再进行认证，需更换银行卡进行认证。<br />
            3.请您使用储蓄卡进行认证，禁止使用信用卡认证。<br />
            4.请认真填写分支行信息，否则可能会造成您的提现无法按时到账。例如：**银行**分行**支行/分理处（招商银行深圳分行中央商务支行），如果您无法确定，建议您致电您的开户银行进行询问。</h2>
          <div class="connr">
            <h3>* 请填写您的银行卡信息</h3>
            <form  id="signupForm"  onsubmit="submit1()" method="post" >
            <p>开户姓名：
              <input name="realName" id="realName" type="text" class="input1" value="${bankNamer}" <c:if test="${!empty bankNamer}"> disabled="true"　</c:if>   />
            	 <c:if test="${empty bankNamer}"> <span id="realNameTip"></span></c:if> 
            </p>
            <p>请选择开户银行：<select name="theBank" id="theBank">
            	<option >-------请选择银行---------</option>
            	<option value="工商银行">工商银行</option>
            	<option value="农业银行">农业银行</option>
            	<option value="建设银行">建设银行</option>
            	<option value="中国银行">中国银行</option>
            	<option value="兴业银行">兴业银行</option>
            	<option value="平安银行">平安银行</option>
            </select>&nbsp;&nbsp;&nbsp;<span id="theBankTip"></span>
            </p>
      <!--       <p>
              <input name="" type="radio" value="" />
              <img src="images/bankpic02.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic01.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic04.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic03.jpg" /><br />
              <input name="" type="radio" value="" />
              <img src="images/bankpic05.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic08.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic16.jpg" />
              <input name="" type="radio" value="" />
              <img src="images/bankpic10.jpg" />
            </p> -->
            <p>银行网点：
              <input name="subbranchName" id="subbranchName" class="input1" type="text" />
              <span id="subbranchNameTip"></span></p>
            <p>银行账号：
              <input name="bankAccout" id="bankAccout" class="input1" type="text" />
              <span id="bankAccoutTip"></span></p>
            <p><i></i>
              <input class="submit6" type="submit"  value="提交" />
              <input class="submit6" type="reset"  value="重置" />
              <input class="submit6" type="hidden" name="token" value="${toAddBank}" />
            </p>
            </form>
            <br />
            </div> 
          </div>      
    </div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
