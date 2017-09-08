<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/cms/addCms.js"></script>
<link href="/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<script>
	window.UMEDITOR_HOME_URL = "/umeditor/";
</script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<style type="text/css">
.uploadify { margin-left: 50px; }
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true"><jsp:param name="val" value="3" /></jsp:include>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">增加CMS</h3>
			<form id="form1" name="form1" class="zcform" action="/managermall/systemmall/cms/saveCms.do" method="post">
				<input type="hidden" value="${cms.id}" name="id" />
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">选择上级类别：</label>
					<select name="parentId">
						<c:forEach items="${cmsCategoryList}" var="cmsCategory1">
							<option value="${cmsCategory1.id}" <c:if test="${cms.parentId==cmsCategory1.id}"> selected="selected"</c:if>>${cmsCategory1.name }</option>
						</c:forEach>
					</select>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">标题：</label>
					<input class="con-email4" name="name" id="name" value="${cms.name}" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">Title：</label>
					<input class="con-email4" name="title" id="title" value="${cms.title}" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">Keywords：</label>
					<input class="con-email4" name="keywords" id="keywords" value="${cms.keywords}" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">description：</label>
					<input class="con-email4" name="description" id="description" value="${cms.description}" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4" style="width: 120px; text-align: right;">是否启用：</label>
					<select name="enabled">
						<option value="0" <c:if test="${cms.enabled==0}"> selected="selected"</c:if>>不启用</option>
						<option value="1" <c:if test="${cms.enabled==1}"> selected="selected"</c:if>>启用</option>
					</select>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email" style="width: 120px; text-align: right;">内容：</label>
				</p>
				<p class="clearfix" style="margin-left: 80px;">
				<div style="margin-left: 120px;">
					<script type="text/plain" id="content" name="content"></script>
					<script type="text/javascript">
						//实例化编辑器
						var ue = UM.getEditor('content', {
							initialFrameHeight : 329,
							initialFrameWidth : 533,
							autoHeightEnabled : false
						});
						ue.ready(function() {
							//设置编辑器的内容
							ue.setContent('${cms.content}');
						});
					</script>
				</div>
				</p>

				<p class="clearfix">
					<input type="submit" value="增加" class="submit24" style="width: 80px; margin-left: 240px;">
				</p>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
	<!--底部-e-->
</body>
</html>
