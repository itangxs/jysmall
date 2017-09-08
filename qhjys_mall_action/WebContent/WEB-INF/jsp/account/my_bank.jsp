<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/account/my_bank.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="8"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
    <div class="member_money">
      <div class="tishi"><strong>温馨提示：</strong>
        <p>下述银行卡号的开户人姓名必须与实名认证的真实姓名一致，个人银行账号必须填写正确，否则您的提现资金将存在风险。</p>
      </div>
      <div class="tabs">
      	
      	<a href="/managermall/account/myBank.do?status=2" <c:if test="${status==2}"> class="tabaction"</c:if>>已认证银行卡</a>
      	<a href="/managermall/account/myBank.do?status=1" <c:if test="${status==1}"> class="tabaction"</c:if>>认证中银行卡</a>
      	<a href="/managermall/account/myBank.do?status=3" <c:if test="${status==3}"> class="tabaction"</c:if>>认证失败银行卡</a>
        <div class="clear"></div>
      </div>
      <div class="tishiul">
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
            <th>银行名称</th>
            <th>银行账号</th>
          </tr>
          <c:forEach var="page" items="${page}">
            <tr> 	
              <td>${page.name}</td>
              <td>${page.carkNum}</td>
            </tr>
          </c:forEach>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0" id="renzhengzhong" style="display:none;">
          <tr>
            <th>银行名称</th>
            <th>银行账号</th>
            <th>操作</th>
          </tr>
          <tr>
            <td>工商银行</td>
            <td>621234564000017123456</td>
            <td><a href="#">取消</a></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0" id="weirenzheng" style="display:none;">
          <tr>
            <th>银行名称</th>
            <th>银行账号</th>
            <th>原因</th>
          </tr>
          <tr>
            <td>工商银行</td>
            <td>621234564000017123456</td>
            <td>与真实姓名不符</td>
          </tr>
        </table>
        
        <p>
         <input class="button_red anniu" type="submit" name="button" id="button" onclick="addBank()" value="添加银行卡 " />
        </p>
        <p class="fontred">温馨提醒：如果您已提交了银行卡认证，并且正在认证中，则不能继续添加新的银行卡，当认证通过后才可以继续添加。</p>
        <!--添加银行卡弹出窗口-->
        <div id="insertBank" class="addbank" style="display: none">
          <div class="title"><em><a href="javascript:;" onclick="closeWindow()">×</a></em>添加银行卡</div>
          <h2><strong>温馨提示：</strong><br />
            1.带*标记的为必填项，必须使 用以本人为户名的银行卡进行认证。<br />
            2.同一银行卡号连续出现三次认证失败，该卡不能再进行认证，需更换银行卡进行认证。<br />
            3.请您使用储蓄卡进行认证，禁止使用信用卡认证。<br />
            4.请认真填写分支行信息，否则可能会造成您的提现无法按时到账。例如：**银行**分行**支行/分理处（招商银行深圳分行中央商务支行），如果您无法确定，建议您致电您的开户银行进行询问。</h2>
          <div class="connr">
            <h3>* 请填写您的银行卡信息</h3>
           	<form id="bankForm" action="" method="post">
	            <p>开户姓名：
	              <input name="cardholder" type="text" class="input1" />
	            </p>
	            <p>请选择开户银行：</p>
	            <p>
	              <input name="name" type="radio" value="中国银行" />
	              <img src="/images/bankpic02.jpg" />
	              <input name="name" type="radio" value="中国工商银行" />
	              <img src="/images/bankpic01.jpg" />
	              <input name="name" type="radio" value="中国建设银行" />
	              <img src="/images/bankpic04.jpg" />
	              <input name="name" type="radio" value="中国农业银行" />
	              <img src="/images/bankpic03.jpg" /><br />
	              <input name="name" type="radio" value="招商银行" />
	              <img src="/images/bankpic05.jpg" />
	              <input name="name" type="radio" value="中国光大银行" />
	              <img src="/images/bankpic08.jpg" />
	              <input name="name" type="radio" value="兴业银行" />
	              <img src="/images/bankpic16.jpg" />
	              <input name="name" type="radio" value="中国邮政储蓄银行" />
	              <img src="/images/bankpic10.jpg" /> </p>
	            <p>银行网点：
	              <input name="branch" class="input1" type="text" />
	            <p>银行账号：
	              <input name="carkNum" class="input1" type="text" />
	            <p><i></i>
	              <input class="button_red" name="saveValue" type="submit" value="提交" />
	              <a href="javascript:;" onclick="closeWindow()"><span>取消</span></a>
	            </p>
            </form>
            <br />
          </div>
        </div>
      </div>
      <!--充值记录--> 
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>