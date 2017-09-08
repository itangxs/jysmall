<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/css/member.css" />
<script type="text/javascript">
$(function() {
	$('#signIn').on('click', function() {
		var $el = $(this).attr('disabled', "true");
		$.ajax({
			async : true,
			type : "POST",
			url : "/signIn.do",
			success : function(data) {
				if (data == 'logOut')
					alert("用户登录超时！");
				else if (data == false) {
					alert("用户已签到！");
					return;
				} else if (data == true)
					;
				else
					alert("用户签到异常，请稍后重试！");
				window.location.reload(true);
			}
		});
	});
});
</script>
</head>
<body>
	
	<div class="memberleft"><!--左侧菜单-->
		<div class="membermenu">
        	<ul>
            	<li><h1><a href="/account/task/mytask.do?mstatus=11">账户总览</a></h1></li>
   		    	<li>
                	<h1><a href="/managermall/account/order/myOrderForm.do">我的飞券</a></h1>
                    <div style="">
                    	<p><a href="/managermall/account/order/myOrderForm.do"  class="${param.position eq '1'?'menucurrent':''}"><span>·</span>我的飞券</a></p> 
                   	  <p><a href="/managermall/account/preordain/list.do" class="${param.position eq '2'?'menucurrent':''}"><span>·</span>我的优惠券</a></p>                    
                   	  <p><a href="/managermall/account/order/myCollet.do" class="${param.position eq '3'?'menucurrent':''}"><span>·</span>我的收藏</a></p> 
                    </div>                    
                </li>
                <li>
                	<h1><a href="/account/task/mytask.do?mstatus=11">我的任务</a></h1>
                    <div style="display:;">
                        <p><a href="/account/task/index.do"  class="${param.position eq '16'?'menucurrent':''}"><span>·</span>最新任务</a></p>
                        <p><a href="/account/task/mytask.do?mstatus=11"  class="${param.position eq '11'?'menucurrent':''}"><span>·</span>完成任务</a></p>
                        <p><a href="/account/task/mytask.do?mstatus=12" class="${param.position eq '12'?'menucurrent':''}"><span>·</span>审核中任务</a></p>                    
                        <p><a href="/account/task/mytask.do?mstatus=13" class="${param.position eq '13'?'menucurrent':''}"><span>·</span>未通过审核任务</a></p>
                    </div>                	 
                </li>
                <li>
                	<h1><a href="/managermall/account/myrecharge.do">金元宝记录</a></h1>
                    <div style="display:;">
                    	<p><a href="/managermall/account/myrecharge.do"  class="${param.position eq '14'?'menucurrent':''}"><span>·</span>金元宝获取记录</a></p>
                    	<p><a href="/managermall/account/myexchange.do" class="${param.position eq '15'?'menucurrent':''}"><span>·</span>金元宝兑换记录</a></p> 
                    </div>                	
                </li>
            	<li>
                	<h1><a href="/managermall/account/evaluate/list.do">我的评价</a></h1>
                    <div style="display:;">
                   	  <p><a href="/managermall/account/evaluate/list.do" class="${param.position eq '4'?'menucurrent':''}"><span>·</span>商品评价</a></p>
                    </div>                    
                </li>
                <li>
                	<h1><a href="/managermall/account/message/zlist.do">我的消息</a></h1>
                    <div style="display:;">
                   	  <p><a href="/managermall/account/message/zlist.do" class="${param.position eq '5'?'menucurrent':''}"><span>·</span>站内消息</a></p>                    
                    </div>
                </li>
                <li>
                	<h1><a href="/managermall/account/setupAccount.do">我的账户</a></h1>
                    <div style="display:;">
                      <p><a href="/managermall/account/setupAccount.do" class="${param.position eq '9'?'menucurrent':''}"><span>·</span>账户设置</a></p>
                      <!-- <p><a href="/managermall/account/myThirdAccount.do" class="${param.position eq '10'?'menucurrent':''}"><span>·</span>关联账户</a></p>  -->
                    </div>
                    
              </li>
            </ul>       		           
        </div>
        
    </div>
</body>
</html>