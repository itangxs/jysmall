<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/kaquan/kaquan_toufang.js"></script>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="11" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:10px; margin-bottom:25px;">投放设定</h3>
      
        <div class="member_myorder">
      		<div class="toufang-way">
            	<div class="zj-toufang">
            		<h1><a href = "javascript:void(0)" onclick = "document.getElementById('cktxjl').style.display='block';document.getElementById('fade').style.display='block'">公众号直接投放</a></h1>
                	<p>通过公众号消息，直接发送卡券。通过
公众号消息，直接发送卡券。</p>
				</div>
            	<div class="zy-toufang">
            		<h1><a href = "javascript:void(0)" onclick = "document.getElementById('cktxj2').style.display='block';document.getElementById('fade').style.display='block'">自营投放</a></h1>
                	<p>顾客通过在本店消费，获得本店卡券，顾
客通过在本店消费，获得本店卡券</p>
				</div>
                <div class="zb-toufang">
           		  <h1><a href = "javascript:void(0)" onclick = "document.getElementById('cktxj3').style.display='block';document.getElementById('fade').style.display='block'">周边投放</a></h1>
                	<p>顾客在本店消费，获得周边卡券，顾客
在本店消费，获得周边卡券</p>
				</div>       
            </div>
         
        

        </div>
       
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->


<!--直接投放-->
<div id="cktxjl" class="white_content" style="">
	<p class="close03">直接投放</p>
    <div class="nr nr03">
    	<div class="zj-tanc">
        	<p><label>公众号每月可推荐次数：</label> <input style="width:90px; height:28px; text-indent:10px" type="text" id="deliveryNum" value="${queryByType.deliveryNum }"><label>&nbsp; </label>次<span style="color:#fa9723">（现有设定${queryByType.deliveryNum }次）</span></p>
            <p><label>&nbsp;&nbsp;&nbsp; </label><label>重置商家可推送次数：</label> <input style="width:90px; height:30px; text-indent:10px" type="text" id="sellerId" ></p>
		</div>
    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
          <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick="updateGzDeliver('${queryByType.id}')" >确认</a>                                              
     </div>   
</div>



<!--自营投放-->
<div id="cktxj2" class="white_content white_content06">
	<p class="close03">自营投放</p>
    <div class="nr nr04">
    	<div class="zy-tanc">
    		<input type="hidden" id="queryByTypeId" value="${queryByType.id }">
        	<p><label>卡券领取数（展示数据）=实际交易笔数×</label> <input class="input-setting" type="text" id="cardReceiveMin"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardReceiveMax"> %
            <br><span class="now-setting">现有设定：${queryByType.cardReceiveMin }%~${queryByType.cardReceiveMax}%</span>
            </p>
            
            <p><label>活动分享数（展示数据）=实际交易笔数×</label> <input class="input-setting"  type="text" id="cardShareMin"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardShareMax"> %
            <br><span class="now-setting">现有设定：${queryByType.cardShareMin }%~${queryByType.cardShareMax }%</span>
            </p>
            
         	 <p><label>分享后卡券领取数（展示数据）=活动分享数（展示数据）×</label> <input class="input-setting"  type="text" id="cardShareReceiveMin"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting"  type="text" id="cardShareReceiveMax"> %
             <br><span class="now-setting">现有设定：${queryByType.cardShareReceiveMin }%~${queryByType.cardShareReceiveMax }%</span>
             </p>
  			
             <div style="margin:0; margin-right:260px;" class="clearfix"><p class="now-setting">现有设定：</p></div>
             <p>
             <label>卡券一抽取概率×</label><input class="input-setting"  type="text" id="firstRankProbability"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span class="now-setting">${queryByType.firstRankProbability }%</span>
      		 </p>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券二抽取概率×</label><input class="input-setting"  type="text" id="secondRankProbability"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span class="now-setting">${queryByType.secondRankProbability }%</span>
      		 </p>
             </div>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券三抽取概率×</label><input class="input-setting"  type="text" id="thirdRankProbability"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span class="now-setting">${queryByType.thirdRankProbability }%</span>
      		 </p>
             </div>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券四抽取概率×</label><input class="input-setting"  type="text" id="fourthRankProbability"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span class="now-setting">${queryByType.fourthRankProbability }%</span>
      		 </p>
             </div>
		</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('cktxj2').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
        <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick="updateZyDeliver('${queryByType.deliverType}')" >确认</a>                                              
     </div>   
</div>



<!--周边投放-->
<div id="cktxj3" class="white_content white_content06">
	<p class="close03">周边投放</p>
    <div class="nr nr02">
    	<div class="zb-tanc">
    	<input type="hidden" id="queryByTypeId2" value="${queryByType2.id }">
        	<p><label>卡券推送次数（展示数据）=实际交易笔数×</label> <input class="input-setting" type="text" id="cardPushMin2"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardPushMax2"> %
            <br><span class="now-setting">现有设定：${queryByType2.cardPushMin }~${queryByType2.cardPushMax }%</span>
            </p>
            
            <p><label>卡券领取数（展示数据）=卡券推送次数（展示数据）×</label> <input class="input-setting" type="text" id="cardReceiveMin2"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardReceiveMax2"> %
            <br><span class="now-setting">现有设定：${queryByType2.cardReceiveMin }%~${queryByType2.cardReceiveMax }%</span>
            </p>
         	 <p><label>活动分享数（展示数据）=卡券推送次数（展示数据）×</label> <input class="input-setting" type="text" id="cardShareMin2"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardShareMax2"> %
             <br><span class="now-setting">现有设定：${queryByType2.cardShareMin }%~${queryByType2.cardShareMax }%</span>
             </p>
             <p><label>分享后卡券领取数（展示数据）=活动分享数（展示数据）×</label> <input class="input-setting" type="text" id="cardShareReceiveMin2"><label>&nbsp; </label>~<label>&nbsp; </label> <input class="input-setting" type="text" id="cardShareReceiveMax2"> %
             <br><span class="now-setting">现有设定：${queryByType2.cardShareReceiveMin }%~${queryByType2.cardShareReceiveMax }%</span>
             </p>
     		
            <div style="margin:0; margin-right:260px;" class="clearfix"><p class="now-setting">现有设定：</p></div>
             <p>
             <label>卡券一抽取概率×</label> <input class="input-setting"  type="text" id="firstRankProbability2"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span class="now-setting">${queryByType2.firstRankProbability }%</span>
      		 </p>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券二抽取概率×</label> <input class="input-setting"  type="text" id="secondRankProbability2"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span class="now-setting">${queryByType2.secondRankProbability }%</span>
      		 </p>
             </div>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券三抽取概率×</label> <input class="input-setting"  type="text" id="thirdRankProbability2"><label>&nbsp; </label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span class="now-setting">${queryByType2.thirdRankProbability }%</span>
      		 </p>
             </div>
             <div class="clearfix" style=" margin:0">
             <p>
             <label>卡券四抽取概率×</label> <input class="input-setting"  type="text" id="fourthRankProbability2"><label>&nbsp;</label> %<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span class="now-setting">${queryByType2.fourthRankProbability }%</span>
      		 </p>
             </div>
             
		</div>

    </div>
    <div class="kqanniu01">
    	<a href = "javascript:void(0)" style=" background-color:#ccc;" onclick = "document.getElementById('cktxj3').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
          <a href = "javascript:void(0)" style=" background-color:#64a1ce;" onclick="updateZbDeliver('${queryByType2.deliverType}')" >确认</a>                                              
     </div>   
</div>




<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
</body>
</html>
