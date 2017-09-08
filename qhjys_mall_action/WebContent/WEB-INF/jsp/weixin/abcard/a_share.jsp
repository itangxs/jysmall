<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>抽取${fqStore.storeName }兑换券</title>
<link href="/css/abcard/A-quan.css" type="text/css" rel="stylesheet" />
<script src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/abcard/jQueryRotate.js"></script>
<script src="/js/abcard/a_share.js"></script>
</head>

<body class="sharebg">
<div class="wrapper">
  <div class="help-title"><div class="bg">助力好友${friendName}抽取兑换券</div></div>
  <!--转盘-->
  <div class="ly-plate">
      <div class="rotate-bg"></div>
      <input id="recordId" name="recordId" type="hidden" value="${recordId}">
      <input id="userId" name="userId" type="hidden" value="${user.id}">
      <input id="acardId" name="acardId" type="hidden" value="${acardId}">
      <input id="storeId" name="storeId" type="hidden" value="${storeId}">
      <input id="orderId" name="orderId" type="hidden" value="${orderId}">
      <div class="rotate-jp">
      	<ul>
        	<li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg1"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg2"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg3"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg4"></div></li>
        </ul>
      </div>
    <div class="lottery-star"><a href="javascript:void(0)" onclick=""><img src="/images/abcard/a-zhizhen.png" id="lotteryBtn"></a></div>
  </div>
<!-- 我也要参与 -->
<div class="help_button"><a href="javascript:gojoin()"><img src="/images/abcard/a-share.png"></a></div>
<!--中奖弹出层-->
<div id="zhongjiang" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('zhongjiang').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">
    	<div class="zhongjiangbox">
        	<p>恭喜你颜值爆表，帮好友抽到了一份</p>
            <h1><input id="zhongjiangName" name="zhongjiangName" readonly="readonly"/></h1>
            <p><img class="img50" src="" id="zhongjiangImage" ></p>
            <div><input onclick="gojoin()" class="buttonred100" name="" type="button" value="我也要参与"></div>            
        </div>
    </div>
  </div>
</div>
<!--已经抽过弹出层-->
<div id="yichouguo" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">  
    	<div class="zhongjiangbox">
            <h2><span class="red">温馨提示：</span><br>您已经帮您的好友抽过奖了，非常感谢您的支持！</h2>
          <div class="buttonbkbox"><input onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'" class="button40k" name="" type="button" value="我知道了"></div>
        </div>      
    </div>          
  </div>
</div>
<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
  <!--抽奖奖项-->
  <div class="jiangpinlist a_rafflebg" >
  <c:forEach items="${lotteryInfoVos }" var="lotteryInfo" varStatus="status">
  	<div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
            	<p>已有${fn:length(lotteryInfo.infos)}个好友<br>帮他抽到</p>
                <h1>${lotteryInfo.prizeName }</h1>
            </div>
    	</div>
        <div class="friendlist">
    		<ul>
            	<c:forEach items="${lotteryInfo.infos }" var="uinfo" varStatus="uinstatus">
    				<li><img src="${uinfo.portrait }"></li>
    			</c:forEach>
            </ul>
    	</div>
        <div class="clear"></div>
    </div>
    </c:forEach>
    
    <!--查看活动规则-->
	<div class="rulesbox">
        <h1>查看活动规则<span><img src="/images/abcard/a-ico1.png"></span></h1>
        <!--这是A券活动规则-->
        <div class="rules_text rules_text_a" style="display:none;">
        	1.可邀请好友帮忙抽奖；<br>
            2.被邀请抽奖的好友也可以发起抽奖活动；<br>
            3.每种兑换券最多可兑换1次；<br>
            4.兑换券有效期限为15天，限下次消费时使用；<br>
            5.所有券可在「菜单」-「我的兑换券」中查看；<br>
            6.本活动最终解释权归飞券网所有。
        </div>  
	</div>
    </div> 
</div>
</body>
</html>