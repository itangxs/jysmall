<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<style type="text/css">
.memberrightttt{float:left; width:928px; /* height:100%; */ padding:20px 20px 50px 20px;}
</style>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script type="text/javascript">
/* function approve() {
	if (confirm("确认审核通过？")) {
		$("#status").val(2);
		$("#formSubmit").submit();
	} 
} */

$(function (){
	$("#approve").click(function(){
		if (confirm("确认审核通过？")) {
			$("#status").val(2);
			var url = "approveSave.do";
			$.ajax({
				async : false,
				type : "post",
				url : url,
				data: $("#formSubmit").serialize(),
				success : function(g) {
					if (g == "success") {
						alert("审批成功");
						window.location.href = "list.do";
					} else {
						alert("审核失败");
						window.location.href = "list.do";
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		} 
	})
})
</script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->

<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/store/uLeft.jsp"><jsp:param name="position"  value="1"/></jsp:include>
	<form action="approveSave.do" method="post" id="formSubmit">
	<div class="memberrightttt">
          <div class="change_myorder ">
            <h3>店铺审核</h3>
            <div class="store-info">
                <div class="info-detail">店铺注册信息</div>
                <div class="info-content">
                    <div class="info-left">
                        <p>
                            <label>店铺ID:</label>
                            <span>${storeInfo.id}</span>
                            <input type="hidden" name="storeId" value="${storeInfo.id}" />
                            <input type="hidden" name="status" id="status" />
                        </p>
                        <p>
                            <label>负责人:</label>
                            <span>${storeInfo.contactName}</span>
                            <input type="hidden" name="contactName" value="${storeInfo.contactName}" />
                        </p>
                        <p>
                            <label>联系人类型:</label>
                            <span>
                            	<c:if test="${storeInfo.contactType eq 'LEGAL_PERSON'}">法人</c:if>
                            	<c:if test="${storeInfo.contactType eq 'CONTROLLER'}">实际控制人</c:if>
                            	<c:if test="${storeInfo.contactType eq 'AGENT'}">代理人</c:if>
                            	<c:if test="${storeInfo.contactType eq 'OTHER'}">其他</c:if>
                            </span>
                            <input type="hidden" name="contactType" value="${storeInfo.contactType}" />
                        </p>
                        <p>
                            <label>公司名称:</label>
                            <span>${companyInfo.name}</span>
                            <input type="hidden" name="companyName" value="${companyInfo.name}" />
                        </p>
                        <p>
                            <label>区域:</label>
                            <span>${provinceName} ${cityName} ${areaName}</span>
                         </p>
                        <p>
                            <label>详细街道:</label>
                            <span>${storeInfo.address}</span>
                            <input type="hidden" name="address" value="${storeInfo.address}" />
                        </p>
                    </div>
                    <div class="info-right">
                        <p>
                            <label>店铺名称:</label>
                            <span>${storeInfo.name}</span>
                            <input type="hidden" name="storeName" value="${storeInfo.name}" />
                        </p>
                        <p>
                            <label>联系电话:</label>
                            <span>${sellerInfo.phone}</span>
                            <%-- <input type="hidden" name="contactTel" value="${storeInfo.contactPhone}" /> --%>
                        </p>
                        <p>
                            <label>法人:</label>
                            <span>${companyInfo.corpnName}</span>
                            <input type="hidden" name="corpnName" value="${companyInfo.corpnName}" />
                        </p>
                        <p>
                            <label>营业执照编号:</label>
                            <span>${companyInfo.licenseNumber}</span>
                            <input type="hidden" name="licenseNumber" value="${companyInfo.licenseNumber}" />
                        </p>
                        <p>
                            <label>营业执照类型:</label>
                            <span>
                            	<c:if test="${companyInfo.businessLicenseType eq 'NATIONAL_LEGAL'}">营业执照</c:if>
                            	<c:if test="${companyInfo.businessLicenseType eq 'NATIONAL_LEGAL_MERGE'}">营业执照多证合一</c:if>
                            	<c:if test="${companyInfo.businessLicenseType eq 'INST_RGST_CTF'}">事业单位法人证书</c:if>
                            </span>
                            <input type="hidden" name="businessLicenseType" value="${companyInfo.businessLicenseType}" />
                        </p>
                    </div>
                </div>
                <div class="info-pic-left">
                    <div class="paper">
                        <img src="${companyInfo.licenseCard}">
                        <div class="pic-intro">营业执照</div>
                        <input type="hidden" name="licenseCard" value="${companyInfo.licenseCard}" />
                    </div>
                </div>
                <div class="info-pic-right">
                    <div class="paper">
                        <img src="${storeInfo.logo}">
                        <div class="pic-intro">店铺照片</div>
                        <input type="hidden" name="licenseCard" value="${storeInfo.logo}" />
                    </div>
                    <div class="paper">
                        <img src="${companyInfo.corpnCard}">
                        <div class="pic-intro">法人身份证正面</div>
                        <input type="hidden" name="corpnCard" value="${companyInfo.corpnCard}" />
                    </div>
                </div>
            </div>
            <div class="bank-info">
            <div class="info-detail">银行卡信息</div>
            <div class="info-content" style="height: 200px;">
                <div class="info-left">
                    <p>
                        <label>开户银行:</label>
                        <span>${bankInfo.name}</span>
                        <input type="hidden" name="bankName" value="${bankInfo.name}" />
                    </p>
                    <p>
                        <label>银行卡号:</label>
                        <span>${bankInfo.carkNum}</span>
                        <input type="hidden" name="carkNum" value="${bankInfo.carkNum}" />
                    </p>
                    <p>
                        <label>身份证号码:</label>
                        <span>${companyInfo.corpnId}</span>
                        <input type="hidden" name="corpnId" value="${companyInfo.corpnId}" />
                    </p>
                </div>
                <div class="info-right">
                    <p>
                        <label>开户人:</label>
                        <span>${bankInfo.cardholder}</span>
                        <input type="hidden" name="cardholder" value="${bankInfo.cardholder}" />
                    </p>
                    <p>
                        <label>开户支行:</label>
                        <span>${bankInfo.branch}</span>
                        <input type="hidden" name="branch" value="${bankInfo.branch}" />
                    </p>
                    <p>
                        <label>预留手机号码:</label>
                        <span>${bankInfo.phone}</span>
                        <input type="hidden" name="phone" value="${bankInfo.phone}" />
                    </p>
                </div>
            </div>
            <div class="info-pic-right">
            </div>
    </div>
       
            <div class="change-success">
                <a href="#" onclick="javascript:window.location.href='editStore.do?storeId=${storeInfo.id}'">修改</a>
                <a href="#" id="approve">审核通过</a>
            </div>
          </div>
       </div>
      </form>
	<div class="clear"></div>
</div>

<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
