<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/seller/updatemessage.js"></script>
<script>
	window.UMEDITOR_HOME_URL = "/umeditor/";
	
</script>
<title>飞券网商家后台中心</title>
</head>
<script>
</script>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="34" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
	<h3 style="padding-left:; padding-bottom:20px;">群发图文信息</h3>
		<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th style="text-align: left; background: #fffef5;">
							<form id="form1" name="form1" method="post">
								
									<p class="clearfix">
									<label class="one" for="contactname">标题：</label>
										<input type="hidden" name="id" value="${message.id }">
										<input id="title" value="${message.title }" name="title" type="text" class="con-email3" style="width: 300px;">
									</p>
									<!-- <p class="clearfix">
									<label class="one" for="contactname">链接：</label>
										<input id="weburl" name="weburl" type="text" class="con-email3" style="width: 300px;">
									</p>-->
									<p class="clearfix">
									<label class="one" for="con-email">封面图片：</label> <input
										type="hidden" id="sptp" name="sptp" />
									<div style="position: relative; display: inline-block;">
										<img id="img1" src="${message.image }" width="150" height="120" alt="" /> 
										<input type="hidden" id="img1u" name="fileName" value="${message.image }" /><input
											class="uploadify" type="file" value="上传" id="update1" />
									</div>&nbsp;&nbsp;
								<p class="clearfix"></p>
								<p class="clearfix">
									<label class="one" for="cpxx">内容：</label>
								</p>
								<p class="clearfix">
								<textarea rows="" cols="" id="content1"  style="display: none;">${message.content }</textarea>
									<script type="text/plain" id="content" name="content"></script>
									<script type="text/javascript">
										//实例化编辑器
										var um = UM.getEditor('content', {
											initialFrameHeight : 329,
											initialFrameWidth : 533,
											autoHeightEnabled : false
										});
									</script>
								</p>
								<p class="clearfix">
									<label class="one" for="con-email"></label> 
									<input onclick="yulanshuju()"class="submit10" type="button" value="预览" />
									<input onclick="submshuju()"class="submit10" type="button" value="保存" />
								</p>
							</form>
							</th>
							</tr>
							</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
</body>
</html>
