<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript">
function selectAll() {
	var b = document.getElementsByName("ids");
	if ($("#selectAll").is(":checked") == true) {
		for (var a = 0; a < b.length; a++) {
			b[a].checked = true
		}
	} else {
		for (var a = 0; a < b.length; a++) {
			b[a].checked = false
		}
	}
}
function audits() {
	var a = document.getElementsByName("ids");
	var c = "";
	var state = "";
	for (var b = 0; b < a.length; b++) {
		if (a[b].checked == true) {
			c += a[b].value+",";
			state += $(a[b]).attr("data-state")+",";
		}
	}
	if (c.length > 0) {
		var state_array = state.split(",");
		for (var i = 0; i < state_array.length; i++) {
			if (state_array[i] != "" && state_array[i].length>0) {
				if (state_array[i] != 0) {		
					alert("您选中的记录中有已审核的记录！");
					return false;
				}
			}
			
		}
		id = c;
		if (confirm("是否确认审核通过?")) {
			 $.ajax({
				async : false,
				type : "POST",
				url : "/managermall/systemmall/msWithdraw/auditSellerCash.do",
				data : {"id" : id},
				success : function(d) {
					alert(d);
					window.location.reload();//刷新当前页面
				}
			}) 
		}
	} else {
		alert("请先选择要审核的商家")
	}
}

function withdraws() {
	var a = document.getElementsByName("ids");
	var c ="";
	var state ="";
	var moeny = parseFloat(0);
	for (var b = 0; b < a.length; b++) {
		if (a[b].checked == true) {
			c += a[b].value+",";
			state += $(a[b]).attr("data-state")+",";
			moeny += parseFloat($(a[b]).attr("data-moeny"));
		}
	}
	if (c.length > 0) {
		var state_array = state.split(",");
		for (var i = 0; i < state_array.length; i++) {
			if (state_array[i] != "" && state_array[i].length>0) {
				if (state_array[i] == 0) {		//选中的数据中有已提现或未审核的数据
					alert("您选中的记录中有未审核的记录！");
					return false;
				} else if (state_array[i] == 2) {
					alert("您选中的记录中有出账中的记录！");
					return false;
				} else if (state_array[i] == 4) {
					alert("您选中的记录中有已出账的记录！");
					return false;
				}
			}
		}
		id = c;
		if (confirm("您一共需要消费"+moeny.toFixed(2)+"金额,是否确认给该商家提现?")) {
			 $.ajax({
				async : true,
				type : "POST",
				url : "/managermall/systemmall/msWithdraw/update_withdraw.do",
				data : {"id" : id},
				dataType : "JSON",
				success : function(data) {
					var head = "提现申请失败";
					var html = "系统处理异常，请联系技术人员！";
					if (data.errorCount == 0) {
						head = "提现申请成功";
						html = "<p>您向民生发起了"+data.sumCount+"笔提现申请，系统已接收，请稍后查询打款状态！</p>";
					} else if (data.successCount == 0) {
						head = "提现申请失败";
						html = "<p>您向民生发起了"+data.sumCount+"笔提现申请，系统未能正常接收，异常原因如下：</p>";
						$.each(data.textInfo,function(i,item){
							html += "<p>"+Number(i+1)+"、店铺ID："+item.storeId+"提现，"+item.msError+".</p>";
			            });
					} else if (data.successCount !=0 && data.errorCount !=0) {
						head = "提现申请部分成功";
						html = "<p>您向民生发起了"+data.sumCount+"笔提现申请，系统成功接收"+data.successCount+"笔，"+data.errorCount+"笔未能正常接收，异常原因如下：</p>";
						$.each(data.textInfo,function(i,item){
							html += "<p>"+Number(i+1)+"、店铺ID："+item.storeId+"提现，"+item.msError+".</p>";
			            });
					}
					$("#cash-head").text(head);
					$("#cash-content").html(html);
					$("#pop-up-box").hide();
					$("#success-cash").show();
				},
				beforeSend : function() {
					$("#pop-up-box").show();
				}
			}) 
		}
	} else {
		alert("请先选择要提现的商家")
	}
}

