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
                	<p><a href="/managermall/systemmall/set/addDistrict.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>商圈增加</a></p>
                    <p><a href="/managermall/systemmall/set/districtList.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>商圈列表</a></p>
                    <p><a href="/managermall/systemmall/set/addImg.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>网站图片管理</a></p>
                </li>      
           </ul>       		           
        </div>
        
    </div>
</body>
</html>