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
                	<h1>任务管理</h1>
                	<p><a href="/managermall/systemmall/task/listTask.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>任务管理</a></p>
                    <p><a href="/managermall/systemmall/task/addTask.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>添加任务</a></p>
                   
                </li> 
            	<li>
                	<h1>任务管理</h1>
                	 <p><a href="/managermall/systemmall/sysTask/list.do" class="${param.val eq '6'?'menucurrent2':''}"><span>·</span>任务列表</a></p>
                	 <p><a href="/managermall/systemmall/sysTask/goWenjuan.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>添加问卷任务</a></p>
                     <p><a href="/managermall/systemmall/sysTask/goGuanzhu.do" class="${param.val eq '4'?'menucurrent2':''}"><span>·</span>添加关注任务</a></p> 
                    <p><a href="/managermall/systemmall/sysTask/goPinlun.do" class="${param.val eq '5'?'menucurrent2':''}"><span>·</span>添加评价任务</a></p>
                   
                </li> 
                    
           </ul>       		           
        </div>
        
    </div>
</body>
</html>