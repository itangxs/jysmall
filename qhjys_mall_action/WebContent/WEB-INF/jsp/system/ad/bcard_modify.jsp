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
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/system/ad/bcard_modify.js"></script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">修改B卡券</h3>
           <form id="from" method="post" action="/managermall/systemmall/ad/bcard_update.do">
            <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家ID</label>
        	<input class="con-email5" name="storeId" value="${bcard.storeId}" readonly="readonly">  
        	 </p>
            <p class="clearfix" style="margin-top:8px;">
        	<label class="con-email120" for="con-email4" >活动时间</label>       
        	<input id="begin" name="beginDate" value="<fmt:formatDate value="${bcard.beginDate }" pattern="yyyy-MM-dd"/>" class="easyui-datebox con-time" >
			<label class="one" for="con-email2">至</label>
			<input id="ending" name="endDate" value="<fmt:formatDate value="${bcard.endDate }" pattern="yyyy-MM-dd"/>" class="easyui-datebox con-time" >
            <label class="one" for="con-email4" >
         </p>
         <p class="clearfix">
            <label class="con-email120" for="con-email130" >有效周期</label>
        	<input class="con-email3" name="validityDate" value="${bcard.validity }" >&nbsp;天&nbsp;&nbsp;&nbsp;<span class="colorgray">注：(7天/14天/21天 可填)</span>
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >商家描述</label>
            <textarea class="textarea_top" name="cardDescript" onchange="descript_limit(this)">${bcard.bcardInfo }
            </textarea>&nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
        	 </p>
        <p class="clearfix ">
            <label class="con-email120" for="con-email4" ></label>
            <b class="fontred" style="font-size:16px;">单一微信账号每天可推送次数为&nbsp;&nbsp;<input class="con-email3" name="pushNum" value="${bcard.pushNum }">&nbsp;&nbsp;次</b>
        </p>
             <div class="title_bggray">商家自荐卡券</div>
             <c:forEach items="${prizes }" var="prize" varStatus="status">
	             <div class="kaquan_zijianbox">
	                 <div class="kaquantitle">卡券${status.count}</div>
	                 <p class="clearfix">
	                <label class="con-email120" for="con-email4" >奖品名称</label>
	                <input class="con-email5" maxlength="15"  name="prizeName" value="${prize.prizeName }">&nbsp;&nbsp;<span class="colorgray">注：(字数不超过15字)</span>    
	                 </p>
	                 <input id="prizeIds" type="hidden" name="prizeIds" value="${prize.id}"/>
	                 <p class="clearfix">
	                <label class="con-email120" for="con-email4" >使用规则</label>
	                消费满&nbsp;&nbsp;<input class="con-email3" maxlength="15"  name="prizeLine" value="${prize.prizeLine }">&nbsp;&nbsp;元即可使用<span class="colorgray">&nbsp;&nbsp;注：(金额必须填整数)</span>    
	                 </p>
	                 <p class="clearfix">
	                <label class="con-email120" for="con-email4" >奖品描述</label>
	                <textarea class="textarea_top" name="prizeInfo" onchange="descript_limit(this)">${prize.prizeInfo }</textarea>
	                &nbsp;&nbsp;<span class="colorgray">注：(字数不超过60字)</span>
	                 </p>
	                 <p class="clearfix">
	                    <label class="con-email120" for="con-email4" ></label>
	                    <img  id="img${status.count}" class="form_img1"  src="${prize.prizeImage }" alt="图片" />
	                    <input type="hidden" id="imgu${status.count}" name="imgs" value="${prize.prizeImage }"/>
	                    <input  class="uploadify" type="file" value="上传" id="update${status.count}"/>
	                 </p>
	             </div>
             </c:forEach>
             
             <div class="title_bggray">商家被荐卡券</div>
             <p class="clearfix">
             	<h3>推荐范围及规则</h3>
       	  </p>
          <div class="addguizebox" id="addguize">
          <input id="rulesize" type="hidden" value="${fn:length(rules)}"/>
          	<dl>
          		<c:forEach items="${rules }" var="rule" varStatus="status">
	            	<dd>
	                    <div class="title_gz">规则</div>
	                    <input id="ruleIds" type="hidden" name="ruleIds" value="${rule.id}"/>
	                  <div class="diqubox">
	                  <ul>
	                      <li>城市<br>
	                          <input id="11${status.count}" class="easyui-combobox" name="city" value="${rule.cityId }" type="text" style="width:120px;height:32px;">
	                      </li>
	                      <li>行政区<br>
	                          <input id="dist11${status.count}" class="easyui-combobox" name="district" value="${rule.districtId }" type="text" style="width:120px;height:32px;">
	                      </li>
	                      <li>商圈<br>
	                          <input id="areadist11${status.count}" class="easyui-combobox" name="area" value="${rule.areaId }" type="text" style="width:120px;height:32px;">
	                      </li>
	                      <li>大行业<br>
		                      <select id="industryselect11${status.count}" name="industry" onchange="industryChange('11${status.count}')">
	                          	<option value="1" <c:if test="${rule.industryId == 1 }">selected="selected"</c:if>>餐饮</option>
	              				<option value="2" <c:if test="${rule.industryId == 2 }">selected="selected"</c:if>>非餐饮</option>
	                          </select>
	                      </li>
	                      <li>细分行业<br>
	                          <input id="industryDetail11${status.count}" class="easyui-combobox" name="industryDetail" value="${rule.industryDetailId }" type="text" style="width:120px;height:32px;">
	                      </li>
	                  </ul>
	                  <br> 
	                <input type="button"   class="submit8"  value="增加" id="addTable" onclick="add_tr(this)"/>
	                <input type="button"  class="submit8"   value="删除" id="deleteTable" onclick="del_tr(this,1,${rule.id})"/>
	                </div>
	            	</dd>
            	</c:forEach>
            </dl>
          </div>
          <input id="token" type="hidden" name="token" value="${token}"/>
          <input id="bcardId" type="hidden" name="bcardId" value="${bcard.id}"/>
   		<p class="clearfix">
        	<label class="con-email120"></label>
       		<input type="submit" value="保存" class="submitred120" > &nbsp;&nbsp;
       		<input name="取消" type="button" class="submitblue120" value="取消" onclick="quxiao()"> 
     	</p>
     </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
