<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信店铺-折扣</title>
<link type="text/css" rel="stylesheet" href="/umeditor//themes/default/css/umeditor.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>

<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bootstrap/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<link href="/bootstrap/css/weixin_storebootstrap.min.css" rel="stylesheet" media="screen">
<link href="/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">



<link type="text/css" rel="stylesheet" href="/common/css/validator.css"></link>
<script src="/common/js/formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript" src="/js/wxRebate.js"></script>







<style type="text/css">
.uploadify {margin-left:100px;}
.uploadify-queue {margin-left:100px;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="21_1" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div id="member_myorder">
				<div id="mainShow" class="main">
						 <hr>
						<div class="member_myorder">
							<form class="zcform" style="margin-left:155px;text-align:left;" method="post" id="formss" action="/managermall/seller/fqRebate.do?id=${fqRebate.id}" >
								<p class="clearfix">
									<label class="one" for="con-email">折扣：</label> 
									<input name="rebate" id="rebate" class="con-email" value="${fqRebate.rebate}" >
								</p>
								
								<p class="clearfix">
									<label class="one" for="con-email">折扣说明：</label> 
										<textarea name="rebateInfo" id="rebateInfo" class="con-email"  style="width:200px;height: 100px;max-width: 200px;max-height: 100px" >${fqRebate.rebateInfo}</textarea>
								</p>
								
								<p class="clearfix">
									<label class="one" for="con-email">是否可用：</label> 
									<select name = "enable" id="enable">
									
								
										<option   value="0" <c:if test="${fqRebate.enable==0}"> selected="selected"</c:if>>不可用</option>
										<option   value="1" 	<c:if test="${fqRebate.enable==1}"> selected="selected"</c:if>>可用</option>
									</select>
								</p>
								<p class="clearfix">
									<label class="one" for="con-email">开始时间：</label> 
									
									<%-- <input id="begin" name="beginTime" class="easyui-datebox con-time" value="${fqRebate.beginTime}" > --%>
										<div class="input-group date form_time col-md-5"  data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
						                    <input class="form-control" name="beginTime" id="beginTime"  size="16"  type="text" value="${fqRebate.beginTime}" readonly>
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
						                </div>
								</p>
								<div class="clear"></div>
								<p class="clearfix">
									<label class="one" for="con-email">结束时间：</label> 
									<%-- <input name=endTime class="con-email" value="${fqRebate.endTime}" > --%>
									<div class="input-group date form_time col-md-5"  data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
						                    <input class="form-control"   name="endTime" id="endTime"  size="16"  type="text" value="${fqRebate.endTime}" readonly>
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
						                </div>
								</p>
								<div class="clear"></div>
								<p class="clearfix">
								<label class="one" for="con-email"></label> 
								<input  type="hidden" name="token" value="${weixinRebateToken}">
									<input class="submit22" type="submit" value="提交">
								</p>
							</form>
						</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
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
		 <c:forEach items="${fqRebateList}" var="fqRebate" end="5" varStatus="i" >
		 $.formValidator.initConfig({validatorGroup:"${fqRebate.id}",formID : "formss${fqRebate.id}",alertMessage : true,onError : function(msg) {alert(msg)}});
			$("#rebate${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "折扣不能为空"}).regexValidator({regExp:"num1",dataType:"enum",onError:"折扣格式不正确"}).inputValidator({min:1,max:10,type:"value",onErrorMin:"你输入的值必须大于等于1",onError:"折扣必须在1-10之间，请确认"});
			$("#rebateInfo${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "折扣说明不能为空"});
			$("#beginTime${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "开始时间不能为空"});
			$("#endTime${fqRebate.id}").formValidator({validatorGroup:"${fqRebate.id}"}).inputValidator({min : 1,onError : "结束时间不能为空"});
		 </c:forEach>	
	});
</script>

</html>