<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                	<h1>会员管理</h1>
                    <p><a href="/managermall/systemmall/malluser/list.do" class="${param.position eq '1'?'menucurrent2':''}" ><span>·</span>会员列表</a></p>
                    <%--< p><a href="/managermall/systemmall/malluser/userLvList.do" class="${param.position eq '2'?'menucurrent2':''}" ><span>·</span>会员等级</a></p> --%>
                    <p><a href="/managermall/systemmall/malluser/consumptionLogList.do" class="${param.position eq '3'?'menucurrent2':''}" ><span>·</span>消费积分操作日志</a></p>
                   <%--  <p><a href="/managermall/systemmall/malluser/gradeLogList.do" class="${param.position eq '4'?'menucurrent2':''}" ><span>·</span>等级积分操作日志</a></p> --%>
                  	<h1>会员活动管理</h1>
                    <p><a href="/managermall/systemmall/active/list.do" class="${param.position eq '5'?'menucurrent2':''}" ><span>·</span>会员注册活动管理</a></p>
                    <p><a href="/managermall/systemmall/active/goSave.do" class="${param.position eq '6'?'menucurrent2':''}" ><span>·</span>添加会员注册活动</a></p>
                </li>      
           </ul>       		           
        </div>
    </div>
</body>
</html>