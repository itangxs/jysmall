<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心</title>
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/seller/commoditymanage/add_page.js"></script>
<script type="text/javascript" src="/js/seller/commoditymanage/add_page_v.js"></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<style type="text/css">.uploadify {margin-left: 50px;}</style>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="9" /></jsp:include>
	<div class="memberright">
		<h3 style="padding-left: 10px;">商品管理——修改商品</h3>
		<div class="member_myorder">
			<div style="text-align:right;width:100%;height:30px;">
				<a href="/managermall/seller/commoditymanage/list.do" class="fontred">返回商品列表</a>
			</div>
			<form id="signupForm" method="post" action="/managermall/seller/commoditymanage/update.do" class="zcform" style="margin-left: 150px;">
				<p class="clearfix">
					<label class="one" for="contactname">商品名称：</label>
					<input id="contactname" class="con-email" name="spmc" value="${p.name}">
					<span id="contactnameTip"></span>
				</p>
				<p class="clearfix">
					<input type="hidden" name="splb" value="${p.prodType}" />
				</p>
				<p class="clearfix">
					<label class="one" for="contactname">商品类：</label>
					<select name="spsx" id="spsx">
						<option value="">------请选择类别-----</option>
						<c:forEach items="${array}" var="y">
							<c:if test="${y.id==sx}"><c:forEach items="${y.childs}" var="e">
							<option value="${e.id}" <c:if test="${p.categoryId==e.id }">selected="selected"</c:if>>${e.text}</option>
							</c:forEach></c:if>
						</c:forEach>
					</select>
					<span id="spsxTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="contactname">商品描述：</label>
					<input id="spms" class="con-email" name="spms" value="${p.title}">
					<span id="spmsTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="contactname">服务承诺：</label>
					<label class="one5"><input name="fwcr" type="checkbox" value="1" style="margin-top:10px;" <c:if test="${fn:contains(p.promise,'1')}">checked="checked"</c:if>/>随时退换 </label>
					<label class="one5"><input name="fwcr" type="checkbox" value="2" style="margin-top:10px;" <c:if test="${fn:contains(p.promise,'2')}">checked="checked"</c:if>/>过期退换</label>
					<label class="one5"><input name="fwcr" type="checkbox" value="3" style="margin-top:10px;" <c:if test="${fn:contains(p.promise,'3')}">checked="checked"</c:if>/>支持代金卷</label>
					<label class="one5"><input name="fwcr" type="checkbox" value="4" style="margin-top:10px;" <c:if test="${fn:contains(p.promise,'4')}">checked="checked"</c:if>/>接受预定</label>
				</p>
				<p class="clearfix">
					<label class="one" for="con-telphone">开始日期：</label>
					<input id="kssj" name="kssj" type="text" readonly="readonly" value='<fmt:formatDate value="${p.starDate}" pattern="yyyy-MM-dd"/>' style="margin-right:3px;"/>
					<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'kssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
					<span id="kssjTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email">结束日期：</label>
					<input id="jssj" name="jssj" type="text" readonly="readonly" value="<fmt:formatDate value="${p.endDate}" pattern="yyyy-MM-dd"/>" style="margin-right:3px;"/>
					<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
					<span id="jssjTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email">下架日期：</label>
					<input id="xjsj" name="xjsj" type="text" readonly="readonly" value="<fmt:formatDate value="${p.offShelf}" pattern="yyyy-MM-dd"/>" style="margin-right:3px;"/>
					<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'xjsj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle">
					<span id="xjsjTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email3">市场价：</label>
					<input id="scj" name="scj" class="con-email3" value="${p.origPrice}"> 元
					<span id="scjTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email3">折扣价：</label>
					<input id="unit" name="unit" class="con-email3" value="${p.unitPrice}"> 元
					<span id="unitTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email3">可售数量：</label>
					<input id="kssl" name="kssl" class="con-email3" value="${p.prodStock}"> 个
					<span id="ksslTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email3">每人限购：</label>
					<input id="mrxg" name="mrxg" class="con-email3" value="${p.maxPay}"> 个
					<span id="mrxgTip"></span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email">商品图片：</label>
					<c:choose><c:when test="${fn:contains(p.images,',') }">
						<input type="hidden" id="sptp" name="sptp" value="${fn:split(p.images, ',')}" />
						<div style="position: relative; display: inline-block;">
							<img id="img1" src="${fn:split(p.images, ',')[0]}" width="150" height="120" alt="" />
							<input type="hidden" id="img1u" name="imgs" value="${fn:split(p.images, ',')[0]}" />
							<input class="uploadify" type="file" value="上传" id="update1" />
						</div>
						<div style="position: relative; display: inline-block;">
							<img id="img2" src="${fn:split(p.images, ',')[1]}" width="150" height="120" alt="" />
							<input type="hidden" id="img2u" name="imgs" value="${fn:split(p.images, ',')[1]}" />
							<input class="uploadify" type="file" value="上传" id="update2" />
						</div>
						<div style="position: relative; display: inline-block;">
							<img id="img3" src="${fn:split(p.images, ',')[2]}" width="150" height="120" alt="" />
							<input type="hidden" id="img3u" name="imgs" value="${fn:split(p.images, ',')[2]}" />
							<input class="uploadify" type="file" value="上传" id="update3" />
						</div>
						<div style="position: relative; display: inline-block;">
							<img id="img4" src="${fn:split(p.images, ',')[3]}" width="150" height="120" alt="" />
							<input type="hidden" id="img4u" name="imgs" value="${fn:split(p.images, ',')[3]}" />
							<input class="uploadify" type="file" value="上传" id="update4" />
						</div>
						</c:when><c:otherwise>
						<input type="hidden" id="sptp" name="sptp" value="${fn:split(p.images, '|')}" />
						<div style="position: relative; display: inline-block;">
							<img id="img1" src="${fn:split(p.images, '|')[0]}" width="150" height="120" alt="" />
							<input type="hidden" id="img1u" name="imgs" value="${fn:split(p.images, '|')[0]}" />
							<input class="uploadify" type="file" value="上传" id="update1" />
						</div>
						<div style="position: relative; display: inline-block;">
							<img id="img2" src="${fn:split(p.images, '|')[1]}" width="150" height="120" alt="" />
							<input type="hidden" id="img2u" name="imgs" value="${fn:split(p.images, '|')[1]}" />
							<input class="uploadify" type="file" value="上传" id="update2" />
						</div>
						<div style="position: relative; display: inline-block;">
							<img id="img3" src="${fn:split(p.images, '|')[2]}" width="150" height="120" alt="" />
							<input type="hidden" id="img3u" name="imgs" value="${fn:split(p.images, '|')[2]}" />
							<input class="uploadify" type="file" value="上传" id="update3" />
						</div>

						<div style="position: relative; display: inline-block;">
							<img id="img4" src="${fn:split(p.images, '|')[3]}" width="150" height="120" alt="" />
							<input type="hidden" id="img4u" name="imgs" value="${fn:split(p.images, '|')[3]}" />
							<input class="uploadify" type="file" value="上传" id="update4" />
						</div>
					</c:otherwise></c:choose>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email"></label>
					<span id="sptpTip"></span>
				<p class="clearfix">
					<label class="one" for="con-email">购买须知：</label>
				<p class="clearfix">
					<div>
						<textarea id="gmxz1" style="display: none;">${p.buyingTips}</textarea>
						<script type="text/plain" id="gmxz" name="gmxz"></script>
						<script type="text/javascript">
							//实例化编辑器
							var um = UM.getEditor('gmxz', {
								initialFrameHeight : 329,
								initialFrameWidth : 533,
								autoHeightEnabled : false
							});
						</script>
					</div>
				</p>
				<span id="txtDefaultHtmlAreaTip"></span>
				<p class="clearfix">
					<label class="one" for="con-email">产品详情：</label>
				</p>
				<p class="clearfix">
					<div>
						<textarea id="cpxq1" style="display: none;">${p.prodDetail}</textarea>
						<script type="text/plain" id="cpxq" name="cpxq"></script>
						<script type="text/javascript">
							//实例化编辑器
							var um = UM.getEditor('cpxq', {
								initialFrameHeight : 329,
								initialFrameWidth : 533,
								autoHeightEnabled : false
							});
						</script>
					</div>
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email"></label> <input class="submit10" type="submit" value="修改" />
				</p>
				<input type="hidden" name="token" value="${token}" /> <input type="hidden" name="id" value="${p.id}" />
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
</body>
</html>
