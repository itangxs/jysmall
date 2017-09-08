<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{
	return ;
}  
%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<!-- UC强制全屏 -->
<meta name="full-screen" content="yes">
<!-- QQ强制全屏 -->
<meta name="x5-fullscreen" content="true">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<title>卡包</title>
<link href="/css/weixin/cardcoupon/card-holder.css" type="text/css" rel="stylesheet" />

<link href="/scroll/dropload.css" type="text/css" rel="stylesheet" />
<script src="/scroll/dropload.js" type="text/javascript"></script>
</head>
<body>
<div class="kabao-nav">
	<ul>
    	<li id="all" class="kabao-all nav-bg"><a>全部</a></li>
    	<li id="lpq" class="kabao-lpq"><a>礼品券</a></li>
        <li id="djq" class="kabao-djq"><a>代金券</a></li>
        <li id="zkq" class="kabao-zkq"><a>折扣券</a></li>
        <div class="clearfix"></div>
    </ul>
</div>
<div id = "scroll">
<div id = "content">
</div>
</div>
<script>
	var pageNum=1;
	var pageSize=15;
	$(function(){
		var itemIndex = 0;
		var allLoadEnd = false;
		var lpqLoadEnd = false;
		var djqLoadEnd = false;
		var zkqLoadEnd = false;
	    $('.kabao-nav li').on('click',function(){
	    	var id = $(this).attr("id");
	    	if(id=="all"){
	    		itemIndex = 0;
	    		$(".kabao-all").addClass("nav-bg");
				$(".kabao-djq").removeClass("nav-bg");
				$(".kabao-zkq").removeClass("nav-bg");
				$(".kabao-lpq").removeClass("nav-bg");
				pageNum =1; 
				$("#content").html("");
				if(!allLoadEnd){
		                // 解锁
		                dropload.unlock();
		                dropload.noData(false);
		        }else{
		                // 锁定
		                dropload.lock('down');
		                dropload.noData();
		       }
	    	}else if(id=="lpq"){
	    		itemIndex = 1;
				$(".kabao-lpq").addClass("nav-bg");
	    		$(".kabao-all").removeClass("nav-bg");
				$(".kabao-djq").removeClass("nav-bg");
				$(".kabao-zkq").removeClass("nav-bg");
				pageNum = 1; 
				$("#content").html("");
				if(!lpqLoadEnd){
	                // 解锁
	                dropload.unlock();
	                dropload.noData(false);
		        }else{
		                // 锁定
		                dropload.lock('down');
		                dropload.noData();
		        }
	    	}else if(id=="djq"){
	    		itemIndex = 2;
	    		$(".kabao-djq").addClass("nav-bg");
				$(".kabao-all").removeClass("nav-bg");
				$(".kabao-zkq").removeClass("nav-bg");
				$(".kabao-lpq").removeClass("nav-bg");		
				pageNum = 1; 
				$("#content").html("");
				if(!djqLoadEnd){
	                // 解锁
	                dropload.unlock();
	                dropload.noData(false);
		        }else{
		                // 锁定
		                dropload.lock('down');
		                dropload.noData();
		        }
	    	}else if(id=="zkq"){
	    		itemIndex = 3;
	    		$(".kabao-zkq").addClass("nav-bg");
				$(".kabao-all").removeClass("nav-bg");
				$(".kabao-djq").removeClass("nav-bg");
				$(".kabao-lpq").removeClass("nav-bg");
				pageNum = 1; 
				$("#content").html("");
				if(!zkqLoadEnd){
	                // 解锁
	                dropload.unlock();
	                dropload.noData(false);
		        }else{
		                // 锁定
		                dropload.lock('down');
		                dropload.noData();
		        }
	    	}
	    	 // 重置
	        dropload.resetload();
	    });
	    

	    // dropload
	  var dropload = $('#scroll').dropload({
	        scrollArea : window,
	        domDown : {
	            domClass   : 'dropload-down',
	            domRefresh : '<div class="dropload-refresh">上拉加载更多..</div>',
	            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
	            domNoData  : '<div class="dropload-noData">到底啦...</div>'
	        },
	        loadDownFn : function(me){
	            if(itemIndex == '0'){
	            	getData(me,null);
	            }else if(itemIndex == '1'){
	            	getData(me,0);
	       	    }else if(itemIndex == '2'){
	       	    	getData(me,1);
	       	    }else if(itemIndex == '3'){
	       	 		getData(me,2);
	       	    }
	        }
	    });
	    function getData(me,couponCfg){
	    	$.ajax({
                type: 'GET',
                url: '/wxMall/getMyCardCoupon.do',
                data: {pageNum:pageNum,pageSize:pageSize,couponCfg:couponCfg},
                dataType: 'json',
                success: function(obj){
                	 if(obj.flag==0){
                		var list   = obj.data.list;
                		var isHave = obj.data.ishasmore;
                		if(list==null||list.length<=0){
                			if(pageNum==1){
                				$("#content").html("<div class=\"no_care_tips\"><img src=\"/images/weixin/cardcoupon/no_care_tips.png\"></div>");
                				 // 锁定
                                me.lock();
                                me.noData();
                                me.resetload(false);
                			}else{
                				 // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                                me.resetload();
                			}
                		}else{
                			 var result = '';
                			for(var i = 0; i < list.length; i++){
                    	  	    result+="<div class=\"card\"> ";
                    	    	var card = list[i];
                    	    	var now = new Date().getTime();
                    	    	if(card.statusCfg==0 && card.validityEndtime>now){ //未使用未过期的
                    	    		result+="<a href=\"/wxMall/queryCardCouponDetail.do?cardCouponId="+card.id+"\">";
                    	    	}else{
                    	    		result+="<a>";
                    	    	}
                    	    	if(card.couponCfg==0){
                    	    		if(card.statusCfg==0 && card.validityEndtime>now){
                    	    			result+="<img src=\"/images/weixin/cardcoupon/lpq_bg.png\">";
                    	    		}else{
                    	    			result+="<img src=\"/images/weixin/cardcoupon/lpq_hui.png\">";
                    	    		}
                    	    	}else if(card.couponCfg==1){
                    	    		if(card.statusCfg==0 && card.validityEndtime>now){
                    	    			result+="<img src=\"/images/weixin/cardcoupon/djq_bg.png\">";
                    	    		}else{
                    	    			result+="<img src=\"/images/weixin/cardcoupon/djq_hui.png\">";
                    	    		}
                    	    	}else if(card.couponCfg==2){
                    	    		if(card.statusCfg==0 && card.validityEndtime>now){
                    	    			result+="<img src=\"/images/weixin/cardcoupon/zkq_bg.png\">";
                    	    		}else{
                    	    			result+="<img src=\"/images/weixin/cardcoupon/zkq_hui.png\">";
                    	    		}
                    	    	}
                    	    	result+="<div class=\"coupon-name\"><p>"+card.name+"</p></div>";
                    	    	result+=" <p class=\"store-name\">"+card.storeName+"</p>";
                    	    	result+="<div class=\"deadline\"><p class=\"right-float\">"+formatDate(card.validityEndtime)+"前有效</p><div class=\"clearfix\"></div></div>";
                    	    	if(card.statusCfg==1){
                    	    		result+="<img class=\"kq_ysy\" src=\"/images/weixin/cardcoupon/kq_ysy.png\">";
                    	    	}else{
                    	    		if(now > card.validityEndtime){
                    	    			result+="<img class=\"kq_ygq\" src=\"/images/weixin/cardcoupon/kq_ygq.png\">";
                    	    		}
                    	    	}
                    	    	result+="</a></div>";
                    	    }
                			if(isHave==true||isHave=="true"){
                    			pageNum = pageNum+1;
                    		}else{
                    			 // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                    		}
                			$('#content').append(result);
                		    me.resetload();
                		}
                	}
                },
                error: function(xhr, type){
                    alert('请求网络失败!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            }); 
	    }
	});
	
	function formatDate(timestamp) {
		var now = new Date(timestamp);
		var year = now.getFullYear(),
		month = now.getMonth() + 1,
		date = now.getDate()
		/* hour = now.getHours(),
		minute = now.getMinutes(),
		second = now.getSeconds(); */
		return year + "/" + month + "/" + date;
	}
</script>
</body>
</html>
