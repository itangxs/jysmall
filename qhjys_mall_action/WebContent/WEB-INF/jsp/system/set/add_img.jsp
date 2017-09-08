<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/set/addimg.js"></script>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true" ><jsp:param name="val" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">

          <h3 style="padding-left:10px; margin-bottom:25px;">网站设置——图片管理</h3>

         <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/set/saveImg.do">
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页banner图片地址：&nbsp;&nbsp;</label>
        	<img id="pcBannerImage" width="150" height="120" src="${img.pcBanner}" alt="" />
        	<input type="hidden" name="pcBanner" id="pcBanner" value="${img.pcBanner}"/>
             <input  type="file" value="上传" id="pcBannerButn" class="submit8">
				
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单美食图片地址：</label>
        	<img id="pcMenuMeisImage" width="150" height="120" src="${img.pcMenuMeis}" alt="" />
        	<input type="hidden" name="pcMenuMeis" id="pcMenuMeis" value="${img.pcMenuMeis}"/>
             <input  type="file" value="上传" id="pcMenuMeisButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单电影图片地址：</label>
        	<img id="pcMenuDianyImage" width="150" height="120" src="${img.pcMenuDiany}" alt="" />
        	<input type="hidden" name="pcMenuDiany" id="pcMenuDiany" value="${img.pcMenuDiany}"/>
             <input  type="file" value="上传" id="pcMenuDianyButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单购物图片地址：</label>
        	<img id="pcMenuGouwImage" width="150" height="120" src="${img.pcMenuGouw}" alt="" />
        	<input type="hidden" name="pcMenuGouw" id="pcMenuGouw" value="${img.pcMenuGouw}"/>
             <input  type="file" value="上传" id="pcMenuGouwButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单旅游图片地址：</label>
        	<img id="pcMenuLvyouImage" width="150" height="120" src="${img.pcMenuLvyou}" alt="" />
        	<input type="hidden" name="pcMenuLvyou" id="pcMenuLvyou" value="${img.pcMenuLvyou}"/>
             <input  type="file" value="上传" id="pcMenuLvyouButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单休闲图片地址：</label>
        	<img id="pcMenuXiuxImage" width="150" height="120" src="${img.pcMenuXiux}" alt="" />
        	<input type="hidden" name="pcMenuXiux" id="pcMenuXiux" value="${img.pcMenuXiux}"/>
             <input  type="file" value="上传" id="pcMenuXiuxButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单丽人图片地址：</label>
        	<img id="pcMenuLirenImage" width="150" height="120" src="${img.pcMenuLiren}" alt="" />
        	<input type="hidden" name="pcMenuLiren" id="pcMenuLiren" value="${img.pcMenuLiren}"/>
             <input  type="file" value="上传" id="pcMenuLirenButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单生活图片地址：</label>
        	<img id="pcMenuShenghImage" width="150" height="120" src="${img.pcMenuShengh}" alt="" />
        	<input type="hidden" name="pcMenuShengh" id="pcMenuShengh" value="${img.pcMenuShengh}"/>
             <input  type="file" value="上传" id="pcMenuShenghButn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >商城首页菜单理财图片地址：</label>
        	<img id="pcMenuLicaiImage" width="150" height="120" src="${img.pcMenuLicai}" alt="" />
        	<input type="hidden" name="pcMenuLicai" id="pcMenuLicai" value="${img.pcMenuLicai}"/>
             <input  type="file" value="上传" id="pcMenuLicaiButn" class="submit8">
         </p>
         <h3 style="padding-left:10px; margin-bottom:25px;">APP端图片管理</h3>
        
           <p class="clearfix">
        	<label class="one" for="con-email4" >APP端首页banner图片地址一：</label>
        	<img id="appBanner1Image" width="150" height="120" src="${img.appBanner1}" alt="" />
        	<input type="hidden" name="appBanner1" id="appBanner1" value="${img.appBanner1}"/>
             <input  type="file" value="上传" id="appBanner1Butn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >APP端首页banner图片地址二：</label>
        	<img id="appBanner2Image" width="150" height="120" src="${img.appBanner2}" alt="" />
        	<input type="hidden" name="appBanner2" id="appBanner2" value="${img.appBanner2}"/>
             <input  type="file" value="上传" id="appBanner2Butn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >APP端首页banner图片地址三：</label>
        	<img id="appBanner3Image" width="150" height="120" src="${img.appBanner3}" alt="" />
        	<input type="hidden" name="appBanner3" id="appBanner3" value="${img.appBanner3}"/>
             <input type="file" value="上传" id="appBanner3Butn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >APP端首页banner图片地址四：</label>
        	<img id="appBanner4Image" width="150" height="120" src="${img.appBanner4}" alt="" />
        	<input type="hidden" name="appBanner4" id="appBanner4" value="${img.appBanner4}"/>
             <input type="file" value="上传" id="appBanner4Butn" class="submit8">
         </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >APP端首页banner图片地址五：</label>
        	<img id="appBanner5Image" width="150" height="120" src="${img.appBanner5}" alt="" />
        	<input type="hidden" name="appBanner5" id="appBanner5" value="${img.appBanner5}"/>
             <input type="file" value="上传" id="appBanner5Butn" class="submit8">
         </p>
           <p class="clearfix"> 
             <input type="submit" value="保存" class="submit8" style="margin-left:300px;">
           </p>
        </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
