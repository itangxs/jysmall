<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<!----------------左侧----------------------->
	<div class="memberleft">
    	<!--左侧菜单-->
   	  <div class="membermenu">
        	<ul>
            	<li>
                	<h1>APP推送消息</h1>
                	<p><a href="/managermall/systemmall/message/addPush.do" class="${param.position eq '2'?'menucurrent2':''}"><span>·</span>APP推送</a>
                    <p><a href="/managermall/systemmall/message/pushPage.do" class="${param.position eq '1'?'menucurrent2':''}"><span>·</span>推送历史</a></p> 	 
                </li>      
            	<!-- <li>
                	<h1>站内消息</h1>
                	<p><a href="/managermall/systemmall/message/addMessage.do" class="${param.position eq '2'?'menucurrent2':''}"><span>·</span>发送消息</a>
                    <p><a href="/managermall/systemmall/message/messagePage.do" class="${param.position eq '1'?'menucurrent2':''}"><span>·</span>发送消息历史</a></p> 	 
                </li>       -->
            	<li>
                	<h1>广告管理</h1>
                	<p><a href="/managermall/systemmall/ad/list.do" class="${param.position eq '3'?'menucurrent2':''}"><span>·</span>广告列表</a>
                    <p><a href="/managermall/systemmall/ad/goSave.do" class="${param.position eq '4'?'menucurrent2':''}"><span>·</span>添加广告</a></p> 	 
                </li> 
                <li>
                	<h1>营销账户管理</h1>
                	<p><a href="/managermall/systemmall/integral/goRecharge.do" class="${param.position eq '21'?'menucurrent2':''}"><span>·</span>营销账户充值</a>
                    <p><a href="/managermall/systemmall/integral/list.do" class="${param.position eq '22'?'menucurrent2':''}"><span>·</span>账户充值列表</a></p> 	 
                </li> 
                <li>
                	<h1>红包活动</h1>
                	<p><a href="/managermall/systemmall/ad/hongbao_create.do" class="${param.position eq '7'?'menucurrent2':''}"><span>·</span>新建红包活动</a>
                    <p><a href="/managermall/systemmall/ad/hongbao_list.do" class="${param.position eq '6'?'menucurrent2':''}"><span>·</span>红包活动列表</a></p> 	 
                </li>
                <li>
                	<h1>商家卡券管理</h1>
                    <p><a href="/managermall/systemmall/cardcoupon/card_list.do" class="${param.position eq '10'?'menucurrent2':''}"><span>·</span>卡券列表</a>
                    <p><a href="/managermall/systemmall/cardcoupon/kaquanToufang.do" class="${param.position eq '11'?'menucurrent2':''}"><span>·</span>投放设定</a></p> 
                	<p><a href="/managermall/systemmall/cardcoupon/kaquanData_list.do" class="${param.position eq '8'?'menucurrent2':''}"><span>·</span>核销与统计</a>
	           </li> 
                <li>
                	<h1>商家服务申请</h1>
                	<p><a href="/managermall/systemmall/apply/list.do" class="${param.position eq '31'?'menucurrent2':''}"><span>·</span>商家服务申请</a>
                	<p><a href="/managermall/systemmall/apply/wxMessageList.do" class="${param.position eq '32'?'menucurrent2':''}"><span>·</span>商家群发管理消息</a>
                </li>         
            	<li>
                	<h1>意见反馈</h1>
                	<p><a href="/managermall/systemmall/feedback/feedbackPage.do" class="${param.position eq '5'?'menucurrent2':''}"><span>·</span>反馈列表</a>
                </li>      
           </ul>       		           
        </div>
    </div>
</body>
</html>