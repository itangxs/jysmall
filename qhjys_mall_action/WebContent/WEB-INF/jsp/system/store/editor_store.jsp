<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>店铺审核</title>
<style type="text/css">
.memberrightttt{float:left; width:928px; /* height:100%; */ padding:20px 20px 50px 20px;}
</style>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />

<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>  
<script type="text/javascript" src="/js/store_auth.js"></script>  
<script>
$.validator.setDefaults({
     submitHandler: function() {
         alert("提交事件!");
     }
});
$().ready(function() {
    $("input").validate();
});
$(function(){
	$("#submitFrom").click(function(){
		if(checkPhone()&&checkTel()&&checkCorpnId()&&checkCarkNumber()){//这个xx就是你要进行的判断，返回true才去请求
			$.ajax({
				async : false,
				type : "POST",
				data : {
					"storeId":$("#storeId").val(),"storeName" : $("#storeName").val()
				},
				url : "/managermall/systemmall/store/checkStoreName.do",
				success : function(g) {
					if (g=="already") {
						$("#errorMsg").html("*店铺名称不能重复!");
						return false;
					} else {
						btnSubmit()
					}
				}
			})
		}
	})
})

function btnSubmit() {
	var url = "editStoreSave.do";
	$.ajax({
		async : false,
		type : "post",
		url : url,
		data: $("#signupForm").serialize(),
		success : function(g) {
			if (g == "success") {
				alert("修改信息成功");
				window.location.href = "list.do";
			} else {
				alert("修改信息失败");
				window.location.href = "list.do";
			}
		},
		error : function() {
			alert("服务器忙");
		}
	})
}

function checkTel() {
	$("#contactTel").next().remove();
	var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">*您输入的手机号码有误!</p>');
	var d = $("#contactTel").val().trim();
	//var a = /^1[3|4|5|8][0-9]\d{4,8}$/ ; 
	var a = /^1[34578][0-9]{9}$/;
	if (d == "") {
		$("#contactTel").after(b)
		return false;
	} else {
		if (!a.test($("#contactTel").val())) {
			var c = "*您输入的手机号码有误!";
			var b = $('<p class="one1" style="width:300px; color: red;  text-align:center;">'
					+ c + "</p>");
			$("#contactTel").after(b)
			return false;
		}
	}
	return true;
}
function checkPhone() {
	$("#phone").next().remove();
	var b = $('<p class="one1" style="width:300px; color: red;text-align:center; ">*手机号码输入有误!!</p>');
	var d = $("#phone").val().trim();
	var a = /^1[34578][0-9]{9}$/;
	if (d == "") {
		$("#phone").after(b)
		return false;
	} else {
		if (!a.test($("#phone").val())) {
			var c = "*手机号码输入有误!";
			var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">'
					+ c + "</p>");
			$("#phone").after(b)
			return false;
		}
	}
	return true;
}
			
function checkCorpnId() {
	$("#corpnId").next().remove();
	var b = $('<p class="one1" style="width:300px; color: red; text-align:center;">*请输入正确的身份证号码!</p>');
	var d = $("#contactTel").val().trim();
	var a = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
	if (d == "") {
		$("#corpnId").after(b)
		return false;
	} else {
		if (!a.test($("#corpnId").val())) {
			var c = "*请输入正确的身份证号码!";
			var b = $('<p class="one1" style="width:300px; color: red;  text-align:center;">'
					+ c + "</p>");
			$("#corpnId").after(b)
			return false;
		}
	}
	return true;
}
function checkCarkNumber() {
	$("#carkNumber").next().remove();
	var b = $('<p class="one1" style="width:300px; color: red; text-align:center;">*您输入的卡号位数不对！</p>');
	var d = $("#carkNumber").val().trim();
	var a = /^\d{16,21}$/;
	if (d == "") {
		$("#carkNumber").after(b)
		return false;
	} else {
		if (!a.test($("#carkNumber").val())) {
			var c = "*您输入的卡号位数不对！";
			var b = $('<p class="one1" style="width:300px; color: red;text-align:center;">'
					+ c + "</p>");
			$("#carkNumber").after(b)
			return false;
		}
	}
	return true;
}     
</script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->

