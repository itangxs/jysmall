<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="金钥匙，金钥匙P2P，短期理财，股票配资,个人理财,线上理财,线下理财,配资,互联网金融,互联网金融服务平台" />
<meta name="description" content="金钥匙网贷{jysp2p.com}是由深圳市前海金钥匙控股集团有限公司精心打造的互联网金融服务平台,为投资者提供了年华收益高达15%-18%的线上理财服务。截止2015年4月，金钥匙网贷累计成交额突破3亿五千万元，为投资者带来近1000万元收益。" />
<title>金钥匙网贷-中国首批自律标杆平台_个人线上理财首选平台</title>
</head>
<c:if test="${!empty param.msg}">
	<script type="text/javascript">
		alert("${param.msg}");
		window.location.href = "${param.url}";
	</script>
</c:if>
<body>
</body>
</html>