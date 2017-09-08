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
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css" />
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/ad/hongbao_ad.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="52" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">活动创建——新建红包活动</h3>
           <form id="from" method="post" action="/managermall/seller/redpack/addRedpack.do">
                <p class="clearfix">
            <label class="con-email120" for="con-email4" >活动名称</label>
        	<input id="hdmc" class="con-email5" name="activityName" ><span id="hdmcTip"></span>
        	 </p>
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动周期</label>      
        	<input id="beginsj" name="startDate"  class="con-email4" readonly  />
        	<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
        	<span id="beginsjTip"></span>

			<label class="one" for="con-email2">至</label>
			<input id="endingsj" name="endDate"  class="con-email4" readonly  />
			<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'endingsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
			<span id="endingsjTip"></span>

            <label class="one" for="con-email4" >
         </p>
         <p class="clearfix">
            <label class="con-email120" for="con-email130" >每日活动时段</label>
        	<input class="con-email9" name="startHour" >&nbsp;点 
             <input class="con-email9" name="startMinute" value="00" >&nbsp;分&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;
             <input class="con-email9" name="endHour" >&nbsp;点 
             <input class="con-email9" name="endMinute" value="00" >&nbsp;分      
            <label for="select">&nbsp;&nbsp;&nbsp;</label>
            <input type="button" value="+新增时段" class="submitred" onclick="jiashiduan()">
            <label for="select">&nbsp;（北京时间早上0点~8点不发放红包）</label>
        </p>
        	 <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4" >红包总金额</label>
        	<input id="zjee" class="con-email4" name="totalMoney" ><span id="zjeeTip"></span>（精确到0.01元，不得高于营销账户余额）  
        	 </p>
            <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4" >红包最大金额</label>
        	<input id="zdjee" class="con-email4" name="packMaxMoney" ><span id="zdjeeTip"></span>（精确到0.01元，不得高于200.00元）  
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >红包最小金额</label>
        	<input id="zxjee" class="con-email4" name="packMinMoney" ><span id="zxjeeTip"></span>（精确到0.01元，不得低于1.00元）    
        	 </p>
             <div>
             	<div class="fdgl-left" style="padding:10px 0 0 0;"><label class="con-email120" for="con-email4" >顾客支付金额</label></div>
                <div id="fendgl" class="fdgl-right">
                
	                	<p class="clearfix">
                        <label for="select">&nbsp;</label><input class="con-email9" name="zhifujineMin"> 元 —  
	                	<input class="con-email9" name="zhifujineMax"> 元
                        <label for="select">&nbsp;&nbsp;</label>
                           获得红包金额&nbsp;
	                    <input class="con-email9" name="subMoneyMin">  元 — <input class="con-email9" name="subMoneyMax"> 元 (精确到0.01元)
                        <label for="select">&nbsp;&nbsp;&nbsp;</label>
	                	<input type="button" value="+新增概率" class="submitred" onclick="jiazhifu()">
                       
                        </p>         
	                  
                   
                </div>
                <div class="clear"></div>
             </div>
             
             <div class="tishibox" id="tips">
             	<strong>温馨提示：</strong><br>
1、获得红包金额必须精确到0.01元，并首尾相连（例如：分段1是0.01元—0.1元，则分段2必须从0.11元开始设置。<br>
<!-- 2、邀请新商户与飞券合作，双方均可获得营销基金，营销基金红包活动由飞券进行设置。<br> -->
2、营销账户可自行充值，充值金额可自行设置红包活动。
             </div> 
             <p class="clearfix">
            <label class="con-email120" for="con-email4" ><span style="line-height:15px;vertical-align:middle; display:inline-block;">单一微信号<br>
每日可领红包数</span></label>
        	<input id="drhbs" class="con-email4" style="vertical-align:middle; display:inline-block;" name="maxPackNum" ><span id="drhbsTip"></span>   
        	 </p>
          
   		<p class="clearfix">
        	 <label class="con-email120"></label>   
        	 <input type="hidden" name="token" value="${token}" class=""/>
        	 <input type="hidden" id="randType" name="randType" value="${randType}"/>
             <input type="submit" value="确认创建" class="submitred120" > &nbsp;&nbsp;
       		 <input name="取消" type="button" class="submitskyblue120" value="取消" onclick="window.location.href='/managermall/seller/redpack/redpacklist.do'"> 
     	</p>
     </form>
	</div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>