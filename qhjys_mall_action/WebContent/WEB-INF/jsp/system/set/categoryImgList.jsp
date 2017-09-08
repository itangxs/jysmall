<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/css/uploadify_.css" />
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="fieldval" value="10" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true"><jsp:param name="val" value="9" /></jsp:include>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">网站设置——WEB首页类别图片配置</h3>
			<form method="post" action="updateCategoryImg.do">
				<p class="clearfix">
					<label class="one" for="con-email4">美食图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img1" src="${list[0].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_1" name="img1" type="hidden" value="${list[0].img}">
					<img id="img2" src="${list[1].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_2" name="img2" type="hidden" value="${list[0].img}">
					<img id="img3" src="${list[2].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_3" name="img3" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field1" value="${list[0].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field2" value="${list[1].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field3" value="${list[2].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img1Butn" type="file" value="上传" class="submit8">
					<input id="img2Butn" type="file" value="上传" class="submit8">
					<input id="img3Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">电影图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img4" src="${list[3].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_4" name="img4" type="hidden" value="${list[0].img}">
					<img id="img5" src="${list[4].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_5" name="img5" type="hidden" value="${list[0].img}">
					<img id="img6" src="${list[5].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_6" name="img6" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field4" value="${list[3].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field5" value="${list[4].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field6" value="${list[5].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img4Butn" type="file" value="上传" class="submit8">
					<input id="img5Butn" type="file" value="上传" class="submit8">
					<input id="img6Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">购物图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img7" src="${list[6].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_7" name="img7" type="hidden" value="${list[0].img}">
					<img id="img8" src="${list[7].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_8" name="img8" type="hidden" value="${list[0].img}">
					<img id="img9" src="${list[8].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_9" name="img9" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field7" value="${list[6].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field8" value="${list[7].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field9" value="${list[8].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img7Butn" type="file" value="上传" class="submit8">
					<input id="img8Butn" type="file" value="上传" class="submit8">
					<input id="img9Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">旅游图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img10" src="${list[9].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_10" name="img10" type="hidden" value="${list[0].img}">
					<img id="img11" src="${list[10].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_11" name="img11" type="hidden" value="${list[0].img}">
					<img id="img12" src="${list[11].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_12" name="img12" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field10" value="${list[9].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field11" value="${list[10].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field12" value="${list[11].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img10Butn" type="file" value="上传" class="submit8">
					<input id="img11Butn" type="file" value="上传" class="submit8">
					<input id="img12Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">休闲娱乐图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img13" src="${list[12].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_13" name="img13" type="hidden" value="${list[0].img}">
					<img id="img14" src="${list[13].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_14" name="img14" type="hidden" value="${list[0].img}">
					<img id="img15" src="${list[14].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_15" name="img15" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field13" value="${list[12].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field14" value="${list[13].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field15" value="${list[14].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img13Butn" type="file" value="上传" class="submit8">
					<input id="img14Butn" type="file" value="上传" class="submit8">
					<input id="img15Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">丽人图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img16" src="${list[15].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_16" name="img16" type="hidden" value="${list[0].img}">
					<img id="img17" src="${list[16].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_17" name="img17" type="hidden" value="${list[0].img}">
					<img id="img18" src="${list[17].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_18" name="img18" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field16" value="${list[15].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field17" value="${list[16].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field18" value="${list[17].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img16Butn" type="file" value="上传" class="submit8">
					<input id="img17Butn" type="file" value="上传" class="submit8">
					<input id="img18Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<label class="one" for="con-email4">生活服务图片配置： </label>
				</p>
				<p class="clearfix">
					<img id="img19" src="${list[18].img}" width="160" height="120" style="margin-left:30px;">
					<input id="img_19" name="img19" type="hidden" value="${list[0].img}">
					<img id="img20" src="${list[19].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_20" name="img20" type="hidden" value="${list[0].img}">
					<img id="img21" src="${list[20].img}" width="160" height="120" style="margin-left:10px;">
					<input id="img_21" name="img21" type="hidden" value="${list[0].img}">
				</p>
				<p class="clearfix">
					<input type="text" name="field19" value="${list[18].field}" class="con-email4" style="margin-left:32px;">
					<input type="text" name="field20" value="${list[19].field}" class="con-email4" style="margin-left:5px;">
					<input type="text" name="field21" value="${list[20].field}" class="con-email4" style="margin-left:5px;">
				</p>
				<p class="clearfix">
					<input id="img19Butn" type="file" value="上传" class="submit8">
					<input id="img20Butn" type="file" value="上传" class="submit8">
					<input id="img21Butn" type="file" value="上传" class="submit8">
				</p>
				<br />
				<p class="clearfix">
					<input type="submit" value="保存" class="submit8" style="margin-left: 300px;">
				</p>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
	<script type="text/javascript" src="/js/system/set/categoryImgList.js"></script>
</body>
</html>
