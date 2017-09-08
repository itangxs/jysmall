<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改微信店铺</title>
<link type="text/css" rel="stylesheet" href="/umeditor//themes/default/css/umeditor.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>

<link type="text/css" rel="stylesheet" href="/common/css/validator.css"></link>
<script src="/common/js/formValidator.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
	


<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bootstrap/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<link href="/bootstrap/css/weixin_storebootstrap.min.css" rel="stylesheet" media="screen">
<link href="/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	

<script type="text/javascript" src="/js/wxstoreInfoupdate.js"></script>
<style type="text/css">
.uploadify {margin-left:100px;}
.uploadify-queue {margin-left:100px;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="21" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div id="member_myorder">
			
				<div id="mainShow" class="main">
					<div class="member_myorder">
						<form class="zcform"  id="form" style="margin-left:155px;text-align:left;" method="post" action="/managermall/seller/updateWXStoreInfo.do">
							<p class="clearfix">
								<label class="one" for="con-email">微店铺名称：</label> 
								<input name="storename"  id="storename" class="con-email" value="${fqStore.storeName}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">微信店铺LOGO：</label>
								<img id="logoImage" width="150" height="120" src="${fqStore.storeLogo}" alt="" />
								请上传一张440px*300px大小的图片
								<input id="logo" type="hidden" name="storelogo" value="${fqStore.storeLogo}" />
								<br>
								<br>
							</p>
							<p class="clearfix">
								<label class="one" for="con-email"></label>
								 <input id="logoButn" type="file" multiple="true">
							</p> 
							
							
								
							
							<p class="clearfix">
								<label class="one" for="con-email">店面或者菜品相关图片1：</label>
								<img id="relevantImage1" width="150" height="120" src="${fn:split(fqStore.storeImage, ',')[0]}" alt="" />
								<input id="relevant1" type="hidden" name="relevantImage1" value="${fn:split(fqStore.storeImage, ',')[0]}" />
								<br>
								<br>
							</p>
							<p class="clearfix">
								<label class="one" for="con-email"></label>
								 <input id="relevantButn1" type="file" multiple="true">
							</p> 
							
							<p class="clearfix">
								<label class="one" for="con-email">店面或者菜品相关图片2：</label>
								<img id="relevantImage2" width="150" height="120" src="${fn:split(fqStore.storeImage, ',')[1]}" alt="" />
								<input id="relevant2" type="hidden" name="relevantImage2" value="${fn:split(fqStore.storeImage, ',')[1]}" />
								<br>
								<br>
							</p>
							<p class="clearfix">
								<label class="one" for="con-email"></label>
								 <input id="relevantButn2" type="file" multiple="true">
							</p> 
							
							<p class="clearfix">
								<label class="one" for="con-email">店面或者菜品相关图片3：</label>
								<img id="relevantImage3" width="150" height="120" src="${fn:split(fqStore.storeImage, ',')[2]}" alt="" />
								<input id="relevant3" type="hidden" name="relevantImage3" value="${fn:split(fqStore.storeImage, ',')[2]}" />
								<br>
								<br>
							</p>
							<p class="clearfix">
								<label class="one" for="con-email"></label>
								 <input id="relevantButn3" type="file" multiple="true">
							</p> 
							
							
							
							
							<p class="clearfix">
								<label class="one" for="con-email">店铺介绍：</label>
								<textarea name="storeinfo" id="storeinfo" style="width:200px;height: 100px;max-width: 200px;max-height: 100px" >${fqStore.storeInfo}</textarea>
								<%-- <input name="storeinfo"  id="storeinfo" class="con-email" value="${fqStore.storeInfo}"> --%>
								字数不可超过150
							</p>
							<!-- <p class="clearfix">
								<label class="one" for="con-email">店铺折扣：</label>
								<input name="storeRebate" id ="storeRebate" class="con-email"  value="${fqStore.storeRebate}">
								请输入1-10之间的数 
							</p> -->
						<%-- 	<p class="clearfix">
								<label class="one" for="con-email">活动介绍：</label>
								<textarea name="activityinfo" id="activityinfo" style="width:200px;height: 100px;max-width: 200px;max-height: 100px" >${fqStore.activityInfo}</textarea>
								<input name="activityinfo" id="activityinfo"  class="con-email" value="${fqStore.activityInfo}">
								字数不可超过150 
							</p> --%>
							<p class="clearfix">
								<label class="one" for="con-email">地址：</label>
								<input name="address"  id ="address" class="con-email" value="${fqStore.address}">
							</p>
							
								
							<p class="clearfix">
								<label class="one" for="con-email">开始营业时间</label>
									<div class="input-group date form_time col-md-5"  data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
						                    <input class="form-control" name="trafficBeginTime" id="trafficBeginTime"  size="16"  type="text" value="${fqStore.trafficBeginTime}" readonly>
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
						                </div>
							</p>
								<p class="clearfix">
									<label class="one" for="con-email">结束营业时间</label> 
									<div class="input-group date form_time col-md-5"  data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
						                    <input class="form-control"   name="trafficEndTime" id="trafficEndTime"  size="16"  type="text" value="${fqStore.trafficEndTime}" readonly>
						                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
											<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
						                </div>
							</p>
							
							
							<p class="clearfix">
								<label class="one" for="con-email">手机号码：</label>
								<input name="phonenum" id="phonenum" class="con-email" value="${fqStore.phoneNum}" maxlength="11">
							</p>
						
							<p class="clearfix">
								<label class="one" for="con-email">定金比例：</label>
								<input name="proportion"  id="proportion" class="con-email" value="${fqStore.proportion}">
								%&nbsp;&nbsp;&nbsp;请输入1-100之间的数 
							</p>
							
							<p class="clearfix">
								<label class="one" for="con-email">商圈：</label>
								<select name="locationid" id="locationid">
									<c:forEach items="${fqLocation}" var="sq">
										<option value="${sq.id}" <c:if test="${fqStore.locationId==sq.id }">selected="selected"</c:if> >${sq.location}</option>
									</c:forEach>
								</select>
							</p>
							<%-- 
							<p class="clearfix">
								<label class="one" for="con-email">菜系：</label>
								<select name="cuisineid"  id="cuisineid">
								<c:forEach items="${fqCuisines}" var="cx">
										<option value="${cx.id}" <c:if test="${fqStore.cuisineId==cx.id }">selected="selected"</c:if>  >${cx.cuisine}</option>
									</c:forEach>
								</select>
							</p> --%>
							<input type="hidden" name="token" value="${updateWeixinStoreToken}" >
							<p class="clearfix">
								<input class="submit22" type="submit" value="修改" style="margin-left:200px;" />
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
	</script>
</html>