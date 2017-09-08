<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="rAOAlrO1Us" /> 
<meta property="qc:admins" content="24217701266213514463757" />
<link rel="stylesheet" type="text/css"  href="/css/index2.css"/>
<link rel="stylesheet" type="text/css"  href="/css/public.css"/>
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/jquery.superslide.2.1.1.banner.js"></script>
<script src="/js/index_lb.js" type="text/javascript"></script>

<title>飞券网（jysmall.com）-比团购更划算的积分通兑、优惠消费商城平台。做任务！赢积分！换优惠!</title>
<meta name="keywords" content="飞券、飞券网、网赚平台、任务平台、免费兑换、免费兑换平台、O2O商城、积分兑换平台、免费平台、深圳免费兑换平台、佛山免费兑换平台、网上赚钱、网赚、返利平台、优惠券、购物、网上购物、Free、Free Change" />
<meta name="description" content="飞券网（www.jysmall.com）是基于互联网与移动互联网，为商户和用户提供服务信息发布与交易达成，使用户随时随地通过完成商户所发布的有趣、简单任务，累积奖励免费兑换各类商户优惠的新型互动生活服务类O2O（Online to Offline）商城平台。通过它您不仅能享受积分返利，尊享积分兑换、理财等会员权益，同时还能提供O2O模式服务，包括美食、电影、旅游、休闲娱乐、丽人、生活服务等线上购物及线下消费体验。" />

