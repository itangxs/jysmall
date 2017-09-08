<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/register.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>

<script type="text/javascript" src="/js/geo.js"></script>


<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/jysmall_step3.js"></script>

<style type="text/css">
.zcform{width:620px;margin:30px 0 0 210px;}
.clearfix img{float:left;margin-right:14px;}
.clearfix .combo{float:left;margin-right:3px;}
.clearfix textarea{float:left;}
.one1 text {color: red;}
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
		<form id="signupForm" method="post"  class="zcform" >
        	<p class="clearfix">
        		<label class="one" for="telphone">营业执照信息</label> 
        	</p>
			<p class="clearfix">
				<label class="one" for="companyName">公司名称：</label>
				<input type="hidden" id="companyId" name="companyId" value="${companyInfo.id }">
				<input id="name" name="name" class="con-email" value="${companyInfo.name}">
				<label class="one1" style="width:180px; color: red;">
					<span id="nameTip"></span>
				</label>
			</p>
			<p class="clearfix">
				<label class="one" for="licenseNumber">营业执照号：</label>
				<input id="licenseNumber" name="licenseNumber" class="con-email"  value="${companyInfo.licenseNumber }">
				<label class="one1" style="width:180px; color: red;">
					<span id="licenseNumberTip"></span>
				</label>
			</p>
			<p class="clearfix">
				<label class="one" for="corpnName">法人姓名：</label>
				<input id="corpnName" name="corpnName" class="con-email" value="${companyInfo.corpnName }">
				<label class="one1" style="width:180px; color: red;">
					<span id="corpnNameTip"></span>
				</label>
			</p>
			<p class="clearfix">
				<label class="one" for="corpnId">身份证号:：</label>
				<input id="corpnId" name="corpnId" class="con-email" value="${companyInfo.corpnId }">
				<label class="one1" style="width:180px; color: red;">
					<span id="corpnIdTip"></span>
				</label>
			</p>
			<p class="clearfix">
				<label class="one" for="corpnCard">法人身份证号电子版：</label>
				<img id="cardImg" width="240" height="120" alt="" src="${companyInfo.corpnCard }" />
				<input type="hidden" id="corpnCard" name="corpnCard"  value="${companyInfo.corpnCard }"/>
				<a name="corpnCardTip"></a>
			</p>
			<p class="clearfix">
				<input id="cardButn" class="submit3" type="file" value="上传" />
			</p>

			<p class="clearfix">
				<label class="one" for="con-email">营业执照所在地：</label>
				<select id="licenseProvince" name="licenseProvince" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="licenseCity" name="licenseCity" class="easyui-combobox" style="width:130px;height:32px"></select>
				<select id="licenseArea" name="licenseArea" class="easyui-combobox" style="width:130px;height:32px"></select>
				<label class="one1" style="width:180px; color: red;" >
					<span id="licenseAreaTip" style="display: none;">请输入所在地</span> 
				</label> 
			</p>
			<p class="clearfix">
				<label class="one" for="licenseAddress">营业执照详细地址：</label>
				<input id="licenseAddress" name="licenseAddress" class="con-email" value="${companyInfo.licenseAddress }">
					<label class="one1" style="width:180px; color: red;">
					<span id="licenseAddressTip"></span> 
				</label> 
			</p>
			<p class="clearfix">
				<label class="one" for="setUpDate">成立时间：</label> 
				<input id="setUpDate" name="setUpDate" class="easyui-datebox" style="height:30px;width:254px;"  value="${companyInfo.setUpDate }">
				<a name="setUpDateTip"></a>
			</p>
			<p class="clearfix">
				<label class="one" for="capital">注册资本：</label>
				<input id="capital" name="capital" class="con-email" value="${companyInfo.capital }">
				<span id="capitalTip"></span> 
			</p>
			<p class="clearfix">
				<label class="one" for="scope">经营范围：</label>
				<textarea id="scope" name="scope" cols="32" rows="3" maxlength="50">${companyInfo.scope }</textarea>
			</p>
			<p class="clearfix">
				<label class="one" for="licenseCard">企业营业执照电子版：</label>
				<img id="licenseImg" width="240" height="120" alt="" src="${companyInfo.licenseCard}" />
				<input type="hidden" id="licenseCard" name="licenseCard" value="${companyInfo.licenseCard}"/>
				<label class="one1" style="width:180px; color: red;">
					<span id="licenseCardTip"style="display: none;">请上传执照电子版</span> 
				</label> 
			</p>
			<p class="clearfix">
				<input id="licenseButn" class="submit3" type="file" value="上传" />
			</p>
			<h3>组织机构代码证</h3>
			<p class="clearfix">
				<label class="one" for="organizationCode">组织机构代码证：</label>
				<input id="organizationCode" name="organizationCode" class="input" value="${companyInfo.organizationCode }">
			</p>
			<p class="clearfix">
				<label class="one" for="organizationImage">组织机构代码电子版：</label>
				<img id="organiImg" width="240" height="120" alt="" src="${companyInfo.organizationImage }" />
				<input type="hidden" id="organizationImage" name="organizationImage"  value="${companyInfo.organizationImage }"/>
			</p>
			<p class="clearfix">
				<input id="organiButn" class="submit3" type="file" value="上传" />
			</p>
			<p class="clearfix">
				<input class="submit23" type="submit" value="下一步，完善公司信息" /><label>&nbsp;&nbsp;&nbsp;</label>
				<a href="saveSellerLinkman.do"><input class="submit22" type="button" value="返回上一步" /></a>
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
</html>