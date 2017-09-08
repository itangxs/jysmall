<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="memberleft">
    	<!--左侧菜单-->
   	  <div class="membermenu">
        	<ul>
            	<li>
                	<h1>财务管理</h1>
                	<p><a href="/managermall/systemmall/finance/userList.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>会员充值</a></p>
                	<p><a href="/managermall/systemmall/finance/userAccountRecordList.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>用户资金操作日志</a></p>
                	<p><a href="/managermall/systemmall/finance/sellerAccountRecordList.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>商家资金操作日志</a></p>
                	<!-- <p><a href="/managermall/systemmall/finance/userWithdrawsRecordList.do" class="${param.val eq '4'?'menucurrent2':''}"><span>·</span>会员提现</a></p> -->
                	<p><a href="/managermall/systemmall/finance/sellerWithdrawsRecordList.do?status=2" class="${param.val eq '5'?'menucurrent2':''}"><span>·</span>商家提现</a></p>
                    <p><a href=""><span>·</span>民生提现报表</a></p>
                </li>
                <li>
                	<h1>金融支持</h1>
                	<p><a href="/managermall/systemmall/finance/queryByStoreId.do" class="${param.val eq '6'?'menucurrent2':''}"><span>·</span>新增项目</a></p>
                	<p><a href="/managermall/systemmall/finance/financelist.do" class="${param.val eq '7'?'menucurrent2':''}"><span>·</span>项目列表</a></p>
                </li>  
                <li>
                	<h1>费率设置</h1>
                	<p><a href="/managermall/systemmall/finance/rateList.do" class="${param.val eq '8'?'menucurrent2':''}"><span>·</span>费率套餐列表</a></p>
                </li>
                <li>
                	<h1>策略设置</h1>
                	<p><a href="/managermall/systemmall/channel/effectiveStore.do" class="${param.val eq '11'?'menucurrent2':''}"><span>·</span>有效店铺策略</a></p>
                    <p><a href="/managermall/systemmall/finance/royalty_incentive_strategy.do" class="${param.val eq '10'?'menucurrent2':''}"><span>·</span>提成奖励策略</a></p>
                    <p><a href="/managermall/systemmall/channel/cashOrder.do" class="${param.val eq '12'?'menucurrent2':''}"><span>·</span>套现订单策略</a></p>
                </li>      
           </ul>       		           
        </div>
        
    </div>
</body>
</html>