</head>
<body class="indexbg">
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="dtlay_main">
  <div class="dtlay_left">
  	<div class="dtlay_ban">
    	
    	<!--左侧菜单-s-->
    <div class="indexleft_menu">
        <ul id="menu">
	        <c:forEach var="node" items="${category}">
	        <li lang="${node.id}">
	        	<h2
	        	  <c:if test="${node.id ==1}"> class="shipin"</c:if>
	        	  <c:if test="${node.id ==2}"> class="dianying"</c:if>
	        	  <c:if test="${node.id ==3}"> class="gouwu"</c:if>
	        	  <c:if test="${node.id ==4}"> class="lvyou"</c:if>
	        	  <c:if test="${node.id ==5}"> class="xiuxianyule"</c:if>
	        	  <c:if test="${node.id ==6}"> class="liren"</c:if>
	        	  <c:if test="${node.id ==7}"> class="shenghuofuwu"</c:if>
	        	></h2>
	        	<div class="memunav">
	        		<p class="menu2"><a href="searchProduct.do?type=sort&category=${node.id}">${node.text}</a></p>
	        		<p class="menu1">
		        		<c:forEach var="it" items="${node.childs}">
		        		<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a> 
		        		</c:forEach>
	        		</p>
	        	</div>
	        </li>
	        </c:forEach>
        </ul>
    </div>
    <!--左侧菜单-e-->
    <!--左侧菜单弹出窗口-s-->
    <div id="menu_pop" class="" style="display: none">
		<c:forEach var="node" items="${category}">
		<div lang="${node.id}" class="bannShow">
			
    	<div class="indexleftmenu_tanchu" lang="${node.id}"  id="${node.id }menu" style="display:none">
    	<dl><dd>
    		<c:forEach var="it" items="${node.childs}" varStatus="status">
    						
                    	<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
                    	<c:if test="${status.count%12 == 0 }"></dd>
          											  <dd></c:if>
                    </c:forEach>
        	</dd>
        </dl>
    	</div>
		</div>
		 </c:forEach>
	</div>
    
    
 
    <!--左侧菜单弹出窗口-e-->
        <!-- banner代码 开始 -->
	<div class="indexbanner">
        <ul class="bannerpic">
        <c:forEach items="${ads1 }" var="ad">
         	<li><a href="${ad.link }" target="_blank"><img src="${ad.image }" alt="飞券、飞券网、"/></a></li>
        </c:forEach>
           
        </ul>
        <a class="prev" href="javascript:;"></a>
        <a class="next" href="javascript:;"></a>
        <div class="num">
            <ul></ul>
        </div>
    </div>
    <script>
    $(".indexbanner").hover(function(){
        $(this).find(".prev,.next").fadeTo("show",0.1);
    },function(){
        $(this).find(".prev,.next").hide();
    })
    $(".prev,.next").hover(function(){
        $(this).fadeTo("show",0.7);
    },function(){
        $(this).fadeTo("show",0.1);
    })
    $(".indexbanner").slide({ titCell:".num ul" , mainCell:".bannerpic" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
    </script>
    
        <div class="dtlay_adv3">
        <c:forEach items="${ads2 }" var="ad" varStatus="sta">
         	<a href="${ad.link }" target="_blank"><img src="${ad.image }" width="210" style="width: 210pxl;" alt="飞券、飞券网、"/></a>
        </c:forEach>
        </div>
    </div>
    <div class="dtlay_srceen">
    <div class="jiantoubefore"></div>
    <div class="jiantouafter"></div>
   	 <div class="picbox">
 	<ul class="piclist mainlist">
			<c:forEach var="prod" items="${plist}">
    		<li title="${prod.title}">
            	<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images, 0, fn:indexOf(prod.images, '|'))}" alt="飞券、飞券网、${prod.storeName}" title="飞券、飞券网、${prod.storeName}"/></a>
            	<h1><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></h1>
                <h2><a href="/getProdDetail.do?prodId=${prod.id}">${prod.title}</a></h2>
                <h3>￥
                	<em><fm:formatNumber  value="${prod.unitPrice}" pattern="#,###.##"/></em>
                	<span><a href="/getProdDetail.do?prodId=${prod.id}">去买单</a></span>
                	<!-- <span><fm:formatNumber value="${prod.origPrice}" type="currency"/></span> 
                	<a href="/getProdDetail.do?prodId=${prod.id}">.......................</a>-->
                </h3>
            </li>
    		</c:forEach>
        </ul>
        <ul class="piclist swaplist"></ul> 
    </div>
    </div>
  </div>

    <!--中通右侧-s-->
    <div class="dtlay_right">
    	<!--会员登陆-s-->
    	<div class="indexlogin">
    	<c:if test="${!empty sessionScope.user}">
        	<h1><span></span><a href="/account/task/mytask.do?mstatus=11"><strong>我的账户</strong></a></h1>
            <!--已登陆-->
    		<div class="indexlogin1">
            	<a href="/account/task/mytask.do?mstatus=11"><img class="img1" src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}"/></a>
                <div class="login1">
                	<a href="/account/task/mytask.do?mstatus=11">
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  != 'JYS' && sessionScope.user.nickname != ''}">${sessionScope.user.nickname }</c:if>
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  == 'JYS' || sessionScope.user.nickname == ''}">
							${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}
						</c:if>
					</a><em>
					<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if></em><br />
                    现有元宝：<i><fm:formatNumber value="${sessionScope.userCashAccount.balance}"/></i><br />
                    冻结元宝：<fm:formatNumber value="${sessionScope.userCashAccount.freezeMoney}"/>        
                </div>
                <a href="/account/task/mytask.do?mstatus=11"><input class="anniu1" id="" name="" type="submit" value="我的账户" /></a>
            	<c:if test="${!signIn}"><input class="anniu" id="signIn" name="" type="submit" value="今日签到" /></c:if>
                <c:if test="${signIn}"><input class="anniuafter"  name="" type="submit" value="已签到" /></c:if>
            </div>  
    	</c:if>
    	<c:if test="${empty sessionScope.user}">
    	<h1><span></span>会员登录</h1>
        	<!--未登陆-->
            <div class="indexlogin1">  
            	<form id="login" action="" method="get">
                    <input id="username" name="username" type="text" value="" class="inputtext inputbg1" />
                    <input id="password" name="password" type="password" value=""  class="inputtext inputbg2" />
                    <p><a href="/account/registrationByTel.do">免费注册</a> <span>|</span> <a href="/account/forgetpassword.do" class="fontred">忘记密码?</a> <span>|</span> <a href="/qqLogin.do"><img style="width:16px;height:16px; margin:0 0 2px 5px; display:inline-block; vertical-align:middle;" src="/images/Connect_logo_1.png"/>登陆</a></p>
                    <input class="anniu1" name="" type="submit" value="登 陆" />                
                </form>   
            </div>
    	</c:if>
                        	
        </div>
        <!--我的任务-s-->
        <!--<div><img src="images/tasttitle.png"/></div>
    	<div class="mytask_index">        	
        	<div class="mytask_indexlist">
        	  
            	<h2><span><a href="/account/task/index.do">查看</a></span><i class="bg1"></i>最新任务</h2>
            	<c:if test="${!empty sessionScope.user}"><c:forEach var="it"  items="${npage}">
            	<p><span><i>${it.fulfilReward }</i>元宝</span>
            		<a href="/account/task/dotask.do?project=${it.project }&userId=${sessionScope.user.id}&ispc=1" target="_blank">
            		<c:if test="${it.taskName.length()>11 }">${fn:substring(it.taskName,0,10) }...</c:if>
            		<c:if test="${it.taskName.length()<=11 }">${it.taskName }</c:if>
            		</a>
            		</p></c:forEach>
           		</c:if> <c:if test="${empty sessionScope.user}">
                <div class="loginbefore"><span></span>你还未登陆，<a href="/account/login.do" class="fontred">登录</a>查看！</div></c:if>
            </div>
            <div class="mytask_indexlist">
            	<h2><span><a href="/account/task/mytask.do?mstatus=11">查看</a></span><i class="bg2"></i>已审核任务</h2>
                <!--登录后显示-->
                <!--<c:if test="${!empty sessionScope.user}">
                <div>
                <c:forEach var="it"  items="${upage}">
            	<p><span><i>${it.totamt }</i>元宝</span><c:if test="${it.taskName.length()>11 }">${fn:substring(it.taskName,0,10) }...</c:if>
            		<c:if test="${it.taskName.length()<=11 }">${it.taskName }</c:if></p></c:forEach>
                </div>
                </c:if>
                <!--登录前显示-->
                <!--<c:if test="${empty sessionScope.user}">
                <div class="loginbefore"><span></span>你还未登陆，<a href="/account/login.do" class="fontred">登录</a>查看！</div></c:if>
          </div>
            <div class="mytask_indexlist">
            	<h2><span><a href="/account/task/mytask.do?mstatus=12">查看</a></span><i class="bg3"></i>审核中任务</h2>
            	<!--登录后显示-->
            	<!--<c:if test="${!empty sessionScope.user}">
                <div>
            	<c:forEach var="it"  items="${unpage}">
            	<p><span><i>${it.fulfilReward }</i>元宝</span><c:if test="${it.taskName.length()>11 }">${fn:substring(it.taskName,0,10) }...</c:if>
            		<c:if test="${it.taskName.length()<=11 }">${it.taskName }</c:if></p></c:forEach>
                </div></c:if>
                <!--登录前显示-->
                <!--<c:if test="${empty sessionScope.user}">
                <div class="loginbefore"><span></span>你还未登陆，<a href="/account/login.do" class="fontred">登录</a>查看！</div></c:if>
            </div>
        </div>-->
    </div>
    <div class="clear"></div>
