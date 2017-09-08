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
                	<h1>CMS管理</h1>
                	<p><a href="/managermall/systemmall/cms/addCmsCategory.do" class="${param.val eq '1'?'menucurrent2':''}"><span>·</span>CMS类别增加</a></p>
                    <p><a href="/managermall/systemmall/cms/cmsListCategory.do" class="${param.val eq '2'?'menucurrent2':''}"><span>·</span>CMS类别列表</a></p>
                    <p><a href="/managermall/systemmall/cms/addCms.do" class="${param.val eq '3'?'menucurrent2':''}"><span>·</span>CMS增加</a></p>
                    <p><a href="/managermall/systemmall/cms/cmsList.do" class="${param.val eq '4'?'menucurrent2':''}"><span>·</span>CMS列表</a></p>
                </li> 
                <li>
                	<h1>网站管理</h1>
                	<p><a href="/managermall/systemmall/set/addDistrict.do" class="${param.val eq '5'?'menucurrent2':''}"><span>·</span>商圈增加</a></p>
                    <p><a href="/managermall/systemmall/set/districtList.do" class="${param.val eq '6'?'menucurrent2':''}"><span>·</span>商圈列表</a></p>
                    <p><a href="/managermall/systemmall/set/addImg.do" class="${param.val eq '7'?'menucurrent2':''}"><span>·</span>网站图片管理</a></p>
                    <p><a href="/managermall/systemmall/set/addAppImg.do" class="${param.val eq '8'?'menucurrent2':''}"><span>·</span>APP九宫格</a></p>
                    <p><a href="/managermall/systemmall/set/categoryImgList.do" class="${param.val eq '9'?'menucurrent2':''}"><span>·</span>WEB类别图片</a></p>
                </li>        
                <li>
                	<h1>APP管理</h1>
                	<p><a href="/managermall/systemmall/app/appVersionPage.do" class="${param.val eq '11'?'menucurrent2':''}"><span>·</span>APP版本列表</a></p>
                    <p><a href="/managermall/systemmall/app/addAppVersion.do" class="${param.val eq '10'?'menucurrent2':''}"><span>·</span>APP新版本上传</a></p>
                </li>        
           </ul>       		           
        </div>
        
    </div>
</body>
</html>