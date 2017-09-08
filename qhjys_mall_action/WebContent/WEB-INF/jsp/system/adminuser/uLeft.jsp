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
                	<h1>系统管理</h1>
                	<p><a href="/managermall/systemmall/user/addUser.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>后台用户增加</a></p>
                    <p><a href="/managermall/systemmall/user/userList.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>后台用户列表</a></p>
                    <p><a href="/managermall/systemmall/userrole/addRole.do" class="${param.val eq '4'?'menucurrent2':''}"><span>·</span>后台权限增加</a></p>
                    <p><a href="/managermall/systemmall/userrole/roleList.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>后台权限列表</a></p>
                    <p><a href="/managermall/systemmall/userrole/addGroup.do" class="${param.val eq '5'?'menucurrent2':''}"><span>·</span>后台用户组增加</a></p>
                    <p><a href="/managermall/systemmall/userrole/groupList.do" class="${param.val eq '6'?'menucurrent2':''}"><span>·</span>后台用户组列表</a></p>
                    <p><a href="/managermall/systemmall/userrole/adminLogs.do" class="${param.val eq '7'?'menucurrent2':''}"><span>·</span>管理员操作日志</a></p>
                </li>      
           </ul>       		           
        </div>
        
    </div>
</body>
</html>