</div>
<!---e-->
<div class="banner_tl">
         	<a href="${ads3.link }" target="_blank"><img src="${ads3.image }"  alt="飞券、飞券网、"/></a>
        </div>

<!--推荐类别-s-->
<div class="index_type">	
    <!--美食类别-->
    
    <c:forEach var="node" items="${category}">
     <div class="title"><b><i><a href="searchProduct.do?type=sort&category=${node.id}">更多</a> ></i><a href="searchProduct.do?type=sort&category=${node.id}">${node.text}</a></b></div>
    <div class="index_type_list">    	
    	<div class="adv">
        	<span>
        	<c:forEach var="it" begin="0" end="5" items="${node.childs}">
				<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
			</c:forEach></span>
          <c:if test="${node.id ==1}"> <img src="${img.pcMenuMeis}" /></c:if>
      	  <c:if test="${node.id ==2}"> <img src="${img.pcMenuDiany}" /></c:if>
      	  <c:if test="${node.id ==3}"> <img src="${img.pcMenuGouw}" /></c:if>
      	  <c:if test="${node.id ==4}"> <img src="${img.pcMenuLvyou}" /></c:if>
      	  <c:if test="${node.id ==5}"> <img src="${img.pcMenuXiux}" /></c:if>
      	  <c:if test="${node.id ==6}"> <img src="${img.pcMenuLiren}" /></c:if>
      	  <c:if test="${node.id ==7}"> <img src="${img.pcMenuShengh}" /></c:if>
      </div>
    	<ul>
    	<c:forEach var="prod" items="${product[node.text]}">
    		<li title="${prod.title}">
            	<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images, 0, fn:indexOf(prod.images, '|'))}"  alt="飞券、飞券网、${prod.storeName}" title="飞券、飞券网、${prod.storeName}"/></a>
            	<h1><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></h1>
                <h2><a href="/getProdDetail.do?prodId=${prod.id}">${prod.title}</a></h2>
                <h3>￥
                	<em><fm:formatNumber  value="${prod.unitPrice}" pattern="#,###.##"/></em>
                	<span><a href="/getProdDetail.do?prodId=${prod.id}">去买单</a></span>
                	<%-- <span><fm:formatNumber value="${prod.origPrice}" type="currency"/></span> 
                	<a href="/getProdDetail.do?prodId=${prod.id}">.......................</a>--%>
                </h3>
            </li>
    	</c:forEach>
        	
            <div class="clear"></div>
        </ul>
    </div>
    </c:forEach>
   
</div>
<!--推荐类别-e-->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/index.js"></script>
<script type="text/javascript">
	/**$(function(){
		$(".menuul li").mouseover(function(){
			var liid = this.id;
			$("#"+liid+"menu").show();
		}).mouseout(function(){
			alert(111)
			var liid = this.id;
			var a= $("#"+liid+"menu").mouseout()
		})
	})*/
</script>
</body>
</html>