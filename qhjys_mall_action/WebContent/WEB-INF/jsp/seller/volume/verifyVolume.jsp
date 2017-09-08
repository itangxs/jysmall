<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>飞券网商家后台中心 -验证飞券串码 </title>

<link href="/css/seller/public.css" rel="stylesheet" type="text/css" />
<link href="/css/seller/membernew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/seller/volume/verifyVolume.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="7" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div class="member_myorder3">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<th class="td132" width="232">验证飞券串码 </th>
						</tr>
					</tbody>
				</table>
				<br>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="td130">
								<input id="vCode" name="vCode" value="" class="con-email2">
								<input id="verify" type="button" value="验证" class="submit7">
							</td>
							
						</tr>
						<tr id="xiaofei" style="display: none;">
							<td class="td130">
								<p id="xiaofei1"></p>
								<input id="verify1" type="button" value="确认消费" class="submit7">
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>