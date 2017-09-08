<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<style type="text/css">
.memberrightttt{float:left; width:928px; /* height:100%; */ padding:20px 20px 50px 20px;}
</style>
<link href="/css/seller/public.css" rel="stylesheet" type="text/css" />
<link href="/css/seller/membernew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>

<link type="text/css" rel="stylesheet" href="/common/css/validator.css"></link>
<script src="/common/js/formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>

<script type="text/javascript"charset="UTF-8">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form",alertMessage:true,onError:function(msg){alert(msg)}});
	//$("#storeRebate").formValidator({onShow:"请输入店铺折扣",onCorrect:"谢谢你的合作"}).regexValidator({regExp:"num1",dataType:"enum",onError:"店铺折扣格式不正确"});

	/* $("#status").formValidator().inputValidator({min:1,onError:"状态不能为空"}); */

$.formValidator.initConfig({	validatorGroup:"formss0t1",formID : "formss",alertMessage : true,
		onError : function(msg) {
			alert(msg)
		}
	});
	$("#rebate").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "折扣不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"折扣格式不正确"}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"折扣必须在1-10之间，请确认"});
	$("#rebateInfo").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "折扣说明不能为空"});
	$("#level").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "排序不能为空"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"排序不能格式不正确,输入正整数"});
	/* $("#enable").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "是否可用不能为空"}); */
	$("#beginTime").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "开始时间不能为空"});
	$("#endTime").formValidator({validatorGroup:"formss0t1"}).inputValidator({min : 1,onError : "结束时间不能为空"});
});
</script>

<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bootstrap/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<link href="/bootstrap/css/weixin_storebootstrap.min.css" rel="stylesheet" media="screen">
<link href="/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">


</head>

<body>
<!-------------------top---------------------------------------------------------------------->

