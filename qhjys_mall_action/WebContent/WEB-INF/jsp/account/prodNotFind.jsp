<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript">
	setInterval(function() {
		location.href = "/";
	}, 3000);
</script>
</head>
<body>
	<jsp:include page="uHeader.jsp" flush="true" />
	<div style="height: 400px; text-align: center; padding-top: 30px">
		<img alt="商品不存在" src="/images/notProduct.jpg">
		<div style="font-size: 18px;">
			<a style="color: #ff8c00;" href="/">返回首页>></a>
		</div>
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
</body>
</html>