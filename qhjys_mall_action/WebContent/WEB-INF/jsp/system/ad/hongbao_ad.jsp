<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link type="text/css" rel="stylesheet"
	href="/common/formValidator4.0.1/style/validator.css" />
	<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript" src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/system/ad/hongbao_ad.js"></script>


<script type="text/javascript">

</script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">新建红包活动</h3>
           <form id="redpackfrom" method="post" action="/managermall/systemmall/ad/hongbao_add.do">
                <p class="clearfix">
            <label class="con-email120" for="con-email4" >活动名称</label>
        	<input id="hdmc" class="con-email5" name="activityName" ><span id="hdmcTip"></span>
        	 </p>
             <p><label class="con-email120" for="con-email4" >商家ID</label>
        	<input class="con-email5" id="ssid" value="" >
        	<input class="con-email5" id="ssid1" name="storeId" value="" type="hidden">
        	<input class="con-email5" id="storeName" name="storeName" value="" type="hidden">
        	<input type="hidden" name="token" value="${token}" class="">
        	 <input type="hidden" id="randType" name="randType" value="${randType}">
        	<input id="idbut" type="button" value="确定" class="submit8" onclick="getstorename()">
        	</p>
        	
        	  <p class="clearfix" style="margin-top:8px;">
        	  <label class="con-email120" for="con-email4" >商家名称</label> <span id="storename"></span></p>
  
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动周期</label>      
        	<input id="beginsj" name="startDate"  class="con-email4" readonly  />
        	<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'beginsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
        	<span id="beginsjTip"></span>
        	<!-- <input id="beginsj" name="startDate" class="easyui-datebox con-time" ><span id="beginsjTip"></span> -->
			<label class="one" for="con-email2">至</label>
			<input id="endingsj" name="endDate"  class="con-email4" readonly  />
			<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'endingsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
			<span id="endingsjTip"></span>
			<!-- <input id="endingsj" name="endDate" class="easyui-datebox con-time" ><span id="endingsjTip"></span> -->
            <label class="one" for="con-email4" >
         </p>
         <p class="clearfix">
            <label class="con-email120" for="con-email130" >每日活动时段</label>
        	<input class="con-email9" name="startHour" >&nbsp;点 
             <input class="con-email9" name="startMinute" value="00" >&nbsp;分&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;
             <input class="con-email9" name="endHour" >&nbsp;点 
             <input class="con-email9" name="endMinute" value="00" >&nbsp;分      
            <label for="select">&nbsp;</label>
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
             	<div class="fdgl-left" style="padding:10px 0 0 0;"><label class="con-email120" for="con-email4" >获得红包金额</label></div>
                <div id="fendgl" class="fdgl-right">
                	<div class="nr">
	                	<p><input class="con-email3" name="subMoneyMin" >元 ——  
	                	<input class="con-email3" name="subMoneyMax" > 元（精确到0.01元)
                        <label for="select">&nbsp;&nbsp;</label>
	                	<input type="button" value="+新增" class="submitred" onclick="jiagailv()">
                       
                        </p>         
	                    <p><input lang="gailv" name="radiogroup" type="radio" checked="checked" onclick="ongailvListener()"> 获得红包概率&nbsp;
	                    <input class="con-email3" name="fenduanGaiLv" > %&nbsp;&nbsp;&nbsp;&nbsp;
	                    <input lang="fenduan" name="radiogroup" type="radio" onclick="onfenduanListener()"> 顾客支付金额&nbsp;
	                    <input class="con-email3" name="zhifujineMin" >  元 —— <input class="con-email3" name="zhifujineMax" > 元 </p>
                    </div>
                </div>
                <div class="clear"></div>
             </div>
             
             <div class="tishibox" id="tips">
             	<strong>温馨提示：</strong><br>
1、获得红包金额必须精确到0.01元，并首尾相连（例如：分段1是0.01元—0.1元，则分段2必须从0.11元开始设置。<br>
2、获得红包概率之和必须为100%（例如：只有两个分段，分段1概率为40%，则分段2概率必须为60%）。<br>
3、每个新任务只能从中选择一种作为概率计算方式。
             </div> 
             <p class="clearfix">
            <label class="con-email120" for="con-email4" ><span style="line-height:15px;vertical-align:middle; display:inline-block;">单一微信号<br>
每日可领红包数</span></label>
        	<input id="drhbs" class="con-email4" style="vertical-align:middle; display:inline-block;" name="maxPackNum" ><span id="drhbsTip"></span>   
        	 </p>
             <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4"  style=" letter-spacing:-1px;">开通商户修改权限</label>
        
        	<label><input type="radio" name="quanxian" checked="checked" value="0">否</label>
            <label>&nbsp;&nbsp;</label>
            <label><input type="radio" name="quanxian" value="1">是</label>
           
        	 </p>
   		<p class="clearfix">
        	 <label class="con-email120"></label>   
        	 
        	
             <input type="button" value="确认创建" class="submitred120" onclick="addredpack()"> &nbsp;&nbsp;
       		 <input name="取消" type="reset" class="submitblue120" value="取消"> 
     	</p>
     </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--商家列表弹出层-->
<div id="shangjielist" class="white_content">
	<p class="close"><a href="javascript:void(0)" onclick="document.getElementById('shangjielist').style.display='none';">
×</a></p>
	<h1>商家列表<span><input id="quanx" type="checkbox" onclick="quanxuan()"> 全选</span></h1>
    <div class="nr">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<c:forEach items="${fqStores }" var="fs" varStatus="statu">
          		 <td><input name="sid" type="checkbox" id="${fs.id }" value="${fs.storeName }">${fs.storeName } </td>
          		 <c:if test="${statu.count%3 == 0 }">
          		 	</tr>
          		 	<tr>
          		 </c:if>
          	</c:forEach>
          </tr>
     	</table>
    </div>
    <p class="clearfix">
        	 <label class="con-email120"></label>
             <center><input type="button" value="确认" class="submitred120" onclick="tcqueding()"> &nbsp;&nbsp;
       		 <input name="tcquxiao" type="button" class="submitblue120" value="取消" onclick="tcquxiao()"> </center>
     </p>
</div>
<!--底部-e-->
</body>
</html>
