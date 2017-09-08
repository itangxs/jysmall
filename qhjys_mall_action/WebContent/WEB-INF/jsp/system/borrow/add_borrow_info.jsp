<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心-借贷管理</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link type="text/css" rel="stylesheet"
	href="/common/formValidator4.0.1/style/validator.css" />
	<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/system/borrow/addcredit.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="13" /></jsp:include>
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/system/borrow/borrowLeft.jsp"><jsp:param name="position"  value=""/></jsp:include>
	<div class="memberright">
		<h3 style="padding-left:10px; margin-bottom:25px;">添加借贷信息</h3>
			<div class="member_myorder">
				<form id="addform" action="/managermall/systemmall/borrow/add.do" method="post">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="td100">店铺ID：</td>
							<td class="">${storeId }
							<input id="storeId" name="storeId" type="hidden" value="${storeId }">
							</td>
						</tr>
						<tr>
							<td class="td100">店铺名称：</td>
							<td class="">${storeName }
							<input id="storeName" name="storeName" type="hidden" value="${storeName }"></td>
						</tr>
						<tr>
							<td class="td100">期数：</td>
							<td class="">${period }
							<input id="period" name="period" type="hidden" value="${period }"></td>
						</tr>
						<tr>
							<td class="td100">借款金额：</td>
							<td><input id="jkje" class="con-email4" id="creditMoney" name="creditMoney" value="" >
							<span id="jkjeTip"></span></td>
						</tr>
						<tr>
							<td class="td100">上期未还金额：</td>
							<td><input id="sqwhje" class="con-email4" id="beforeMoney" name="beforeMoney" value="${beforeMoney}" >
								<span id="sqwhjeTip"></span></td>
						</tr>
						
						<tr>
							<td class="td100">开始日期：</td>
							<td><input id="ksrq" name="ksrq"  class="con-email4" readonly="readonly"  />
								<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'ksrq'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
          						<span id="ksrqTip"></span>
							</td>
						</tr>
						
						<tr>
							<td class="td100">结束日期：</td>
							<td><input id="jsrq" name="jsrq"  class="con-email4"  readonly="readonly"  />
								<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,el:'jsrq'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
          						<span id="jsrqTip"></span>
							</td>
						</tr>
						
						<tr>
							<td class="td100">负责人：</td>
							<td><input id="fzr" class="con-email4" id="principal" name="principal" value="" >
							<span id="fzrTip"></span></td>
						</tr>
						
						<tr>
							<td class="td100">负责人电话：</td>
							<td><input id="fzrdh" class="con-email4" id="principalPhone" name="principalPhone" value="" >
							<span id="fzrdhTip"></span></td>
						</tr>
						
						<tr>
							<td class="td100">每周预警金额：</td>
							<td><input id="zyjje" class="con-email4" id="weekMoney" name="weekMoney" value="" >
							<span id="zyjjeTip"></span></td>
						</tr>
						<tr>
							<td><input  name="token" type="hidden" value="${token}"/></td>
							<!-- <td><input id="toaddreb" type="button" value="提交" class="submit9" onclick="addcredit()">&nbsp;&nbsp;</td> -->
							<td><input id="toaddreb" type="submit" value="提交" class="submit9" >&nbsp;&nbsp;</td>
						</tr>
					</table>
				</form>
			</div>
	</div>
<div class="clear"></div>
</div>
</body>
</html>