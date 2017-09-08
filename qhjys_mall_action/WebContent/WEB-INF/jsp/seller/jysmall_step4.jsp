<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/register.css" />
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<style type="text/css">
.zcform {width:570px;margin:30px 0 0 332px}
.zcform .submit2{margin-left:135px;}
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
                    <th class="td164">&nbsp;</th>
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
                    <th class="td160" style="height:50px;">&nbsp;</th>
                    <th class="td160"></th>                            
                    <th class="td80"></th>
                </tr>
                <tr>
                    <th class="td80"></th>
                    <th class="td160" style="text-align:left;">资质审核：<br />
            可在下方查看资质审核状态，也可在办公时间拨打飞券客服电话400-6333-088或发送邮件到邮箱jysmall@163.com进行质询</th>                            
                    <th class="td80"></th>
                </tr>
                <tr>
                    <th class="td160" style="height:50px;">&nbsp;</th>
                    <th class="td160"></th>                            
                    <th class="td80"></th>
                </tr>
            </table>
         </div>
         <div class="member_myorder">
                   
             <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:#f7f6ed;">
                <tr>
                    <th class="td160" style="text-align:center;">入驻审核时间</th>
                    <th class="td160" style="text-align:center;">操作时间</th>
                    <th class="td160" style="text-align:center;">结果</th> 
					<th></th>
					<th></th>
					<th></th>
				
                </tr>
                <tr>
                	<th><fmt:formatDate value="${store.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                	<c:if test="${empty storeAudit.time}">
                		<th><fmt:formatDate value="${store.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                	</c:if>
                	<c:if test="${not empty storeAudit.time}">
                		<th><fmt:formatDate value="${storeAudit.time}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
                	</c:if>
                	<c:if test="${store.status ==0}">
                		<th>审核中</th>
                	</c:if>
                	<c:if test="${store.status ==1}">
                		<th>审核失败</th>
                	</c:if>
               		<c:if test="${store.status ==2}">
                		<th>审核通过</th>
                	</c:if>
                	<th></th>
                </tr>
            </table>
            </div>
    	</div>
	<div class="clear"></div>
</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
