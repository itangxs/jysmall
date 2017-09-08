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
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/redpack/hongbao_modify.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="52" /></jsp:include>
    <!--------------右侧------------------>

    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">修改红包活动</h3>
           <form id="from" method="post" action="/managermall/seller/redpack/hongbao_update.do">
            <p class="clearfix">
            <label class="con-email120" for="con-email4" >活动名称</label>
        	<input class="con-email5" name="activityName" value="${fqRedpack.activityName }">
        	 </p>
             <p><label class="con-email120" for="con-email4" >参与商家</label>
        	<input id="storeNames" class="con-email5" readonly="readonly" name="storeName" value="${fqRedpack.storeName }"> </p>
        	<input id="storeIds" class="con-email5" name="storeId" type="hidden" value="${fqRedpack.storeId }">
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动周期</label>      
        	<input id="begin" name="startDate" class="easyui-datebox con-time" value="<fmt:formatDate value="${fqRedpack.beginDate }" pattern="yyyy-MM-dd"/>">
			<label class="one" for="con-email2">至</label>
			<input id="ending" name="endDate" class="easyui-datebox con-time" value="<fmt:formatDate value="${fqRedpack.endDate }" pattern="yyyy-MM-dd"/>">
            <label class="one" for="con-email4" >
         </p>
         <c:forEach items="${redpackTimes }" var="time" varStatus="status">
         	<p class="clearfix" id="tt${status.count}">
            	<label class="con-email120" for="con-email130" ><c:if test="${status.count==1}">每日活动时段</c:if></label>
           		<input class="con-email9" name="startHour" value="<fmt:parseNumber integerOnly="true" value="${time.beginTime/60}" />">&nbsp;点 
            	<input class="con-email9" name="startMinute" value="${time.beginTime%60}" >&nbsp;分&nbsp;&nbsp;
            	至
            	<input class="con-email9" name="endHour" value="<fmt:parseNumber integerOnly="true" value="${time.endTime/60}" />">&nbsp;点 
            	<input class="con-email9" name="endMinute" value="${time.endTime%60}" >&nbsp;分
            	<c:if test="${status.count==1}">
            	<label for="select">&nbsp;&nbsp;</label>
           		<input type="button" value="+新增时段" class="submitred" onclick="jiashiduan()">
             	</c:if>
            	<c:if test="${status.count>1}">
	            <label for="select">&nbsp;&nbsp;</label>
	            <input type="button" value="-删除时段" class="submitblue" onclick="jianshiduan('tt${status.count}')">
             	</c:if>
             	 <label for="select">&nbsp;（北京时间早上0点~8点不发放红包）</label>
             	<input type="hidden" name="timeIds" value="${time.id}" class=""/>
        	</p>
        </c:forEach>	 
       		  <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4" >红包总金额</label>
        	<input id="zjee" class="con-email4" readonly="readonly" name="totalMoney" value="${fqRedpack.totalMoney }"><span id="zjeeTip"></span>（精确到0.01元，不得高于营销账户余额）  
        	 </p>
            <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4" >红包最大金额</label>
        	<input class="con-email4" name="packMaxMoney" value="${fqRedpack.maxAmount }">  元（精确到0.01元，不得高于200.00元）  
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >红包最小金额</label>
        	<input class="con-email4" name="packMinMoney" value="${fqRedpack.minAmount }">  元（精确到0.01元，不得低于1.00元）  
        	 </p>
             <div>
             	<div class="fdgl-left" style="padding:10px 0 0 0;"><label class="con-email120" for="con-email4" >顾客支付金额</label></div>
                <div id="fendgl" class="fdgl-right">
                	
                		<c:forEach items="${details }" var="detail" varStatus="status">
                		<c:if test="${status.count == 1 }">
                		<p class="clearfix" id="ss${status.count}" >
                        <label for="select">&nbsp;</label><input class="con-email7" name="zhifujineMin" value="${detail.minMoney }"> 元 —  
	                	<input class="con-email7" name="zhifujineMax" value="${detail.maxMoney }"> 元
                        <label for="select">&nbsp;&nbsp;</label> 获得红包金额&nbsp;
	                    <input class="con-email7" name="subMoneyMin" value="${detail.minAmount }">  元 — <input class="con-email7" name="subMoneyMax" value="${detail.maxAmount }"> 元 (精确到0.01元)
                        <label for="select">&nbsp;</label>
	                	<input type="button" value="+新增概率" class="submitred" onclick="jiazhifu()">
	                	 <input type="hidden" name="detailIds" value="${detail.id}" class=""/>
                        </p>  
                        </c:if>  
                        <c:if test="${status.count > 1 }">
                        <p class="clearfix" id="ss${status.count}">
                        <label for="select">&nbsp;</label><input class="con-email7" name="zhifujineMin" value="${detail.minMoney }"> 元 —  
	                	<input class="con-email7" name="zhifujineMax" value="${detail.maxMoney }"> 元
                        <label for="select">&nbsp;&nbsp;</label> 获得红包金额&nbsp;
	                    <input class="con-email7" name="subMoneyMin" value="${detail.minAmount }">  元 — <input class="con-email7" name="subMoneyMax" value="${detail.maxAmount }"> 元 (精确到0.01元)
                        <label for="select">&nbsp;</label>
	                	<input type="button" value="-删除概率" class="submitblue" onclick="jianzhifu('ss${status.count}')">
	                	 <input type="hidden" name="detailIds" value="${detail.id}" class=""/>
                        </p>  
                        </c:if>
                		</c:forEach>
                    
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
        	<input class="con-email4" style="vertical-align:middle; display:inline-block;" name="maxPackNum" value="${fqRedpack.daliyNum }">  个   
        	 </p>
        	
   		<p class="clearfix">
        	 <label class="con-email120"></label>   
        	 <input type="hidden" name="token" value="${token}" class=""/>
        	 <input type="hidden" name="redpackId" value="${redpackId}" class=""/>
        	 <input type="hidden" name="quanxian" value="${fqRedpack.hasChange}" class=""/>
        	 <input type="hidden" id="randType" name="randType" value="2"/>
             <input type="submit" value="确认保存" class="submitred120" > &nbsp;&nbsp;
       		 <input name="取消" type="button" class="submitblue120" value="取消" onclick="quxiao()"> 
     	</p>
     </form>
	</div>
	<div class="clear"></div>
</div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>