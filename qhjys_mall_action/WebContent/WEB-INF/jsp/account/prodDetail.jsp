<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${product.name}-飞券网</title>
<meta name="keywords" content="${product.keywords}" />
<meta name="description" content="${product.description}" />
<link rel="stylesheet" type="text/css" href="/css/listdetail.css" />
<script type="text/javascript" src="/js/account/show.js"></script>
<link rel="stylesheet" type="text/css" href="/css/jiaodian.css" />
<script type="text/javascript" src="/js/account/prodDetail.js"></script>
</head>
<body>
<jsp:include page="uHeader.jsp" flush="true" />
	<div class="prodetail_top">
		<div class="title">
			<h1>${product.name}</h1>
			<h2>${product.title}</h2>
		</div>
		<div class="pronr">
		<div class="pronr_left">
			<!-- 缩略图开始 -->	
			<div class="idx-focus" id="j_idx_focus" monkey="idxfocus">
				<div class="idx-foc-tmp">
					<ul class="focus-pic" rel="xtaberItems" style="left: -2800px; top: 0px; position: absolute; width: 3360px; height: 430px;">
					<c:forTokens varStatus="i" var="img" items="${product.images}" delims="|" >
						<c:if test="${!empty img&&' '!=img}">
							<li class="xtaber-item">
								<a href="${img}"  class="white" target="_blank" ><img src="${img}" alt="飞券、飞券网、${product.storeName}" title="飞券、飞券网、${product.storeName}"></a>
							</li>
						</c:if>
					</c:forTokens>
					</ul>
				</div>
				<ul rel="xtaberTabs" class="xtaber-tabs">
				<c:forTokens varStatus="i" var="img" items="${product.images}" delims="|">
				    <c:if test="${!empty img&&' '!=img}">
						<li rel="xtaberTabItem" class="">
						<a href="javascript:void(0);" >
						<img src="${img}"alt="飞券、飞券网、${product.storeName}" title="飞券、飞券网、${product.storeName}"></a><i></i>
						</li>
					</c:if>
				 </c:forTokens>	
				</ul>
				<a href="javascript:;" class="btn-prev"></a>
				<a href="javascript:;" class="btn-next"></a>
			</div>
			<!-- 缩略图结束 -->	
			</div>
			<div class="pronr_right">
				<div id="shopMsg" class="joincartsuccess" style="display:none;">
					<div class="close">
						<a href="javascript:;">×</a>
					</div>
					<p class="jointext">成功加入购物车</p>
					<p>
						<a id="close" href="javascript:;" class="button_blue">«继续浏览</a>
						<a href="/managermall/account/toCartConfirm.do" class="button_red">«去购物车结算</a>
					</p>
				</div>
				<div class="tuangoujia">
                	<div class="showshenfen"><a href="/account/login.do"><img src="/images/show.jpg" /></a></div>
					<h1>
						<em style="font-size:18px; width:75px;">飞券价：</em>
						<label style="font-size:24px;" class="fontred">￥<fm:formatNumber  value="${product.unitPrice}" pattern="#,###.##"/></label>&nbsp;&nbsp;<!--消费前秀出你<label style="font-size:24px;" class="fontred">飞券网</label>身份，即可享受<label style="font-size:24px;" class="fontred">飞券价--></label> 
					</h1>
					<!--<h1>
						<em2>您使用金元宝</em2>
						<input id="price" name="price" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  style="width:130px; height:32px;font-size:20px; text-align:right;padding-right: 10px;" onpaste="return false"/>
						兑换<label id="convert1" class="fontred">1</label>张<label id="convert" class="fontred">￥0.00</label>该商品抵用券
						<input type="hidden" id="prodId" value="${product.id}" readonly>
					</h1>
					<div style="margin-top:15px;">
						<a href="javascript:;"><img src="/images/ttu1.png"></a>
						<a href="/managermall/account/rechargeList.do"><img src="/images/ttu3.png"></a>
						<a href="/mallcms/info3.do?id=10"><img src="/images/ttu2.png"></a>
					</div>-->
                    <!--飞券支付-->
                    <!--<div class="zhifudiv">
                    	<ul>
                        	<li id="rtanchu1">
                            	<span class="zhifuimg1"></span>支持飞券部分/全额支付
                                <div class="zhifudiv_tanchu" id="tanchu1" style="display:none;">
                                    <div class="zhifudiv_tanchu_jiantou"></div>
                                    <div class="zhifudiv_tanchucontent">可以使用飞券进行部分或全额支付，部分支付后剩余金额在到店消费时用现金或其他方式进行支付。</div> 	
                                </div>
                            </li>                            
                        	<li class="nocursor"><span class="zhifuimg2"></span>20金元宝 =1飞券=1元</li>
                            <li class="nocursor"><span class="zhifuimg3"></span><a class="fontred" href="/managermall/account/myrecharge.do">金元宝查询</a></li>
                            <li id="rtanchu4">
                            	<span class="zhifuimg4"></span>兑换说明
                            	<div class="zhifudiv_tanchu" id="tanchu4" style="display:none;">
                                    <div class="zhifudiv_tanchu_jiantou"></div> 
                                    <div class="zhifudiv_tanchucontent">成功兑换飞券后会通过手机短信发送串码到您的手机，到店消费时出示串码给店员即可使用。</div> 	
                                </div>
                            </li>                            
                        </ul>
                    </div>-->
				</div>
				<div class="tuangoujia">
					<input id="prodId" type="hidden" value="${product.id}" readonly>
					<input id="maxPrice" type="hidden" value="${product.unitPrice}" readonly>
					<input id="maxPay" type="hidden" value="${product.maxPay}" readonly>
					<input id="stock" type="hidden" value="${product.prodStock}" readonly>
					<h1>
						<em3>有效期限</em3>截至到 <fm:formatDate value="${product.endDate}" pattern="yyyy-MM-dd"/>
					</h1>
					<!--<h1>
						<em>飞券数量</em>
						<div class="goumaishuliang">
							<input id="subtract" type="button" value="-" />
							<input id="prodNum" name="prodNum" class="input1" value="1" onkeyup="this.value=this.value.replace(/[^0-9][^0-9]\D*$/,'');" onpaste="return false"/>
							<input id="add" type="button" value="+" />
						</div>
					</h1>-->
					<h1>
						<div class="goumaigou">
							<c:if test="${product.status==2&&product.enabled==1}">
							<!--<a name="qiang" href="javascript:;" class="qiang">兑换飞券</a>-->
                            <a name="weixinpay" id="weixinpay" href="javascript:;" class="weixinpay">微信支付</a>
                            <div id="weixintanchu" class="weixinpay-tanchu" style="display:none;">
                            	飞券网<br>
                            	<img src="/images/weixinpay.jpg" /><br>
                                关注公众号进行微信支付
                            </div>
                            
							<!--<a name="jiaru" href="javascript:;" class="jiaru">加入购物车</a>-->
							<!-- <a id="yuyue" href="javascript:;" class="yuyue">我要预约</a> -->
							</c:if>
							<c:if test="${product.status==0||product.status==1}">
							<span>对不起，该商品未发布！</span>
							</c:if>
							<c:if test="${product.status==3||product.enabled==0}">
							<span>对不起，该商品已下架！</span>
							</c:if>
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
				<%-- <div class="fuwuchengnuo">
					服务承诺： 
					 <c:if test="${fn:contains(product.promise,'1') }">
	                	<span class="fwcnbg1"></span>随时退换
	                </c:if>
	                 <c:if test="${fn:contains(product.promise,'2') }">
	               		 <span class="fwcnbg2"></span>过期退换
	                </c:if>
	                 <c:if test="${fn:contains(product.promise,'3') }">
	                	<span class="fwcnbg3"></span>支持代金劵
	                </c:if>
	                 <c:if test="${fn:contains(product.promise,'4') }">
	              	 	 <span class="fwcnbg4"></span>接受预定
	                </c:if>
				</div> --%>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="contentall">
		<div class="contentleft">
			<!--商家的其他产品-->
	   		<div class="otherpro">
	  	  		<div class="title">
	                <h4>该商家的其他产品</h4>
	                <h5>飞券价</h5>
	            	<h5>市场价</h5>
	            </div>
	        	<ul>
	        		<c:forEach var="prod" items="${other}">
					<li>
	                	<h1><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></h1>
	                    <h2><fm:formatNumber value="${prod.unitPrice}" type="CURRENCY" pattern="#,###.##"/></h2>
	                    <h3><fm:formatNumber value="${prod.origPrice}" type="CURRENCY" pattern="#,###.##"/></h3>
	                </li>        		
	        		</c:forEach>
	            </ul>
	        </div>
			<div class="detail_alltitle" style="z-index: 999999">
				<!--  class="current" -->
				<a href="javascript:void(0)" onclick="document.getElementById('shangjiaweizhi').scrollIntoView();">商家位置</a><a href="javascript:void(0)" onclick="document.getElementById('goumaixuzhi').scrollIntoView();">使用须知</a><a href="javascript:void(0)" onclick="document.getElementById('bandanxiangqing').scrollIntoView();">本单详情</a><a href="javascript:void(0)" onclick="document.getElementById('xiaofeipingjia').scrollIntoView();">消费评价<span>${review.personNum}</span></a>
			</div> 
			<!--商家位置-->
			<a id="shangjiaweizhi" name="shangjiaweizhi"></a>
			<div class="detail_shangjiaweizhi" >
				<div class="detail_alltitle_title">商家位置</div>
				<div class=""   >
					<div class="mapimg">
						 <iframe  src="/html/web/prodMap.html?longitude=${store.longitude}&latitude=${store.latitude}&stitle=${store.address}&&scontent=${store.name}" width="500px" height="300px" style="border:1px solid #ccc;" name=inner frameborder="0" scrolling=NO></iframe>
					</div>
					<h1><span id="">${store.name}</span></h1>
					<h2>
						<span>评价：</span>
						<c:if test="${empty review.avgScore}">0</c:if>
						<c:if test="${!empty review.avgScore}">	<fm:formatNumber value="${review.avgScore}"  type="currency" pattern="0"/></c:if>
						分
					</h2>
					<p>地址：${store.address}</p>
					<h3 class="padding50">
						<!-- <a href="javascript:;" class="fontred">查看地图</a> -->
						<%-- <a href="http://api.map.baidu.com/direction?origin=latlng:小梅沙|name:我的位置&destination=latlng:${store.longitude},${store.latitude}|name:终点&mode=driving&region=深圳&output=html&src=yourCompanyName|yourAppName" class="fontred">公交/驾车去这里</a> --%>
					<!-- 	<a href="http://api.map.baidu.com/direction?origin=大梅沙|name:小梅沙&destination=联合广场&mode=driving&region=深圳&output=html&src=yourCompanyName|yourAppName" class="fontred"  target="_Blank">公交/驾车去这里</a> -->
						<a href="javascript:diag1('${store.address}');">公交/驾车去这里</a>
				   </h3>
					<p>电话：${store.contactTel }</p>
					<div class="clear"></div>
				</div>
			</div>
			<!--购买须知-->
			<a id="goumaixuzhi" name="goumaixuzhi"></a>
			<div class="detail_goumaixuzhi" >
				<div class="detail_alltitle_title">使用须知</div>
	        	${product.buyingTips}
			</div>
	        <!--本单详情-->
	        <a  id="bandanxiangqing" name="bandanxiangqing"></a>
	        <div class="detail_bandanxiangqing">
	        	<div class="detail_alltitle_title">本单详情</div>
				${product.prodDetail}
	        </div>
	        <!--商家介绍-->
	        <div class="detail_bandanxiangqing">
	        	<div class="detail_alltitle_title">商家介绍</div>
	        	${store.storeDetail}
	        </div>
			<!--消费评价-->
			   <a  id="xiaofeipingjia" name="xiaofeipingjia"></a>
			<div class="detail_xiaofeipingjia" >
				<div class="detail_alltitle_title">消费评价</div>
				<!--评价top-->
				<div class="pingjia_top">
					<div class="pingjiafen">
						<h1>
							<b>
							<c:if test="${empty  review.avgScore}">0</c:if>
							<c:if test="${!empty  review.avgScore}">
							<fm:formatNumber value="${review.avgScore}"  type="currency" pattern="0"/>
							</c:if>
							<i>分</i>
							</b>
							<span><em style="width:${review.avgScore*20}%;"></em></span>  
							<div class="clear"></div>
						</h1>
						<p>共有${review.personNum}人评价</p>
					</div>
					<div class="haozhongchaping">
						<p>
							<em>好评</em>
							<div class="pingjia_jindu">
								<h2 style="width:${review.praiseNum*100/review.personNum}%"></h2>
								<h1></h1>
							</div>
							<i>
							<c:if test="${empty review.praiseNum}">0</c:if>
							<c:if test="${!empty review.praiseNum}">
							${review.praiseNum}
							</c:if>
							条</i>
							<div class="clear"></div>
						</p>
						<p>
							<em>中评</em>
							<div class="pingjia_jindu">
								<h2 style="width:${review.generalNum*100/review.personNum}%"></h2>
								<h1></h1>
							</div>
							<i>
							<c:if test="${empty review.generalNum}">0</c:if>
							<c:if test="${!empty review.generalNum}">
							${review.generalNum}
							</c:if>
							条</i>
							<div class="clear"></div>
						</p>
						<p>
							<em>差评</em>
							<div class="pingjia_jindu">
								<h2 style="width:${review.badNum*100/review.personNum}%"></h2>
								<h1></h1>
							</div>
							<i>
							<c:if test="${empty review.badNum}">0</c:if>
							<c:if test="${!empty review.badNum}">
							${review.badNum}
							</c:if>
							条</i>
							<div class="clear"></div>
						</p>
					</div>
					<%-- <c:if test="${not empty marked}"> --%>
					<div class="woyaopingjia">
					<p>我在飞券网买过此商品</p>
					<a href="/managermall/account/evaluate/toAddPage.do?proId=${product.id }&id=${evaNull.detailId}">我要评价</a>
						<!--<c:if test="${!empty shop }">
							<p>我在飞券网买过此商品</p>
							<c:if test="${!empty evaNull }">
								<a href="/managermall/account/evaluate/toAddPage.do?id=${evaNull.detailId}">我要评价</a>
							</c:if>
							<c:if test="${empty evaNull }">
								您已评论过，欢迎下次购买！
							</c:if>
						</c:if>
						<c:if test="${empty shop }">
								<p>您还未购买过此商品，不能评价</p>
								<div class="qianggou"><a name="goTop" href="javascript:;">立即兑换</a></div>
						</c:if>-->
					</div>
					<%-- </c:if> --%>
					<div class="clear"></div>
				</div>
				<!--评价内容-->
				<div class="pingjia_nr">
					<div class="title">
						<a href="javascript:;" class="linkcurrent"><input name="level" type="radio" value="1" checked="checked" />全部</a>
						<a href="javascript:;" class="linkcurrent"><input name="level" type="radio" value="2"/>好评</a>
						<a href="javascript:;" class="linkcurrent"><input name="level" type="radio" value="3"/>中评</a>
						<a href="javascript:;" class="linkcurrent"><input name="level" type="radio" value="4" />差评</a>
						<a href="javascript:;" class="linkcurrent"><input name="level" type="radio" value="5" />图片</a>
					</div>
					<ul id="reviews">
	                </ul>
				</div>
			</div>
			<div class="jiarugouwuche">
				<ul>
					<li>
						<p class="jiage" style="width:100%;display:inline-block;">
							<strong>折扣价</strong>
						</p>
						<p class="jiage">
							<span><fm:formatNumber value="${product.unitPrice}" type="CURRENCY" pattern="#,###.##"/></span>
						</p>
					</li>
					<li>
						<p>
							<strong>市场价</strong>
						</p>
						<p>
							<span><fm:formatNumber value="${product.origPrice}" type="currency" pattern="#,###.##"/></span>
						</p>
					</li>
					<li>
						<p>
							<strong>评价</strong>
						</p>
						<p>
							<c:if test="${empty product.scoreAvg}">0</c:if>
							<c:if test="${!empty product.scoreAvg}">${review.avgScore}</c:if>分
						</p>
					</li>
					<div class="clear"></div>
					<div class="qianggou"><a name="goTop" href="javascript:;">去买单</a></div>
				</ul>
			</div>
		</div>
		<!--右侧-s-->
		<div class="contentright">
			<!--右侧广告-->
			<div class="right_adv">
				<a href="${ad1.link == null?'#':ad1.link }"><img src="${ad1.image == null?'images/list_right_adv.jpg':ad1.image }" /></a> <!-- <a href="${ad2.link == null?'#':ad2.link }"><img src="${ad2.image == null?'images/list_right_appdown.jpg':ad2.image }" /></a> -->
			</div>
			<!--右侧推荐-->
			<div class="right_tuijian">
				<h1>
					<span><a href="javascript:window.location.reload();">换一批</a></span>看了又看
				</h1>
				<ul>
				<c:forEach var="i" items="${prodCate}">
					<li>
						<a href="/getProdDetail.do?prodId=${i.id}"><img src="${fn:substring(i.images,0, fn:indexOf(i.images,'|'))}" alt="飞券、飞券网、${i.storeName}" title="飞券、飞券网、${i.storeName}" /></a>
						<p><a href="/getProdDetail.do?prodId=${i.id}">${i.name}</a></p>
						<b>
							<em><fm:formatNumber value="${i.unitPrice}" type="currency" pattern="#,###.##"/></em>
							<i><fm:formatNumber value="${i.origPrice}" type="currency" pattern="#,###.##"/></i>
						</b>
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
<script>
document.getElementById('weixinpay').onmouseover=function(){document.getElementById('weixintanchu').style.display='block'}
document.getElementById('weixinpay').onmouseleave=function(){document.getElementById('weixintanchu').style.display='none'}
</script> 
</body>
</html>