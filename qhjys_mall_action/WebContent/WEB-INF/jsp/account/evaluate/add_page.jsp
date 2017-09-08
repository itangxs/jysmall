<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link href="/css/account/evaluate/add_page.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/account/evaluate/add_page.js"></script>
<link href="/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<style type="text/css">
.uploadify{
margin-left: 5px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<!--------------------------我的账户-------------------------------------------------------->
	<div class="membercontent">
		<!----------------左侧----------------------->
		<jsp:include page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="4" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div class="ratesdetail">
				<div class="tishi" style="display:none;">
					<p>
						积分规则升级啦！认真评价可获 <b>100</b> 积分奖励！<a href="/mallcms/info2.do?id=14" class="fontred" target="_blank">积分规则 >></a>
					</p>
					<!-- <p>
						您有 <b>3</b> 个待评价的订单。现在评价，赢取 <b>80</b> 积分。
					</p> -->
				</div>
				<div class="pingjiaimg">
					<img src="${orderDetailVo.prodImage}" />
					<p>
						<span>${orderDetailVo.prodName }</span>
					</p>
					<p>
						你为本团购打几分？<!--评价可得<b>
						<c:choose>
							<c:when test="${sessionScope.user.level==6}">20</c:when>
							<c:when test="${sessionScope.user.level==5}">18</c:when>
							<c:when test="${sessionScope.user.level==4}">16</c:when>
							<c:when test="${sessionScope.user.level==3}">14</c:when>
							<c:when test="${sessionScope.user.level==2}">12</c:when>
							<c:when test="${sessionScope.user.level==1}">8</c:when>
							<c:otherwise>4</c:otherwise>
						</c:choose></b>
						积分。-->
					</p>
				</div>
				<div class="pingjiacontent">
					<form method="post" id="myForm">
						<div class="member_zhengtipingjia">
							<div id="star">
								<span>我的总体评价：</span>
								<ul>
									<li><a href="javascript:;">1</a></li>
									<li><a href="javascript:;">2</a></li>
									<li><a href="javascript:;">3</a></li>
									<li><a href="javascript:;">4</a></li>
									<li><a href="javascript:;">5</a></li>
								</ul>
								<span></span>
								<div></div>
							</div>
							<div class="clear"></div>
							<!-- <h1><b></b><span><em style="width:70%;"></em><i>3.5分</i></span>
                	 
                    </h1> -->
						</div>
						<p>写够15个字，才是好同志</p>
						<input type="hidden" name ="detailId" value="${orderDetailVo.detailId}" />
						<input type="hidden" name="prodId" value="${orderDetailVo.prodId}" />
						<input type="hidden" name="token" value="${token}" />
						<input type="hidden" name="pf" id="pf" />
						<div class="title">
							<span><input name="ym" type="checkbox" value="ym" /> 匿名评价</span>
							<!-- <a href="#" class="titleshaitu">晒图</a>最多传20张，按住Ctrl 或Shift 可选择多张  -->
						</div>
						<textarea rows="8" name="content"></textarea>
						<div style="position:relative;display: inline-block;">
				        	<img  id="img1"  src="" width="150" height="120" alt="" />
				        	<input type="hidden" id="img1u" name="imgs" value=" "/>
				        	  <input  class="uploadify" type="file" value="上传" id="update1"/>
			        	</div>
			        	
			        	<div style="position:relative;display: inline-block;">
				        	<img  id="img2"  src="" width="150" height="120" alt="" />
				        	<input type="hidden" id="img2u" name="imgs" value=" "/>
				        	 <input  class="uploadify" type="file" value="上传" id="update2"/>
			        	</div>
			        	<div style="position:relative;display: inline-block;">
				        	<img  id="img3"  src="" width="150" height="120" alt="" />
				        	<input type="hidden" id="img3u" name="imgs" value=" " />
				        	<input  class="uploadify" type="file" value="上传" id="update3"/>
			        	</div>
			        	<div style="position:relative;display: inline-block;">
					       	<img  id="img4"  src="" width="150" height="120" alt="" />
					       	<input type="hidden" id="img4u" name="imgs" value=" " />
					       	 <input  class="uploadify" type="file" value="上传" id="update4"/>
			        	</div>
						<p>* 还可输入500个字。请上传原创、真实、合法的图片，如果发现用户上传的图片有侵权内容，金钥匙有权先行删除。</p>
						
						<input type="button" onclick="subimt1(${orderDetailVo.prodId})" value="发表评价" class="anniu" />
					</form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