<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="4"/></jsp:include>
	<div class="memberrightttt">
      <h3 style="padding-left:5px; margin-bottom:25px;">微信店铺详情</h3>
      
          <table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td style="text-align:right;"><a href="javascript:history.go(-1);"></a></td></tr></table>
          <div class="member_myorder">
            <form action="/managermall/systemmall/store/updateWxStore.do?id=${fqStore.id}" method="post" id="form" >
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  
                <tr>
                  <td class="td160">店铺ID</td>
                <%--   <td class="td504">${fqStore.sellerId}</td> --%>
                  <td class="td504">${fqStore.id}</td>
                </tr>
                <tr>
                  <td class="td160">店铺名称</td>
                  <td class="td504">${fqStore.storeName}</td>
                </tr>
                
                
                
                <tr>
                  <td class="td160">店铺LOGO:</td>
                  <td class="td504"><img src ="${fqStore.storeLogo}"width="150" height="120" ></td>
                </tr>
                
                
                <tr>
                  <td class="td160">活动介绍:</td>
                  <td class="td504">${fqStore.activityInfo}</td>
                </tr>
                
                
                
                <tr>
                  <td class="td160">店铺介绍:</td>
                  <td class="td504">${fqStore.storeInfo}</td>
                </tr>
              <!--   <tr>
                  <td class="td160">店铺折扣:</td>
                  <td class="td504">${fqStore.storeRebate}</td>
                </tr> -->
                <tr>
                  <td class="td160">营业开始:</td>
                  <td class="td504">${fqStore.trafficBeginTime}</td>
                </tr>
                <tr>
                  <td class="td160">营业结束:</td>
                  <td class="td504">${fqStore.trafficEndTime}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">地址:</td>
                  <td class="td504">${fqStore.address}</td>
                </tr>
                
                
              <%--   <tr>
                  <td class="td160">营业时间:</td>
                  <td class="td504">${fqStore.trafficTime}</td>
                </tr> --%>
                
                
                <tr>
                  <td class="td160">电话:</td>
                  <td class="td504">${fqStore.phoneNum}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">定金比例:</td>
                  <td class="td504">${fqStore.proportion}</td>
                </tr>
                
                
                <tr>
                  <td class="td160">商圈:</td>
                  <td class="td504">
                  	<c:forEach items="${fqLocation}" var="sq">
                  		<c:if test="${sq.id==fqStore.locationId}">${sq.location}</c:if>
                  	</c:forEach>
                  </td>
                </tr>
                
                
              <%--   <tr>
                  <td class="td160">菜系:</td>
                  <td class="td504">
                  <c:forEach items="${fqCuisines}" var="cx">
                  		<c:if test="${cx.id==fqStore.cuisineId}">${cx.cuisine}</c:if>
                  	</c:forEach>
                  </td>
                </tr> --%>
                
                
                
                <tr>
                  <td class="td160">申请时间:</td>
                  <td class="td504">
                  <fmt:formatDate value="${fqStore.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                </tr>
                
                 <tr>
                  <td class="td160">是否是金融商家:</td>
                  <td class="td504">
                    <select  name="type" id="type">
                  	<option  <c:if test="${fqStore.type==0}">selected="selected"</c:if> value="0" >否</option>
                  	<option  <c:if test="${fqStore.type==1}">selected="selected"</c:if> value="1">是</option>
                  </select>
                  </td>
                </tr>
              
                 <tr>
                  <td class="td160">状态:</td>
                  <td class="td504">
                  
                  <select  name="status" id="status">
                  	<option  <c:if test="${fqStore.status==0}">selected="selected"</c:if> value="0" >未审核</option>
                  	<option  <c:if test="${fqStore.status==1}">selected="selected"</c:if> value="1">审核通过</option>
                  </select>
                  </td>
                </tr>
                
                 <tr>
                  <td class="td160">排序:</td>
                  <td class="td504">
						<input name="level" id="level"
						<c:if test="${!empty fqStore.level}">
						 value="${fqStore.level}"
						 </c:if>
						 
						<c:if test="${empty fqStore.level}">
							 value="100"
						 </c:if>
						 >   ps:越小排到越前         		
                  </td>
                </tr>
                
              <!--   <tr>
                  <td class="td160">店铺折扣:</td>
                  <td class="td504">
                  	<input type="text" name="storeRebate" id="storeRebate" value="${fqStore.storeRebate}"/>ps:要修改必须填写店铺折扣 
                  </td>
                </tr> -->
                <tr>
                  <td class="td160">店铺负责人手机:</td>
                  <td class="td504">
                  	<input type="text" name="clerkPhone" id="clerkPhone" value="${fqStore.clerkPhone}"/>
                  </td>
                </tr> 
                <tr>
                  <td class="td160">每日额度:</td>
                  <td class="td504">
                  	<input type="text" name="daliyCredit" id="daliyCredit" value="${fqStore.daliyCredit}"/>
                  </td>
                </tr>
                <tr>
                  <td class="td160">是否开通提现功能:</td>
                  <td class="td504">
                    <select  name="withdrawStatus" id="withdrawStatus">
                  	<option  <c:if test="${withdrawStatus==0}">selected="selected"</c:if> value="0">否</option>
                  	<option  <c:if test="${withdrawStatus==1}">selected="selected"</c:if> value="1">是</option>
                  </select>
                  </td>
                </tr>
                <tr>
                <td class="td160"></td>
                   <td class="td504">
                  	<input class="submit8" type="submit" value="修改"/>
                   </td>
                </tr>
            </table>
            </form>
            <br/>
      </div>
  </div>
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>

<script language="javascript" type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	$(document).ready(function(){
		 <c:forEach items="${fqRebates}" var="fqRebate" end="5" varStatus="i" >
				 $.formValidator.initConfig({validatorGroup:"${fqRebate.id}",formID : "formss${fqRebate.id}",alertMessage : true,onError : function(msg) {alert(msg)}});
			$("#rebate${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "折扣不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"折扣格式不正确"}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"折扣必须在1-10之间，请确认"});
			$("#rebateInfo${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "折扣说明不能为空"});
			$("#beginTime${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "开始时间不能为空"});
			$("#endTime${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "结束时间不能为空"});
		 </c:forEach>	
	});
</script>
</html>
