<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
	<link href="/css/public.css" rel="stylesheet" type="text/css" />
	<link href="/css/taskjindu.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/register.css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
	<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/js/account/selleruser.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="taskjindubox">	
<form id="selleruserform5" action="/account/task/selleruser5.do" method="post">
<input type="hidden" value="5" id="seltype">
	<div class="taskjindubox1">
    	<div class="taskjindutop"></div>        
        <div class="taskjindunr">
        	<!--进度条-->  
            <div class="jindu jd67"><i class="text40">67</i><em class="text25">%</em></div>
            <!--问卷-->
             <input type="hidden" name="userId" value="${user.id }">
             <input type="hidden" name="ispc" value="${ispc }">
            <div class="xuanbox">
            	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现居住城市：</p>                    	
              <label class="marginbottom30">
              <select id="licenseProvince" name="licenseProvince" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="licenseCity" name="licenseCity" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="licenseArea" name="licenseArea" class="easyui-combobox" style="width:130px;height:32px"></select>
              </label>                
          </div>
        </div>
   	  <div class="taskjindubottom"></div>
        <div><input class="nextanniu" type="button" value="下一题" onclick="submit5();"/></div>
    </div>    </form>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>