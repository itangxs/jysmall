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
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/redpack/chongzhi.js"></script>

<script>

</script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="51" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:10px; padding-bottom:20px;">账户充值</h3>
       	<div class="hongbaocreate03"> 
        
        	<div style="border-bottom:1px #bbcbef solid;">
        	<p  class="chongzhi_type">商家账户<span style="font-size:14px;">充值到</span>营销账户
            </p>
            <p class="balance_show">
            <span style="color:#f93;">${cash.integral }</span><span style="font-size:14px;">元</span><br>
            <span style="font-size:14px; color:#b5b5b5; line-height:14px;">（营销账户余额）</span>         	
            </p>
            <div class="clearfix"></div>
            </div>
            <div style="margin:30px 0 0 50px;">
            <div style="float:left; text-align:right; margin-right:10px;">
             <p class="clearfix" style="margin-bottom:50px;">商家账户总额：</p>
             <p class="clearfix" style="margin-bottom:36px;">转入金额：</p>
             <p class="clearfix">输入支付密码：</p>
            </div>
             <div style="float:left; text-align:left;">
             	<p class="clearfix" style="margin-bottom:50px;"><span style="font-size:16px; color:#f93"> ${cash.balance }</span><span style="font-size:14px;">元</span></p>
                <p class="clearfix" style="margin-bottom:26px;"><input type="text" id="czje" class="con-email2">元</p>
                <p class="clearfix"><input type="password" id="czpassw" class="con-email2"><span style="color:#f93;">（支付密码即商家账户提现密码）</span></p>
            </div>
            </div>             
        </div>
         <p class="clearfix">   
             <a class="queren_chongzhi" href="javascript:showRecords()" >确认</a>      
     	 </p>
       
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->

<!--充值确认信息弹窗-->
<div id="cktxjl" class="white_content" style="display:none;">
	<p class="close02">提示</p>
    <div class="nr">
    	<div class="chongzhi_queren">
    		<p style="line-height:40px;">您正在向营销账户进行充值<br>
        	充值金额：<span style="color:#f93" id="qrcz"></span>
       	 <p>
        </div>
        
        <div class="chongzhi_tips">
        	<p style=" border-bottom:1px #ccc solid;">提示：请核对以上信息，确认充值后将无法撤销！</p>
       	</div>
    </div>
    <div class="hranniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">返回修改</a>
          <a href = "javascript:chongzhitijiao()" style=" background-color:#f93;" >确认充值</a>
                                                  
     </div>   
</div>


<!--充值余额不足弹窗-->
<div id="cktxj3" class="white_content">
	<p class="close02">提示</p>
    <div class="nr">
    	<div class="chongzhi_queren03">
    		<p style="line-height:60px;">您的商家账户余额不足，请修改充值金额！
       	 <p>
        </div>
        
        <div class="chongzhi_tips">
        	<p style=" border-bottom:1px #ccc solid;padding-bottom:22px"></p>
       	</div>
    </div>
    <div class="hranniu01">
          <a href = "" onclick = "" style=" background-color:#f93;" >返回修改</a>
                                                  
     </div>   
</div>



<!--充值成功弹窗-->
<div id="cktxj2" class="white_content">
	<p class="close02">提示</p>
    <div class="nr">
    	<div class="chongzhi_queren02">
    		<p style="line-height:40px;" id="czcgts">恭喜，您已成功充值1000元！<br>
        	赶快去新建红包活动吧！
       	 <p>
        </div>
        
        <div class="chongzhi_tips">
        	<p style=" border-bottom:1px #ccc solid;padding-bottom:22px"></p>
       	</div>
    </div>
    <div class="hranniu01">
    	<a href = "javascript:jixuchongzhi()" style=" background-color:#ccc;" onclick = "">继续充值</a>
          <a href = "/managermall/seller/redpack/createRedpack.do" onclick = "" style=" background-color:#f93;" >新建活动</a>
                                                  
     </div>   
</div>

<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>