function Excel() {
	if (confirm("是否确认导出报表?")) {
	    var b = $("#from").serialize();
	    var a = "/managermall/systemmall/msWithdraw/withdraw_export.do?" + b;
	    window.open(a);
	}
};

function AccountExcel() {
	var endDate = $("#endDate").val();
	var d=new Date(Date.parse(endDate.replace(/-/g,"/")));
	var date = new Date();
	
	if (endDate.length <=0 ){
		alert("请选择对账时间！");
		return false;
	} else if (d.getTime()>date.getTime()) {
		alert("您选中的对账时间已经大于当前时间！");
	} else {
		if (confirm("是否确认下载账单?")) {
		    var a = "/managermall/systemmall/msWithdraw/account_file_export.do?endDate=" + $("#endDate").val();
		    window.open(a);
		}
	}
};

</script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="13" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
      	<h3 style="padding-left:10px; margin-bottom:25px;">民生账户总览</h3>
           <form id="from" name="from"  class="zcform1" action="/managermall/systemmall/msWithdraw/withdraw_list.do">
           		<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
           		
           		<p class="clearfix w-bg">     	
	        		<label class="one" for="con-email2" style="font-size:16px; border-right:1px #ccc solid; width:302px;text-align:center;height:42px; display:block; line-height:42px; float:left; margin-top:14px;">商家账户总额： <span  style="font-size:16px; color:#F60">￥${sumMoney}</span></label>				                    
	        		<label class="one" for="con-email2" style="font-size:16px; border-right:1px #ccc solid; width:302px;text-align:center;height:42px; display:block; line-height:42px; float:left;margin-top:14px;">商家已入账金额： <span  style="font-size:16px; color:#F60">￥${allFeeMoney}</span></label>
                    <label class="one" for="con-email2" style="font-size:16px;width:302px;text-align:center;height:42px; display:block; line-height:42px; float:left;margin-top:14px;">商家未入账金额： <span  style="font-size:16px; color:#F60">￥${notFeeMoney}</span></label>
				</p>
				
                <h3 style="margin-bottom:25px;">提现报表</h3>
		        <p class="clearfix">
		        	<label class="one" for="con-email4" >创建时间：</label>
					<input id="startDate" name="startDate"  class="con-email3"  type="text" readonly  value="${msWithdraw.startDate}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'startDate'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/> 
		            <label class="one" for="con-email4" >-</label>
		            <input id="endDate" name="endDate"  class="con-email3"  type="text" readonly  value="${msWithdraw.endDate}"/>
        			<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'endDate'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"/>
                    <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	 
                    <label class="one" for="con-email4" >店铺ID：</label>
        			<input class="con-email4" name="storeId" style="width:100px;" value="${msWithdraw.storeId}"> 
        			<label class="one" for="con-email4" >提现订单号：</label>
        			<input class="con-email4" name="orderNo" value="${msWithdraw.orderNo}">
		        </p>
                
   		        <p class="clearfix">	
            		<label class="one" for="con-email4" >店铺名称：</label>
        			<input class="con-email4" name="storeName" value="${msWithdraw.storeName}">
                    <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	
		            <label class="one" for="con-email4" >审核状态：</label>
		        	<select name="state" style="width:118px;">
            	 		<option value="">全部</option>
            	 		<option value="0" <c:if test="${msWithdraw.state eq '0'}">selected="selected"</c:if>>未审核</option>
            	 		<option value="1" <c:if test="${msWithdraw.state eq '1'}">selected="selected"</c:if>>已审核</option>
                        <option value="2" <c:if test="${msWithdraw.state eq '2'}">selected="selected"</c:if>>提现中</option>
                        <option value="3" <c:if test="${msWithdraw.state eq '3'}">selected="selected"</c:if>>提现失败</option>
                        <option value="4" <c:if test="${msWithdraw.state eq '4'}">selected="selected"</c:if>>提现成功</option>
            		</select>
            		<label class="one" for="con-email4" >页面大小：</label>
        			<input class="con-email4" onkeyup="value=value.replace(/[^\d]/g,'')" name="pageSize" value="${pageSize}" style="width:100px;">
                    <label for="select">&nbsp;&nbsp;</label>
		            <input type="submit" value="查询" class="submit8">
                    <label for="select">&nbsp;</label> 
                  <input type="button" value="导出报表" class="submit9" onclick="Excel();">
		        </p>
        	</form>
        	<form id="form1" name="form1"  class="zcform1">
	           	<p class="clearfix">
	             	<input type="button" value="审核通过" class="submitblue" onclick="audits();">
                    <label for="select">&nbsp;</label>
	             	<input type="button" value="提现" class="submitred" onclick="withdraws();">
	             	<label for="select">&nbsp;</label>
	             	<input type="button" value="下载账单" class="submitred" onclick="AccountExcel();">
	           	</p>
         </form>  
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<th class="td80"><input id="selectAll" type="checkbox" value="" onclick="selectAll();" /></th>
                    <th class="td160">创建时间</th>
                    <th class="td80">店铺ID</th>
                    <th class="td120 th20">店铺<br>名称</th>        
                    <th class="td120 th20">交易<br>金额</th>
                    <th class="td120 th20">未扣费<br>金额</th>
                    <th class="td120 th20">已扣费<br>金额</th>
                    <th class="td120 th20">手续<br>费用</th>
                    <th class="td120 th20">提现<br>金额</th>
                    
                    <th class="td120">开户人</th>
                    <th class="td120 th20">开户<br>银行</th>
                    <th class="td160 th20">银行<br>卡号</th>
                    <th class="td80">状态</th>
                </tr>
                <c:forEach var="item" items="${page}">
                <tr>
                	<td class="td80"><input name="ids" type="checkbox" value="${item.id}" data-state="${item.state}" data-moeny="${item.withdrawMoeny}"/></td>
                    <td class="td160"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td class="td80">${item.storeId}</td>
                    <td class="td120">${item.storeName}</td>        
                    <td class="td120">${item.payMoney}</td>
                    <td class="td120">${item.notFeeMoney}</td>
                    <td class="td120">${item.allFeeMoney}</td>
                    <td class="td120">${item.rateFee}</td>
                    <td class="td120">${item.withdrawMoeny}</td>
                    <td class="td120">${item.cardholder}</td>
                    <td class="td120">${item.bankName}</td>
                    <td class="td160">${item.bankCard}</td>
                    <td class="td80">
                    	<c:if test="${item.state eq 0}">未审核</c:if>
                    	<c:if test="${item.state eq 1}">已审核</c:if>
                    	<c:if test="${item.state eq 2}">提现中</c:if>
                    	<c:if test="${item.state eq 3}">提现失败</c:if>
                    	<c:if test="${item.state eq 4}">提现成功</c:if>
                    </td>
                </tr>
				</c:forEach>
              </table>
        </div>
            <!--上一页下一页-->
          	<div class="page">
            		<c:if test="${page.getPageNum()>1}">
            		<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            		</c:if>
            		<c:choose>
            		<c:when test="${page.getPages()<7}">
            			<c:forEach var="i" begin="1" end="${page.getPages()}">
            				<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            				<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            			</c:forEach>
            		</c:when>
            		<c:when test="${page.getPages()>6}">
            			<c:forEach var="i" begin="1" end="3">
            				<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            				<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            			</c:forEach>
            			<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            			<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            				<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            			</c:forEach>
            			<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            			<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            				<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            				<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            			</c:forEach>
            		</c:when>
            		</c:choose>
            		<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               		<span>共${page.getPages()}页</span><span>到第</span>
               		<input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
    </div>            
	<div class="clear"></div>
	<!-- 提示框 -->
	<div class="mask" id="pop-up-box" style="display:none;">
		<div class="spinner" style="width:374px;margin:354px auto;">
		  <div class="double-bounceasd" ><h1>提现正在处理中，请稍候...</h1></div>
		</div>
	</div>
	<!-- 弹出框 -->
	<div class="success-cash" id="success-cash" style="display:none;">
 		<div class="success-box">
 		<div class="success-contents">
 			<h3 id="cash-head"></h3>
 			<div class="success-content" id="cash-content">
 			
 			</div>
 			</div>
 			<button onclick="document.getElementById('success-cash').style.display= 'none';window.location.reload();">关闭</button>
 		</div>
 	</div>
</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>