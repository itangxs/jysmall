<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网</title>
<meta name="keywords" content="飞券、飞券网、Free、Free Change、免费兑换、网上购物、网赚平台、网络兼职、积分购物、免费购物、金元宝、积分兑换、积分商城、金钥匙商城、理财购物、本地生活服务、O2O商城 " />
<meta name="description" content="飞券网（www.jysmall.com）是基于互联网与移动互联网，为商户和用户提供服务信息发布与交易达成，使用户随时随地通过完成商户所发布的有趣、简单任务，累积奖励免费兑换各类商户优惠的新型互动生活服务类O2O（Online to Offline）商城平台。通过它您不仅能享受积分返利，尊享积分兑换、理财等会员权益，同时还能提供O2O模式服务，包括美食、电影、旅游、休闲娱乐、丽人、生活服务等线上购物及线下消费体验。" />
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<div class="memberleft"><!--左侧菜单-->
<div class="membermenu">
        	<ul>
            	<li>
                	<h1><span class="fontred">法律申明</span></h1>
                    <p>
                    <a href="/mallcms/info4.do?id=6">法律声明</a>
                    </p>
                </li>    
            </ul>       		           
        </div>
        
    </div>
    <!--------------右侧------------------>
	<div class="memberright">
   	  <div class="memberright_title" id="info001">${cms.name}</div>
   	  <div class="member_myorder">${cms.content }</div>
    </div>
    
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
