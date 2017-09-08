<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/register.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jysmall_step5.js"></script>
<script>
	window.UMEDITOR_HOME_URL = "/umeditor/"
</script>

<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.zcform{width:620px;margin:30px 0 0 212px;}
.clearfix img{margin-right:14px;}
.clearfix .combo{float:left;margin-right:3px;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="1" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
    	<form id="form1" name="form1">
            <h3 style="padding-left:10px;">商家中心——店铺认证</h3>
        </form>
        <div class="member_myorder2">
        <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td161">&nbsp;</th>
                    <th class="td162">&nbsp;</th>
                    <th class="td167">&nbsp;</th>
                    <th class="td168">&nbsp;</th>
                 </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                     <th class="td160">入驻须知</th>
                    <th class="td160">公司信息认证</th>
                    <th class="td160">店铺信息认证</th>
                    <th class="td160">等待审核</th>
                 </tr>
            </table>
        </div>
	<div class="member_myorder">   
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>    
          <th style="text-align:left;background:#fffef5;">
		<form id="signupForm" method="post" action="" class="zcform" >
			<p class="clearfix">
				<label class="one" for="telphone">经营信息</label>
				<input type="hidden" name="addStoreInfotoken" value="${addStoreInfotoken }">
			</p>
			<p class="clearfix">
				<label class="one" for="name">商户名称：</label>
				<input type="hidden"  id="storeId" name="storeId"  value="${storyInfo.id }">
				<input id="name" name="name" class="con-email" value="${storyInfo.name }">
			</p>
			<p class="clearfix">
				<label class="one" for="contactName">联系人：</label>
				<input id="contactName" name="contactName" class="con-email" value="${storyInfo.contactName }">
			</p>
			<p class="clearfix">
				<label class="one" for="contactTel">联系电话：</label>
				<input id="contactTel" name="contactTel" class="con-email" value="${storyInfo.contactTel }">
			</p>
			<p class="clearfix">
				<label class="one" for="contactPhone">手机号码：</label>
				<input id="contactPhone" name="contactPhone" class="con-email" value="${storyInfo.contactPhone }">
				<a name="corpnCardTip"></a>
			</p>
			<p class="clearfix">
				<label class="one" for="con-email">商户地址：</label>
				<select id="province" name="province" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="city" name="city" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="area" name="area" class="easyui-combobox" style="width:130px;height:32px"></select>
			</p>
			<p class="clearfix">
				<label class="one" for="contactPhone">商户详细地址：</label>
				<input id="address" name="address" class="con-email"  value="${storyInfo.address }">
			</p>
			<p class="clearfix">
				<label class="one" for="telphone" style="width:80px;hight:90px;">银行账号</label>
			</p>
			<p class="clearfix">
				<label class="one" for="bankName">所属银行：</label>
				<input id="bankId" name="bankId" type="hidden" value="${bankInfo.id }">
				<input id="bankName" name="bankName" class="con-email" value="${bankInfo.name }${bankInfo.id }">
			</p>
			<p class="clearfix">
				<label class="one" for="branch">开户银行支行名称：</label>
				<input id="branch" name="branch" class="con-email" value="${bankInfo.branch }">
			</p>
			<p class="clearfix">
				<label class="one" for="cardholder">真实姓名：</label>
				<input id="cardholder" name="cardholder"class="con-email" value="${bankInfo.cardholder }">
			</p>
			<p class="clearfix">
				<label class="one" for="carkNumber">银行账号：</label>
				<input id="carkNumber" name="carkNum" class="con-email" value="${bankInfo.carkNum }">
			</p>
			<p class="clearfix">
				<label class="one" for="phone">手机号码：</label>
				<input id="phone" name="phone" class="con-email" value="${bankInfo.phone }">
			</p>
			<p class="clearfix">
				<label class="one" for="telphone">店铺信息</label>
			</p>
			<p class="clearfix">
				<label class="one" for="image">店铺LOGO：</label>
				<img id="logoImage" width="180" height="120" src="${storyInfo.logo }" alt="" />
				<input type="hidden" id="logo" name="logo"  value="${storyInfo.logo }"/>
			</p>
			<p class="clearfix">
				<input id="logoButn" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">
				<label class="one" for="longitude">地图经度：</label>
				<input id="longitude" name="longitude" class="con-email" value="${storyInfo.longitude }">
			</p>
			<p class="clearfix">
				<label class="one" for="latitude">地图纬度：</label>
				<input id="latitude" name="latitude" class="con-email" value="${storyInfo.latitude }">
			</p>
			<p class="clearfix">
				<label class="one" for="imagess">商家图片：</label>
				<img id="storeImage1" width="150" height="120" src="" alt="" /> 
				<input type="hidden" id="images1" name="images" />
			</p>	
			<p class="clearfix">
				<input id="storeButn1" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">
			    <label class="one" for="imagess">&nbsp</label>
				<img id="storeImage2" width="150" height="120" src="" alt="" /> 
				<input type="hidden" id="images2" name="images" value="" />
			</p>	
			<p class="clearfix">
				<input id="storeButn2" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">
			    <label class="one" for="imagess">&nbsp</label>
				<img id="storeImage3" width="150" height="120" src="" alt="" /> 
				<input type="hidden" id="images3" name="images" value="" />
			</p>
			<p class="clearfix">	
				<input id="storeButn3" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">	
			   <label class="one" for="imagess">&nbsp;</label>
				<img id="storeImage4" width="150" height="120" src="" alt="" /> 
				<input type="hidden" id="images4" name="images" value="" />
			</p>
			<p class="clearfix">	
				<input id="storeButn4" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">
				<label class="one" for="con-email">商家介绍：</label>
			</p>
			<p style="margin-left:150px;">
				<script type="text/plain" id="storeDetail" name="storeDetail">
           		</script>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('storeDetail',{initialFrameHeight : 329,initialFrameWidth : 533,autoHeightEnabled : false});
					UM.getEditor('storeDetail').setContent("${storyInfo.storeDetail }");
				</script>
         	</p>
			</br>
			</br>
			<p class="clearfix">
				<label class="one" for="labels">标签：</label>
				<input id="labels" name="labels" class="con-email"  value="${storyInfo.labels }">
			</p>
			<p class="clearfix">
				<input class="submit23" type="submit" value="下一步，完善店铺信息" />
					<label>&nbsp;&nbsp;&nbsp;</label>
				<a href="addCompany.do"><input class="submit22" type="button" value="返回上一步" /></a>
			</p>
		</form>
		</th></tr>
          </table>
	</div>
	</div>
	<div class="clear"></div>
</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
<script type="text/javascript">
$(function(){
	var images = "${storyInfo.images}";
	var strs= new Array();
	 strs = images.split(",");
	 for (var i=0 ; i< strs.length ; i++)
	 {
		$("#storeImage"+(i+1)).attr("src",strs[i]);
		$("#images"+(i+1)).val(strs[i]);
	 }
})

</script>
</html>