<!-----------------------------------页面编辑----------------------------------------------->
<div class="membercontent">
	<!-------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="1"/></jsp:include>
	<div class="memberrightttt">
          <form class="change_myorder" id="signupForm" method="get" action="">
            <h3>店铺审核</h3>
            <div class="store-info" style="height:320px;">
                <div class="info-detail">店铺注册信息</div>
                <div class="info-content">
                    <div class="info-left" >
                        <p>
                             <label>店铺ID &nbsp;: </label>
                             <span style="margin-left: 40px;">${storeInfo.id}</span>
                             <input  type="hidden" id="storeId" name="storeId" value="${storeInfo.id}" />
                        </p>
                        <p>
                        	
                            <label><span class="warming-star">*</span>负责人&nbsp;:</label>
                            <input style="width:200px;" required  type="text" id="contactName" name="contactName" value="${storeInfo.contactName}"/>
                        </p>
                        <p>
                            <label><span class="warming-star">*</span>联系人类型&nbsp;:</label>
                            <select name="contactType">
                        		<option value="OTHER" <c:if test="${storeInfo.contactType == 'OTHER'}">selected</c:if>>其他</option>
                        		<option value="LEGAL_PERSON" <c:if test="${storeInfo.contactType == 'LEGAL_PERSON'}">selected</c:if>>法人</option>
                        		<option value="CONTROLLER" <c:if test="${storeInfo.contactType == 'CONTROLLER'}">selected</c:if>>实际控制人</option>
                        		<option value="AGENT" <c:if test="${storeInfo.contactType == 'AGENT'}">selected</c:if>>代理人</option>
                        	</select>
                        </p>
                        <p>
                            <label><span class="warming-star">*</span>公司名称&nbsp;:</label>
                            <input required id="companyName"  type="text" name="companyName" value="${companyInfo.name}"/>
                        </p>
                        <p class="clearfix"> 
                            <label  class="one" for="con-email" style="margin-right: 32px;"><span class="warming-star">*</span>区域&nbsp;:</label> 
                   			<select required id="province" name="province" class="address-bank easyui-combobox" style="display:block;"></select>
                   			<select required id="city" name="city" class="address-bank easyui-combobox" ></select>
                            <select required id="area" name="area" class="address-bank easyui-combobox"></select>
                         </p>
                        <p>
                            <label> <span class="warming-star">*</span>详细街道&nbsp;:</label>
                            <input id="address" required  name="address" type="text" value="${storeInfo.address}"/>
                        </p>
                    </div>
                    <div class="info-right">
                        <p>
                            <label>  <span class="warming-star">*</span>店铺名称&nbsp;:</label>
                            <input required id="storeName"  type="text"  name="storeName" value="${storeInfo.name}"/>
                       		<p class="one1" id="errorMsg" style="width:300px; color: red;text-align:center;"></p>
                        </p>
                        <p>
                            <label> <span class="warming-star">*</span>联系电话&nbsp;:</label>
                            <input required id="contactTel" name="contactTel" number type="text" value="${sellerInfo.phone}"/>
                        </p>
                        <p>
                            <label>法人&nbsp;:</label>
                            <input type="text" name="corpnName" value="${companyInfo.corpnName}"/>
                        </p>
                        <p>
                        	<label>营业执照编号&nbsp;:</label>
                            <input number  type="text" name="licenseNumber" value="${companyInfo.licenseNumber}"/>
                        </p>
                        <p>
                        	<label>营业执照类型&nbsp;:</label>
                        	<select name="businessLicenseType" style="width:174px;">
                        		<option value="NATIONAL_LEGAL_MERGE" <c:if test="${companyInfo.businessLicenseType == 'NATIONAL_LEGAL_MERGE'}">selected</c:if>>营业执照（多证合一）</option>
                        		<option value="NATIONAL_LEGAL" <c:if test="${companyInfo.businessLicenseType == 'NATIONAL_LEGAL'}">selected</c:if>>营业执照</option>
                        		<option value="INST_RGST_CTF" <c:if test="${companyInfo.businessLicenseType == 'INST_RGST_CTF'}">selected</c:if>>事业单位法人证书</option>
                        	</select>
                        </p>
                    </div>
                </div>
                <%-- <div class="info-pic-left">
                    <div class="paper">
                        <img src="${companyInfo.licenseCard}" id="storeImage1">
                         <div class="storeButn3-position">
                          上传图片
                        <input required type="file" value="上传" id="storeButn1" name="licenseCard"/>
                        </div>
                        <div class="pic-intro">营业执照</div>
						<input type="hidden" id="images1" name="images1" value="${companyInfo.licenseCard}"/>
                    </div>
                </div>
                <div class="info-pic-right">
                    <div class="paper">
                        <img src="${storeInfo.logo}" id="storeImage2">
                         <div class="storeButn3-position">
                         上传图片
                         <input required id="logo" type="file" value="上传" id="storeButn2" name="logo"/>
                          </div>
                        <div class="pic-intro">店铺照片</div>
                        <input type="hidden" id="images2" name="images2" value="${storeInfo.logo}"/>
                    </div>
                    <div class="paper">
                        <img src="${companyInfo.corpnCard}" id="storeImage3">
                        <div class="storeButn3-position">
                         上传图片
                        <input required type="file" value="上传" id="storeButn3" name="corpnCard"/>
                        </div>
                        <div class="pic-intro">法人身份证正面</div>
                        <input type="hidden" id="images3" name="images3" value="${companyInfo.corpnCard}"/>
                    </div>
               </div> --%>
            </div>
            <div class="bank-info">
	            <div class="info-detail">银行卡信息</div>
	            <div class="info-content" style="height: 250px;">
	                <div class="info-left " style="margin-bottom:0px;">
	                    <p>
	                        <label><span class="warming-star">*</span>开户银行:</label>
	                        <input id="bankName" type="text" required name="bankName" value="${bankInfo.name}"/>
	                    </p>
	                    <p>
	                        <label> <span class="warming-star">*</span>银行卡号:</label>
	                        <input required name="carkNum" id="carkNumber" type="text" value="${bankInfo.carkNum}"/>
	                    </p>
	                    <p>
	                        <label> <span class="warming-star">*</span>身份证号码:</label>
	                        <input required id="corpnId" name="corpnId" number  type="text" value="${companyInfo.corpnId}"/>
	                    </p>
	                </div>
	                <div class="info-right banks-info">
	                    <p>
	                        <label><span class="warming-star">*</span>开户支行:</label>
	                        <input required id="branch" type="text" name="branch" value="${bankInfo.branch}"/>
	                    </p>
	                    <p>
	                        <label><span class="warming-star">*</span>开户人:</label>
	                        <input id="cardholder" name="cardholder" required type="text" value="${bankInfo.cardholder}"/>
	                    </p>
	                    <p>
	                        <label><span class="warming-star">*</span>预留手机号码:</label>
	                        <input required number id="phone" name="phone" type="text" value="${bankInfo.phone}"/>
	                    </p>
	                </div>
	            </div>
	            <div class="info-pic-right">
	            <!-- <div class="paper">
		            <img src="">
		            <input type="file" value="上传"/>
		            <div class="pic-intro">营业执照</div>
	            </div> -->
            </div>
    		</div>
          
            <div class="change-success">
                <button id="submitFrom" style="margin: 25px 50px 0;">保存</button>
                <div class="warming"><span class="warming-star">*</span>标注内容为必填项！</div>
            </div>
          </form>
          </div>
	<div class="clear"></div>
</div>

<!------------------------------尾---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--åºé¨-e-->
</body>
</html>
