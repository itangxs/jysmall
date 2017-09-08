<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/register.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jysmall_step6.js"></script>
<style type="text/css">
.zcform {width:540px;margin:30px 0 0 210px}
.clearfix .combo{float:left;margin-right:3px;}
.zcform .submit2{margin-left:130px;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="1" /></jsp:include>
		<!--------------右侧------------------>
	<div class="memberright">
    	<form id="form1" name="form1">
            <h3 style="padding-left:10px;">商家中心——店铺认证</h3>
        </form>
        <div class="member_myorder2">
        <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td161">&nbsp;</th>
                    <th class="td162">&nbsp;</th>
                    <th class="td163">&nbsp;</th>
                    <th class="td168">&nbsp;</th>
                 </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">入驻须知</th>
                    <th class="td160">公司信息认证</th>
                    <th class="td160">店铺信息认证</th>
                    <th class="td160">等待审核</th>
                 </tr>
            </table>
        </div>  
          <div class="member_myorder">   
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>    
          <th style="text-align:left;background:#fffef5;">
		<form id="signupForm" method="post" action="" class="zcform">
			<h3>选择可经营项目</h3>
			<p class="clearfix">
				<label class="one" for="categoryId">主营类目：</label> 
				
                 <select id="categoryId2" name="categoryId2" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="categoryId" name="categoryId" class="easyui-combobox" style="width:130px;height:32px">
				</select>
			</p>
			 
			<p class="clearfix" >
				<input class="submit23" type="submit" value="下一步，提交入驻申请" /><label>&nbsp;&nbsp;&nbsp;</label>
				<a href="addStoreInfo.do"><input class="submit22" type="button" value="返回上一步" /></a>
			</p>
		</form>
</th></tr>
          </table>
         </div>
    	</div>
	<div class="clear" ></div>
</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
