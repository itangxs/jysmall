<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/kaquan/card_list.js"></script>
</head>

<body>
	<!-------------------top---------------------------------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true"><jsp:param name="val" value="11" /></jsp:include>
	<!--------------------------我的账户-------------------------------------------------------->
	<div class="membercontent">
		<!----------------左侧----------------------->
		<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true"><jsp:param
				name="position" value="10" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">卡券列表</h3>
			<form id="from" class="zcform1" method="post"
				action="/managermall/systemmall/cardcoupon/card_list.do">
				<p class="clearfix" >
					<input id="page" name="pageNum" type="hidden" value="${page.getPageNum() }">
					 <label class="one" for="con-email4">店铺名称：</label> <input class="con-email4" 
					 	name="storeName" value="${storeName}"> <label
						for="select">&nbsp;&nbsp;&nbsp;&nbsp; 
						</label> <label class="one" for="con-email4">店铺ID：</label> 
						<input class="con-email4" name="storeId" value="${storeId}"> <br> <br> 
						<label class="one" for="con-email4">卡券类型：</label> 
					<select name="couponCfg">
						<option value="">全部</option>
						<option value="0" <c:if test="${couponCfg ==0 }"> selected='selected'</c:if>>礼品券</option>
						<option value="1" <c:if test="${couponCfg ==1 }"> selected='selected'</c:if>>代金券</option>
						<option value="2" <c:if test="${couponCfg ==2 }"> selected='selected'</c:if>>折扣券</option>
					</select> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp; </label> <label
						class="one" for="con-email4">投放状态：</label> 
					<select name="deleveryType">
						<option value="">全部</option>
						<option value="1" <c:if test="${deleveryType ==1 || deleveryType ==2 }"> selected="selected"</c:if>>投放中</option>
						<option value="0" <c:if test="${deleveryType ==0 }"> selected="selected"</c:if>>未投放</option>
					</select> 
					<label for="select">&nbsp;&nbsp;&nbsp;&nbsp; </label> <label class="one" for="con-email4">投放方式：</label> 
					<select name="statusCfg">
						<option value="">全部</option>
						<option value="1" <c:if test="${statusCfg ==1 }"> selected="selected"</c:if>>自营投放</option>
						<option value="2" <c:if test="${statusCfg ==2 }"> selected="selected"</c:if>>周边投放</option>
					</select> <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="submit" value="查询" class="submit8">
				</p>
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<th class="td100">店铺ID</th>
                    <th class="td100">店铺名称</th>
                    <th class="td100">卡券名称</th>      
                    <th class="td100">卡券类型</th>      
                    <th class="td100">状态</th>
                    <th class="td100">投放方式</th>
                    <th class="td240">操作</th>
                </tr>
					<c:forEach var="p" items="${page }">
						<tr>
							<td class="td100">${p.storeId }</td>
							<td class="td160">${p.storeName }</td>
							<td class="td100">${p.name }</td>
							<td class="td100">
								<c:if test="${p.couponCfg == 0 }">礼品券</c:if>
								<c:if test="${p.couponCfg == 1 }">代金券</c:if>
								<c:if test="${p.couponCfg == 2 }">折扣券</c:if> 
							</td>
							<td class="td100">
								<c:if test="${p.statusCfg == 1 || p.statusCfg == 2 }">投放中</c:if>
								<c:if test="${p.statusCfg == 0 }">未投放</c:if>
							</td>
							<td class="td100">
								<c:if test="${p.statusCfg == 1 }">自营投放</c:if>
								<c:if test="${p.statusCfg == 2 }">周边投放</c:if>
								<c:if test="${p.statusCfg == 0 }">-</c:if>
							</td>
							<td class="td100"><span class="submitblue70">
							
							<c:if test="${p.statusCfg == 1 || p.statusCfg == 2}"><a class="submitblue70" href="javascript:updateStatus('${p.sellerId }','${p.id }')">停止投放</a></c:if>
							</span><span><a class="submitblue70" href="javascript:void(0)" onclick="deleteFun('cktxjl', '${p.sellerId }','${p.id}','${p.name }')">删除</a></span><span><a class="submitblue70" href="javascript:void(0)" onclick="detail('${p.id}','${p.validityCfg }')">详情</a></span></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
			<!--上一页下一页-->
			<div class="page">
				<c:if test="${page.getPageNum()>1}">
					<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
				</c:if>
				<c:choose>
					<c:when test="${page.getPages()<7}">
						<c:forEach var="i" begin="1" end="${page.getPages()}">
							<c:choose>
								<c:when test="${i==page.getPageNum()}">
									<em class="current">${i}</em>
								</c:when>
								<c:otherwise>
									<a href="javascript:previousPage(${i},'from','page')">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:when test="${page.getPages()>6}">
						<c:forEach var="i" begin="1" end="3">
							<c:choose>
								<c:when test="${i==page.getPageNum()}">
									<em class="current">${i}</em>
								</c:when>
								<c:otherwise>
									<a href="javascript:previousPage(${i},'from','page')">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page.getPageNum()>4}">
							<em>...</em>
						</c:if>
						<c:forEach var="i" begin="4" end="${page.getPages()-3}">
							<c:if test="${i==page.getPageNum()}">
								<em class="current">${i}</em>
							</c:if>
						</c:forEach>
						<c:if test="${page.getPageNum()<page.getPages()-3}">
							<em>...</em>
						</c:if>
						<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
							<c:choose>
								<c:when test="${i==page.getPageNum()}">
									<em class="current">${i}</em>
								</c:when>
								<c:otherwise>
									<a href="javascript:previousPage(${i},'from','page')">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
				</c:choose>
				<c:if test="${page.getPages()>page.getPageNum()}">
					<a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a>
				</c:if>
				<span>共${page.getPages()}页</span><span>到第</span> <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}"
					onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}" /><span>页</span>
			</div>
			<div class="clear"></div>
		</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
	<!--底部-e-->

	<!--卡券删除弹窗-->
	<div id="cktxjl" class="white_content" style="">
		<input type="hidden" id="numId"/>
		<input type="hidden" id="sellerId">
		<p class="close03">提示</p>
		<div class="nr nr02">
			<div class="kaquan_list">
				<p style="line-height: 40px; font-size: 24px">是否确认删除卡券：<br />
				<span id="k_name"></span>
				<p>
			</div>
		</div>
		<div class="kqanniu01">
			<a href="javascript:void(0)" style="background-color: #ccc;"
				onclick="document.getElementById('cktxjl').style.display='none';document.getElementById('fade').style.display='none'">取消</a>
			<a href="javascript:void(0)" style="background-color: #64a1ce;"
				onclick="delet($('#numId').val(),$('#sellerId').val());onLoad()">确认</a>
		</div>
	</div>

	<!--卡券详情弹窗-->
	<div id="cktxj2" class="white_content" style="">
		<p class="close03">详情</p>
		<div class="nr nr02">

			<div class="kaquan_detail">
				<div class="detail_left">
					<p class="clearfix">
						<label class="con-email130" for="con-email4">卡券名称： <span id="cardname"></span></label>
					</p>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">剩余数量： <span id="surpluscount"></span></label>
					</p>
				</div>
				<div class="detail_right">
					<p class="clearfix">
						<label class="con-email130" for="con-email4">面额/折扣： <span id="discount"></span></label>
					</p>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">有效期限：<span id="validityday"></span>
						<span id="validityStarttime"></span><span id="validityEndtime"></span></label>
					</p>
				</div>
				<div>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">领券限制： <span id="countlimit"></span></label>
					</p>
					<p class="clearfix">
						<label class="con-email130" for="con-email4">使用说明： <span id="explain"></span></label>
					</p>
				</div>
			</div>

		</div>
		<div class="kqanniu01">
			<a href="javascript:void(0)" style="background-color: #ccc;"
				onclick="document.getElementById('cktxj2').style.display='none';document.getElementById('fade').style.display='none'">返回</a>
		</div>
	</div>

	<!--弹出幕布-->
	<div id="fade" class="black_overlay"></div>
</body>
</html>
