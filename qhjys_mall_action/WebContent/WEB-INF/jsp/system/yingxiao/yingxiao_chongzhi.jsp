<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link type="text/css" rel="stylesheet"
	href="/common/formValidator4.0.1/style/validator.css" />
	<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidator-4.0.1.js"></script>
<script type="text/javascript"
	src="/common/formValidator4.0.1/js/formValidatorRegex.js"></script>
<script type="text/javascript" src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/system/recharge/recharge.js"></script>


<script type="text/javascript">

</script>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="position" value="21" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">营销账户充值</h3>
           <form id="from" method="post" action="/managermall/systemmall/ad/hongbao_add.do">
        
             <p class="clearfix"><label class="con-email120" for="con-email4" >商家ID</label>
        	<input class="con-email4" value="" id="storeId">
        </p>
             
              <p class="clearfix">
            <label class="con-email120" for="con-email4" >店铺名称</label>
        	<input class="con-email4" id="storename" readonly="readonly" value="此店铺不存在,请确认店铺ID">
        	 </p>
             <p class="clearfix">
            <label class="con-email120" for="con-email4" >充值金额</label>
        	<input class="con-email4" id="rechargeMoney" >
        	 </p>
             
              <p class="clearfix">
            <label class="con-email120" for="con-email4" >备注</label>
        	<textarea class="con-email4"style="width:400px; height:160px" id="remark"></textarea>
        	 </p>
  
   		<p class="clearfix">
        	 <label class="con-email120"></label>   
        	
             <a class="submitblue110" href = "javascript:querenrecharge()" >确认充值</a>
       	
     	</p>
     </form>
	</div>
    
  
    
    
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />

<!--底部-e-->
<!--查看提现记录弹出层2-->

<!--弹出幕布-->
 <div id="tanchu02" class="" style="position:absolute;left:50%; top:386px; margin-left:-275px; z-index:100;width:550px; height:380px;background-color:#fffef5; border-radius:10px; color:#666666; display:none;">                               
                		                         
                	<h1 style="font-size:16px; padding:10px 0; background-color:#64a1ce; border-radius:10px 10px 0 0; color:white; font-weight:normal; padding-left:40px;">
                        	提示
                             <a href = "javascript:void(0)" onclick = "document.getElementById('fade').style.display='none';document.getElementById('tanchu02').style.display='none';"><img src="/images/close.png" style=" float:right; margin:4px 20px 0 0;"></a>
                        </h1> 
                        <div style="border-bottom:1px #ccc solid; margin-top:90px; padding-bottom:90px;" class="clearfix">
                        	 <img src="/images/muzhi.png" style="float:left; margin-left:140px;">              
                			 <p style="font-size:16px; margin:10px 0 0 10px;float:left;">您已成功向该商户充值，<br>赶快去新建红包活动吧！</p>        		               			</div>
                        <div style=" float:right; margin:20px 24px 0 0;">
                        	<a href="/managermall/systemmall/ad/hongbao_create.do" style=" background-color:#64a1ce;color:white; border-radius:4px; font-size:16px;padding:10px 22px;">新建活动</a>
                            <a href="/managermall/systemmall/integral/goRecharge.do" style=" background-color:#ccc;color:white; border-radius:4px; font-size:16px;padding:10px 22px; margin-left:14px;">继续充值</a>                      
                        </div>
                </div>



    <div id="tanchu" class="" style="position:absolute;left:50%; top:386px; margin-left:-275px; z-index:100;width:550px; height:380px;background-color:#fffef5; border-radius:10px; color:#666666; font-size:16px; display:none;">    
                                     		                         
              <h1 style="font-size:16px; padding:10px 0; background-color:#64a1ce; border-radius:10px 10px 0 0; color:white; font-weight:normal; padding-left:40px;">
              提示
              <a href = "javascript:void(0)" onclick = "document.getElementById('fade').style.display='none';document.getElementById('tanchu').style.display='none';"><img src="/images/close.png" style=" float:right; margin:4px 20px 0 0;"></a>
              </h1>
              
               
              <div style="margin-top:70px; padding-bottom:26px;" class="clearfix">  
                  <div style="font-size:16px; text-align:right; float:left; width:49%;">         
                	 <p>商家ID：</p> 
                     <p>店铺名称：</p> 
                     <p>充值金额：</p> 
                     <p>备注：</p> 
                  </div> 
                  <div style="font-size:16px; margin-bottom:10px; text-align:left; float:right;width:49%;">
                     <p id="qrstoreId"></p>
                     <p id="qrstoreName"></p>
                     <p id="qrrechargeMoney"></p>
                      <p id="qrremark"></p> 
                   </div>                        
                   </div>
                   <div style="text-align:center;font-size:16px;border-bottom:1px #ccc solid; margin-bottom:14px;">
                   		<p>提示：请核对以上信息，确认充值后将无法撤销！</p>
                   </div>          
                   <div style=" float:right; margin:20px 24px 0 0;">
                        	<a href = "javascript:chongzhi()"  style=" background-color:#64a1ce;color:white; border-radius:4px; font-size:16px;padding:10px 22px;" >确认充值</a>
                            <a href="javascript:fanhuixiugai();" style=" background-color:#ccc;color:white; border-radius:4px; font-size:16px;padding:10px 22px; margin-left:14px;">返回修改</a>                      
                   </div>
               </div>
	<div id="fade" class="black_overlay" style="z-index:1;"></div>   
</body>
</html>
