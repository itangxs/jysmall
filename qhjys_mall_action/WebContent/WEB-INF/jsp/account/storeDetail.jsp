<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/listdetail.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaodian.css" />
<script type="text/javascript" src="/js/account/show.js"></script>
<script type="text/javascript" src="/js/account/storeDetail.js"></script>
</head>
<body>
	<jsp:include page="uHeader.jsp" flush="true" />
	<div class="prodetail_top">
		<div class="title">
			<input id="storeId" type="hidden" value="${store.id}">
			<h1>${store.name}</h1>
		</div>
		<div class="pronr">
			<div class="pronr_left">
				<%-- <img src="${fn:substring(store.imageUrl, 0, fn:indexOf(store.imageUrl, ','))}" class="prodImg" />
				<div class="smallpic">
					<ul><c:forTokens varStatus="i" var="img" items="${store.imageUrl}" delims=",">
						<li><a href="javascript:;"><img src="${img}" <c:if test="${i.index==0}">class="a-current"</c:if>/></a></li>
					</c:forTokens></ul>
				</div> --%>
				<!-- 缩略图开始 -->	
			<div class="idx-focus" id="j_idx_focus" monkey="idxfocus">
				<div class="idx-foc-tmp">
					<ul class="focus-pic" rel="xtaberItems" style="left: -2800px; top: 0px; position: absolute; width: 3360px; height: 430px;">
					<c:forTokens varStatus="i" var="img" items="${store.imageUrl}" delims=",">
						<c:if test="${!empty img&&' '!=img}">
							<li class="xtaber-item">
								<a href="javascript:;"  class="white" target="_blank" title="飞券网"><img src="${img}" alt="飞券网"></a>
							</li>
						</c:if>
					</c:forTokens>
					</ul>
				</div>
				<ul rel="xtaberTabs" class="xtaber-tabs">
				<c:forTokens varStatus="i" var="img" items="${store.imageUrl}" delims=",">
					    <c:if test="${!empty img&&' '!=img}">
							<li rel="xtaberTabItem" class="">
							<a href="javascript:void(0);" title="飞券网">
							<img src="${img}" alt="飞券网"></a><i></i>
							</li>
						</c:if>
				 </c:forTokens>	
				</ul>
				<a href="javascript:;" class="btn-prev"></a>
				<a href="javascript:;" class="btn-next"></a>
			</div>
			</div>
			</div>
			<div class="pronr_right">
				<div class="tuangoujia">
					<div class="pingjiafentop">
						<h1>
							<b><c:if test="${empty review.avgScore}">0</c:if><fm:formatNumber value="${review.avgScore}" type="currency" pattern="#0.0"/><small>分</small></b>
							<span><em style="width:${review.avgScore*20}%;"></em></span>
							<div class="clear"></div>
						</h1>
						<p>共有${review.personNum}人评价</p>
					</div>
					<h1>
						<em>营业时间：</em> 08:00-24:00
					</h1>
					<h1>
						<em>电话：</em>${store.contactTel}
					</h1>
					<h1>
						<em>手机：</em>${store.contactPhone}
					</h1>
					<h1>
						<em>地址：</em>${store.province} ${store.city} ${store.areaName} ${store.address}
					</h1>	
                    <div class="zhifudiv" style="height:70px;">
                    	<ul>
                        	<li class="nocursor"><span class="zhifuimg2"></span>20金元宝 =1飞券=1元</li>
                            <li class="nocursor li120"><span class="zhifuimg3"></span><a class="fontred" href="/managermall/account/myrecharge.do">金元宝查询</a></li>
                            <li class="li100" id="rtanchu4">
                            	<span class="zhifuimg4"></span>兑换说明
                                <div id="tanchu4" style="position:absolute; right:-68px; display:none;">
                                    <div class="zhifudiv_tanchu">
                                        <div class="zhifudiv_tanchu_jiantou1"></div>
                                        <div class="zhifudiv_tanchucontent">成功兑换飞券后会通过手机短信发送串码到您的手机，到店消费时出示串码给店员即可使用。</div> 	
                                    </div>
                                </div>
                            </li>                            
                        </ul>
                    </div>				
				</div>
                
                <div class="tuangoujia">			
					<h1>
						<em>飞券数量</em>
						<div class="goumaishuliang">
							<input id="subtract" type="button" value="-" />
							<input id="prodNum" name="prodNum" class="input1" value="1" onkeyup="this.value=this.value.rephttp://www.jysmall.com/app/jysmall.apklace(/[^1-9]\D*$/,'');" onpaste="return false"/>
							<input id="add" type="button" value="+" />
						</div>
					</h1>
					<h1>
						<div class="goumaigou">
							<a name="qiang" href="javascript:;" class="qiang">立即兑换</a>
							<a name="jiaru" href="javascript:;" class="jiaru">加入购物车</a>
						</div>
						<div class="shoucang-fenxiang" style="position: relative;">
							<c:if test="${!colleted}"><a href="javascript:shoucang(${product.id},1);" class="shoucang"  id="shoucang">未收藏</a></c:if>
							<c:if test="${colleted}"><a href="javascript:shoucang(${product.id},2);" class="yishoucang" id="shoucang">已收藏</a></c:if>
							<div class="bdsharebuttonbox" style="display: inline-block; position: absolute; top: 6px; left: 70px; width: 100px;">
								<a href="javascript:;" class="bds_more" data-cmd="more">&nbsp;分享</a>
							</div>
							<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":[],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
						</div>
					</h1>
				</div>
				<div class="fuwuchengnuo" style="display:none;">
					服务承诺： 
					<span class="fwcnbg1"></span><a href="javascript:;">随时退</a>
					<span class="fwcnbg2"></span><a href="javascript:;">过期退</a>
					<span class="fwcnbg3"></span><a href="javascript:;">支持代金券</a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="contentall">
		<div class="contentleft">
			<div class="detail_alltitle" style="z-index: 999999">
				<!--  class="current" -->
				<a href="javascript:void(0)" onclick="document.getElementById('shangjiaweizhi').scrollIntoView();">商家位置</a>
				<a href="javascript:void(0)" onclick="document.getElementById('shangjiajies').scrollIntoView();">商家介绍</a>
				<a href="javascript:void(0)" onclick="document.getElementById('shangpinjies').scrollIntoView();">商品介绍</a>
				<a href="javascript:void(0)" onclick="document.getElementById('xiaofeipingjia').scrollIntoView();">消费评价<span>${review.personNum}</span></a>
			</div>
			 <a  id="shangjiaweizhi" name="shangjiaweizhi"></a>
			<div class="detail_shangjiaweizhi">
				<div class="detail_alltitle_title">商家位置</div>
				<%-- <div class="">
					<div class="mapimg">
						<img src="images/weizhi.jpg" />
					</div>
					<h1>${store.name}</h1>
					<h2><span>评价：</span>
					<c:if test="${empty review.avgScore}">0</c:if>
					<fm:formatNumber value="${review.avgScore}" type="currency" pattern="0.0"/>
					分</h2>
					<p>地址：${store.province} ${store.city} ${store.areaName} ${store.address}</p>
					<h3 class="padding50">
						<a href="javascript:;" class="fontred">查看地图</a><a href="javascript:;" class="fontred">公交/驾车去这里</a>
					</h3>
					<p>电话：${store.contactTel}</p>
					<div class="clear"></div>
				</div> --%>
				<div class=""   >
					<div class="mapimg">
						<input type="hidden" id="longitude" value="${store.lngtd }" />
						<input type="hidden" id="latitude" value="${store.lattd }" />
						<input type="hidden" id="stitle" value="${store.address }" />
						<input type="hidden" id="scontent" value="${store.name}" />
						<script type="text/javascript"  src="http://api.map.baidu.com/api?key=&amp;v=1.1&amp;services=true"></script>
							<div style="WIDTH: 500px; HEIGHT: 350px;" id="dituContent"></div>
						<script type="text/javascript" src="/js/account/map.js"></script>
					</div>
					<h1><span id="">${store.name}</span></h1>
					<h2>
						<span>评价：</span>
						<c:if test="${empty review.avgScore}">0</c:if>
						<c:if test="${!empty review.avgScore}">	<fm:formatNumber value="${review.avgScore}"  type="currency" pattern="0"/></c:if>
						分
					</h2>
					<p>地址：${store.province} ${store.city} ${store.areaName} ${store.address}</p>
					<h3 class="padding50">
						<a href="javascript:diag1('${store.address}');">公交/驾车去这里</a>
				   </h3>
					<p>电话：${store.contactTel }</p>
					<div class="clear"></div>
				</div>
			</div>
			<!--商家介绍-->
			<a  id="shangjiajies" name="shangjiajies"></a>
			<div class="detail_bandanxiangqing">
				<div class="detail_alltitle_title">商家介绍</div>
				<div>${store.storeDetail}</div>
			</div>
			
			<a  id="shangpinjies" name="shangpinjies"></a>
			<div class="detail_bandanxiangqing">
				<div class="detail_alltitle_title">店铺商品</div>
			</div>
			<div class="ganxingqu">
				<ul>
					<c:forEach var="prod" items="${other}">
					<li>
						<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images,0, fn:indexOf(prod.images,'|'))}" /></a>
						<p><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></p>
						<h2>￥<fm:formatNumber value="${prod.unitPrice}"  type="currency" pattern="#,###.##"/></h2>
					</li>
					</c:forEach>
					<div class="clear"></div>
				</ul>
			</div>
			<!--消费评价-->
			<a id="xiaofeipingjia" name="xiaofeipingjia"></a>
			<div class="detail_xiaofeipingjia">
				<div class="detail_alltitle_title">消费评价</div>
				<!--评价内容-->
				<div class="pingjia_nr">
					<div class="title">
						<form action="" method="get">
							<input name="level" type="radio" value="1" checked="checked" /><a href="javascript:;" class="linkcurrent">全部</a>
							<input name="level" type="radio" value="2" /><a href="javascript:;">好评</a>
							<input name="level" type="radio" value="3" /><a href="javascript:;">中评</a>
							<input name="level" type="radio" value="4" /><a href="javascript:;">差评</a>
							<input name="level" type="radio" value="5" /><a href="javascript:;">图片</a>
						</form>
					</div>
					<ul id="reviews"></ul>
				</div>
			</div>
			<!--加入购物车-->
		</div>
		<!--右侧-s-->
		<div class="contentright">
			<!--右侧广告-->
			<div class="right_adv">
				<a href="${ad1.link == null?'#':ad1.link }"><img src="${ad1.image == null?'images/list_right_adv.jpg':ad1.image }" /></a><!--  <a href="${ad2.link == null?'#':ad2.link }"><img src="${ad2.image == null?'images/list_right_appdown.jpg':ad2.image }" /></a> -->
			</div>
			<!--右侧推荐-->
			<div class="right_tuijian">
				<h1>
					<span><a href="javascript:;">换一批</a></span>看了又看
				</h1>
				<ul>
					<c:forEach var="prod" items="${prodCate}">
					<li>
						<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images,0, fn:indexOf(prod.images,'|'))}" /></a>
						<p><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></p>
						<h2>￥<fm:formatNumber value="${prod.unitPrice}"  type="currency" pattern="#,###.##"/></h2>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--右侧-e-->
		<div class="clear"></div>
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
<script src="/js/account/jquery.pin.min.js"></script>
    <!-- PIN ALL THE THINGS! -->
    <script>
      $(".detail_alltitle").pin();
    </script>
</body>
</html>