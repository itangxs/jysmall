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
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/system/ad/hongbao_modify.js"></script>
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
           <form id="from" method="post" action="/managermall/systemmall/ad/hongbao_update.do">
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
         	<p class="clearfix"  id="tt${status.count}">
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
             	<div class="fdgl-left" style="padding:10px 0 0 0;"><label class="con-email120" for="con-email4" >获得红包金额</label></div>
                <div id="fendgl" class="fdgl-right">
                	
                		<c:forEach items="${details }" var="detail" varStatus="status">
                			<div class="nr" id="nr${status.count}">
                			<p><input class="con-email3" name="subMoneyMin" value="${detail.minAmount }">元 ——  
                			<input class="con-email3" name="subMoneyMax" value="${detail.maxAmount }"> 元（精确到0.01元）
                			<c:if test="${status.count==1 }">
                			<input type="button" value="+新增概率" class="submitred" onclick="jiagailv()">
                			</c:if> 
                			<c:if test="${status.count>1 }">
                			<input type="button" value="-删除概率" class="submitblue" onclick="jiangailv('nr${status.count}')">
                			</c:if> 
                			<c:if test="${detail.type==1}">
	                			<p><input lang="gailv" name="radiogroup${status.count }" type="radio" checked="checked" onclick="ongailvListener()"> 获得红包概率&nbsp;
			                    <input class="con-email3" name="fenduanGaiLv" value="${detail.probability }"> %&nbsp;&nbsp;&nbsp;&nbsp;
			                    <input lang="fenduan" name="radiogroup${status.count }" type="radio" onclick="onfenduanListener()"> 顾客支付金额&nbsp;
			                    <input class="con-email3" name="zhifujineMin" >  元 —— <input class="con-email3" name="zhifujineMax" > 元 （精确到0.01元）
			                    <input type="hidden" name="detailIds" value="${detail.id}" class=""/>
			                    </p>
			                </c:if>
			                <c:if test="${detail.type==2 }">
	                			<p><input lang="gailv" name="radiogroup${status.count }" type="radio" onclick="ongailvListener()"> 获得红包概率&nbsp;
			                    <input class="con-email3" name="fenduanGaiLv" > %&nbsp;&nbsp;&nbsp;&nbsp;
			                    <input lang="fenduan" name="radiogroup${status.count }" type="radio" checked="checked" onclick="onfenduanListener()"> 顾客支付金额&nbsp;
			                    <input class="con-email3" name="zhifujineMin" value="${detail.minMoney }">  元 —— 
			                    <input class="con-email3" name="zhifujineMax" value="${detail.maxMoney }"> 元 （精确到0.01元）
			                    <input type="hidden" name="detailIds" value="${detail.id}" class=""/>
			                    </p>
			                </c:if>
	                		</p></div>
                		</c:forEach>
                    
                </div>
                <div class="clear"></div>
             </div>
             
             <div class="tishibox">
             	<strong>温馨提示：</strong><br>
1、分段金额必须精确到0.01元，并首尾相连（例如：分段1是0.01元—0.1元，则分段2必须从0.11元开始设置。<br>
2、分段概率之和必须为100%（例如：只有两个分段，分段1概率为40%，则分段2概率必须为60%）。<br>
3、"分段概率"及"分段支付金额"每个新任务只能从中选择一种作为概率计算方式。
             </div> 
             <p class="clearfix">
            <label class="con-email120" for="con-email4" ><span style="line-height:15px;vertical-align:middle; display:inline-block;">单一微信号<br>
每日可领红包数</span></label>
        	<input class="con-email4" style="vertical-align:middle; display:inline-block;" name="maxPackNum" value="${fqRedpack.daliyNum }">  个   
        	 </p>
        	  <p id="jinee" class="clearfix">
            <label class="con-email120" for="con-email4"  style=" letter-spacing:-1px;">开通商户修改权限</label>
        
        	<label><input type="radio" name="quanxian"  <c:if test="${fqRedpack.hasChange == 0 }">checked="checked"</c:if> value="0">否</label>
            <label>&nbsp;&nbsp;</label>
            <label><input type="radio" name="quanxian" <c:if test="${fqRedpack.hasChange == 1 }">checked="checked"</c:if> value="1">是</label>
           
        	 </p>
   		<p class="clearfix">
        	 <label class="con-email120"></label>   
        	 <input type="hidden" name="token" value="${token}" class=""/>
        	 <input type="hidden" name="redpackId" value="${redpackId}" class=""/>
        	 <input type="hidden" id="randType" name="randType" value="${randType }"/>
             <input type="submit" value="确认保存" class="submitred120" > &nbsp;&nbsp;
       		 <input name="取消" type="button" class="submitblue120" value="取消" onclick="quxiao()"> 
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
