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
                	<h1>商品管理</h1>
                	<p><a href="/managermall/systemmall/product/list.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>商品管理</a></p>
                    <p><a href="/managermall/systemmall/product/audit/list.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>商品审核</a></p>
                    <p><a href="/managermall/systemmall/product/review/list.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>商品评论</a></p>
                    <p><a href="/managermall/systemmall/rebate/list.do" class="${param.val eq '4'?'menucurrent2':''}"><span>·</span>折扣审核</a></p>
                    <h1>活动管理</h1>
                    <p><a href="/managermall/systemmall/activity/list.do" class="${param.val eq '5'?'menucurrent2':''}"><span>·</span>活动列表</a></p>
                    <p><a href="/managermall/systemmall/activity/dish_list.do" class="${param.val eq '6'?'menucurrent2':''}"><span>·</span>菜品列表</a></p>
                    <p><a href="/managermall/systemmall/activity/virify_list.do" class="${param.val eq '7'?'menucurrent2':''}"><span>·</span>记录消费卷列表</a></p>
                </li>   
           </ul>       		           
        </div>
        
    </div>
</body>
</html>